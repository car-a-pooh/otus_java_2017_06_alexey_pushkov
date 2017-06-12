import java.io.*;

/**
 * Created by carapooh on 11.06.2017.
 */
public class MemoryMeter {
    public long sizeOf (Object obj) throws IOException, InterruptedException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        int size = 9_000_000;
        try(ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(obj);
            oos.flush();
            oos.close();
            baos.close();
            byte[] byteData = baos.toByteArray();
            Object[] arr = new Object[size];
            System.gc();
            Thread.sleep(10);
            long memoryBefore = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
            for (int i = 0; i != size; i++) {
                ByteArrayInputStream bais = new ByteArrayInputStream(byteData);
                arr[i] = new ObjectInputStream(bais).readObject();
                bais.close();
            }
            System.gc();
            Thread.sleep(10);
            long memoryAfter = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
            return (memoryAfter - memoryBefore) / size;
        }
        catch (NotSerializableException nse){
            Object[] arr = new Object[size];
            System.gc();
            Thread.sleep(10);
            long memoryBefore = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
            for (int i = 0; i != size; i++) {
                arr[i] = obj.getClass().newInstance();
            }
            System.gc();
            Thread.sleep(10);
            long memoryAfter = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
            return (memoryAfter - memoryBefore) / size;
        }
    }
}
