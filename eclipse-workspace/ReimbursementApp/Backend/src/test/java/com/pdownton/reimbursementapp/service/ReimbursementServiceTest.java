/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pdownton.reimbursementapp.service;

import com.pdownton.reimbursementapp.models.Reimbursement;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Pat Down
 */
public class ReimbursementServiceTest {
    
    public ReimbursementServiceTest() {
    }//ReimbursementServiceTest()

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
    }//testGetReimbursement()

    /**
     * Test of getReimbursements method, of class ReimbursementService.
     */
    @Test
    public void testGetReimbursements() {
        System.out.println("getReimbursements");
        int id = 0;
        ReimbursementService instance = new ReimbursementService();
        List<Reimbursement> expResult = null;
        List<Reimbursement> result = instance.getReimbursements(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }//testGetReimbursements()

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
    }//testGetAll()

    /**
     * Test of create method, of class ReimbursementService.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        float amount = 0.0F;
        String reason = "";
        int employeeID = 0;
        ReimbursementService instance = new ReimbursementService();
        Reimbursement expResult = null;
        Reimbursement result = instance.create(amount, reason, employeeID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }//testCreate()

    /**
     * Test of update method, of class ReimbursementService.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        int id = 0;
        String newStatus = "";
        int managerId = 0;
        String message = "";
        ReimbursementService instance = new ReimbursementService();
        Reimbursement expResult = null;
        Reimbursement result = instance.update(id, newStatus, managerId, message);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }//testUpdate()
    
}//ReimbursementServiceTest
