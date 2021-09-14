package com.pdownton.reimbursementapp.service;

import com.pdownton.reimbursementapp.models.Reimbursement;
import com.pdownton.reimbursementapp.utils.ConnectionFactory;
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
