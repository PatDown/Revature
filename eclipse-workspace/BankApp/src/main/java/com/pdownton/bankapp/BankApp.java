package com.pdownton.bankapp;

import com.pdownton.bankapp.controller.AccountController;
import com.pdownton.bankapp.controller.ClientController;
import io.javalin.Javalin;
import java.sql.Connection;
import java.util.Scanner;

public class BankApp {
    public static Scanner input = new Scanner(System.in);
    public static final String DIVIDER = "=========================================";
    public static void main(String[] args) {
        
        Javalin app = Javalin.create().start(3000);
        Connection conn = ConnectionFactory.getConnection();
        ClientController.init(app, conn);
        AccountController.init(app, conn);
        app.get("/exception", ctx -> {
            throw new Exception("test");
        });
        
        app.exception(Exception.class, (e, ctx) -> {
           System.out.println("exception: " + e + " - " + ctx);
           ctx.status(500);
        });
        
        app.error(500, "html", ctx -> {
            ctx.result("500 Internal Server Error!!!");
        });
        
    }//main()
     
}//BankAppStart
