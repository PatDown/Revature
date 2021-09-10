package com.pdownton.reimbursementapp.controller;

import com.pdownton.reimbursementapp.service.ReimbursementService;
import io.javalin.http.Context;
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

    public static void getReimbursements(Context ctx) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}//ReimbursementController
