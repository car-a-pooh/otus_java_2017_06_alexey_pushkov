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
public class UserDataSetDAO {

    public void save(UserDataSet user) throws SQLException {
        try (Connection connection = ConnectionHelper.getConnection();
             Statement statement = connection.createStatement()){

            String query = "insert into userdataset values (" + user.getId() + ", '" + user.getName() + "', " + user.getAge() + ")";
            statement.execute(query);
        }
    }

    public UserDataSet load(long id) throws SQLException {
        try (Connection connection = ConnectionHelper.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from userdataset where id=" + id)){

            resultSet.next();
            return new UserDataSet(id, resultSet.getString("name"), resultSet.getInt("age"));
        }
    }
}
