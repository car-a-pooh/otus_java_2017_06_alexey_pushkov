import dao.AddressDataSetDAO;
import dataset.AddressDataSet;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by carapooh on 16.09.2017.
 */
public class AddressDAOTest {

    AddressDataSetDAO addressDataSetDAO = new AddressDataSetDAO();

    @Test
    public void saveAddress(){
        AddressDataSet address = new AddressDataSet(23, "Montmartre");
        addressDataSetDAO.save(address);
    }

    @Test
    public void loadAddress(){
        AddressDataSet address = addressDataSetDAO.load(23L);
        Assert.assertTrue(address.getStreet().equals("Montmartre"));
    }
}
