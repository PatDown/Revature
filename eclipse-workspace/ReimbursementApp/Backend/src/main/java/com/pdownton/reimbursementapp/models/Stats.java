package com.pdownton.reimbursementapp.models;

/**
 *
 * @author Pat Down
 */
public class Stats {
    private float mean; 
    private float totalSpent;
    private float maxSpent;
    private String biggestSpender;
    
    public Stats(){
        super();
    }//Stats()

    public float getMean() {
        return mean;
    }//getMean()

    public void setMean(float mean) {
        this.mean = mean;
    }//setMean(float)

    public float getTotalSpent() {
        return totalSpent;
    }//getTotalSpent()

    public void setTotalSpent(float totalSpent) {
        this.totalSpent = totalSpent;
    }//setTotalSpent(float)

    public float getMaxSpent() {
        return maxSpent;
    }//getMaxSpent()

    public void setMaxSpent(float maxSpent) {
        this.maxSpent = maxSpent;
    }//setMaxSpent(float)

    public String getBiggestSpender() {
        return biggestSpender;
    }//getBiggestSpender()

    public void setBiggestSpender(String biggestSpender) {
        this.biggestSpender = biggestSpender;
    }//setBiggestSpender(String)
    
}//Stats

