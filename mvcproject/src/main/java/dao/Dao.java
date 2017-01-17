package dao;

import java.util.List;

public interface Dao<T>{
    List<T> findAll();
    T find(int id);
    int save(T entity);
    int update(int id, T entity);
    boolean delete(int id);
}
