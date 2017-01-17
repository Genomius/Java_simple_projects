package services;

import dao.AutoDao;
import models.Auto;
import models.User;

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
}
