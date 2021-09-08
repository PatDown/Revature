package com.pdownton.bankapp.controller;

import com.pdownton.bankapp.models.Account;
import com.pdownton.bankapp.models.Client;
import com.pdownton.bankapp.service.ClientService;
import io.javalin.http.Context;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Pat Down
 */

public class ClientController {
    
    private static ClientService clientService;
    
    public static void init(Connection conn){
        clientService = new ClientService(conn);
        
    }//init(Connection)
    
    public static void create(Context ctx) throws SQLException{
        Client row = new Client(ctx.body());
        clientService.create(row);
        //clientService.create(row.getName());
        
        ctx.status(201);
    }//create(Context)
    
    public static void getClients(Context ctx) throws SQLException{
        List<Client> rows = clientService.getClients();
        
        ctx.json(rows);
        ctx.status(200);
    }//getClients(Context)
    
    public static void getClient(Context ctx)throws SQLException {
        Client row = clientService.getClient(Integer.parseInt(ctx.pathParam("id")));
        
        if (row == null)
            ctx.status(404);
        else {
            ctx.result(String.format("%d", row.getID()));
            ctx.json(row);
        }
    }//getClient(Context)
    
    public static void updateClient(Context ctx) throws SQLException {
        Client row = clientService.getClient(Integer.parseInt(ctx.pathParam("id")));
        String update = ctx.body();
        boolean result = clientService.updateClient(row.getID(), update);
        
        if (!result)
            ctx.status(404);
    }//updateClient(Context)
    
    public static void removeClient(Context ctx) throws SQLException{
        Client row = clientService.getClient(Integer.parseInt(ctx.pathParam("id")));
        
        boolean result = clientService.removeClient(row.getID());
        
        if (result)
            ctx.status(205);
        else
            ctx.status(404);
    }//removeClient(int)
    
    public static void getAccounts(Context ctx) throws SQLException{
        Client row = clientService.getClient(Integer.parseInt(ctx.pathParam("id")));
        AccountController.getAccounts(ctx);
    }
    
    public static void findAccounts(Context ctx){
        Client row = clientService.getClient(Integer.parseInt(ctx.pathParam("id")));
        float[] bounds = {Float.parseFloat(ctx.pathParam("lessThan")), Float.parseFloat(ctx.pathParam("greaterThan"))};
        Collection<Account> accounts = clientService.findAccounts(row, bounds);
        
        if (accounts.isEmpty())
            ctx.status(404);
        else
            ctx.json(accounts);
    }//findAccounts(Context)
//    //@RequestMapping("/transfer")
//    public String transfer(int id, String accNum1, String accNum2, float amount) throws SQLException {
//        
//        boolean result = clientService.transfer(id, accNum1, accNum2, amount);
//        
//        if (result)
//            return String.format("Transferred $%.2f from %s to %s.", amount, accNum1, accNum2); 
//        else
//            return "Cannot complete transfer";
//    }//transfer(Account, Account, float)
//    
}//ClientController
