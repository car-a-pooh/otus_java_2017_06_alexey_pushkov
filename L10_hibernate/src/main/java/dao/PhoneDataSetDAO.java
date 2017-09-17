package dao;

import dataset.PhoneDataSet;
import dbservice.DBServiceHibernateImpl;

/**
 * Created by carapooh on 17.09.2017.
 */
public class PhoneDataSetDAO {

    DBServiceHibernateImpl dbServiceHibernate = new DBServiceHibernateImpl();

    public void save(PhoneDataSet phoneDataSet){
        dbServiceHibernate.save(phoneDataSet);
    }

    public void saveOrUpdate(PhoneDataSet phoneDataSet){
        dbServiceHibernate.saveOrUpdate(phoneDataSet);
    }

    public PhoneDataSet load(long id){
        return dbServiceHibernate.load(PhoneDataSet.class, id);
    }

}
