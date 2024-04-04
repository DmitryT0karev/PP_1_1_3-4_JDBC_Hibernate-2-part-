package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS users " +
            "(id BIGINT NOT NULL AUTO_INCREMENT, " +
            "name VARCHAR(45) NOT NULL, " +
            "lastname VARCHAR(45) NOT NULL, " +
            "age TINYINT NOT NULL, " +
            "PRIMARY KEY (id))";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS users";

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try (Session session = Util.getHibernateSession()) {
            session.beginTransaction();
            session.createSQLQuery(CREATE_TABLE).executeUpdate();
            session.getTransaction().commit();
            System.out.println("Table has been created");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getHibernateSession()) {
            session.beginTransaction();
            session.createSQLQuery(DROP_TABLE).executeUpdate();
            session.getTransaction().commit();
            System.out.println("Table has been deleted");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getHibernateSession()) {
            User user = new User(name, lastName, age);
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            System.out.println("User with name: " + name + " " + lastName + " has been added to the database");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getHibernateSession()) {
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
            System.out.println("User data with id " + id + " has been deleted");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> usersList = new ArrayList<>();
        try (Session session = Util.getHibernateSession()) {
            session.beginTransaction();
            usersList = session.createQuery("from User").getResultList();
            User user = new User();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usersList;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getHibernateSession()) {
            session.beginTransaction();
            session.createQuery("delete User").executeUpdate();
            session.getTransaction().commit();
            System.out.println("Table has been cleared");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}