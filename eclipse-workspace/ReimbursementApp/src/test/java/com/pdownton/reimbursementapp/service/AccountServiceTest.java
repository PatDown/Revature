package com.pdownton.reimbursementapp.service;

import com.pdownton.reimbursementapp.models.Account;
import com.pdownton.reimbursementapp.models.Reimbursement;
import com.pdownton.reimbursementapp.utils.ConnectionFactory;
import java.util.List;
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
public class AccountServiceTest {
    public static AccountService accountService = new AccountService(ConnectionFactory.getConnection());
    public AccountServiceTest() {
    }//AccountServiceTest()
    
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
     * Test of login method, of class AccountService.
     */
    @Test
    public void testLogin() {
        System.out.println("login");
        String username = "pDownton";
        String password = "pd10";
        accountService.getAccounts();
        String expResult = "Hello Patrick Downton!";
        String result = accountService.login(username, password);
        assertEquals(expResult, result);
    }//testLogin()

    /**
     * Test of logout method, of class AccountService.
     */
    @Test
    public void testLogout() {
        System.out.println("logout");
        AccountService instance = new AccountService();
        instance.logout();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isLoggedIn method, of class AccountService.
     */
    @Test
    public void testIsLoggedIn() {
        System.out.println("isLoggedIn");
        AccountService instance = new AccountService();
        boolean expResult = false;
        boolean result = instance.isLoggedIn();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createRequest method, of class AccountService.
     */
    @Test
    public void testCreateRequest() {
        System.out.println("createRequest");
        Reimbursement r = null;
        AccountService instance = new AccountService();
        Reimbursement expResult = null;
        Reimbursement result = instance.createRequest(r);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getReimbursements method, of class AccountService.
     */
    @Test
    public void testGetReimbursements() {
        System.out.println("getReimbursements");
        AccountService instance = new AccountService();
        List<Reimbursement> expResult = null;
        List<Reimbursement> result = instance.getReimbursements();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateReimbursement method, of class AccountService.
     */
    @Test
    public void testUpdateReimbursement() {
        System.out.println("updateReimbursement");
        int id = 0;
        String newStatus = "";
        AccountService instance = new AccountService();
        boolean expResult = false;
        boolean result = instance.updateReimbursement(id, newStatus);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of statistics method, of class AccountService.
     */
    @Test
    public void testStatistics() {
        System.out.println("statistics");
        AccountService instance = new AccountService();
        String expResult = "";
        String result = instance.statistics();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
