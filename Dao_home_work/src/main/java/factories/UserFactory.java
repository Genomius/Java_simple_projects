package factories;

import dao.UserDao;
import models.Auto;
import models.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import org.hibernate.cfg.Configuration;

public class UserFactory {
    private static UserFactory instance;
    private UserDao userDao;
    
    private Configuration configuration;
    private SessionFactory sessionFactory;
    
    static{
        instance = new UserFactory();
    }
    
    private UserFactory(){
        Properties properties = new Properties();
        
        try {
            properties.load(new FileInputStream("/home/den/IdeaProjects/Dao_home_work/src/main/resources/context.properties"));
            String dataType = properties.getProperty("data.type");
            String dataClass = properties.getProperty("userdata." + dataType);
    
            switch (dataType) {
                case "file": {
                    String dataFileUrl = properties.getProperty("userfile.url");
            
                    Constructor<?> constructor = Class.forName(dataClass).getConstructor(String.class);
                    userDao = (UserDao) constructor.newInstance(dataFileUrl);
                    break;
                }
                case "jdbc": {
                    String dataJdbcUser = properties.getProperty("database.user");
                    String dataJdbcPass = properties.getProperty("database.pass");
                    String dataJdbcUrl = properties.getProperty("datajdbc.url");
                    String jdbcDriver = properties.getProperty("db.driver");
            
                    Class.forName(jdbcDriver);
                    Connection connection = DriverManager.getConnection(dataJdbcUrl, dataJdbcUser, dataJdbcPass);
                    Constructor<?> constructor = Class.forName(dataClass).getConstructor(Connection.class);
                    userDao = (UserDao) constructor.newInstance(connection);
                    break;
                }
                case "data_source": {
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
                    userDao = (UserDao) constructor.newInstance(dataSource);
                    break;
                }
                case "hibernate":
                    configuration = new Configuration();
                    configuration.setProperty("hibernate.connection.url", properties.getProperty("datajdbc.url"));
                    configuration.setProperty("hibernate.connection.driver_class", properties.getProperty("db.driver"));
                    configuration.setProperty("hibernate.connection.username", "database.user");
                    configuration.setProperty("hibernate.connection.password", "database.pass");
                    configuration.setProperty("hibernate.show_sql", "true");
                    configuration.setProperty("hibernate.dialect", properties.getProperty("hibernate_dialect"));
                    configuration.setProperty("hibernate.hbm2ddl.auto", "update");
    
                    configuration.addResource("User.hbm.xml");
                    configuration.addResource("Auto.hbm.xml");
                    //configuration.addAnnotatedClass(User.class);
                    //configuration.addAnnotatedClass(Auto.class);
                    
                    Session session = getSession();
    
                    Constructor<?> constructor = Class.forName(dataClass).getConstructor(Session.class);
                    userDao = (UserDao) constructor.newInstance(session);
                    break;
            }
        } catch (IllegalAccessException | NoSuchMethodException | InstantiationException | InvocationTargetException |
                IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    private Session getSession() throws HibernateException {
        Session session = sessionFactory.openSession();
        if (!session.isConnected()) {
            this.reconnect();
        }
        return session;
    }
    
    private void reconnect() throws HibernateException {
        this.sessionFactory = configuration.buildSessionFactory();
    }
    
    public static UserFactory getInstance(){
        return instance;
    }
    
    public UserDao getUserDao(){
        return userDao;
    }
}
