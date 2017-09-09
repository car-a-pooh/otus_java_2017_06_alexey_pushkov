import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by carapooh on 09.09.2017.
 */
public class ConnectionTest {

    @Test
    public void simpleConnection() throws SQLException {
       try(Connection connection = ConnectionHelper.getConnection()){
           Assert.assertTrue(connection.getMetaData().getDatabaseProductName().equals("MySQL"));
           Assert.assertTrue(connection.getMetaData().getDriverName().equals("MySQL Connector Java"));
       }
    }
}
