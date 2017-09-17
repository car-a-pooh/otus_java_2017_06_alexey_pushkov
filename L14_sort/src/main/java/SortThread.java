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

    public void shellSortSubArray(){
        int len = right - left + 1;
        int[] ciur = {1, 4, 10, 23, 57, 132, 301, 701, 1750};
        int i = 0;
        while (i < ciur.length && ciur[i] < len / 2){
            i++;
        }
        for (; i >= 0; i--){
            int j = left;
            while (j < len - ciur[i] + left){
                int k = j + ciur[i];
                while ((k >= ciur[i] + left) && array[k] < array[k - ciur[i]]){
                    double temp = array[k];
                    array[k] = array[k - ciur[i]];
                    array[k - ciur[i]] = temp;
                    k -= ciur[i];
                }
                j++;
            }
        }
    }
}
