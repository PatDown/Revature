package com.pdownton.reimbursementapp.controller;

import com.pdownton.reimbursementapp.models.Account;
import com.pdownton.reimbursementapp.service.AccountService;
import com.pdownton.reimbursementapp.models.Stats;
import io.javalin.http.Context;
import io.javalin.http.HttpCode;
import java.util.List;

/**
 *
 * @author Pat Down
 */
public class AccountController {
    private static AccountService accountService;
    
    public static void init(){
        accountService = new AccountService();
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
            ctx.json(account.getName());
        else
            ctx.status(HttpCode.NOT_FOUND);
    }//getAccount(Context)
    
    public static void login(Context ctx){
        int id = accountService.login(ctx.formParam("username"), ctx.formParam("password"));
        
        Account account = accountService.getAccount(id);
        if (account != null){
            ctx.json(id);
        } else
            ctx.status(HttpCode.NOT_FOUND);
        
    }//login(Context)
    
    public static void logout(Context ctx){
        boolean loggedOut = accountService.logout();
        
        if (loggedOut){
            ctx.redirect("reimbursements/login");
            ctx.status(HttpCode.OK);
            ctx.json("Logged out");
        } else
            ctx.status(HttpCode.EXPECTATION_FAILED);
        
    }//logout(Context)
    
}//AccountController
