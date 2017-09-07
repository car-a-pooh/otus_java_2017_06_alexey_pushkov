import javax.json.JsonValue;

/**
 * Created by carapooh on 06.09.2017.
 */
public class JsonValueImpl implements JsonValue {

    private ValueType value;

    public JsonValueImpl(ValueType value){
        this.value = value;
    }

    @Override
    public ValueType getValueType() {
        return value;
    }
}
