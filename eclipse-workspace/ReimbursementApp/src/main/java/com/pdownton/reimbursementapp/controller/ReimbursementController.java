package com.pdownton.reimbursementapp.controller;

import com.pdownton.reimbursementapp.service.ReimbursementService;
import java.sql.Connection;

/**
 *
 * @author Pat Down
 */
public class ReimbursementController {

    private static ReimbursementService rService;
    
    public static void init(Connection conn) {
        rService = new ReimbursementService(conn);
    }//init(Connection)

}//ReimbursementController
