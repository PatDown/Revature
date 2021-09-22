package com.pdownton.reimbursementapp.controller;

import com.pdownton.reimbursementapp.models.Stats;
import com.pdownton.reimbursementapp.service.StatsService;
import io.javalin.http.Context;
import io.javalin.http.HttpCode;

/**
 *
 * @author Pat Down
 */
public class StatsController {
    
    private static StatsService sService;
    public static void init() {
        sService = new StatsService();
    }//init()

    public static void getStats(Context ctx){
        int id = Integer.parseInt(ctx.pathParam("id"));
        Stats stats = sService.getStats(id);
        
        if (stats  != null)
            ctx.json(stats);
        else
            ctx.status(HttpCode.UNAUTHORIZED);
    }//getStats(Context)
}//StatsController
