package com.pdownton.bankapp.controller;

import com.pdownton.bankapp.models.Account;
import com.pdownton.bankapp.service.AccountService;
import io.javalin.http.Context;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Pat Down
 */
public class AccountController {
    private static AccountService accountService;
    
    public static void init(Connection conn){
        accountService = new AccountService(conn);
    }//init(Connection)
    
    public static void create(Context ctx)throws SQLException {
        String[] accountData = ctx.body().split(", ");
        accountService.create(accountData);
        ctx.status(201);
    }//create(Context)
    
    public static void getAccounts(Context ctx){
        List<Account> rows = accountService.getAccounts(Integer.parseInt(ctx.pathParam("id")));
        ctx.json(rows);
        ctx.status(201);
    }//getAccounts(Context)
    
    public static void getAccount(Context ctx) throws SQLException{
        Account row = accountService.getAccount(ctx.pathParam("num"));
        if (row == null)
            ctx.status(404);
        else
            ctx.json(row);
    }//getAccount(Context)
    
    public static void removeAccount(Context ctx) throws SQLException {
        Account row = accountService.getAccount(ctx.pathParam("num"));
        boolean result = accountService.removeAccount(row.getNumber());
        if (result)
            ctx.status(404);
        else
            ctx.status(205);
    }//removeAccount(Context)
    
    /*public static void withdraw(Context ctx) {
        
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
    */
    
    
}//AccountController
