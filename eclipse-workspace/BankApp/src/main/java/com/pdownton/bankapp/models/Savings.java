package com.pdownton.bankapp.models;

/**
 *
 * @author Pat Down
 */
public class Savings extends Account{
    private float interest;

    public Savings(float balance, float interest) {
        super(balance);
        this.interest = interest;
        type = "Savings";
    }//Savings(float, float)

    public float getInterest() {
        return interest;
    }//getInterest()

    public void setInterest(float interest) {
        this.interest = interest;
    }//setInterest(float)
    
    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append("Account number: ");
        s.append(getNumber());
        s.append(" | Balance: $");
        s.append(String.format("%.2f", getBalance()));
        s.append(" | Account type: ");
        s.append(getType());
        s.append(" | Interest rate: ");
        s.append(getInterest());
        s.append("%");
        
        return s.toString();
    }//toString override
}//Savings
