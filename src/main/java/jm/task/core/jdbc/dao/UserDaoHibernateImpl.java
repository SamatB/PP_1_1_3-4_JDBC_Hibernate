package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private final SessionFactory sessionFactory = Util.getSessionFactory();

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Session session = sessionFactory.openSession();
        String sql = "create table if not exists users (" +
                "id serial primary key," +
                "name varchar(50) not null," +
                "last_name varchar(50) not null," +
                "age int);";
        session.beginTransaction();
        session.createSQLQuery(sql);
        session.getTransaction().commit();
        System.out.println("Таблица пользователей создана");
        session.close();
    }

    @Override
    public void dropUsersTable() {
        String sql = "drop table if exists users";
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.createSQLQuery(sql);
            session.getTransaction().commit();
            System.out.println("Таблица пользователей успешно удалена");
        } catch (IllegalStateException e) {
            if (session.getTransaction().getStatus() == TransactionStatus.ACTIVE
                    || session.getTransaction().getStatus() == TransactionStatus.MARKED_ROLLBACK) {
                session.getTransaction().rollback();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
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
        List<User> users;
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        users = session.createQuery("from User", User.class).getResultList();
        session.getTransaction().commit();
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.createSQLQuery("truncate table users").executeUpdate();
        session.getTransaction().commit();
        System.out.println("Таблица пользователей была очищена");
        session.close();
    }
}
