/**
 * Created by carapooh on 17.09.2017.
 */
public class SortNThreads {

    public void sort(double[] array, int threadNumber){

        SortThread[] threads = new SortThread[threadNumber];
        if (threadNumber > array.length){
            threadNumber = array.length;
        }
        int len = array.length / threadNumber;
        for (int i = 0; i < threadNumber - 1; i++) {
            threads[i] = new SortThread(i * len, i * len + len - 1, array);
        }
        threads[threadNumber - 1] = new SortThread((threadNumber - 1) * len, array.length - 1, array);

        try {
            for (int i = 0; i < threadNumber; i++) {
                threads[i].setName("thread " + i);
                threads[i].start();
                threads[i].join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        mergeNPartsSorted(array, threadNumber);
    }

    public void mergeNPartsSorted(double[] source, int partNumber) {

        if (partNumber >= source.length) {
            return;
        } else {
            int[] left = new int[partNumber];
            int[] right = new int[partNumber];
            double[] sorted = new double[source.length];
            int len = source.length / partNumber;
            for (int i = 0; i < partNumber - 1; i++){
                left[i] = i * len;
                right[i] = i * len + len - 1;
            }
            left[partNumber - 1] = (partNumber - 1) * len;
            right[partNumber - 1] = source.length - 1;
            for (int i = 0; i < source.length; i++){
                int minIndex = 0;
                while(left[minIndex] > right[minIndex]){
                    minIndex++;
                }
                for (int j = minIndex + 1; j < partNumber; j++){
                    if (left[j] <= right[j] && source[left[j]] < source[left[minIndex]]){
                        minIndex = j;
                    }
                }
                sorted[i] = source[left[minIndex]];
                left[minIndex]++;
            }
            for (int i = 0; i < source.length; i++){
                source[i] = sorted[i];
            }
        }
    }
}
