package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService us = new UserServiceImpl();
        us.createUsersTable();
        us.saveUser("Jonathan", "Ive", (byte) 57);
        us.saveUser("Stephen", "Wozniak", (byte) 73);
        us.saveUser("Mark", "Zuckerberg", (byte) 39);
        us.saveUser("Bill", "Gates", (byte) 68);
        us.getAllUsers();
        us.removeUserById(4);
        us.cleanUsersTable();
        us.dropUsersTable();
    }
}

