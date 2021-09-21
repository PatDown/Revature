package com.pdownton.reimbursementapp.service;

import com.pdownton.reimbursementapp.models.Reimbursement;
import com.pdownton.reimbursementapp.repository.ReimbursementRepository;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Pat Down
 */
public class ReimbursementService {
    private ReimbursementRepository reimbursementRepo;
    public static Map<Integer, Reimbursement> reimbursements = new HashMap<>();

    public ReimbursementService(){
        super();
    }//ReimbursementService()
    
    public ReimbursementService(Connection conn){
        super();
        reimbursementRepo = new ReimbursementRepository(conn);
    }//ReimbursementService(Connection)
    
    public Reimbursement getReimbursement(int id){
        Reimbursement reimbursement = null;
        try {
            reimbursement = reimbursementRepo.get(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }//catch (SQLException)
        
        return reimbursement;
    }//getReimbursement(int)
    
    public List<Reimbursement> getReimbursements(int id){
        List<Reimbursement> rmbsmts = new ArrayList<>();
        try {
            rmbsmts = reimbursementRepo.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }//catch (SQLException)
        
        for (var r : rmbsmts)
            if (!reimbursements.containsValue(r))
                reimbursements.put(r.getId(), r);
        
        if (id > 100) {
            List<Reimbursement> personal = new ArrayList<>();
            rmbsmts.stream().filter(r -> (r.getEmployeeId() == id)).forEachOrdered(r -> {
                personal.add(r);
            });
            return personal;
        } else
            return rmbsmts;
    }//getReimbursements(int)
    
    public List<Reimbursement> getAll(){
        List<Reimbursement> rmbsmts = new ArrayList<>();
        try {
            rmbsmts = reimbursementRepo.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }//catch (SQLException)
        
        for (var r : rmbsmts)
            if (!reimbursements.containsValue(r))
                reimbursements.put(r.getId(), r);
        return rmbsmts;
    }//getAll()
    
    public Reimbursement create(float amount, String reason, int employeeID){
        Reimbursement reimbursement = new Reimbursement(amount, reason, employeeID);
        
        reimbursementRepo.save(reimbursement);
       
        return reimbursement;
    }//create(Reimbursement)
    
    public String update(int id, String newStatus, int managerId, String message){
        Reimbursement r = getReimbursement(id);
        if (r == null)
            return "Request does not exist.";
        if (managerId < 10 || managerId > 99)
            return "You are not a manager.";
        if (r.getEmployeeId() == managerId)
            return "You cannot approve your own request.";
        if (!r.getStatus().equals("Pending"))
            return "Cannot update status.";
        r.setStatus(newStatus);
        r.setMessage(message);
        reimbursementRepo.update(r);
         
        return String.format("Request %s!", newStatus);
    }//update(int, String, int)
    
    public boolean delete(Reimbursement r){
        try {
            reimbursementRepo.delete(r);
        } catch (SQLException e){
           e.printStackTrace();
           return false;
        }//catch (SQLException)
        return true;
    }//delete(Reimbursement)
    
}//ReimbursementService
