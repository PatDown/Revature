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
    
}//Checking
