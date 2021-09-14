package com.pdownton.reimbursementapp.models;

/**
 *
 * @author Pat Down
 */
public class Employee extends Account{
    
    public Employee(){
        super();
        id = generateId();
    }//Employee()

    public Employee(String username, String password) {
        super(username, password);
        id = generateId();
    }//Employee(int, String, String)
    
    private int generateId(){
        int i = (int)(Math.random()*(9000)+1000);
        if (ids.contains(i))
            i = generateId();
        ids.add(i);
        return i;
    }//generateId()
}//Employee