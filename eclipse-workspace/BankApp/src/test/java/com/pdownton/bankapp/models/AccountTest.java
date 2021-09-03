package com.pdownton.bankapp.models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Pat Down
 */
public class AccountTest {
    
    public AccountTest() {
    }//AccountTest()
    
    @BeforeAll
    public static void setUpClass() {
    }//setUpClass()
    
    @AfterAll
    public static void tearDownClass() {
    }//tearDownClass()
    
    @BeforeEach
    public void setUp() {
    }//setUp()
    
    @AfterEach
    public void tearDown() {
    }//tearDown()

    /**
     * Test of withdraw method, of class Account.
     */
    @Test
    public void testWithdraw() {
        System.out.println("withdraw");
        float amount = 500;
        Account instance = new Checking(1000);
        
        String expResult = String.format("New balance: $%.2f", 500F);
        String result = instance.withdraw(amount);
        
        assertEquals(instance.getBalance(), 500F);
        assertEquals(expResult, result);
        
        System.out.println(result);
    }//testWithdraw()

    /**
     * Test of deposit method, of class Account.
     */
    @Test
    public void testDeposit() {
        System.out.println("deposit");
        float amount = 500;
        Account instance = new Savings(1000, 0.01F);
        
        String expResult = String.format("New balance: $%.2f", 1500F);
        String result = instance.deposit(amount);
        
        assertEquals(expResult, result);
        System.out.println(result);
    }//testDeposit()
    
}//AccountTest
