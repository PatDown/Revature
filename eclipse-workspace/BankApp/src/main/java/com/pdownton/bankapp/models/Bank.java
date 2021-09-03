package com.pdownton.bankapp.models;

import java.util.*;//HashMap, Map

/**
 *
 * @author Pat Down
 */
public class Bank {
    public static final int QUIT = 7;
    private Map<Integer, Client> clients = new HashMap<>();
    public static Map<Account, Client> accounts = new HashMap<>();
    private Client currentClient = null;
    
    public Client getCurrentClient(){
        return currentClient;
    }//getCurrentClient()
    
    public void setCurrentClient(Client client){
        currentClient = client;
    }//setCurrentClient(Client)
    
    public Map<Account, Client> getAccounts(){
        return accounts;
    }//getAccounts()
    
    public void setAccounts(Map<Account, Client> accounts){
        this.accounts = accounts;
    }
    
    public Map<Integer, Client> getClients(){
        return clients;
    }//getClients()
    
    public Client getClient(int id){
        return clients.getOrDefault(id, null);
    }//getClient(int)
    
    public String printClients(){
        StringBuilder clientList = new StringBuilder();
        if (!clients.isEmpty()){
            
            clients.values().forEach(c -> {
                clientList.append(c.toString());
                clientList.append("\n");
            });
            return clientList.toString();
        } else 
           return "No clients.";
    }//printClients()
    
    public Client createClient(String name){
        Client client = new Client(name);
        return client;
    }//createClient(String)
    
    public String addClient(Client client){
        while(clients.containsKey(client.getID()))
            client = new Client(client.getName());
        if (clients.isEmpty())
            currentClient = client;
        clients.put(client.getID(), client);
        return "New client added.";
    }//addClient(Client)
    
    public String removeClient(int id){
        if (clients.containsKey(id)){
            Client client = getClient(id);
            clients.remove(id);
            accounts.entrySet().stream().filter(e -> (e.getValue() == client)).forEachOrdered(e -> {
                accounts.remove(e.getKey());
            });
            if(client == currentClient)
                currentClient = clients.values().stream().findFirst().orElse(null);
            return String.format("Client %d removed.", id);
        } else
            return String.format("Cannot find client with ID %d", id);
    }//removeClient(int)
    
    public String updateClient(int id, String name){
        if (clients.containsKey(id)){
            Client client = clients.get(id);
            client.setName(name);
            clients.replace(client.getID(), client);
            accounts.entrySet().stream().filter(e -> (e.getValue()==client)).forEachOrdered(e -> {
                accounts.replace(e.getKey(), client);
            });
            return String.format("Updated client %d", id);    
        } else
            return String.format("Cannot find client with ID %d" , id );
    }//updateClient(int, String)
    
    public String changeClient(int id){
        if(clients.containsKey(id)){
            if (clients.get(id) == currentClient)
                return String.format("Current client is already client %d", id);
            else {
                int currentID = currentClient.getID();
                currentClient = clients.get(id);
                return String.format("Changed from client %d to client %d.", currentID, id);
            }//else
        } else
            return String.format("Cannot find client with ID %d.", id);
    }//changeClient(int)
    
    public boolean isClient(int id){
        return clients.containsKey(id);
    }//isClient(int)    
    
}//Bank
