package com.pdownton.reimbursementapp.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Pat Down
 */
public class Employee extends Account{
    private final List<Reimbursement> reimbursements;
    private static Map<Integer, Employee> employees = new HashMap<>();
    
    public Employee(){
        super();
        id = generateId();
        reimbursements = new ArrayList<>();
    }//Employee()

    public Employee(String username, String password) {
        super(username, password);
        id = generateId();
        reimbursements = new ArrayList<>();
    }//Employee(int, String, String)
    
    public List<Reimbursement> getReimbursements(){
        return reimbursements;
    }//getReimbursements()
    
    public static Map<Integer, Employee> getEmployees(){
        return employees;
    }//getEmployees()
    
    private int generateId(){
        int i = (int)(Math.random()*(9000)+1000);
        if (ids.contains(i))
            i = generateId();
        ids.add(i);
        return i;
    }//generateId()
}//Employee
