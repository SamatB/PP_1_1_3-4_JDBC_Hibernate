package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

public class Main {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoJDBCImpl();
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
    }
}
