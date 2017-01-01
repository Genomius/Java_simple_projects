package dao.file;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FilesAndData {
    
    static String getFileData(String file){
        File users_file = new File(file);
        StringBuilder sb = new StringBuilder();
        
        try {
            BufferedReader in = new BufferedReader(new FileReader( users_file.getAbsoluteFile()));
            try {
                String s;
                while ((s = in.readLine()) != null) {
                    sb.append(s);
                    sb.append("\n");
                }
            } catch(IOException e) {
                throw new RuntimeException(e);
            } finally {
                try{
                    in.close();
                }
                catch (IOException e){
                    throw new RuntimeException(e);
                }
            }
        } catch(FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        
        return sb.toString().replaceAll("\n", "");
    }
    
    static boolean setFileData(String fileName, String text){
        File file = new File(fileName);
        
        try {
            if(!file.exists()){
                file.createNewFile();
            }
            
            PrintWriter out = new PrintWriter(file.getAbsoluteFile());
            
            try {
                out.print(text);
            } finally {
                out.close();
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
        
        return true;
    }
    
    static List<Map<String, String>> getEntityData(String fileData, String entity){
        List<Map<String,String>> data = new ArrayList<Map<String,String>>();
        String field, value;
        
        // Берем все сущности со всеми его полями и их значениями
        String[] usersWithOurFieldsAndValues = fileData.split(entity+":");
        // Берем каждого конкретную сущность со всеми его полями
        for(String userWithOurFieldsAndValues : usersWithOurFieldsAndValues){
            // Берем все поля и значения для данной сущности
            String[] allFieldsWithValues = userWithOurFieldsAndValues.split(";");
            for(String fieldWithValue : allFieldsWithValues){
                if(!fieldWithValue.equals("")) {
                    field = fieldWithValue.split("=")[0];
                    value = fieldWithValue.split("=")[1];
                    
                    Map map = new HashMap<String,String>();
                    map.put(field, value);
                    data.add(map);
                }
            }
        }
        
        return data;
    }
}
