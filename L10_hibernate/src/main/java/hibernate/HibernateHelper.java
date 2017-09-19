package hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Created by carapooh on 16.09.2017.
 */
public class HibernateHelper {

    private static SessionFactory sessionFactory;

    static Configuration getConfiguration(){
        Configuration configuration = new Configuration();
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost/carapooh");
        configuration.setProperty("hibernate.connection.username", "carapooh");
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.hbm2ddl.auto", "validate");
        configuration.addAnnotatedClass(dataset.UserDataSet.class);
        configuration.addAnnotatedClass(dataset.AddressDataSet.class);
        configuration.addAnnotatedClass(dataset.PhoneDataSet.class);

        return configuration;
    }

    private static SessionFactory createSessionFactory(){
        Configuration configuration = getConfiguration();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();

        return configuration.buildSessionFactory(serviceRegistry);
    }

    public static SessionFactory getSessionFactory(){
        if (sessionFactory == null){
            sessionFactory = createSessionFactory();
        }

        return sessionFactory;
    }
}
