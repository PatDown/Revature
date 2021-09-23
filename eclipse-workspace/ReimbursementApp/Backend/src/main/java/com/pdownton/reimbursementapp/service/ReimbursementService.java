package com.pdownton.reimbursementapp.service;

import com.pdownton.reimbursementapp.models.Reimbursement;
import com.pdownton.reimbursementapp.repository.ReimbursementRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Pat Down
 */
public class ReimbursementService {
    private final ReimbursementRepository reimbursementRepo;
    private final AccountService aService;
    public ReimbursementService(){
        super();
        reimbursementRepo = new ReimbursementRepository();
        aService = new AccountService();
    }//ReimbursementService()
    
    public Reimbursement getReimbursement(int id){
        Reimbursement reimbursement = reimbursementRepo.get(id);
        
        return reimbursement;
    }//getReimbursement(int)
    
    public List<Reimbursement> getReimbursements(int id){
        List<Reimbursement> rmbsmts = reimbursementRepo.getAll();
        
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
        List<Reimbursement> rmbsmts = reimbursementRepo.getAll();
        
        return rmbsmts;
    }//getAll()
    
    public Reimbursement create(float amount, String reason, int employeeID){
        Reimbursement reimbursement = new Reimbursement(amount, reason, employeeID);
        reimbursement.setEmployeeName(aService.getAccount(employeeID).getName());
        reimbursementRepo.save(reimbursement);
       
        return reimbursement;
    }//create(float, String, int)
    
    public Reimbursement update(int id, String newStatus, int managerId, String message){
        Reimbursement r = getReimbursement(id);
        if (r != null){
            if (managerId < 10 || managerId > 99){
                r.setStatus("Pending");
                r.setMessage("You are not a manager.");
            } else if (r.getEmployeeId() == managerId) {
                r.setStatus("Pending");
                r.setMessage("You cannot approve your own request.");
            } else if (!r.getStatus().equals("Pending")){
                r.setMessage("Cannot change status");
            } else {
                r.setStatus(newStatus);
                r.setMessage(message);
            }//else
             
            reimbursementRepo.update(r);
        }
        return r;
    }//update(int, String, int, String)
    
}//ReimbursementService

