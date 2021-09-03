package com.pdownton.bankapp.models;

/**
 *
 * @author Pat Down
 */
public class Savings extends Account{
    
    private float interest;

    public Savings(){
        super();
        balance = 0.00F;
        type = "Savings";
        clientID = 0;
        interest = 0.00F;
    }//Savings()
    
    public Savings(float balance, int clientID, float interest) {
        super(balance, clientID);
        type = "Savings";
        this.interest = interest;
    }//Savings(float, int, float)

    public float getInterest() {
        return interest;
    }//getInterest()

    public void setInterest(float interest) {
        this.interest = interest;
    }//setInterest(float)
    
    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append(getNumber());
        s.append(", ");
        s.append(String.format("%.2f", getBalance()));
        s.append(", ");
        s.append(getType());
        s.append(", ");
        s.append(getClientID());
        s.append(", ");
        s.append(String.format("%.2f", getInterest()));        
        return s.toString();
    }//toString override
}//Savings
