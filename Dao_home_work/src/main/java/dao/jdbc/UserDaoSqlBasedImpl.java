package dao.jdbc;

import dao.UserDao;
import dao.AutoDao;
import models.Auto;
import models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoSqlBasedImpl implements UserDao {
    private static final String SQL_SELECT_ALL_USERS = "SELECT * FROM user_dao;";
    private static final String SQL_SELECT_USER_BY_ID = "SELECT * FROM user_dao WHERE id=?;";
    private static final String SQL_INSERT_USER = "INSERT INTO user_dao(name, age) VALUES(?,?);";
    private static final String SQL_DELETE_USER = "DELETE FROM user_dao WHERE id=?;";
    private static final String SQL_UPDATE_USER = "UPDATE user_dao SET name=?, age=? WHERE id=?;";
    
    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;
    
    public UserDaoSqlBasedImpl(Connection connection) {
        this.connection = connection;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<User> findAllUsers() {
        List<User> users = new ArrayList<User>();
        
        try {
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_USERS);
    
            while(resultSet.next()){
                User user = new User(resultSet.getString("name"), resultSet.getInt("age"));
                user.setId(resultSet.getInt("id"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        AutoDao autoDaoSqlBased = new AutoDaoSqlBasedImpl(connection);
        List<Auto> autos = autoDaoSqlBased.findAllAutos();
        for(User user : users) {
            for (Auto auto : autos) {
                if (auto.getUserId() == user.getId())
                    user.addAuto(auto);
            }
        }
    
        return users;
    }
    
    public User find(int id) {
        User user = null;
        
        try {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(SQL_SELECT_USER_BY_ID);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                user = new User(resultSet.getString("name"), resultSet.getInt("age"));
                user.setId(resultSet.getInt("id"));
            }
    
            AutoDao autoDaoSqlBased = new AutoDaoSqlBasedImpl(connection);
            List<Auto> autos = autoDaoSqlBased.findAllAutos();
            for(Auto auto : autos){
                if(auto.getUserId() == user.getId())
                    user.addAuto(auto);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return user;
    }
    
    public boolean save(User user) {
        try {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(SQL_INSERT_USER);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setInt(2, user.getAge());
            
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return true;
    }
    
    public boolean update(User user, User new_user) {
        try {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(SQL_UPDATE_USER);
            preparedStatement.setString(1, new_user.getName());
            preparedStatement.setInt(2, new_user.getAge());
            preparedStatement.setInt(3, user.getId());
        
            preparedStatement.executeUpdate();
            connection.commit();
            
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return true;
    }
    
    public boolean delete(int id) {
        try {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(SQL_DELETE_USER);
            preparedStatement.setInt(1, id);
    
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return true;
    }
}
