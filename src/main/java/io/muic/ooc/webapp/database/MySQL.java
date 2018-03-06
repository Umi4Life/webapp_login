package io.muic.ooc.webapp.database;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQL {
    private static final String dbUrl = "jdbc:mysql://localhost:3306/webapp_login?useSSL=false";
    private static final String dbUser = "muic";
    private static final String dbPassword = "ooc";
    private static final String dbTable = "credentials";
    private static Connection connection = null;
    private static PreparedStatement preparedStatement;

    public static Connection getConnection() {

        try {
            //Class.forName("com.mysql.cj.jdbc.Driver");
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            }
        }  catch (SQLException e) {
            e.printStackTrace();
        }
        if (connection == null) {
            System.out.println("Connection is NULL!");
        }
        return connection;
    }

    public static boolean addUser(String username, String password,
                                     String email) {

        for (User u: getUsers()){
            if(u.getUsername().equals(username)){
                return false;
            }
        }

        try {
            String sql = "insert into " + dbTable + "(username,email,password) values (?,?,?);";
            password = BCrypt.hashpw(password, BCrypt.gensalt());
            preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void editUser(String username, String password, String email)
    {
        try
        {
            String changePass = "update "+dbTable+" set password = ? where username = ?";
            preparedStatement  = getConnection().prepareStatement(changePass);;
            preparedStatement.setString   (1, password);
            preparedStatement.setString(2, username);
            preparedStatement.executeUpdate();

            String changeEmail = "update "+dbTable+" set email = ? where username = ?";
            preparedStatement  = getConnection().prepareStatement(changeEmail);;
            preparedStatement.setString   (1, email);
            preparedStatement.setString(2, username);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static boolean removeUser(String username) {
        try {
            String sql = "delete from " + dbTable + " where username = ?;";
            preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static User getUser(String username) {

        try {
            String sql = "SELECT * FROM " + dbTable + " WHERE username = ?;";
            preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.first()) {
                User user = new User();
                user.setUsername(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static List<User> getUsers() {
        List<User> users = new ArrayList<>();
        try {
            String query = "select * from " + dbTable;
            preparedStatement = getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User user = new User();
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

}
