package com.pdownton.bankapp.service;

import com.pdownton.bankapp.models.Account;
import com.pdownton.bankapp.models.Checking;
import com.pdownton.bankapp.models.Savings;
import com.pdownton.bankapp.repository.AccountRepository;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Pat Down
 */
public class AccountService {
    private final AccountRepository accountRepository;
    
    public AccountService(Connection conn){
        accountRepository = new AccountRepository(conn);
    }//init(Connection)
    
    public Account create(String[] accountData) throws SQLException{
        Account account;
        float balance = Float.valueOf(accountData[0]);
        String type = accountData[1];
        int clientID = Integer.valueOf(accountData[2]);
        if (type.equalsIgnoreCase("Savings")){
            float interest = Float.valueOf(accountData[3]);
            account = new Savings(balance, clientID, interest);
        } else
            account = new Checking(balance, clientID);
        accountRepository.save(account);
        return account;
    }//create(String[])
    
    public Account create(float balance, int clientID, float interest){
        Account account = new Savings(balance, clientID, interest);
        try {
            accountRepository.save(account);
        } catch(SQLException e) {
            e.printStackTrace();
        }//
        return account;
    }//create(float, int, float)
    
    public List<Account> getAccounts(int clientID) {
        List<Account> accounts = new ArrayList<>();
        Map<String, Account> allAccounts;
        try {
            allAccounts = accountRepository.getAccounts();
            allAccounts.values().stream().filter(account -> (account.getClientID() == clientID)).forEachOrdered(account -> {
                accounts.add(account);
            });
        } catch (SQLException e){
            e.printStackTrace();
        }//catch(SQLException)
        
        return accounts;
    }//getAccounts(int)
    
    public Account getAccount(int id, String accNum) {
        Account account = null;
        try {
            account = accountRepository.findById(accNum);
            if (account.getClientID() != id)
                account = null;
        } catch (SQLException e){
            e.printStackTrace();
        }//catch(SQLException)
        
        return account;
    }//getAccount(String, int)
    
    public boolean withdraw(String accNum, float amount) {
        try {
            Account account = accountRepository.findById(accNum);

            if (account == null)
                return false;
            float currentBalance = account.getBalance();
            if (currentBalance < amount)
                return false;
            account.setBalance(currentBalance - amount);
            accountRepository.update(account, new String[]{"balance =  " + account.getBalance()});
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }//
        return false;
    }//withdraw(String, float)
    
    public boolean deposit(String accNum, float amount) {
        try {
            Account account = accountRepository.findById(accNum);

            if (account == null)
                return false;
            float currentBalance = account.getBalance();

            account.setBalance(currentBalance + amount);
            accountRepository.update(account, new String[]{"balance =  " + account.getBalance()});
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }//
        return false;
    }//deposit(String, float)
    
    public boolean updateAccount(String accNum, String[] params){
        Account account;
        try {
            account = accountRepository.findById(accNum);
            if (account == null)
                return false;
            accountRepository.update(account, params);
            return true;
        } catch (SQLException e){
            e.printStackTrace();
        }//
        return false;
    }//
    
    public boolean removeAccount(String accNum){
        Account account;
        try {
            account = accountRepository.findById(accNum);
            if (account == null)
                return false;
            accountRepository.delete(account);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }//
        
        return true;
    }//removeAccount(String)
}//AccountService
