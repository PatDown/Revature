package com.pdownton.reimbursementapp;

import com.pdownton.reimbursementapp.controller.AccountController;
import com.pdownton.reimbursementapp.controller.ReimbursementController;
import com.pdownton.reimbursementapp.utils.ConnectionFactory;
import io.javalin.Javalin;
import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.post;
import static io.javalin.apibuilder.ApiBuilder.put;
import java.sql.Connection;

/**
 *
 * @author Pat Down
 */
public class ReimbursementApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Javalin app = Javalin.create().start(3000);
        Connection conn = ConnectionFactory.getConnection();
        AccountController.init(conn);
        ReimbursementController.init(conn);
        
        app.routes(() -> {
            path("reimbursements", () -> {
                path("login", () -> {
                    post(AccountController::login);
                    get(AccountController::logout);
                });
                path("employee", () -> {
                    get(AccountController::getAccounts);
                    path("{id}", () ->{
                        get(AccountController::getAccount);
                        path("requests", () -> {
                            get(ReimbursementController::getReimbursements);
                            post(ReimbursementController::create);
                            path("{rId}", () -> {
                                get(ReimbursementController::getReimbursement);
                                post(ReimbursementController::update);
                            });
                            path("stats", () -> {
                                get(AccountController::statistics); 
                            });
                        });
                    });
                });
            });
        });
        
        app.after(ctx -> {
            ctx.res.addHeader("Access-Control-Allow-Origin", "null");
            ctx.res.addHeader("Access-Control-Allow-Methods", "POST, GET, DELETE, OPTIONS, PUT, PATCH");
            ctx.res.addHeader("Access-Control-Allow-Headers", "append,delete,entries,foreach,get,has,keys,set,values,Authorization");
        });
    }//main(String[])
    
}//ReimbursementApp
