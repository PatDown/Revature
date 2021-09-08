package com.pdownton.bankapp.models;

/**
 *
 * @author Pat Down
 */
public class Checking extends Account {
        
    public Checking(){
        super();
        balance = 0.00F;
        type = "Checking";
        clientID = 0;
    }//Checking()
    
    public Checking(float balance, int clientID) {
        super(balance, clientID);
        type = "Checking";
        this.clientID = clientID;
    }//Checking(float, int)
    
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
    
}//Checking
