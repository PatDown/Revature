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
    public final String D1 = "=================================================";
    public final String D2 = "-------------------------------------------------";
    
    public ReimbursementServiceTest() {
        rService.getAll();
        System.out.println(String.format("%s\n%s\nReimbursementServiceTest", D1, D1));
    }//ReimbursementServiceTest()

    /**
     * Test of getReimbursement method, of class ReimbursementService.
     */
    @Test
    public void testGetReimbursement() {
        System.out.println(D1 
                + "\nMethod: getReimbursement"
                + "\nConditions: Valid request id.");
        int id = 718977156;
        
        Reimbursement expResult = ReimbursementService.reimbursements.get(id);
        Reimbursement result = rService.getReimbursement(id);
        
        assertEquals(expResult.getId(), result.getId());
        System.out.println(result.toString());
    }//testGetReimbursement()
    
    /**
     * Test of getReimbursements method, of class ReimbursementService.
     */
    @Test
    public void testGetReimbursements(){
        System.out.println(D1
                + "\nMethod: getReimbursements"
                + "\nConditions: Logged in as a manager.");
        int id = 10;
        
        List<Reimbursement> result = rService.getReimbursements(id);
        
        assertNotNull(result);
        result.forEach(r -> {
            System.out.println(r.toString());
        });
        
        System.out.println(D2 
                + "\nConditions: Logged in as a regular employee.");
        id = 7688;
        result.clear();
        result = rService.getReimbursements(id);
        
        assertNotNull(result);
        result.forEach(r -> {
            System.out.println(r.toString());
        });
        
    }//testGetReimbursements()

    /**
     * Test of create method, of class ReimbursementService.
     */
    @Test
    public void testCreate() {
        System.out.println(D1 
                + "\nMethod: create"
                + "\nConditions: Valid reimbursement.");
        rService.getAll();
        Reimbursement r = new Reimbursement(100.58F, "Test input", 6437);
        
        Reimbursement expResult = r;
        Reimbursement result = rService.create(r);
        
        assertEquals(expResult, result);
        System.out.println(result.toString());
        rService.delete(r);
    }//testCreate()

    /**
     * Test of update method, of class ReimbursementService.
     */
    @Test
    public void testUpdate() {
        System.out.println(D1 
                + "\nMethod: update"
                + "\nConditions: Manager approves");
        int id = 718977156;
        String body = "Approved\nI understand it was hard for you to get a flight, so I think this one should be covered.";
        int managerId = 10;
        
        String expResult = "Request Approved!";
        String result = rService.update(id, body, managerId);
        
        assertEquals(expResult, result);
        System.out.println(result);
        
    }//testUpdate()
    
}//ReimbursementServiceTest
