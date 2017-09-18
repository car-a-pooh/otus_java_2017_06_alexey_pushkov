import org.glassfish.json.JsonProviderImpl;

import javax.json.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Collection;

/**
 * Created by carapooh on 31.07.2017.
 */
public class Jason {

    public static String createJson(Object object) throws IllegalAccessException {
        JsonBuilderFactory builderFactory = Json.createBuilderFactory(null);
        JsonObjectBuilder jsonObjectBuilder = builderFactory.createObjectBuilder();
        JsonArrayBuilder jsonArrayBuilder = builderFactory.createArrayBuilder();
        if (object == null){
            return "null";
        }
        else {
            Class klass = object.getClass();
            if (ReflectionHelper.isBoolean(klass)){
                if ((boolean) object){
                    return "true";
                }
                else {
                    return "false";
                }
            }
            else if (ReflectionHelper.isNumber(klass)) {
                return String.valueOf(object);
            }
            else if (object instanceof String || ReflectionHelper.isCharacter(klass)){
                return (String) object;
            }
            else if (object instanceof Collection) {
                for (Object obj : (Collection) object) {
                    jsonArrayBuilder.add(createJson(obj));
                }
                return String.valueOf(jsonArrayBuilder.build());
            }
            else if (klass.isArray()) {
                for (int i = 0; i != Array.getLength(object); i++) {
                    jsonArrayBuilder.add(createJson(Array.get(object, i)));
                }
                return String.valueOf(jsonArrayBuilder.build());
            }
            else {
                Field[] fields = klass.getFields();
                for (Field field : fields) {
                    jsonObjectBuilder.add(field.getName(), createJson(field.get(object)));
                }
                return String.valueOf(jsonObjectBuilder.build());
            }
        }
    }

    public static void writeObjectToJsonFile(Object obj, String filename) throws IOException, IllegalAccessException {
        try(FileWriter fw = new FileWriter(new File(filename), false)){
            fw.write(createJson(obj).toString());
            fw.flush();
        }
    }
}
