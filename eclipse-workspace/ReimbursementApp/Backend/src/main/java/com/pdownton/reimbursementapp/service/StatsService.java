package com.pdownton.reimbursementapp.service;

import com.pdownton.reimbursementapp.models.Reimbursement;
import com.pdownton.reimbursementapp.models.Stats;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Pat Down
 */
public class StatsService {
    private final ReimbursementService rService;
    public StatsService(){
        super();
        rService = new ReimbursementService();
    }//StatsService()
    
    public Stats getStats(int id){
        Stats stats;
        if (id > 9 && id < 100){
            stats = new Stats();
            List<Reimbursement> requests = rService.getReimbursements(id);
            Map<Integer, Float> employeeSpending = new HashMap<>();

            requests.stream().map(r -> {
                float amount = r.getAmount();
                int employeeId = r.getEmployeeId();
                stats.setTotalSpent(stats.getTotalSpent() + amount);
                if (employeeSpending.containsKey(employeeId))
                    employeeSpending.replace(employeeId, employeeSpending.get(employeeId) + amount);
                else
                    employeeSpending.put(employeeId, amount);
                return employeeId;
            }).filter(employeeId -> (employeeSpending.get(employeeId) > stats.getMaxSpent())).map(employeeId -> {
                stats.setMaxSpent(employeeSpending.get(employeeId));
                return employeeId;
            }).forEachOrdered(employeeId -> {
                stats.setBiggestSpender(employeeId);
            });

            stats.setMean(stats.getTotalSpent() / requests.size());
        } else
            stats = null;
        return stats;
    }//getStats(List<Reimbursement>)
}//StatsService
