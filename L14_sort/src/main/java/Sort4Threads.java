/**
 * Created by carapooh on 17.09.2017.
 */
public class Sort4Threads {

    public void sort(double[] array){

        SortThread firstQuarter = new SortThread(0, array.length / 4 - 1, array);
        SortThread secondQuarter = new SortThread(array.length / 4, array.length / 2 - 1, array);
        SortThread thirdQuarter = new SortThread(array.length / 2, array.length / 2 + array.length / 4 - 1, array);
        SortThread fourthQuarter = new SortThread(array.length / 2 + array.length / 4, array.length - 1, array);

        firstQuarter.setName("firstQuarter");
        secondQuarter.setName("secondQuarter");
        thirdQuarter.setName("thirdQuarter");
        fourthQuarter.setName("fourthQuarter");

        firstQuarter.start();
        secondQuarter.start();
        thirdQuarter.start();
        fourthQuarter.start();

        try {
            firstQuarter.join();
            secondQuarter.join();
            thirdQuarter.join();
            fourthQuarter.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        double[] array2 = new double[array.length];
        mergeTwoPartsSorted(0, array.length / 4 - 1,
                array.length / 4, array.length / 2 - 1,
                0, array, array2);
        mergeTwoPartsSorted(array.length / 2, array.length / 2 + array.length / 4 - 1,
                array.length / 2 + array.length / 4, array.length - 1,
                array.length / 2, array, array2);
        mergeTwoPartsSorted(0, array.length / 2 - 1,
                array.length / 2, array.length - 1,
                0, array2, array);

    }

    public void mergeTwoPartsSorted(int left1, int right1,
                                    int left2, int right2,
                                    int start,
                                    double[] source, double[] dest){

        int left = left1;
        int right = left2;
        int target = start;

        while (left <= right1 && right <= right2){
            if (source[left] < source[right]){
                dest[target] = source[left];
                left++;
            }
            else {
                dest[target] = source[right];
                right++;
            }
            target++;
        }
        if (left > right1){
            for (int i = 0; i <= right2 - right; i++){
                dest[target + i] = source[right + i];
            }
        }
        else {
            for (int i = 0; i <= right1 - left; i++){
                dest[target + i] = source[left + i];
            }
        }
    }
}
