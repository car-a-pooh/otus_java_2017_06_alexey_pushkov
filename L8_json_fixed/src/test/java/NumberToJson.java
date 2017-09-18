import org.junit.Assert;
import org.junit.Test;

/**
 * Created by carapooh on 05.09.2017.
 */
public class NumberToJson {

    @Test
    public void intToJsonTest() throws IllegalAccessException {

        int n = 78964;

        String result = Jason.createJson(n);

        Assert.assertTrue(result.equals(String.valueOf(n)));

    }

    @Test
    public void doubleToJsonTest() throws IllegalAccessException {

        double pi = 3.14159265358;

        String result = Jason.createJson(pi);

        Assert.assertTrue(result.equals(String.valueOf(pi)));

    }

}
