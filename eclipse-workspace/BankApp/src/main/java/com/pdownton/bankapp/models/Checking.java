package com.pdownton.bankapp.models;

/**
 *
 * @author Pat Down
 */
public class Checking extends Account {
    
    public Checking(float balance) {
        super(balance);
        type = "Checking";
    }//Checking(float)
    
    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append("Account number: ");
        s.append(getNumber());
        s.append(" | Balance: $");
        s.append(String.format("%.2f", getBalance()));
        s.append(" | Account type: ");
        s.append(getType());
        return s.toString();
    }//toString override
    
}//Checking
