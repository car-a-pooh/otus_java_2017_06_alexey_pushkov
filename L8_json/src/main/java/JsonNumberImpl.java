import javax.json.JsonNumber;
import javax.json.JsonValue;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Created by carapooh on 05.09.2017.
 */
public class JsonNumberImpl implements JsonNumber {
    private String value;

    public JsonNumberImpl(String value){
        this.value = value;
    }

    @Override
    public boolean isIntegral() {
        return false;
    }

    @Override
    public int intValue() {
        return 0;
    }

    @Override
    public int intValueExact() {
        return 0;
    }

    @Override
    public long longValue() {
        return 0;
    }

    @Override
    public long longValueExact() {
        return 0;
    }

    @Override
    public BigInteger bigIntegerValue() {
        return null;
    }

    @Override
    public BigInteger bigIntegerValueExact() {
        return null;
    }

    @Override
    public double doubleValue() {
        return 0;
    }

    @Override
    public BigDecimal bigDecimalValue() {
        return null;
    }

    @Override
    public ValueType getValueType() {
        return ValueType.NUMBER;
    }

    @Override
    public String toString(){
        return value;
    }
}
