package dao;

import dataset.AddressDataSet;
import dbservice.DBServiceHibernateImpl;

/**
 * Created by carapooh on 16.09.2017.
 */
public class AddressDataSetDAO {

    DBServiceHibernateImpl dbServiceHibernate = new DBServiceHibernateImpl();

    public void save(AddressDataSet addressDataSet){
        dbServiceHibernate.save(addressDataSet);
    }

    public void saveOrUpdate(AddressDataSet addressDataSet){
        dbServiceHibernate.saveOrUpdate(addressDataSet);
    }

    public void delete(AddressDataSet addressDataSet){
        dbServiceHibernate.delete(addressDataSet);
    }

    public AddressDataSet load(long id){
        return dbServiceHibernate.load(AddressDataSet.class, id);
    }
}
