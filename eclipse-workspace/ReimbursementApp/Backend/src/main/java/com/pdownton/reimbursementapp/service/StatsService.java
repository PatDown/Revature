package com.pdownton.reimbursementapp.service;

import com.pdownton.reimbursementapp.models.Reimbursement;
import com.pdownton.reimbursementapp.models.Stats;
import java.util.*;//HashMap, List, Map

/**
 *
 * @author Pat Down
 */
public class StatsService {
    private final ReimbursementService rService;
    private final AccountService aService;
    public StatsService(){
        super();
        rService = new ReimbursementService();
        aService = new AccountService();
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
                stats.setBiggestSpender(aService.getAccount(employeeId).getName());
            });

            stats.setMean(stats.getTotalSpent() / requests.size());
        } else
            stats = null;
        return stats;
    }//getStats(int)
}//StatsService
