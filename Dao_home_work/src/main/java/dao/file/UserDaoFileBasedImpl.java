package dao.file;

import dao.UserDao;
import models.User;
import models.Auto;

import java.util.*;

import static java.lang.Integer.parseInt;

public class UserDaoFileBasedImpl implements UserDao {
    private String usersFileName;
    
    public UserDaoFileBasedImpl(String usersFileName) {
        this.usersFileName = usersFileName;
    }
    
    private String setEntityData(List<Map<String, String>> data, String entity){
        int i = 0;
        String text = "";
        
        for(Map<String, String> map : data){
            Iterator<Map.Entry<String, String>> entries = map.entrySet().iterator();
    
            if(i == 0 || i % 3 == 0){
                if(i != 0)
                    text += "\n";
                text += entity+":";
            }
    
            while (entries.hasNext()) {
                Map.Entry<String, String> entry = entries.next();
                if (entry.getKey().equals("id")) text += "id="+entry.getValue()+";";
                else if (entry.getKey().equals("name")) text += "name="+entry.getValue()+";";
                else if (entry.getKey().equals("age")) text += "age="+entry.getValue()+";";
                i++;
            }
        }
        
        return text;
    }
    
    public List<User> findAllUsers() {
        ArrayList<User> users = new ArrayList<User>();
        String fileData = FilesAndData.getFileData(this.usersFileName);
        List<Map<String, String>> user_data = FilesAndData.getEntityData(fileData,"user");
        List<Auto> autos = new AutoDaoFileBasedImpl("src/main/java/autos.txt").findAllAutos();
    
        int i = 0;
        int id = -1;
        int age = 0;
        String name = "";
        
        for(Map<String, String> map : user_data) {
            Iterator<Map.Entry<String, String>> entries = map.entrySet().iterator();
            
            while (entries.hasNext()) {
                Map.Entry<String, String> entry = entries.next();
                if(entry.getKey().equals("id"))id = parseInt(entry.getValue());
                else if(entry.getKey().equals("age"))age = parseInt(entry.getValue());
                else if(entry.getKey().equals("name"))name = entry.getValue();
            }
            i++;
            
            if(i!=0 && i%3==0) {
                User user = new User(name, age);
                user.setId(id);
                
                for(Auto auto : autos)
                    if(auto.getUserId() == id)
                        user.addAuto(auto);
                
                users.add(user);
            }
        }

        return users;
    }
    
    public User find(int id) {
        List<User> users = findAllUsers();
        
        for(User user : users)
            if(user.getId() == id)
                return user;
        return null;
    }
    
    public boolean save(User user) {
        Object value;
        
        List<Map<String, String>> user_data = new ArrayList<Map<String,String>>();
        List<User> users = findAllUsers();
        
        if(user.getId() == -1) {
            int id = -1;
            for (User u : users) {
                if (u.getId() > id)
                    id = u.getId();
            }
    
            id++;
            if (id != 0 || users.size() == 0) {
                user.setId(id);
                users.add(user);
            } else {
                System.out.println("Не удалось создать пользователя с таким id");
                return false;
            }
        }
        else{
            users.add(user);
        }
    
        for(User u : users) {
            Map map = new HashMap<String, String>();
            value = u.getId();
            map.put("id", value.toString());
            value = u.getName();
            map.put("name", value.toString());
            value = u.getAge();
            map.put("age", value.toString());
            
            user_data.add(map);
        }
    
        FilesAndData.setFileData(this.usersFileName, setEntityData(user_data, "user"));
        
        return true;
    }
    
    public boolean delete(int id) {
        List<Map<String, String>> user_data = new ArrayList<Map<String,String>>();
        Object value;
        List<User> users = findAllUsers();
    
        Iterator<User> i = users.iterator();
        while (i.hasNext()) {
            User u = i.next();
            if(u.getId() == id)
                i.remove();
        }
    
        for(User u : users) {
            Map map = new HashMap<String, String>();
            value = u.getId();
            map.put("id", value.toString());
            value = u.getName();
            map.put("name", value.toString());
            value = u.getAge();
            map.put("age", value.toString());
        
            user_data.add(map);
        }
    
        FilesAndData.setFileData(this.usersFileName, setEntityData(user_data, "user"));
        
        return true;
    }
    
    public boolean update(User user, User new_user) {
        if(user == null){
            System.out.println("Нет такого пользователя !");
            return false;
        }
        
        user.setName(new_user.getName());
        user.setAge(new_user.getAge());
        
        delete(user.getId());
        save(user);
        
        return true;
    }
}
