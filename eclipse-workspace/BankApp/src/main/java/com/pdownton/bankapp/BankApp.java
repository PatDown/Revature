package com.pdownton.bankapp;

import com.pdownton.bankapp.controller.AccountController;
import com.pdownton.bankapp.controller.ClientController;
import io.javalin.Javalin;
import static io.javalin.apibuilder.ApiBuilder.delete;
import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.post;
import static io.javalin.apibuilder.ApiBuilder.put;
import java.sql.Connection;
import java.util.Scanner;

public class BankApp {
    public static Scanner input = new Scanner(System.in);
    public static final String DIVIDER = "=========================================";
    public static void main(String[] args) {
        
        Javalin app = Javalin.create().start(3000);
        Connection conn = ConnectionFactory.getConnection();
        ClientController.init(conn);
        AccountController.init(conn);
        
        app.routes(() -> {
            path("clients", () -> {
                post(ClientController::create);
                get(ClientController::getClients);
                path("{id}", () -> {
                    get(ClientController::getClient);
                    put(ClientController::updateClient);
                    delete(ClientController::removeClient);
                    path("accounts", () -> {
                        get(ClientController::getAccounts);
                        post(AccountController::create);
                        path("{num}", () -> {
                            get(AccountController::getAccount);
                            //patch(AccountController::withdraw);
                            //patch(AccountController::deposit);
                            delete(AccountController::removeAccount);
                        });
                        
                    });
                    /*path("accounts{lessThan}{greaterThan}", () -> {
                        get(ClientController::findAccounts);
                    });*/
                });
            });
        });
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
