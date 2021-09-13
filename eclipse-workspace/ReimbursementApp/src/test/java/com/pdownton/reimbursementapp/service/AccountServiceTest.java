package com.pdownton.reimbursementapp.service;

import com.pdownton.reimbursementapp.models.Reimbursement;
import com.pdownton.reimbursementapp.utils.ConnectionFactory;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Pat Down
 */
public class AccountServiceTest {
    public static AccountService accountService = new AccountService(ConnectionFactory.getConnection());
    public final String D1 = "=================================================";
    public final String D2 = "-------------------------------------------------";
    public AccountServiceTest() {
        accountService.getAccounts();
    }//AccountServiceTest()

    /**
     * Test of login method, of class AccountService.
     */
    @Test
    public void testLogin() {
        System.out.println(D1 
                + "\nMethod: login"
                + "\nConditions: Valid login credentials.");
        String username = "pDownton";
        String password = "pd10";
        accountService.setCurrentAccount(0);
        
        String expResult = "Hello Patrick Downton!";
        String result = accountService.login(username, password);
        
        assertEquals(expResult, result);
        System.out.println(result);
        
        
        System.out.println(D2 
                +"\nConditions: Invalid login credentials.");
        accountService.setCurrentAccount(0);
        username = "pDownton";
        password = "pd09";
        
        expResult = "Invalid credentials.";
        result = accountService.login(username, password);
        
        assertEquals(expResult, result);
        System.out.println(result);
    }//testLogin()

    /**
     * Test of logout method, of class AccountService.
     */
    @Test
    public void testLogout() {
        System.out.println(D1 
                + "\nMethod: logout"
                + "\nConditions: Not logged in.");
        accountService.setCurrentAccount(0);
        
        String expResult = "Could not log out.";
        String result = accountService.logout();
        
        assertEquals(expResult, result);
        System.out.println(result);
        
        System.out.println(D2 
                + "\nConditions: Logged in.");
        accountService.setCurrentAccount(10);
        
        expResult = "Successfully logged out.";
        result = accountService.logout();
        
        assertEquals(expResult, result);
        System.out.println(result);
        
    }//testLogout()

    /**
     * Test of createRequest method, of class AccountService.
     */
    @Test
    public void testCreateRequest() {
        System.out.println(D1 
                + "\nMethod: createRequest"
                + "\nConditions: Valid request");
        accountService.setCurrentAccount(6437);
        Reimbursement r = new Reimbursement(100.58F, "Test input", accountService.getCurrentAccount().getId());
        
        String expResult = "Successfully created reimbursement request.";
        String result = accountService.createRequest(r);
        
        assertEquals(expResult, result);
        System.out.println(result);
        accountService.setCurrentAccount(0);
    }//testCreateRequest()

    /**
     * Test of showReimbursements method, of class AccountService.
     */
    @Test
    public void testShowReimbursements() {
        System.out.println(D1
                + "\nMethod: showReimbursements"
                + "\nConditions: Logged in as a manager.");
        accountService.setCurrentAccount(10);
        
        List<Reimbursement> result = accountService.showReimbursements();
        
        assertNotNull(result);
        result.forEach(r -> {
            System.out.println(r.toString());
        });
        
        System.out.println(D2 
                + "\nConditions: Logged in as a regular employee.");
        accountService.setCurrentAccount(7688);
        result.clear();
        result = accountService.showReimbursements();
        
        assertNotNull(result);
        result.forEach(r -> {
            System.out.println(r.toString());
        });
        
        System.out.println(D2 
                + "\nConditions: Not logged in.");
        accountService.setCurrentAccount(0);
        
        result = accountService.showReimbursements();
        assertNull(result);
        System.out.println("Not logged in.");
        
    }//testShowReimbursements()

    /**
     * Test of updateReimbursement method, of class AccountService.
     */
    @Test
    public void testUpdateReimbursement() {
        System.out.println(D1 
                + "\nMethod: updateReimbursement"
                + "\nConditions: Not a manager.");
        int id = 224530604;
        String newStatus = "Approved";
        accountService.setCurrentAccount(6437);
        
        String expResult = "You are not a manager.";
        String result = accountService.updateReimbursement(id, newStatus);
        
        assertEquals(expResult, result);
        System.out.println(result);
    }//testUpdateReimbursement()

    /**
     * Test of statistics method, of class AccountService.
     */
    @Test
    public void testStatistics() {
        System.out.println(D1 
                + "\nMethod: statistics"
                + "\nConditions: Not logged in.");
        accountService.setCurrentAccount(0);
        
        String expResult = "You are not logged in.";
        String result = accountService.statistics();
        
        assertEquals(expResult, result);
        System.out.println(result);
    }//testStatistics()
    
}//AccountServiceTest()
