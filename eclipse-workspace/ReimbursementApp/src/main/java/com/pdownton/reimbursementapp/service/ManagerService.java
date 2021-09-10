package com.pdownton.reimbursementapp.service;

import com.pdownton.reimbursementapp.models.Manager;
import com.pdownton.reimbursementapp.models.Reimbursement;
import com.pdownton.reimbursementapp.repository.ManagerRepository;
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
public class ManagerService {
    
    private ManagerRepository managerRepo;
    private ReimbursementService reimbursementService;
    public static final Map<Integer, Manager> managers = new HashMap<>();

    public ManagerService(){
        super();
    }//ManagerService()
    
    public ManagerService(Connection conn) {
        super();
        managerRepo = new ManagerRepository(conn);
        reimbursementService = new ReimbursementService();
    }//ManagerService(Connection)
    
    public Manager getManager(int id){
       Manager manager = new Manager();
       try {
           manager = managerRepo.get(id);
        } catch (SQLException e) {
           e.printStackTrace();
        }//catch (SQLException)
       return manager;
    }//getManager(int)
    
    public List<Manager> getManagers(){
        List<Manager> managerList = new ArrayList<>();
        try {
            managerList = managerRepo.getAll();
        } catch (SQLException e){
            e.printStackTrace();
        }//catch (SQLException)
        return managerList;
    }//getManagers()
    
    public List<Reimbursement> getReimbursements(){
        return reimbursementService.getAll();
    }//getReimbursements()
    
    public boolean updateReimbursement(int id, String newStatus){
        return reimbursementService.updateStatus(id, newStatus);
    }//updateReimbursement(int, String)
    
    public String statistics(){
        StringBuilder stats = new StringBuilder();
        List<Reimbursement> reimbursements = getReimbursements();
        float mean, totalSpent = 0, maxSpent = 0;
        int biggestSpender = 0;
        Map<Integer, Float> employeeSpending = new HashMap<>();
        
        for (var r : reimbursements){
            float amount = r.getAmount();
            int employeeId = r.getEmployeeId();
            totalSpent += amount;
            
            if (employeeSpending.containsKey(employeeId))
                employeeSpending.replace(employeeId, employeeSpending.get(employeeId) + amount);
            else
                employeeSpending.put(employeeId, amount);
            
            if (employeeSpending.get(employeeId) > maxSpent){
                maxSpent = employeeSpending.get(employeeId);
                biggestSpender = employeeId;
            }//if (employeeSpending.get(employeeId) > maxSpent)
            
        }//for (var r : reimbursements)
        
        mean = totalSpent / reimbursements.size();
        
        stats.append(String.format("Mean: $%.2f\n", mean));
        stats.append(String.format("Biggest Spender: %d - $%.2f\n", biggestSpender, maxSpent));
        
        return stats.toString();
        
    }//statistics()
    
    
}//ManagerService
