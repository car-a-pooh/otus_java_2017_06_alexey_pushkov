/**
 * Created by carapooh on 04.09.2017.
 */
public class ReflectionHelper {

    public static boolean isPrimitive(Class klass){
        if (klass.isPrimitive()){
            return true;
        }
        else {
            return  klass == Boolean.class ||
                    klass == Byte.class ||
                    klass == Short.class ||
                    klass == Character.class ||
                    klass == Integer.class ||
                    klass == Long.class ||
                    klass == Float.class ||
                    klass == Double.class;
        }
    }

    public static boolean isBoolean(Class klass){
        return  klass == boolean.class || klass == Boolean.class;
    }

    public static boolean isCharacter(Class klass){
        return klass == char.class || klass == Character.class;
    }

    public static boolean isNumber(Class klass){
        return  klass == byte.class ||
                klass == short.class ||
                klass == int.class ||
                klass == long.class ||
                klass == float.class ||
                klass == double.class ||
                klass == Byte.class ||
                klass == Short.class ||
                klass == Integer.class ||
                klass == Long.class ||
                klass == Float.class ||
                klass == Double.class;
    }
}
