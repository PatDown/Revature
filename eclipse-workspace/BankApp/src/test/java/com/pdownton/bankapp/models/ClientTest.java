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
public class ClientTest {
    
    public ClientTest() {
    }//ClientTest()
    
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
     * Test of createAccount method, of class Client.
     */
    @Test
    public void testCreateCheckingAccount() {
        System.out.println("createAccount - Checkings");
        float balance = 1000;
        String type = "Checking";
        Client instance = new Client();
        
        Account result = instance.createAccount(balance, type);
        assertNotNull(result);
        System.out.println(result.toString());
    }//testCreateCheckingAccount()

    /**
     * Test of createAccount method, of class Client.
     */
    @Test
    public void testCreateSavingsAccount() {
        System.out.println("createAccount - Savings");
        float balance = 1000;
        String type = "Savings";
        float interest = 0.01F;
        Client instance = new Client();
        
        Account result = instance.createAccount(balance, type, interest);
        assertNotNull(result);
        System.out.println(result);
    }//testCreateSavingsAccount()

    /**
     * Test of addAccount method, of class Client.
     */
    @Test
    public void testAddCheckingAccount() {
        System.out.println("addAccount - Checking");
        Account account = new Checking(1000);
        Client instance = new Client();
        int id = instance.getID();
        
        String expResult = String.format("Account #%s added to client %d", account.getNumber(), id);
        String result = instance.addAccount(account);
        assertEquals(expResult, result);
        System.out.println(result);
    }//testAddCheckingAccount()
    
    /**
     * Test of addAccount method, of class Client.
     */
    @Test
    public void testAddSavingsAccount() {
        System.out.println("addAccount - Savings");
        Account account = new Savings(1000, 0.01F);
        Client instance = new Client();
        int id = instance.getID();
        
        String expResult = String.format("Account #%s added to client %d", account.getNumber(), id);
        String result = instance.addAccount(account);
        assertEquals(expResult, result);
        System.out.println(result);
    }//testAddSavingsAccount()

    /**
     * Test of deleteAccount method, of class Client.
     */
    @Test
    public void testDeleteCheckingAccount() {
        System.out.println("deleteAccount - Checking");
        Client instance = new Client();
        int id = instance.getID();
        Account account = new Checking(1000);
        String accNum = account.getNumber();
        
        instance.getAccounts().put(accNum, account);
        
        String expResult = String.format("Account #%s removed from client %d", accNum, id);
        String result = instance.deleteAccount(accNum);
        assertEquals(expResult, result);
        System.out.println(result);
    }//testDeleteCheckingAccount()
    
    /**
     * Test of deleteAccount method, of class Client.
     */
    @Test
    public void testDeleteSavingsAccount() {
        System.out.println("deleteAccount - Savings");
        Client instance = new Client();
        int id = instance.getID();
        Account account = new Savings(1000, 0.01F);
        String accNum = account.getNumber();
        
        instance.getAccounts().put(accNum, account);
        
        String expResult = String.format("Account #%s removed from client %d", accNum, id);
        String result = instance.deleteAccount(accNum);
        assertEquals(expResult, result);
        System.out.println(result);
    }//testDeleteSavingsAccount()

    /**
     * Test of changeAccount method, of class Client.
     */
    @Test
    public void testChangeAccount() {
        System.out.println("changeAccount");
        Client instance = new Client();
        Account account1 = new Checking(1000);
        Account account2 = new Savings(1000, 0.01F);
        String accNum = account2.getNumber();
        
        instance.getAccounts().put(account1.getNumber(), account1);
        instance.getAccounts().put(accNum, account2);
        instance.setCurrentAccount(account1);
        
        String expResult = String.format("Switched from account #%s to account #%s", account1.getNumber(), accNum);
        String result = instance.changeAccount(accNum);
        assertEquals(expResult, result);
        System.out.println(result);
    }//testChangeAccount()

    /**
     * Test of transfer method, of class Client.
     */
    @Test
    public void testTransfer() {
        System.out.println("transfer");
        Account acc1 = new Checking(1000);
        Account acc2 = new Savings(1000, 0.01F);
        float amount = 500;
        Client instance = new Client();
        
        instance.getAccounts().put(acc1.getNumber(), acc1);
        instance.getAccounts().put(acc2.getNumber(), acc2);
        
        String expResult = String.format("Transferred $%.2f from %s to %s.", amount, acc1.getNumber(), acc2.getNumber());
        String result = instance.transfer(acc1, acc2, amount);
        assertEquals(expResult, result);
        System.out.println(result);
    }//testTransfer()

    /**
     * Test of printAccounts method, of class Client.
     */
    @Test
    public void testPrintAccounts() {
        System.out.println("printAccounts");
        Account account1 = new Checking(1000);
        Account account2 = new Savings(1000, 0.01F);
        Account account3 = new Checking(5000);
        Client instance = new Client();
        
        instance.getAccounts().put(account1.getNumber(), account1);
        instance.getAccounts().put(account2.getNumber(), account2);
        instance.getAccounts().put(account3.getNumber(), account3);
        
        String expResult = "";
        expResult = instance.getAccounts().values().stream().map(c -> c.toString() + "\n").reduce(expResult, String::concat);
        String result = instance.printAccounts();
        assertEquals(expResult, result);
        System.out.print(result);
    }//testPrintAccounts()
    
}//ClientTest
