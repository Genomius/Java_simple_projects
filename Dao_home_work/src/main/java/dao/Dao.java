package dao;

import java.util.List;

public interface Dao<T>{
    List<T> findAll();
    T find(int id);
    int save(T entity);
    int update(T entity, T new_entity);
    int delete(int id);
}
