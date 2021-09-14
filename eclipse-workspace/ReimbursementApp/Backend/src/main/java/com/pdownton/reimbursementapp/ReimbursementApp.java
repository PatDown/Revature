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
            path("login", () -> {
                get(AccountController::login);
            });
            path("employee/{id}", () ->{
                get(AccountController::getAccount);
                path("requests", () -> {
                    get(ReimbursementController::getAll);
                    post(ReimbursementController::create);
                    path("{rId}", () -> {
                        get(ReimbursementController::getReimbursement);
                        put(ReimbursementController::update);
                    });
                    path("stats", () -> {
                        get(AccountController::statistics); 
                    });
                });
                
            });
        });
        
    }//main(String[])
    
}//ReimbursementApp
