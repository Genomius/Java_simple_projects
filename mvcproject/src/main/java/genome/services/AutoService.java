package genome.services;

import genome.dao.AutoDao;
import genome.models.Auto;

import java.util.ArrayList;
import java.util.List;

public class AutoService {
    AutoDao autoDao;
    
    public AutoService(AutoDao autoDao) {
        this.autoDao = autoDao;
    }
    
    public List<Auto> getAllAutos(){
        return autoDao.findAll();
    }
    
    public Auto getAutoById(int id){
        return autoDao.find(id);
    }
    
    public boolean deleteAutoById(int autoId){
        return autoDao.delete(autoId);
    }
    
    public int saveAuto(Auto auto){return autoDao.save(auto);}
    
    public int updateAuto(int autoId, Auto newAuto){
        return autoDao.update(autoId, newAuto);
    }
    
    public List<Auto> getAllAutosByUserId(int userId){
        List<Auto> autos = autoDao.findAll();
        List<Auto> userAutos = new ArrayList<Auto>();
        
        for (Auto auto : autos) {
            if (auto.getUser().getId() == userId)
                userAutos.add(auto);
        }
        
        return userAutos;
    }
}
