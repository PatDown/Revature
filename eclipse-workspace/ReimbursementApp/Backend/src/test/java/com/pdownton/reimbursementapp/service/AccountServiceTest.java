/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pdownton.reimbursementapp.service;

import com.pdownton.reimbursementapp.models.Account;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Pat Down
 */
public class AccountServiceTest {
    
    public AccountServiceTest() {
    }//AccountServiceTest()

    /**
     * Test of create method, of class AccountService.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        Account account = null;
        AccountService instance = new AccountService();
        Account expResult = null;
        Account result = instance.create(account);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }//testCreate()

    /**
     * Test of getAccount method, of class AccountService.
     */
    @Test
    public void testGetAccount() {
        System.out.println("getAccount");
        int id = 10;
        AccountService instance = new AccountService();
        Account expResult = null;
        Account result = instance.getAccount(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }//testGetAccount()

    /**
     * Test of getAccounts method, of class AccountService.
     */
    @Test
    public void testGetAccounts() {
        System.out.println("getAccounts");
        AccountService instance = new AccountService();
        List<Account> expResult = null;
        List<Account> result = instance.getAccounts();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }//testGetAccounts()

    /**
     * Test of login method, of class AccountService.
     */
    @Test
    public void testLogin() {
        System.out.println("login");
        String username = "";
        String password = "";
        AccountService instance = new AccountService();
        int expResult = 0;
        int result = instance.login(username, password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }//testLogin()

    /**
     * Test of logout method, of class AccountService.
     */
    @Test
    public void testLogout() {
        System.out.println("logout");
        AccountService instance = new AccountService();
        boolean expResult = false;
        boolean result = instance.logout();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }//testLogout()
    
}//AccountServiceTest
