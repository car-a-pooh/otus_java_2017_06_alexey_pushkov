import org.junit.Assert;
import org.junit.Test;

import javax.json.JsonValue;

/**
 * Created by carapooh on 05.09.2017.
 */
public class NumberToJson {

    @Test
    public void intToJsonTest() throws IllegalAccessException {

        int n = 78964;

        JsonValue jsonValue = Jason.createJson(n);
        String result = jsonValue.toString();

        Assert.assertTrue(result.equals(String.valueOf(n)));

    }

    @Test
    public void doubleToJsonTest() throws IllegalAccessException {

        double pi = 3.14159265358;

        JsonValue jsonValue = Jason.createJson(pi);
        String result = jsonValue.toString();

        Assert.assertTrue(result.equals(String.valueOf(pi)));

    }

}
