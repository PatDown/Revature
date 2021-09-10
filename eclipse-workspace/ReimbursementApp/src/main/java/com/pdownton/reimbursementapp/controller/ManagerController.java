package com.pdownton.reimbursementapp.controller;

import com.pdownton.reimbursementapp.service.ManagerService;
import io.javalin.http.Context;
import java.sql.Connection;

/**
 *
 * @author Pat Down
 */
public class ManagerController {
    private static ManagerService managerService;
    
    public static void init(Connection conn) {
        managerService = new ManagerService(conn);
    }//init(Connection)
    
    public static void login(Context ctx){
        
    }//login(Context)

}//ManagerController
