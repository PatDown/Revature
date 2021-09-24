package com.pdownton.reimbursementapp.service;

import com.pdownton.reimbursementapp.models.Account;
import com.pdownton.reimbursementapp.repository.AccountRepository;
import java.util.*;//HashMap, List, Map

/**
 *
 * @author Pat Down
 */
public class AccountService {
    private final AccountRepository accountRepo;
    public static final Map<Integer, Account> accounts = new HashMap<>();
    private Account currentAccount = null;
    
    public AccountService(){
        super();
        accountRepo = new AccountRepository();
    }//AccountService()
    
    public Account getCurrentAccount(){
        return currentAccount;
    }//getCurrentAccount()
    
    public void setCurrentAccount(int id){
        currentAccount = getAccount(id);
    }//setCurrentAccount(int)
    
    public Account create(Account account){
        accountRepo.save(account);
        accounts.put(account.getId(), account);
        return account;
    }//create(Account)
    
    public Account getAccount(int id){
        Account account = accountRepo.get(id);
        return account;
    }//getAccount(int)
    
    public List<Account> getAccounts(){
        List<Account>accountList = accountRepo.getAll();
        
        accountList.stream().filter(acc -> (!accounts.containsValue(acc))).forEachOrdered(acc -> {
            accounts.put(acc.getId(), acc);
        });
        return accountList;
    }//getAccounts()
    
    public int login(String username, String password){
        if (!isLoggedIn()){
            getAccounts();
            for (var acc : accounts.values()){
                if (acc.getUsername().equals(username) && acc.getPassword().equals(password)){
                    currentAccount = acc;
                    return acc.getId();
                }//if (acc.getUsername().equals(username) && acc.getPassword().equals(password))
            }//for (var acc : accs)
            return 0;
        } else
            return 0;
    }//login(String, String)
    
    public boolean logout(){
        if (isLoggedIn())
            currentAccount = null;
        else
            return false;
        return true;
    }//logout()
    
    private boolean isLoggedIn(){
        return currentAccount != null;
    }//isLoggedIn()
    
}//AccountService
