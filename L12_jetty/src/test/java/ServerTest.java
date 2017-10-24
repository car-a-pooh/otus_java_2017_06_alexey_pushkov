import dao.UserDataSetDAO;
import dataset.UserDataSet;
import dbservice.DBServiceCacheImpl;
import jetty.CacheEngineServer;
import org.junit.Test;

/**
 * Created by carapooh on 16.10.2017.
 */
public class ServerTest {

    CacheEngineServer engineServer = new CacheEngineServer(1000, 100000L, 10000L);
    UserDataSetDAO userDataSetDAO = new UserDataSetDAO(new DBServiceCacheImpl(engineServer));


    public ServerTest() throws Exception {
    }

    @Test
    public void simpleTest() throws Exception {
        System.out.println("Check server started.");
        engineServer.startServer();
        engineServer.joinServer();
    }

    @Test
    public void missTest() throws Exception {
        UserDataSet user = userDataSetDAO.load(99L);
        engineServer.startServer();
        engineServer.joinServer();
    }

    @Test
    public void hitsTest() throws Exception {
        UserDataSet user;
        engineServer.startServer();
        for (int i = 0; i < 1000; i++){
            user = userDataSetDAO.load(99L);
        }
        engineServer.joinServer();
    }

}
