import java.util.ArrayList;
import java.util.List;

/**
 * Created by carapooh on 26.06.2017.
 */
public class L4_GC {
    public static void main(String[] args){
        List<Double[]> list = new ArrayList<>(100);
        int tail = 0;
        int arrayLength = 10;
        int add = 100, cutFromHead = 50, cutFromTail = 54;
        long startTimeNanos = System.nanoTime();
        long currentTimeNanos;
        long cutOldPeriodNanos = 100_000_000L;
        long cutOldPeriodsDone = 0L;
        while(true){
            for (int i = 0; i != add; i++) {
                Double[] arr = new Double[arrayLength];
                for (int j = 0; j != arrayLength; j++) {
                    arr[j] = new Double(tail + arrayLength * i + j);
                }
                list.add(arr);
            }
            tail += arrayLength * arrayLength * add;
            for (int i = 0; i != cutFromTail; i++) {
                list.remove(list.size() - 1);
            }
            currentTimeNanos = System.nanoTime();
            if (currentTimeNanos - startTimeNanos > (cutOldPeriodsDone + 1L) * cutOldPeriodNanos){
                cutOldPeriodsDone++;
                for (int i = 0; i != cutFromHead; i++){
                    list.remove(i);
                }
            }
        }
    }
}
