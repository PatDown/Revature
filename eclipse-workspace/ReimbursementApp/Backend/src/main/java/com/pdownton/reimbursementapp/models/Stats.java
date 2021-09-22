package com.pdownton.reimbursementapp.models;

/**
 *
 * @author Pat Down
 */
public class Stats {
    private float mean; 
    private float totalSpent;
    private float maxSpent;
    private int biggestSpender;
    
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

    public int getBiggestSpender() {
        return biggestSpender;
    }//getBiggestSpender()

    public void setBiggestSpender(int biggestSpender) {
        this.biggestSpender = biggestSpender;
    }//setBiggestSpender(int)
    
}//Stats

