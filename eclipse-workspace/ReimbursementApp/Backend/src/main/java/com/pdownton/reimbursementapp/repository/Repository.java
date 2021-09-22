package com.pdownton.reimbursementapp.repository;

import java.util.List;

/**
 *
 * @author Pat Down
 */
public interface Repository<T> {
    T get(int id);
    
    List<T> getAll();
    
    void save(T t);
    void update(T t);
    void delete(T t);
}//Repository
