package dao.jdbc;

import dao.AutoDao;
import models.Auto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AutoDaoSqlBasedImpl implements AutoDao {
    private static final String SQL_SELECT_ALL_AUTOS = "SELECT * FROM auto_dao;";
    private static final String SQL_SELECT_AUTO_BY_ID = "SELECT * FROM auto_dao WHERE id=?;";
    private static final String SQL_INSERT_AUTO= "INSERT INTO auto_dao(model, color, user_id) VALUES(?,?,?);";
    private static final String SQL_DELETE_AUTO = "DELETE FROM auto_dao WHERE id=?;";
    private static final String SQL_UPDATE_AUTO = "UPDATE auto_dao SET model=?, color=?, user_id=? WHERE id=?;";
    
    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;
    
    public AutoDaoSqlBasedImpl(Connection connection) {
        this.connection = connection;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Auto> findAllAutos() {
        List<Auto> autos = new ArrayList<Auto>();
        
        try {
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_AUTOS);
            
            while(resultSet.next()){
                Auto auto = new Auto(resultSet.getString("model"), resultSet.getString("color"), resultSet.getInt("user_id"));
                auto.setId(resultSet.getInt("id"));
                autos.add(auto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return autos;
    }
    
    public Auto find(int id) {
        Auto auto = null;
        
        try {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(SQL_SELECT_AUTO_BY_ID);
            preparedStatement.setInt(1, id);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                auto = new Auto(resultSet.getString("model"), resultSet.getString("color"), resultSet.getInt("user_id"));
                auto.setId(resultSet.getInt("id"));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return auto;
    }
    
    public boolean save(Auto auto) {
        try {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(SQL_INSERT_AUTO);
            preparedStatement.setString(1, auto.getModel());
            preparedStatement.setString(2, auto.getColor());
            preparedStatement.setInt(3, auto.getUserId());
    
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return true;
    }
    
    public boolean update(Auto auto, Auto new_auto) {
        try {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(SQL_UPDATE_AUTO);
            preparedStatement.setString(1, new_auto.getModel());
            preparedStatement.setString(2, new_auto.getColor());
            preparedStatement.setInt(3, new_auto.getUserId());
            preparedStatement.setInt(4, auto.getId());
            
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
            preparedStatement = connection.prepareStatement(SQL_DELETE_AUTO);
            preparedStatement.setInt(1, id);
            
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return true;
    }
}