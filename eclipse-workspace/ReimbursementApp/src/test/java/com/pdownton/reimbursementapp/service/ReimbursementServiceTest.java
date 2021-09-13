package com.pdownton.reimbursementapp.service;

import com.pdownton.reimbursementapp.models.Reimbursement;
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
public class ReimbursementServiceTest {
    
    public ReimbursementServiceTest() {
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
     * Test of getReimbursement method, of class ReimbursementService.
     */
    @Test
    public void testGetReimbursement() {
        System.out.println("getReimbursement");
        int id = 0;
        ReimbursementService instance = new ReimbursementService();
        Reimbursement expResult = null;
        Reimbursement result = instance.getReimbursement(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAll method, of class ReimbursementService.
     */
    @Test
    public void testGetAll() {
        System.out.println("getAll");
        ReimbursementService instance = new ReimbursementService();
        List<Reimbursement> expResult = null;
        List<Reimbursement> result = instance.getAll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of create method, of class ReimbursementService.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        Reimbursement reimbursement = null;
        ReimbursementService instance = new ReimbursementService();
        Reimbursement expResult = null;
        Reimbursement result = instance.create(reimbursement);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateStatus method, of class ReimbursementService.
     */
    @Test
    public void testUpdateStatus() {
        System.out.println("updateStatus");
        int id = 0;
        String status = "";
        int managerId = 0;
        ReimbursementService instance = new ReimbursementService();
        String expResult = "";
        String result = instance.updateStatus(id, status, managerId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
