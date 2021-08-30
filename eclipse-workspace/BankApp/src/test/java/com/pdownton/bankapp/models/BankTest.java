/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pdownton.bankapp.models;

import java.util.Map;
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
public class BankTest {
    
    public BankTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of printClients method, of class Bank.
     */
    @Test
    public void testPrintClients() {
        System.out.println("printClients");
        Bank instance = new Bank();
        instance.createClient("Patrick Downton");
        instance.printClients();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createClient method, of class Bank.
     */
    @Test
    public void testCreateClient() {
        System.out.println("createClient");
        String name = "";
        Bank instance = new Bank();
        instance.createClient(name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addClient method, of class Bank.
     */
    @Test
    public void testAddClient() {
        System.out.println("addClient");
        Client client = null;
        Bank instance = new Bank();
        instance.addClient(client);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeClient method, of class Bank.
     */
    @Test
    public void testRemoveClient() {
        System.out.println("removeClient");
        int id = 0;
        Bank instance = new Bank();
        instance.removeClient(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateClient method, of class Bank.
     */
    @Test
    public void testUpdateClient() {
        System.out.println("updateClient");
        int id = 0;
        Bank instance = new Bank();
        instance.updateClient(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of changeClient method, of class Bank.
     */
    @Test
    public void testChangeClient() {
        System.out.println("changeClient");
        int id = 0;
        Bank instance = new Bank();
        instance.changeClient(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isClient method, of class Bank.
     */
    @Test
    public void testIsClient() {
        System.out.println("isClient");
        int id = 0;
        Bank instance = new Bank();
        boolean expResult = false;
        boolean result = instance.isClient(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
