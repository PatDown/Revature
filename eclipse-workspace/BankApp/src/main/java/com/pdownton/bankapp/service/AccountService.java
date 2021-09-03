package com.pdownton.bankapp.service;

import com.pdownton.bankapp.models.Account;
import com.pdownton.bankapp.models.Checking;
import com.pdownton.bankapp.models.Savings;
import com.pdownton.bankapp.repository.AccountRepository;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Pat Down
 */
public class AccountService {
    private AccountRepository accountRepository;
    
    public void init(Connection conn){
        accountRepository = new AccountRepository(conn);
    }//init(Connection)
    
    public Account create(float balance, int clientID) throws SQLException{
        Account account = new Checking(balance, clientID);
        accountRepository.save(account);
        return account;
    }//create(float, int)
    
    public Account create(float balance, int clientID, float interest) throws SQLException {
        Account account = new Savings(balance, clientID, interest);
        accountRepository.save(account);
        return account;
    }//create(float, int, float)
    
    public List<Account> getAccounts() {
        List<Account> accounts = null;
        try {
            accounts = accountRepository.getAll();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return accounts;
    }//getAccounts()
    
    public Account getAccount(String accNum) throws SQLException {
        Account account = null;
        try {
            account = accountRepository.findById(accNum);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return account;
    }
    
    public boolean withdraw(String accNum, float amount) throws SQLException {
        Account account = accountRepository.findById(accNum);
        
        if (account == null)
            return false;
        float currentBalance = account.getBalance();
        if (currentBalance < amount)
            return false;
        account.setBalance(currentBalance - amount);
        accountRepository.update(account, new String[]{});
        return true;
    }//withdraw(String, float)
    
    public boolean deposit(String accNum, float amount) throws SQLException {
        Account account = accountRepository.findById(accNum);
        
        if (account == null)
            return false;
        float currentBalance = account.getBalance();
        
        account.setBalance(currentBalance + amount);
        //accountRepository.update(account, params);
        return true;
    }//deposit(float)
}//AccountService
