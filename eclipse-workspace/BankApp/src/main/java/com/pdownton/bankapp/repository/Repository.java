package com.pdownton.bankapp.repository;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Pat Down
 * @param <T>
 */
public interface Repository<T> {
    T get(int id) throws SQLException;
    T findById(String id) throws SQLException;
    
    List<T> getAll() throws SQLException;
    
    void save(T t) throws SQLException;
    void update(T t, String[] params) throws SQLException;
    void delete(T t) throws SQLException;
    

}//Repository
