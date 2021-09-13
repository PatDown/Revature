package com.pdownton.reimbursementapp.service;

import com.pdownton.reimbursementapp.models.Reimbursement;
import com.pdownton.reimbursementapp.repository.ReimbursementRepository;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Reimbursement reimbursement = new Reimbursement();
        try {
            reimbursement = reimbursementRepo.get(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }//catch (SQLException)
        
        return reimbursement;
    }//getReimbursement(int)
    
    public List<Reimbursement> getAll(int id){
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
    }//getAll(int)
    
    public Reimbursement create(Reimbursement reimbursement){
        try {
            reimbursementRepo.save(reimbursement);
        } catch (SQLException e){
           e.printStackTrace();
        }//catch (SQLException)
        return reimbursement;
    }//create(Reimbursement)
    
    public String updateStatus(int id, String status, int managerId){
        Reimbursement r = getReimbursement(id);
        if (r == null)
            return "Request does not exist.";
        if (managerId < 10 || managerId > 99)
            return "You are not a manager.";
        if (r.getEmployeeId() == managerId)
            return "You cannot approve your own request.";
        if (!r.getStatus().equals("Pending"))
            return "Cannot update status.";
        try {
            reimbursementRepo.update(r, new String[]{status});
        } catch (SQLException e){
           e.printStackTrace();
        }//catch (SQLException)
         
        return String.format("Request %s!", status);
    }//updateStatus(Reimbursement, String)
    
}//ReimbursementService
