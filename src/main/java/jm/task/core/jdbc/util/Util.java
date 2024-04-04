package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/pp";
    private static final String DB_USERNAME = "root123";
    private static final String DB_PASSWORD = "root123";
    private static final String DB_DIALECT = "org.hibernate.dialect.MySQLDialect";
    private static SessionFactory factory;

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            System.out.println("Соединение с БД установлено!");
        } catch (SQLException e) {
            System.out.println("Соединение с БД не установлено!");
        }
        return connection;
    }

    public static Session getHibernateSession() {
        if (factory == null) {
            try {
                Configuration configuration = new Configuration();
                Properties property = new Properties();
                property.put(Environment.DRIVER, DB_DRIVER);
                property.put(Environment.URL, DB_URL);
                property.put(Environment.USER, DB_USERNAME);
                property.put(Environment.PASS, DB_PASSWORD);
                property.put(Environment.DIALECT, DB_DIALECT);
                property.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                property.put(Environment.SHOW_SQL, "true");
                configuration.setProperties(property);
                configuration.addAnnotatedClass(User.class);
                factory = configuration.buildSessionFactory();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return factory.getCurrentSession();
    }
}
