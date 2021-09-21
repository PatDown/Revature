package com.pdownton.reimbursementapp.repository;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Pat Down
 */
public interface Repository<T> {
    T get(int id) throws SQLException;
    
    List<T> getAll() throws SQLException;
    
    void save(T t) throws SQLException;
    void update(T t);
    void delete(T t) throws SQLException;
}//Repository
