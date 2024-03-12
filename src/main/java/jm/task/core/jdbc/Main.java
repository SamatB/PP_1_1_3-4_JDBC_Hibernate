package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userDao = new UserServiceImpl();
        userDao.createUsersTable();
        userDao.saveUser("Samat", "Beganov", (byte) 26);
        userDao.saveUser("Alex", "Smirnov", (byte) 25);
        userDao.saveUser("Bayaman", "Baraev", (byte) 23);
        userDao.saveUser("Tima", "Kashey", (byte) 29);
        System.out.println(userDao.getAllUsers());
        userDao.removeUserById(1);
        System.out.println(userDao.getAllUsers());
        userDao.cleanUsersTable();
        userDao.dropUsersTable();
        userDao.dropUsersTable();
    }
}
