package com.pdownton.reimbursementapp.models;

/**
 *
 * @author Pat Down
 */
public class Manager extends Account{
    
    public Manager() {
        super();
        id = generateId();
    }//Manager()

    public Manager(String username, String password) {
        super(username, password);
        id = generateId();
    }//Manager(String, String)
    
    private int generateId(){
        int i = (int)(Math.random()*(90)+10);
        if (ids.contains(i))
            i = generateId();
        ids.add(i);
        return i;
    }//generateId()
    
}//Manager
