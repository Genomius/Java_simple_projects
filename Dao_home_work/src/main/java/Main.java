import dao.AutoDao;
import dao.UserDao;
import dao.jdbc.AutoDaoSqlBasedImpl;
import dao.jdbc.UserDaoSqlBasedImpl;
import factories.UserFactory;
import models.Auto;
import models.User;

import java.sql.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserDao userDao = UserFactory.getInstance().getUserDao();
        
        Connection connection = null;
        UserDao userDaoSqlBased;
        AutoDao autoDaoSqlBased;
        String connection_url = "jdbc:postgresql://localhost:5432/user_auto_dao";
        String user_name = "den";
        String user_pass = "123321";
    
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(connection_url, user_name, user_pass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        userDaoSqlBased = new UserDaoSqlBasedImpl(connection);
        autoDaoSqlBased = new AutoDaoSqlBasedImpl(connection);
        
        
        
        
        
        
        
        
//
//        List<User> users = userDaoSqlBased.findAllUsers();
////
////        models.User user = new models.User("Masha", 23);
////        userDaoSqlBased.save(user);
//        users = userDaoSqlBased.findAllUsers();
//        for (User u : users)
//            u.printUserData();
//        System.out.println("");
////
////        user = userDaoSqlBased.find(17);
////
////        System.out.println("IS DELETE: " + userDaoSqlBased.delete(users.get(3).getId()));
////        System.out.println("IS UPDATE: " + userDaoSqlBased.update(user, new models.User("Ingiborg", 666)));
//
//        Auto auto = new Auto("Porshe new-series", "black", 3);
//        autoDaoSqlBased.save(auto);
        
//        models.User user;
//        models.Auto auto;
//
//        UserDao userDaoFileBased = new UserDaoFileBasedImpl(
//                "src/main/java/users.txt"
//        );
//
//        AutoDao autoDaoFileBased = new AutoDaoFileBasedImpl(
//                "src/main/java/autos.txt"
//        );
//
//        List<models.User> users = userDaoFileBased.findAllUsers();
//        for (models.User u : users)
//            u.printUserData();
//        System.out.println("");
//
//        user = userDaoFileBased.find(0);
//        if(user != null)
//            user.printUserData();
//        else
//            System.out.println("Нет такого пользователя !");
//        System.out.println("");
//
//        user = new models.User("John Connor", 22);
//        if(userDaoFileBased.save(user))
//            System.out.println("Пользователь успешно сохранен !");
//        else
//            System.out.println("Не удалось сохранить пользователя !");
//
//        user = userDaoFileBased.find(6);
//        if(userDaoFileBased.update(user, new models.User("Terminator", 23)))
//            System.out.println("Пользователь успешно обновлен !");
//        else
//            System.out.println("Не удалось обновить пользователя !");
//        System.out.println("");
//
//
//
//
//        auto = autoDaoFileBased.find(0);
//        if(auto != null)
//            auto.printAutoData();
//        else
//            System.out.println("Нет такого автомобиля !");
//        System.out.println("");
//
//        auto = new models.Auto("Porshe new-series", "black", 7);
//        if(autoDaoFileBased.save(auto))
//            System.out.println("Автомобиль успешно сохранен !");
//        else
//            System.out.println("Не удалось сохранить автомобиль !");
//
//        auto = autoDaoFileBased.find(6);
//        if(autoDaoFileBased.update(auto, new models.Auto("UAZ Patriot", "black metallic", 7)))
//            System.out.println("Автомобиль успешно обновлен !");
//        else
//            System.out.println("Не удалось обновить автомобиль !");
//        System.out.println("");
//
//        auto = autoDaoFileBased.find(8);
//        if(auto != null) {
//            if (autoDaoFileBased.delete(8))
//                System.out.println("Автомобиль успешно удален !");
//            else
//                System.out.println("Не удалось удалить автомобиль !");
//        }
//        else{
//            System.out.println("Автомобиль не был найден !");
//        }
//        System.out.println("");
    }
}
