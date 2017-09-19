package dao;

import dataset.AddressDataSet;
import dataset.UserDataSet;
import dbservice.DBServiceHibernateImpl;
import hibernate.HibernateHelper;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by carapooh on 09.09.2017.
 */
public class UserDataSetDAO {

    DBServiceHibernateImpl dbServiceHibernate = new DBServiceHibernateImpl();

    public void save(UserDataSet user){
        dbServiceHibernate.save(user);
    }

    public void saveOrUpdate(UserDataSet user){
        dbServiceHibernate.saveOrUpdate(user);
    }

    public void delete(UserDataSet user){
        dbServiceHibernate.delete(user);
    }

    public UserDataSet load(long id){
        return dbServiceHibernate.load(UserDataSet.class, id);
    }
}
