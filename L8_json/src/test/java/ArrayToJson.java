import org.junit.Assert;
import org.junit.Test;

import javax.json.JsonValue;
import java.io.IOException;

/**
 * Created by carapooh on 06.09.2017.
 */
public class ArrayToJson {

    class Element {
        public float first = 0.3f;
        public boolean second = false;
    }

    @Test
    public void simpleArrayTest() throws IllegalAccessException {

        int[] aValue = {1, 2, 3};

        JsonValue jsonValue = Jason.createJson(aValue);
        String result = jsonValue.toString();

        Assert.assertTrue(result.equals("[1,2,3]"));

    }

    @Test
    public void objectArrayTest() throws IllegalAccessException {

        Element[] aValue = {new Element(), new Element()};

        JsonValue jsonValue = Jason.createJson(aValue);
        String result = jsonValue.toString();

        Assert.assertTrue(result.equals("[{\"first\":0.3,\"second\":false},{\"first\":0.3,\"second\":false}]"));

    }
}
