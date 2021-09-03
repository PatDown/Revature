/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pdownton.bankapp;

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
public class BankAppTest {
    
    BankApp bankApp;
    public BankAppTest() {
        
    }//BankAppTest()
    
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
     * Test of main method, of class BankApp.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        BankApp.main(args);
        assertTrue(true);
    }//testMain()
    
}//BankAppTest
