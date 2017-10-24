import org.junit.Assert;
import org.junit.Test;

/**
 * Created by carapooh on 17.09.2017.
 */
public class SortTest {

    @Test
    public void simpleTest(){

        double[] a = {.2, .3, .5, .4, .7, .6, .1, .9};

        SortNThreads sortNThreads = new SortNThreads();
        sortNThreads.sort(a, 4);

        Assert.assertTrue(a[2] == .3);
        Assert.assertTrue(a[0] == .1);
    }

}
