/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pdownton.reimbursementapp.service;

import com.pdownton.reimbursementapp.models.Stats;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Pat Down
 */
public class StatsServiceTest {
    
    public StatsServiceTest() {
    }//StatsServiceTest()

    /**
     * Test of getStats method, of class StatsService.
     */
    @Test
    public void testGetStats() {
        System.out.println("getStats");
        int id = 0;
        StatsService instance = new StatsService();
        Stats expResult = null;
        Stats result = instance.getStats(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }//testGetStats()
    
}//StatsServiceTest
