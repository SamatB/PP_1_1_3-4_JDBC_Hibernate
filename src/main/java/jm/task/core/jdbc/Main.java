package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;

public class Main {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoHibernateImpl();
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
