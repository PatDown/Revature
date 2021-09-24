package com.pdownton.reimbursementapp.controller;

import com.pdownton.reimbursementapp.models.Reimbursement;
import com.pdownton.reimbursementapp.service.ReimbursementService;
import io.javalin.http.Context;
import io.javalin.http.HttpCode;
import java.util.List;

/**
 *
 * @author Pat Down
 */
public class ReimbursementController {

    private static ReimbursementService rService;
    
    public static void init() {
        rService = new ReimbursementService();
    }//init()

    public static void getReimbursement(Context ctx){
        int rId = Integer.parseInt(ctx.pathParam("rId"));

        Reimbursement reimbursement = rService.getReimbursement(rId);
        if (reimbursement != null)
            ctx.json(reimbursement);
        else
            ctx.status(HttpCode.NOT_FOUND);
        
    }//getReimbursement(Context)
    
    public static void getAll(Context ctx){
        List<Reimbursement> rList = rService.getAll();
        
        if (!rList.isEmpty())
            ctx.json(rList);
        else
            ctx.status(HttpCode.NOT_FOUND);
    }//getAll(Context)
    
    public static void getReimbursements(Context ctx){
        int id = Integer.parseInt(ctx.pathParam("id"));
        List<Reimbursement> rList = rService.getReimbursements(id);
        
        if (!rList.isEmpty())
            ctx.json(rList);
        else
            ctx.status(HttpCode.NOT_FOUND);
    }//getReimbursements(Context)
    
    public static void create(Context ctx){
        int id = Integer.parseInt(ctx.pathParam("id"));
        float amount = Float.parseFloat(ctx.formParam("amount"));
        String reason = ctx.formParam("reason");
        Reimbursement reimbursement = rService.create(amount, reason, id);
        
        ctx.json(reimbursement);
        ctx.status(HttpCode.OK);
    }//create(Context)

    public static void update(Context ctx){
        int id = Integer.parseInt(ctx.pathParam("id"));
        int rId = Integer.parseInt(ctx.pathParam("rId"));
        String newStatus = ctx.formParam("new_status");
        String message = ctx.formParam("message");
        Reimbursement reimbursement = rService.update(rId, newStatus, id, message);
        
        ctx.json(reimbursement);        
        
    }//update(Context)
}//ReimbursementController
