package com.pdownton.reimbursementapp.service;

import com.pdownton.reimbursementapp.models.Account;
import com.pdownton.reimbursementapp.models.Employee;
import com.pdownton.reimbursementapp.models.Manager;
import com.pdownton.reimbursementapp.models.Reimbursement;
import com.pdownton.reimbursementapp.repository.AccountRepository;
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
public class AccountService {
    private AccountRepository accountRepo;
    private ReimbursementService reimbursementService;
    public static final Map<Integer, Account> accounts = new HashMap<>();
    private Account currentAccount = null;
    
    public AccountService(){
        super();
    }//AccountService()
    
    public AccountService(Connection conn){
        super();
        accountRepo = new AccountRepository(conn);
        reimbursementService = new ReimbursementService();
    }//AccountService(Connection)
    
    public Account create(Account account){
        try {
            accountRepo.save(account);
            accounts.put(account.getId(), account);
        } catch (SQLException e){
           e.printStackTrace();
        }//catch (SQLException)
        return account;
    }//create(Account)
    
    public Account getAccount(int id){
        Account account = null;
        
        try {
            account = accountRepo.get(id);
        } catch (SQLException e){
           e.printStackTrace();
        }//catch (SQLException)
        
        return account;
    }//getAccount(int)
    
    public List<Account> getAccounts(){
        List<Account> accountList = new ArrayList<>();
        try {
            accountList = accountRepo.getAll();
        } catch (SQLException e){
           e.printStackTrace();
        }//catch (SQLException)
        
        for(Account acc : accountList)
            if (!accounts.containsValue(acc))
                accounts.put(acc.getId(), acc);
        
        return accountList;
    }//getAccounts()
    
    public int login(String username, String password){
        for (var entry : accounts.entrySet())
            if (entry.getValue().getUsername().equals(username) && entry.getValue().getPassword().equals(password)){
                currentAccount = entry.getValue();
                return entry.getKey();
            }//if (entry.getValue().getUsername().equals(username) && entry.getValue().getPassword().equals(password))
        return 0;
    }//login(String, String)
    
    public void logout(){
        currentAccount = null;
    }//logout()
    
    public boolean isLoggedIn(){
        return currentAccount != null;
    }//isLoggedIn()
    
    public Reimbursement createRequest(Reimbursement r){
        if (isLoggedIn()){
            r.setEmployeeId(currentAccount.getId());
            getReimbursements().add(reimbursementService.create(r));
            return r;
        } else
            return null;
    }//createRequest(Reimbursement)
    
    public List<Reimbursement> getReimbursements(){
        List<Reimbursement> reimbursements = reimbursementService.getAll();
        if (currentAccount.getId() > 100) {
            reimbursements.stream().filter(r -> (r.getEmployeeId() != currentAccount.getId())).forEachOrdered(r -> {
                reimbursements.remove(r);
            });
        }//if (currentAccount.getId() > 100)
        return reimbursements;
    }//getReimbursements()
    
    public boolean updateReimbursement(int id, String newStatus){
        if (id < 100)
            return reimbursementService.updateStatus(id, newStatus);
        else
            return false;
    }//updateReimbursement(int, String)
    
    public String statistics(){
        if (currentAccount.getId() < 100) {
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
        } else
            return "You are not a manager.";
    }//statistics()
}//AccountService
