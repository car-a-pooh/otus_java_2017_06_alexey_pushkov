import dao.PhoneDataSetDAO;
import dataset.PhoneDataSet;
import org.junit.Test;

/**
 * Created by carapooh on 18.09.2017.
 */
public class PhoneDAOTest {

    PhoneDataSetDAO phoneDataSetDAO = new PhoneDataSetDAO();

    @Test
    public void deleteTest(){
        PhoneDataSet phone = phoneDataSetDAO.load(23L);
        phoneDataSetDAO.delete(phone);
    }
}
