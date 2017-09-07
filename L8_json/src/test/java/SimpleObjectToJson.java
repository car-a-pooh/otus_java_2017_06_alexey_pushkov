import org.junit.Assert;
import org.junit.Test;

import javax.json.JsonObject;
import javax.json.JsonValue;
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

        JsonObject jsonObject = (JsonObject) Jason.createJson(new TwoFields());
        String result = jsonObject.toString();

        Assert.assertTrue(result.equals(String.valueOf("{\"first\":2,\"second\":3}")));

    }

    @Test
    public void nullFieldToJson() throws IllegalAccessException {

        JsonObject jsonObject = (JsonObject) Jason.createJson(new NullField());
        String result = jsonObject.toString();

        Assert.assertTrue(result.equals(String.valueOf("{\"first\":9,\"second\":null}")));

    }

}
