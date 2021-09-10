package com.pdownton.reimbursementapp.models;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Pat Down
 */
public class Manager extends Account{
    
    private static Map<Integer, Manager> managers = new HashMap<>();
    public Manager() {
        super();
        id = generateId();
    }//Manager()

    public Manager(String username, String password) {
        super(username, password);
        id = generateId();
    }//Manager(String, String)

    public static Map<Integer, Manager> getManagers() {
        return managers;
    }//getManagers()
    
    private int generateId(){
        int i = (int)(Math.random()*(90)+10);
        if (ids.contains(i))
            i = generateId();
        ids.add(i);
        return i;
    }//generateId()
}//Manager
