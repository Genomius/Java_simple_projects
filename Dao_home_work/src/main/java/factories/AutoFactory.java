package factories;

import dao.AutoDao;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class AutoFactory {
    private static AutoFactory instance;
    AutoDao autoDao;
    
    static{
        instance = new AutoFactory();
    }
    
    private AutoFactory(){
        Properties properties = new Properties();
        
        try {
            properties.load(new FileInputStream("/home/den/IdeaProjects/Dao_home_work/src/main/resources/context.properties"));
            String dataType = properties.getProperty("data.type");
            String dataClass = properties.getProperty("autodata." + dataType);
            
            if(dataType.equals("file")){
                String dataFileUrl = properties.getProperty("autofile.url");
                
                Constructor<?> constructor = Class.forName(dataClass).getConstructor(String.class);
                autoDao = (AutoDao) constructor.newInstance(dataFileUrl);
            } else if(dataType.equals("jdbc")){
                String dataJdbcUser = properties.getProperty("database.user");
                String dataJdbcPass = properties.getProperty("database.pass");
                String dataJdbcUrl = properties.getProperty("datajdbc.url");
                String dbDriver = properties.getProperty("db.driver");
                
                Class.forName(dbDriver);
                Connection connection = DriverManager.getConnection(dataJdbcUrl, dataJdbcUser, dataJdbcPass);
                Constructor<?> constructor = Class.forName(dataClass).getConstructor(Connection.class);
                autoDao = (AutoDao) constructor.newInstance(connection);
            }
            else if (dataType.equals("data_source")) {
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
                autoDao = (AutoDao) constructor.newInstance(dataSource);
            }
        } catch (IllegalAccessException | NoSuchMethodException | InstantiationException | InvocationTargetException |
                IOException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public static AutoFactory getInstance(){
        return instance;
    }
    
    public AutoDao getAutoDao(){
        return autoDao;
    }
}
