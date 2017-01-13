package factories;

import dao.UserDao;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

public class UserFactory {
    private static UserFactory instance;
    private UserDao userDao;
    
    static{
        instance = new UserFactory();
    }
    
    private UserFactory(){
        Properties properties = new Properties();
        
        try {
            properties.load(new FileInputStream("/home/den/IdeaProjects/Dao_home_work/src/main/resources/context.properties"));
            String dataType = properties.getProperty("data.type");
            String dataClass = properties.getProperty("userdata." + dataType);
    
            if (dataType.equals("file")) {
                String dataFileUrl = properties.getProperty("userfile.url");
        
                Constructor<?> constructor = Class.forName(dataClass).getConstructor(String.class);
                userDao = (UserDao) constructor.newInstance(dataFileUrl);
            } else if (dataType.equals("jdbc")) {
                String dataJdbcUser = properties.getProperty("database.user");
                String dataJdbcPass = properties.getProperty("database.pass");
                String dataJdbcUrl = properties.getProperty("datajdbc.url");
                String jdbcDriver = properties.getProperty("db.driver");
        
                Class.forName(jdbcDriver);
                Connection connection = DriverManager.getConnection(dataJdbcUrl, dataJdbcUser, dataJdbcPass);
                Constructor<?> constructor = Class.forName(dataClass).getConstructor(Connection.class);
                userDao = (UserDao) constructor.newInstance(connection);
            } else if (dataType.equals("data_source")) {
                DataSource dataSource;
                String dataJdbcUser = properties.getProperty("database.user");
                String dataJdbcPass = properties.getProperty("database.pass");
                String dataJdbcUrl = properties.getProperty("datajdbc.url");
                String jdbcDriver = properties.getProperty("db.driver");
    
                DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
                driverManagerDataSource.setDriverClassName(jdbcDriver);
                driverManagerDataSource.setUrl(dataJdbcUrl);
                driverManagerDataSource.setUsername(dataJdbcUser);
                driverManagerDataSource.setPassword(dataJdbcPass);
                dataSource = driverManagerDataSource;
    
                Constructor<?> constructor = Class.forName(dataClass).getConstructor(DataSource.class);
                userDao = (UserDao)constructor.newInstance(dataSource);
            }
        } catch (IllegalAccessException | NoSuchMethodException | InstantiationException | InvocationTargetException |
                IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    public static UserFactory getInstance(){
        return instance;
    }
    
    public UserDao getUserDao(){
        return userDao;
    }
}
