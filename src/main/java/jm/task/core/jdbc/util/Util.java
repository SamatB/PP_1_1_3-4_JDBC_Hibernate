package jm.task.core.jdbc.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    public static final String URL = "jdbc:mysql://localhost:3306/mydb";
    public static final String USERNAME = "user";
    public static final String PASSWORD = "user";

    public static Connection connection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    private static final SessionFactory sessionFactory = buildSessionFactory();
    private static SessionFactory buildSessionFactory() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.connection.driver.class", "com.mysql.jdbc.Driver");
        properties.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/mydb");
        properties.setProperty("hibernate.connection.username", "user");
        properties.setProperty("hibernate.connection.password", "user");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        properties.setProperty("hibernate.show_sql", "false");
        properties.setProperty("hibernate.format_sql", "true");
        properties.setProperty("hibernate.hbm2ddl.auto", "create");
        Configuration cfg = new Configuration();
        cfg.addAnnotatedClass(jm.task.core.jdbc.model.User.class);
        cfg.setProperties(properties);
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
        return cfg.buildSessionFactory(serviceRegistry);
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
