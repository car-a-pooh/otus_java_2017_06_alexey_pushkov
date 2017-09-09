import javax.persistence.Column;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by carapooh on 09.09.2017.
 */
public class Executor {

    private String table;

    public Executor(String table){
        this.table = table;
    }

    <T extends DataSet> void save(T user) throws SQLException, InvocationTargetException, IllegalAccessException {
        try (Connection connection = ConnectionHelper.getConnection();
             Statement statement = connection.createStatement()){

            StringBuilder builder = new StringBuilder("insert into " + table + " values (" + user.getId() + ", ");
            Class klass = user.getClass();
            klass.getFields();
            Field[] fields = klass.getDeclaredFields();
            Method[] methods = klass.getMethods();
            for (Field field : fields) {
                Column column = field.getAnnotation(Column.class);
                if (column != null) {
                    for (Method method : methods) {
                        if (method.getName().toLowerCase().equals("get" + field.getName())) {
                            String value = method.invoke(user).toString();
                            if (String.class.isAssignableFrom(field.getType())){
                                value = "'" + value + "'";
                            }
                            builder.append(value + ", ");
                            break;
                        }
                    }
                }
            }
            String str = builder.toString();
            String query = str.substring(0, str.length() - 2) + ")";
            statement.execute(query);
        }
    }

    <T extends DataSet> T load(long id, Class<T> klass) throws SQLException, IllegalAccessException, InstantiationException, InvocationTargetException {
        try (Connection connection = ConnectionHelper.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from " + table + " where id=" + id)){

            resultSet.next();
            T dataSet = klass.newInstance();
            dataSet.setId(id);
            Field[] fields = klass.getDeclaredFields();
            Method[] methods = klass.getMethods();
            for (Field field : fields){
                Column column = field.getAnnotation(Column.class);
                if (column != null){
                    for (Method method : methods){
                        if (method.getName().toLowerCase().equals("set" + field.getName())){
                            method.invoke(dataSet, resultSet.getObject(column.name()));
                            break;
                        }
                    }
                }
            }
            return dataSet;
        }
    }
}
