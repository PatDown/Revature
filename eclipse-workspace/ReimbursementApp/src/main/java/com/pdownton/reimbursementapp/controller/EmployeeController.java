package com.pdownton.reimbursementapp.controller;

import com.pdownton.reimbursementapp.models.Account;
import com.pdownton.reimbursementapp.service.EmployeeService;
import io.javalin.http.Context;
import java.sql.Connection;
import java.sql.SQLException;


/**
 *
 * @author Pat Down
 */
public class EmployeeController {
    private static EmployeeService employeeService;
    
    public static void init(Connection conn){
        employeeService = new EmployeeService(conn);
    }//init(Connection)
    
    public static void login(Context ctx) throws SQLException{
        String[] credentials = ctx.body().split(",");
        int id = employeeService.login(credentials[0], credentials[1]);
        
        if (id != 0)
            ctx.json("Login Successful");
        else
            ctx.json("Invalid credentials.");
    }//login(Context)
}//EmployeeController
