package com.pdownton.reimbursementapp.service;

import com.pdownton.reimbursementapp.models.Account;
import com.pdownton.reimbursementapp.models.Reimbursement;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Pat Down
 */
public class AccountServiceTest {
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
        
        Account expResult = AccountService.accounts.get(10);
        Account result = accountService.login(username, password);
        
        assertEquals(expResult, result);
        System.out.println(result.toString());
        
        System.out.println(D2 
                +"\nConditions: Invalid login credentials.");
        accountService.setCurrentAccount(0);
        username = "pDownton";
        password = "pd09";
        
        result = accountService.login(username, password);
        for (Account a : AccountService.accounts.values())
            assertNotEquals(a, result);
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
        
        System.out.println(D2 
                + "\nConditions: Logged in as normal employee.");
        accountService.setCurrentAccount(7688);
        
        expResult = "You are not a manager.";
        result = accountService.statistics();
        
        assertEquals(expResult, result);
        System.out.println(result);
        
        System.out.println(D2 
                + "\nConditions: Logged in as manager.");
        accountService.setCurrentAccount(10);
        
        expResult = "Mean: $536.28\nBiggest Spender: John Paul Key\n";
        result = accountService.statistics();
        
        assertEquals(expResult, result);
        System.out.println(result);
    }//testStatistics()
    
}//AccountServiceTest()
