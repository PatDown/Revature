package com.pdownton.bankapp.models;

/**
 *
 * @author Pat Down
 */
public abstract class Account {
    private static final int MIN = 1000;
    private static final int MAX = 9999;
    
    protected String number;
    protected float balance;
    protected String type;
    protected int clientID;
    
    public Account(){
        super();
        number = generateAccountNumber();
        balance = 0.0F;
        type = "";
        clientID = 0;
    }//Account()
    
    public Account(float balance, int clientID) {
        super();
        number = generateAccountNumber();
        this.balance = balance;
        type = "";
        this.clientID = clientID;
    }//Account(float, String, int)
    
    private String generateAccountNumber(){
        StringBuilder numberBuilder = new StringBuilder();
        int[] parts = new int[4];
        for(int i = 0; i < parts.length; i++) {
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
    
    public void setNumber(String number){
        this.number = number;
    }//setNumber(String)

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
    
    public int getClientID(){
        return clientID;
    }//getClientID()
    
    public void setClientID(int clientID){
        this.clientID = clientID;
    }//setClientID(int)
    
    public Savings toSavings(){
        return (Savings) this;
    }//toSavings(Account)
    
    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append(getNumber());
        s.append(", ");
        s.append(String.format("%.2f", getBalance()));
        s.append(", ");
        s.append(getType());
        s.append(", ");
        s.append(getType());
        s.append(", ");
        s.append(getClientID());
        s.append(", NULL");
        return s.toString();
    }//toString override
    
}//Account
