import org.junit.Assert;
import org.junit.Test;

/**
 * Created by carapooh on 10.09.2017.
 */
public class DBServiceTest {

    DBService dbService = new DBServiceImpl(new UserDataSetDAO());

    @Test
    public void loadTest(){

        long id = 1L;

        DataSet dataSet = dbService.load(UserDataSet.class, id);

        Assert.assertTrue(((UserDataSet) dataSet).getName().equals("pooh"));
    }

    @Test
    public void saveTest(){

        long id = 8L;

        dbService.save(new UserDataSet(id, "eighth", 23));

    }
}
