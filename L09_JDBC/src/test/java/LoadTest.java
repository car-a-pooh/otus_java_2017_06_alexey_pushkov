import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

/**
 * Created by carapooh on 09.09.2017.
 *
 * 'Load' isn't connected to 'Load testing'
 * just the name of the tested method.
 */
public class LoadTest {

    @Test
    public void simpleLoadTest() throws SQLException, IllegalAccessException, InvocationTargetException, InstantiationException {

        Executor executor = new Executor("userdataset");
        DataSet dataSet = executor.load(1, UserDataSet.class);

        Assert.assertTrue(((UserDataSet) dataSet).getName().equals("pooh"));
        Assert.assertTrue(((UserDataSet) dataSet).getAge() == 43);

    }
}
