package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        String sql = "create table if not exists users(" +
                "id serial primary key," +
                "name varchar(50) not null," +
                "last_name varchar(50) not null," +
                "age int2);";
        SessionFactory sessionFactory = Util.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.createSQLQuery(sql);
        session.getTransaction().commit();
        System.out.println("Таблица пользователей создана");
        session.close();
    }

    @Override
    public void dropUsersTable() {
        String sql = "drop table if exists users";
        SessionFactory sessionFactory = Util.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.createSQLQuery(sql);
        session.getTransaction().commit();
        System.out.println("Таблица пользователей успешно удалена");
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        SessionFactory sessionFactory = Util.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        User user = new User();
        user.setName(name);
        user.setLastName(lastName);
        user.setAge(age);
        session.save(user);
        session.getTransaction().commit();
        System.out.println(user + " сохранен");
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        SessionFactory sessionFactory = Util.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        User user = session.get(User.class, id);
        session.delete(user);
        session.getTransaction().commit();
        System.out.println("Удален пользователь с ID " + id);
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        String sql = "select * from users";
        List users;
        SessionFactory sessionFactory = Util.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        users = session.createSQLQuery(sql).getResultList();
        session.getTransaction().commit();
        session.close();
        return users;
    }

    @Override
    public void cleanUsersTable() {
        String sql = "truncate users";
        SessionFactory sessionFactory = Util.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.createSQLQuery(sql);
        session.getTransaction().commit();
        System.out.println("Таблица пользователей была очищена");
        session.close();
    }
}
