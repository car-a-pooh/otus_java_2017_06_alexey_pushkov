import org.junit.Assert;
import org.junit.Test;

/**
 * Created by carapooh on 17.09.2017.
 */
public class MergeTest {

    @Test
    public void simpleTest(){

        double[] a = {.2, .3, .5, .4, .7, .6, .1, .9};
        double[] b = new double[4];

        Sort4Threads sort4Threads = new Sort4Threads();
        sort4Threads.mergeTwoPartsSorted(1, 2, 6, 7, 0, a, b);

        Assert.assertTrue(b[0] == .1);
        Assert.assertTrue(b[1] == .3);
        Assert.assertTrue(b[2] == .5);
        Assert.assertTrue(b[3] == .9);
    }
}
