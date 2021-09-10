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
    
    public List<Reimbursement> getAll(){
        List<Reimbursement> rmbsmts = new ArrayList<>();
        try {
            rmbsmts = reimbursementRepo.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }//catch (SQLException)
        
        for (var r : rmbsmts)
            reimbursements.putIfAbsent(r.getId(), r);
        
        return rmbsmts;
    }//getAll()
    
    public Reimbursement create(Reimbursement reimbursement){
        try {
            reimbursementRepo.save(reimbursement);
        } catch (SQLException e){
           e.printStackTrace();
        }//catch (SQLException)
        return reimbursement;
    }//create(Reimbursement)
    
    public boolean updateStatus(int id, String status){
        Reimbursement r = getReimbursement(id);
        try {
            reimbursementRepo.update(r, new String[]{status});
            
        } catch (SQLException e){
           e.printStackTrace();
           return false;
        }//catch (SQLException)
         
        return true;
    }//updateStatus(Reimbursement, String)
    
}//ReimbursementService
