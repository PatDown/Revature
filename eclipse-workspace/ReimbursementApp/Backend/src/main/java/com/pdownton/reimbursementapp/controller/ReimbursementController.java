package com.pdownton.reimbursementapp.controller;

import com.pdownton.reimbursementapp.models.Reimbursement;
import com.pdownton.reimbursementapp.service.ReimbursementService;
import io.javalin.http.Context;
import io.javalin.http.HttpCode;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author Pat Down
 */
public class ReimbursementController {

    private static ReimbursementService rService;
    
    public static void init(Connection conn) {
        rService = new ReimbursementService(conn);
    }//init(Connection)

    public static void getReimbursement(Context ctx){
        int rId = Integer.parseInt(ctx.pathParam("rId"));
        Reimbursement reimbursement = rService.getReimbursement(rId);
        if (reimbursement == null)
            ctx.status(HttpCode.NOT_FOUND);
        else
            ctx.status(HttpCode.FOUND);
        
    }//getReimbursement(Context)
    
    public static void getAll(Context ctx){
        int id = Integer.parseInt(ctx.pathParam("id"));
        List<Reimbursement> rList = rService.getAll(id);
        
        if (!rList.isEmpty())
            ctx.json(rList);
        else
            ctx.status(HttpCode.NOT_FOUND);
    }//getAll(Context)
    
    public static void create(Context ctx){
        int id = Integer.parseInt(ctx.pathParam("id"));
        Reimbursement reimbursement = ctx.bodyAsClass(Reimbursement.class);
        reimbursement.setEmployeeId(id);
        rService.create(reimbursement);
    }//create(Context)

    public static void update(Context ctx){
        int id = Integer.parseInt(ctx.pathParam("id"));
        int rId = Integer.parseInt(ctx.pathParam("rId"));
        
        String status = rService.updateStatus(rId, ctx.body(), id);
    }//update(Context)
}//ReimbursementController
