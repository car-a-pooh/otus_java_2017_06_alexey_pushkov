import dao.UserDataSetDAO;
import dataset.AddressDataSet;
import dataset.PhoneDataSet;
import dataset.UserDataSet;
import dbservice.DBServiceCacheImpl;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by carapooh on 16.09.2017.
 */
public class UserDAOTest {

    UserDataSetDAO userDataSetDAO = new UserDataSetDAO(new DBServiceCacheImpl());

    @Test
    public void saveTest(){
        List<PhoneDataSet> phones = new ArrayList<>();
        phones.add(new PhoneDataSet(23, "9999"));
        AddressDataSet address = new AddressDataSet(12L, "Green Square");
        userDataSetDAO.save(new UserDataSet(99L, "Valerian", 54, address, phones));
    }

    @Test
    public void updateTest(){
        List<PhoneDataSet> phones = new ArrayList<>();
        phones.add(new PhoneDataSet(23, "9988"));
        phones.add(new PhoneDataSet(24, "1122"));
        AddressDataSet address = new AddressDataSet(12L, "Red Square");
        UserDataSet user = new UserDataSet(99L, "Valerian", 55, address, phones);
        for (PhoneDataSet phone : user.getPhones()){
            phone.setUser(user);
        }
        userDataSetDAO.saveOrUpdate(user);
    }

    @Test
    public void loadTest(){
        UserDataSet user = userDataSetDAO.load(99L);
        Assert.assertTrue(user.getName().equals("Valerian"));
        Assert.assertTrue(user.getPhones().size() == 2);
    }

    //@Test
    public void deleteTest(){
        UserDataSet user = userDataSetDAO.load(99L);
        userDataSetDAO.delete(user);
    }
}
