package genome.services;

import genome.models.Auto;

import java.util.List;

public interface AutoService {
    List<Auto> getAllAutos();
    Auto getAutoById(int id);
    boolean deleteAutoById(int autoId);
    int saveAuto(Auto auto);
    int updateAuto(int autoId, Auto newAuto);
    List<Auto> getAllAutosByUserId(int userId);
}
