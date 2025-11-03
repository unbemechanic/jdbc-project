package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Environment;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.io.InputStream;
import java.io.Reader;
import java.util.Properties;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                InputStream input = HibernateUtil.class
                        .getClassLoader()
                        .getResourceAsStream("config.properties");

                if (input == null) {
                    throw new RuntimeException("config.properties not found");
                }

                Properties props = new Properties();
                props.load(input);
                Configuration configuration = new Configuration();

                Properties settings = new Properties();
                settings.put(Environment.DRIVER, props.getProperty("db.driver"));
                settings.put(Environment.URL, props.getProperty("db.url"));
                settings.put(Environment.USER, props.getProperty("db.user"));
                settings.put(Environment.PASS, props.getProperty("db.password"));
                settings.put(Environment.DIALECT, props.getProperty("db.dialect"));
                settings.put(Environment.SHOW_SQL, props.getProperty("db.show_sql"));
                settings.put(Environment.HBM2DDL_AUTO, props.getProperty("db.hbm2ddl"));
// automatically creates tables

                configuration.setProperties(settings);
                configuration.addAnnotatedClass(User.class);

                StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties());

                sessionFactory = configuration.buildSessionFactory(registryBuilder.build());
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to build Hibernate session factory");
            }
        }
        return sessionFactory;
    }
}
