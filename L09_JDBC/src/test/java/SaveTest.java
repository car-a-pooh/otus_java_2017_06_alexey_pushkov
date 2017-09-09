import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

/**
 * Created by carapooh on 10.09.2017.
 */

public class SaveTest {

    @Test
    public void simpleSaveTest() throws IllegalAccessException, SQLException, InvocationTargetException {

        Executor executor = new Executor("userdataset");
        executor.save(new UserDataSet(4,"fourth",48));

    }
}
