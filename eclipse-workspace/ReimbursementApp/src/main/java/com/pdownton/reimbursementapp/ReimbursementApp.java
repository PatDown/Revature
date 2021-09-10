package com.pdownton.reimbursementapp;

import com.pdownton.reimbursementapp.controller.AccountController;
import com.pdownton.reimbursementapp.controller.ReimbursementController;
import com.pdownton.reimbursementapp.utils.ConnectionFactory;
import io.javalin.Javalin;
import java.sql.Connection;

/**
 *
 * @author Pat Down
 */
public class ReimbursementApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Javalin app = Javalin.create().start(3000);
        Connection conn = ConnectionFactory.getConnection();
        AccountController.init(conn);
        ReimbursementController.init(conn);
        
    }//main(String[])
    
}//ReimbursementApp
