package com.pdownton.bankapp.models;

/**
 *
 * @author Pat Down
 */
public abstract class Account {
    private static final int MIN = 1000;
    private static final int MAX = 9999;
    
    private final String number = generateAccountNumber();
    private float balance;
    protected String type;
    
    public Account(float balance) {
        super();
        this.balance = balance;
        type = "";
    }//Account(float)

    public String generateAccountNumber(){
        StringBuilder numberBuilder = new StringBuilder();
        int[] parts = new int[4];
        for(int i = 0; i < parts.length; i++){
            parts[i] = (int)(Math.random()*(MAX-MIN+1)+MIN);
            numberBuilder.append(parts[i]);
            if (i+1 < parts.length)
                numberBuilder.append("-");
        }//for(int i = 0; i < parts.length; i++)
        return numberBuilder.toString();
    }//generateAccountNumber()
    
    public String getNumber() {
        return number;
    }//getNumber()

    public float getBalance() {
        return balance;
    }//getBalance()

    public void setBalance(float balance) {
        this.balance = balance;
    }//setBalance(float)
    
    public String getType(){
        return type;
    }//getType()
    
    public void setType(String type){
        this.type = type;
    }//setType(String)
    
    public void withdraw(float amount) {
        if (getBalance() < amount){
            System.out.println("Not enough money in account.");
        } else {
            setBalance(getBalance() - amount);
        }//else
    }//withdraw(float)
    
    public void deposit(float amount){
        setBalance(getBalance() + amount);
    }//deposit(float)
    
}//Account
