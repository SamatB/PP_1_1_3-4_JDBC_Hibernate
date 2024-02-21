package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String sql = "create table if not exists users(" +
                "id serial primary key," +
                "name varchar(50) not null," +
                "last_name varchar(50) not null," +
                "age int2);";
        try (Connection connection = Util.connection(); Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("Таблица с именем \"users\" создана!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void dropUsersTable() {
        String sql = "drop table if exists users";
        try (Connection connection = Util.connection(); Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("Таблица удалена");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "insert into users(name, last_name, age) values (?, ?, ?);";
        try (Connection connection = Util.connection(); PreparedStatement prostate = connection.prepareStatement(sql)) {
            prostate.setString(1, name);
            prostate.setString(2, lastName);
            prostate.setByte(3, age);
            prostate.executeUpdate();
            System.out.println("Пользователь " + name + " успешно сохранен!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeUserById(long id) {
        String sql = "delete from users where id = ?;";
        try (Connection connection = Util.connection(); PreparedStatement prostate = connection.prepareStatement(sql)) {
            prostate.setLong(1, id);
            prostate.executeUpdate();
            System.out.println("Пользователь с ID: " + id + " успешно удален");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        String sql = "select * from users;";
        List<User> users = new ArrayList<>();
        User user;
        try (Connection conn = Util.connection(); Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        String sql = "truncate users";
        try (Connection connection = Util.connection(); Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("Таблиц была очищена");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
