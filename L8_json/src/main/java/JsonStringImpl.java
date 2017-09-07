import javax.json.JsonString;
import javax.json.JsonValue;

/**
 * Created by carapooh on 06.09.2017.
 */
public class JsonStringImpl implements JsonString {

    private String value;

    public JsonStringImpl(String value){
        this.value = value;
    }

    @Override
    public String getString() {
        return value;
    }

    @Override
    public CharSequence getChars() {
        return value;
    }

    @Override
    public ValueType getValueType() {
        return ValueType.STRING;
    }
}
