import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.mysql.cj.jdbc.Driver;

/**
 * Created by carapooh on 09.09.2017.
 */
public class ConnectionHelper {

    public static Connection getConnection() throws SQLException {
        DriverManager.registerDriver(new Driver());
        String url = "jdbc:mysql://" +
                "localhost/" +
                "carapooh?" +
                "useSSL=false" +
                "&user=carapooh";

        return DriverManager.getConnection(url);
    }
}
