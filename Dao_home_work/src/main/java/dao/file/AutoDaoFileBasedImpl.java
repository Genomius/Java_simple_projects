package dao.file;

import dao.AutoDao;
import models.Auto;

import java.util.*;
import static java.lang.Integer.parseInt;

public class AutoDaoFileBasedImpl implements AutoDao {
    private String autosFileName;
    
    public AutoDaoFileBasedImpl(String autosFileName) {
        this.autosFileName = autosFileName;
    }
    
    private String setEntityData(List<Map<String, String>> data, String entity){
        int i = 0;
        String text = "";
        
        for(Map<String, String> map : data){
            Iterator<Map.Entry<String, String>> entries = map.entrySet().iterator();
            
            if(i == 0 || i % 4 == 0){
                if(i != 0)
                    text += "\n";
                text += entity+":";
            }
            
            while (entries.hasNext()) {
                Map.Entry<String, String> entry = entries.next();
                if (entry.getKey().equals("id")) text += "id="+entry.getValue()+";";
                else if (entry.getKey().equals("model")) text += "model="+entry.getValue()+";";
                else if (entry.getKey().equals("color")) text += "color="+entry.getValue()+";";
                else if (entry.getKey().equals("user")) text += "user="+entry.getValue()+";";
    
                i++;
            }
        }
        
        return text;
    }
    
    public List<Auto> findAllAutos() {
        ArrayList<Auto> autos = new ArrayList<Auto>();
        String fileData = FilesAndData.getFileData(this.autosFileName);
        List<Map<String, String>> auto_data = FilesAndData.getEntityData(fileData,"auto");
        
        int i = 0;
        int id = 0;
        String model = "";
        String color = "";
        int user_id = 0;
        
        for(Map<String, String> map : auto_data) {
            Iterator<Map.Entry<String, String>> entries = map.entrySet().iterator();
            
            while (entries.hasNext()) {
                Map.Entry<String, String> entry = entries.next();
                if (entry.getKey().equals("id")) id = parseInt(entry.getValue());
                else if (entry.getKey().equals("model")) model = entry.getValue();
                else if (entry.getKey().equals("color")) color = entry.getValue();
                else if (entry.getKey().equals("user")) user_id = parseInt(entry.getValue());
            }
            i++;
            
            if (i != 0 && i % 4 == 0) {
                Auto auto = new Auto(model, color, user_id);
                auto.setId(id);
                autos.add(auto);
            }
        }
        
        return autos;
    }
    
    public Auto find(int id) {
        List<Auto> autos = findAllAutos();
        
        for(Auto auto : autos)
            if(auto.getId() == id)
                return auto;
        return null;
    }
    
    public boolean save(Auto auto) {
        Object value;
        
        List<Map<String, String>> auto_data = new ArrayList<Map<String,String>>();
        List<Auto> autos = findAllAutos();
    
        if(auto.getId() == -1) {
            int id = -1;
            for (Auto a : autos) {
                if (a.getId() > id)
                    id = a.getId();
            }
    
            id++;
            if (id != 0) {
                auto.setId(id);
                autos.add(auto);
            } else {
                System.out.println("Не удалось создать пользователя с таким id");
                return false;
            }
        }
        else{
            autos.add(auto);
        }
        
        for(Auto a : autos) {
            Map map = new HashMap<String, String>();
            value = a.getId();
            map.put("id", value.toString());
            value = a.getModel();
            map.put("model", value.toString());
            value = a.getColor();
            map.put("color", value.toString());
            value = a.getUserId();
            map.put("user", value.toString());
    
            auto_data.add(map);
        }
        
        FilesAndData.setFileData(this.autosFileName, setEntityData(auto_data, "auto"));
        
        return true;
    }
    
    public boolean delete(int id) {
        List<Map<String, String>> auto_data = new ArrayList<Map<String,String>>();
        Object value;
        List<Auto> autos = findAllAutos();
        
        Iterator<Auto> i = autos.iterator();
        while (i.hasNext()) {
            Auto a = i.next();
            if(a.getId() == id)
                i.remove();
        }
        
        for(Auto a : autos) {
            Map map = new HashMap<String, String>();
            value = a.getId();
            map.put("id", value.toString());
            value =a.getModel();
            map.put("model", value.toString());
            value = a.getColor();
            map.put("color", value.toString());
            value = a.getUserId();
            map.put("user", value.toString());
    
            auto_data.add(map);
        }
        
        FilesAndData.setFileData(this.autosFileName, setEntityData(auto_data, "auto"));
        
        return true;
    }
    
    public boolean update(Auto auto, Auto new_auto) {
        if(auto == null){
            System.out.println("Нет такого автомобиля !");
            return false;
        }
        
        auto.setModel(new_auto.getModel());
        auto.setColor(new_auto.getColor());
        auto.setUserId(new_auto.getUserId());
    
        delete(auto.getId());
        save(auto);
        
        return true;
    }
}
