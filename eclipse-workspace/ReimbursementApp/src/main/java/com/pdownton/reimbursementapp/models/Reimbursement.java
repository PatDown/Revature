package com.pdownton.reimbursementapp.models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Pat Down
 */
public class Reimbursement {
    private int id = generateId();
    private float amount;
    private String reason;
    private String status;
    private int employeeId;
    
    public static List<Integer> ids = new ArrayList<>();

    public Reimbursement() {
        super();
    }//Reimbursement()
    
    public Reimbursement(float amount, String reason, int employee_id) {
        this.amount = amount;
        this.reason = reason;
        status = "pending";
        this.employeeId = employee_id;
    }//Reimbursement(float, String, String, int)

    public int getId(){
        return id;
    }//getId()
    
    public void setId(int id){
        this.id = id;
    }//setId(int)
    
    public float getAmount() {
        return amount;
    }//getAmount()

    public void setAmount(float amount) {
        this.amount = amount;
    }//setAmount(float)

    public String getReason() {
        return reason;
    }//getReason()

    public void setReason(String reason) {
        this.reason = reason;
    }//setReason(String)

    public String getStatus() {
        return status;
    }//getStatus()

    public void setStatus(String status) {
        this.status = status;
    }//setStatus(String)

    public int getEmployeeId() {
        return employeeId;
    }//getEmployeeId()

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }//setEmployeeId(int)

    private int generateId(){
        int[] i = new int[4];
        StringBuilder idBuilder = new StringBuilder();
        for (int j = 0; j < 4; j++){
            i[j] = (int)(Math.random()*(900)+100);
            idBuilder.append(i[j]);
        }//for (int j = 0; j < 4; j++)
        int k = Integer.valueOf(idBuilder.toString());
        if (ids.contains(k))
            k = generateId();
        ids.add(k);
        return k;
    }//generateId()
}//Reimbursement
