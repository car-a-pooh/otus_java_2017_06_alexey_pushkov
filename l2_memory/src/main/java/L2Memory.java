/**
 * Created by carapooh on 11.06.2017.
 */
public class L2Memory {
    public static void main(String[] args) throws Exception {
        MemoryMeter memoryMeter = new MemoryMeter();
        Object sample = new String(new char[10]);
        System.out.println(sample.getClass().getName());
        System.out.println(memoryMeter.sizeOf(sample) + " bytes");
    }
}
