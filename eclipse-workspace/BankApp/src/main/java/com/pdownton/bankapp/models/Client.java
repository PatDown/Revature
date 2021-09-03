package com.pdownton.bankapp.models;

import java.util.*;//Map, HashMap

/**
 *
 * @author Pat Down
 */

public class Client {
    public static final int BACK = 8;
    
    private int id;
    private String name;
    private Map<String, Account> accounts;
    private Account currentAccount;
    
    public Client(){
        super();
        this.id = (int)(Math.random()*(900)+100);;
        name = "";
        accounts = new HashMap<>();
        currentAccount = null;
    }//Client()

    public Client(String name) {
        super();
        this.id = (int)(Math.random()*(900)+100);
        this.name = name;
        accounts = new HashMap<>();
        currentAccount = null;
    }//Client(String)

    public int getID() {
        return id;
    }//getID()
    
    public void setID(int id){
        this.id = id;
    }//setID(int)

    public String getName() {
        if(this == null)
            return null;
        return name;
    }//getName()

    public void setName(String name) {
        this.name = name;
    }//setName(String)

    public Map<String, Account> getAccounts() {
        return accounts;
    }//getAccounts()
    
    public Account getCurrentAccount(){
        return currentAccount;
    }//getCurrentAccount()
    
    public void setCurrentAccount(Account account){
        currentAccount = account;
    }//setCurrentAccount(Account)
    
    public Account getAccount(String accNum){
        Account account = null;
        if (accounts.containsKey(accNum))
            account = accounts.get(accNum);

        return account;
    }//getAccount(String)
    
    @Override
    public String toString(){
        return "Client(id= " + id + ", name= " + name + ")";
    }//toString override
    
}//Client
