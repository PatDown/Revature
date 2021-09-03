package com.pdownton.bankapp.controller;

import com.pdownton.bankapp.service.AccountService;
import io.javalin.Javalin;
import static io.javalin.apibuilder.ApiBuilder.delete;
import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.patch;
import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.post;
import io.javalin.http.Context;
import java.sql.Connection;

/**
 *
 * @author Pat Down
 */
public class AccountController {
    private static AccountService accountService;
    private static Javalin javalin;
    
    public static void init(Javalin app, Connection conn){
        javalin = app;
        accountService.init(conn);
        javalin.routes(() -> {
            path("accounts", () ->{
               get(AccountController::getAccounts);
               post(AccountController::create);
               path(":accNum", () -> {
                  get(AccountController::getAccount);
                  patch(AccountController::withdraw);
                  patch(AccountController::deposit);
                  delete(AccountController::removeAccount);
               });
            });
        });
    }//init(Javalin, Connection)
    
    public static void create(Context ctx){
       
    }//create(Context)
    
    public static void getAccounts(Context ctx){
        
    }//getAccounts(Context)
    
    public static void getAccount(Context ctx){
        
    }//getAccount(Context)
    
    public static void withdraw(Context ctx) {
        if (balance < amount)
            return "Not enough money in account.";
        else {
            balance -= amount;
            return String.format("New balance: $%.2f", balance);
        }//else
    }//withdraw(Context)
    
    public static void deposit(Context ctx){
        balance += amount;
        return String.format("New balance: $%.2f", balance);
    }//deposit(Context)
    
    public static void removeAccount(Context ctx) {
        
    }//removeAccount(Context)
}//AccountController
