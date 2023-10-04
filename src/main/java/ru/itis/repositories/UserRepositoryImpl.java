package ru.itis.repositories;

import ru.itis.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/java2";
    private static final String DB_USER = "java";
    private static final String DB_PASSWORD = "1234";

    private Connection connection = null;
    private PreparedStatement preparedStatement = null;

    public UserRepositoryImpl() {
        try {
            Class.forName("org.postgresql.Driver");

            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to connect to the database.");
        }
    }
    @Override
    public void saveAll(List<User> users) {
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO users (username, fullname, age) VALUES (?, ?, ?)");

            for (User user : users) {
                preparedStatement.setString(1, user.getUsername());
                preparedStatement.setString(2, user.getFullname());
                preparedStatement.setInt(3, user.getAge());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to save users to the database.");
        }
    }

    @Override
    public List<User> findAll() {
        List<User> userList = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM users");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String fullname = resultSet.getString("fullname");
                int age = resultSet.getInt("age");
                userList.add(new User(username, fullname, age));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to retrieve users from the database.");
        }
        return userList;
    }

    @Override
    public List<User> findByAge(int age) {
        List<User> userList = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE age = ?");
            preparedStatement.setInt(1, age);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String fullname = resultSet.getString("fullname");
                userList.add(new User(username, fullname, age));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to retrieve users by age from the database.");
        }
        return userList;
    }

    public void close() {
        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
