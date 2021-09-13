package com.pdownton.reimbursementapp.controller;

import com.pdownton.reimbursementapp.models.Account;
import com.pdownton.reimbursementapp.models.Reimbursement;
import com.pdownton.reimbursementapp.service.AccountService;
import io.javalin.http.Context;
import io.javalin.http.HttpCode;
import java.sql.Connection;
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
    
    public static void getAccounts(Context ctx) {
        List<Account> accounts = accountService.getAccounts();
        
        if (!accounts.isEmpty())
            ctx.json(accounts);
        else
            ctx.status(HttpCode.NOT_FOUND);
    }//getAccounts(Context)
    
    public static void getAccount(Context ctx){
        int id = Integer.parseInt(ctx.pathParam("id"));
        Account account = accountService.getAccount(id);
        
        if (account != null)
            ctx.json(account);
        else
            ctx.status(HttpCode.NOT_FOUND);
    }//getAccount(Context)
    
    public static void login(Context ctx){
        String[] credentials = ctx.body().split(",");
        String message = accountService.login(credentials[0], credentials[1]);
        
        ctx.json(message);
    }//login(Context)
    
    public static void logout(Context ctx){
        String message = accountService.logout();
        ctx.json(message);
        ctx.status(HttpCode.OK);
    }//logout(Context)
    
    public static void statistics(Context ctx){
        String stats = accountService.statistics();
        ctx.json(stats);
    }//statistics(Context)
    
}//AccountController
