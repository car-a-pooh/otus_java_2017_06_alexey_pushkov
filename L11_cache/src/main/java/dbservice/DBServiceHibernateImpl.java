package dbservice;

import dataset.DataSet;
import hibernate.HibernateHelper;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Created by carapooh on 10.09.2017.
 */
public class DBServiceHibernateImpl implements DBService {

    @Override
    public void save(DataSet dataSet) {
        try(Session session = HibernateHelper.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(dataSet);
            transaction.commit();
        }
    }

    @Override
    public void saveOrUpdate(DataSet dataSet) {
        try(Session session = HibernateHelper.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(dataSet);
            transaction.commit();
        }
    }

    @Override
    public void delete(DataSet dataSet){
        try(Session session = HibernateHelper.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(dataSet);
            transaction.commit();
        }
    }

    @Override
    public <T extends DataSet> T load(Class<T> klass, long id) {
        try(Session session = HibernateHelper.getSessionFactory().openSession()) {
            return session.get(klass, new Long(id));
        }
    }
}
