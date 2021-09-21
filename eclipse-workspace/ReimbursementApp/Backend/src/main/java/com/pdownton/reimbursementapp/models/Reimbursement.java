package com.pdownton.reimbursementapp.models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Pat Down
 */
@Entity
@Table(name="requests")
public class Reimbursement{
    @Id
    @Column
    private int id = generateId();
    @Column
    private float amount;
    @Column
    private String reason;
    @Column
    private String status;
    @Column(name="employee_id")
    private int employeeId;
    @Column
    private String message;
    
    public static List<Integer> ids = new ArrayList<>();

    public Reimbursement() {
        super();
    }//Reimbursement()
    
    public Reimbursement(float amount, String reason, int employee_id) {
        super();
        this.amount = amount;
        this.reason = reason;
        status = "Pending";
        this.employeeId = employee_id;
        message = "None";
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

    public String getMessage() {
        return message;
    }//getMessage()

    public void setMessage(String message) {
        this.message = message;
    }//setMessage(String)

    private int generateId(){
        int[] i = new int[3];
        StringBuilder idBuilder = new StringBuilder();
        for (int j = 0; j < 3; j++){
            i[j] = (int)(Math.random()*(900)+100);
            idBuilder.append(i[j]);
        }//for (int j = 0; j < 3; j++)
        int k = Integer.valueOf(idBuilder.toString());
        if (ids.contains(k))
            k = generateId();
        ids.add(k);
        return k;
    }//generateId()
    
    @Override
    public String toString(){
        StringBuilder rBuilder = new StringBuilder();
        rBuilder.append(String.format("Amount: $%.2f | ", amount));
        rBuilder.append(String.format("Reason: %s | ", reason));
        rBuilder.append(String.format("Status: %s | ", status));
        rBuilder.append(String.format("Employee ID: %d", employeeId));
        if (!message.equals("None"))
            rBuilder.append(String.format(" | Message: %s", message));
        return rBuilder.toString();
    }//toString()
}//Reimbursement
