package com.pdownton.bankapp.controller;

import com.pdownton.bankapp.models.Client;
import com.pdownton.bankapp.service.ClientService;
import io.javalin.Javalin;
import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.post;
import static io.javalin.apibuilder.ApiBuilder.put;
import io.javalin.http.Context;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Pat Down
 */

public class ClientController {
    
    private static ClientService clientService;
    private static Javalin javalin;
    
    public static void init(Javalin app, Connection conn){
        javalin = app;
        clientService.init(conn);
        javalin.routes(() -> {
            path("clients", () -> {
                post(ClientController::create);
                get(ClientController::getClients);
                path(":id", () -> {
                    get(ClientController::getClient);
                    put(ClientController::updateClient);
                    delete(ClientController::removeClient);
                });
            });
        });
    }//init(Connection)
    
    //@RequestMapping("/create")
    public static void create(Context ctx) throws SQLException{
        Client row = ctx.bodyAsClass(Client.class);
        clientService.create(row.getName());
        ctx.status(201);
    }//create(Context)
    
    public static void getClients(Context ctx) throws SQLException{
        
    }//getClients(Context)
    
    public static void getClient(Context ctx)throws SQLException {
        Client row = clientService.getClient(Integer.parseInt(ctx.pathParam("id")));
        ctx.json(row);
    }//getClient(Context)
    
    //@RequestMapping("/addAccount")
    public String addAccount(int id, String name, float balance, String type, float interest) throws SQLException{
        boolean result = clientService.addAccount(id, name, balance, type, interest);
        
        if (result)
            return "Account added to client " + id;
        else
            return "Cannot add account to client " + id;
    }//addAccount(int, String, float, String, float)
    
    //@RequestMapping("/deleteAccount")
    public String deleteAccount(int id, String accNum) throws SQLException{
        boolean result = clientService.deleteAccount(id, accNum);
        if (result)
            return String.format("Account #%s removed from client %d", accNum, id);
        else
            return String.format("Client %d does not own account #%s.", id, accNum);
    }//deleteAccount(int, String)
    
    //@RequestMapping("/changeAccount")
    public String changeAccount(int id, String accNum) throws SQLException{
        boolean result = clientService.deleteAccount(id, accNum);
        if (result)
            return String.format("Switched to account #%s", accNum);
        else
            return String.format("Account #%s could not be found.", accNum);
    }//changeAccount(String)
    
    public static void updateClient(Context ctx) throws SQLException {
        
    }//updateClient(Context)
    
    public static void removeClient(Context ctx) {
        if (clients.containsKey(id)){
            Client client = getClient(id);
            clients.remove(id);
            accounts.entrySet().stream().filter(e -> (e.getValue() == client)).forEachOrdered(e -> {
                accounts.remove(e.getKey());
            });
            if(client == currentClient)
                currentClient = clients.values().stream().findFirst().orElse(null);
            //return String.format("Client %d removed.", id);
        } 
    }//removeClient(int)
    
    //@RequestMapping("/transfer")
    public String transfer(int id, String accNum1, String accNum2, float amount) throws SQLException {
        
        boolean result = clientService.transfer(id, accNum1, accNum2, amount);
        
        if (result)
            return String.format("Transferred $%.2f from %s to %s.", amount, accNum1, accNum2); 
        else
            return "Cannot complete transfer";
    }//transfer(Account, Account, float)
    
}//ClientController
