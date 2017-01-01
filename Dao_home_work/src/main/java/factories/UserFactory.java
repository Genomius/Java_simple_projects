package factories;

import dao.UserDao;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class UserFactory {
    private static UserFactory instance;
    String userDaoClassName;
    UserDao userDao;
    
    static{
        instance = new UserFactory();
    }
    
    private UserFactory(){
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("/home/den/IdeaProjects/Dao_home_work/src/main/resources/context.properties"));
            String current_taking_data_mode = properties.getProperty("current_taking_data_mode");
            
            if(current_taking_data_mode.equals("file")){
                String userDaoClassName = properties.getProperty("jdbc_user.class");
                try {
                    userDao = (UserDao)Class.forName(userDaoClassName).newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } else if(current_taking_data_mode.equals("jdbc")){
                System.out.println("jdbc");
            }
        } catch (IOException e) {
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
