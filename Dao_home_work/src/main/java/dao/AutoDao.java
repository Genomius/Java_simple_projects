package dao;

import models.Auto;
import java.util.List;

public interface AutoDao {
    List<Auto> findAllAutos();
    Auto find(int id);
    boolean save(Auto auto);
    boolean update(Auto auto, Auto new_auto);
    boolean delete(int id);
}
