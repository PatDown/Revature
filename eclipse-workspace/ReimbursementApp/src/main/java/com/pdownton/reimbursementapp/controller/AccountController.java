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
        accountService.logout();
        ctx.status(HttpCode.OK);
    }//logout(Context)
    
    public static void createRequest(Context ctx){
        Reimbursement r = ctx.bodyAsClass(Reimbursement.class);
        int id = Integer.parseInt(ctx.pathParam("id"));
        r.setEmployeeId(id);
        Reimbursement r1 = accountService.createRequest(r);
        if (r == r1) {
            ctx.status(HttpCode.CREATED);
            ctx.json(r);
        } else
            ctx.status(HttpCode.NOT_MODIFIED);
    }//createRequest(Context)
    
    public static void getReimbursements(Context ctx){
        List<Reimbursement> rList = accountService.getReimbursements();
        if (!rList.isEmpty()){
            ctx.status(HttpCode.FOUND);
            ctx.json(rList);
        }else
            ctx.status(HttpCode.NOT_FOUND);
    }//getReimbursements(Context)
    
    public static void updateReimbursement(Context ctx){
        int rId = Integer.parseInt(ctx.pathParam("rId"));
        boolean result = accountService.updateReimbursement(rId, ctx.body());
        
        if (result)
            ctx.status(HttpCode.ACCEPTED);
        else
            ctx.status(HttpCode.NOT_MODIFIED);
    }//updateReimbursement(Context)
    
    public static void statistics(Context ctx){
        String stats = accountService.statistics();
        ctx.json(stats);
    }//statistics(Context)
    
}//AccountController
