/**
 * Created by carapooh on 17.09.2017.
 */
public class SortThread extends Thread {

    private int left;
    private int right;
    private double[] array;

    public SortThread(int left, int right, double[] array){
        this.left = left;
        this.right = right;
        this.array = array;
    }

    @Override
    public void run(){
        shellSortSubArray();
    }

    /**
     * Shell sort with sequence
     * <a href="http://sun.aei.polsl.pl/~mciura/publikacje/shellsort.pdf">Ciura</a>
     * used.
     */
    public void shellSortSubArray(){
        int len = right - left + 1;
        int[] ciura = {1, 4, 10, 23, 57, 132, 301, 701, 1750};
        int i = 0;
        while (i < ciura.length && ciura[i] < len / 2){
            i++;
        }
        for (; i >= 0; i--){
            int j = left;
            while (j < len - ciura[i] + left){
                int k = j + ciura[i];
                while ((k >= ciura[i] + left) && array[k] < array[k - ciura[i]]){
                    double temp = array[k];
                    array[k] = array[k - ciura[i]];
                    array[k - ciura[i]] = temp;
                    k -= ciura[i];
                }
                j++;
            }
        }
    }
}
