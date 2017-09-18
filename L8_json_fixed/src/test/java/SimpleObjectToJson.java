import org.junit.Assert;
import org.junit.Test;
import java.util.Vector;

/**
 * Created by carapooh on 04.09.2017.
 */

public class SimpleObjectToJson {

    class TwoFields {
        public byte first = (byte) 2;
        public long second = 3L;
    }

    class NullField {
        public int first = 9;
        public Vector second = null;
    }

    @Test
    public void twoFieldToJson() throws IllegalAccessException {

        String result = Jason.createJson(new TwoFields());

        Assert.assertTrue(result.equals(String.valueOf("{\"first\":\"2\",\"second\":\"3\"}")));

    }

    @Test
    public void nullFieldToJson() throws IllegalAccessException {

        String result = Jason.createJson(new NullField());

        Assert.assertTrue(result.equals(String.valueOf("{\"first\":\"9\",\"second\":\"null\"}")));

    }

}
