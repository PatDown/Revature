package com.pdownton.bankapp.models;

import com.pdownton.bankapp.BankApp;
import java.util.*;//HashMap, Map


/**
 *
 * @author Pat Down
 */
public class Bank {
    public static final int QUIT = 7;
    public final Map<Integer, Client> clients = new HashMap<>();
    public static final Map<Account, Client> accounts = new HashMap<>();
    private Client currentClient = null;
    
    public void init() {
        //menu();
    }//init()
    
    public Client getCurrentClient(){
        return currentClient;
    }//getCurrentClient()
    
    public void setCurrentClient(Client client){
        currentClient = client;
    }//setCurrentClient(Client)
    
    public Map<Account, Client> getAccounts(){
        return accounts;
    }//getAccounts()
    
    public Map<Integer, Client> getClients(){
        return clients;
    }//getClients()
    
    public Client getClient(int id){
        return getClients().getOrDefault(id, null);
    }//getClient(int)
    
    public String printClients(){
        StringBuilder clientList = new StringBuilder();
        if (!getClients().isEmpty()){
            getClients().values().forEach(c -> {
                clientList.append(c.toString());
                clientList.append("\n");
            });
        } else 
           return "No clients.";
        return clientList.toString();
    }//printClients()
    
    public Client createClient(String name){
        Client client = new Client(name);
        //addClient(client);
        return client;
    }//createClient()
    
    public void addClient(Client client){
        while(isClient(client.getID()))
            client = new Client(client.getName());
        if (getClients().isEmpty())
            setCurrentClient(client);
        getClients().put(client.getID(), client);
    }//addClient(Client)
    
    public void removeClient(int id){
        if (isClient(id)){
            Client client = getClient(id);
            getClients().remove(id);
            getAccounts().entrySet().stream().filter(e -> (e.getValue() == client)).forEachOrdered(e -> {
                getAccounts().remove(e.getKey());
            });
            if(client == getCurrentClient())
                setCurrentClient(getClients().values().stream().findFirst().orElse(null));
        } else
            System.out.println("Cannot find client with ID " + id +". Returning to menu.");
    }//removeClient(int)
    
//    public int clientUpdateMenu(){
//        System.out.println("What needs updating?\n"
//               + "1. Name\n"
//               + "2. Back\n"
//               + "Enter choice:");
//        int choice = BankApp.input.nextInt();
//        return choice;
//    }//clientUpdateMenu()
    
    public void updateClient(int id){
        if (isClient(id)){
            Client client = getClients().get(id);
            //clientUpdateMenu();
            int choice = BankApp.input.nextInt();
            switch(choice){
                case 1:
                    BankApp.input.nextLine();
                    System.out.println("Enter new name:");
                    client.setName(BankApp.input.nextLine().trim());
                    getClients().replace(client.getID(), client);
                    getAccounts().entrySet().stream().filter(e -> (e.getValue()==client)).forEachOrdered(e -> {
                        getAccounts().replace(e.getKey(), client);
                    });
                    break;
                case 2:
                    break;
                default:
                    System.out.println("Invalid input. Please try again.");
                    updateClient(id);
                    break;
            }//switch(BankApp.input.nextInt())
        } else
            System.out.println("Cannot find client with ID " + id +". Returning to menu.");
    }//updateClient(int)
    
    public void changeClient(int id){
        if(isClient(id))
            setCurrentClient(getClients().get(id));
        else
            System.out.println("Cannot find client with ID " + id +". Returning to menu.");
    }//changeClient(int)
    
    public boolean isClient(int id){
        return getClients().containsKey(id);
    }//isClient(int)
    
//    public void menu(){
//        System.out.println(BankApp.DIVIDER);
//        if (getCurrentClient() != null)
//            System.out.println(getCurrentClient().toString());
//        
//        System.out.println("Main menu\n"
//                + "1. Client Menu\n"
//                + "2. Add new client\n"
//                + "3. Remove client\n"
//                + "4. Change client\n"
//                + "5. Update client\n"
//                + "6. View clients\n"
//                + "7. Exit\n"
//                + "Enter selection:");
//        int choice;
//        
//        try {
//            choice = BankApp.input.nextInt();
//        } catch (InputMismatchException e){
//            System.out.println("Invalid input");
//            choice = 0;
//        }//catch
//        
//        switch(choice){
//            case 1:
//                if (getCurrentClient() == null)
//                    System.out.println("No clients.");
//                else
//                    getCurrentClient().clientMenu();
//                break;
//            case 2:
//                createClient();
//                break;
//            case 3:
//                printClients();
//                if(!getClients().isEmpty()){
//                    System.out.println("Enter the ID of the client");
//                    removeClient(BankApp.input.nextInt());
//                }//if(!getClients().isEmpty())
//                break;
//            case 4:
//                printClients();
//                if(!getClients().isEmpty()){
//                    System.out.println("Enter the ID of the client");
//                    changeClient(BankApp.input.nextInt());
//                }//if(!getClients().isEmpty())
//                break;
//            case 5:
//                printClients();
//                if(!getClients().isEmpty()){
//                    System.out.println("Enter the ID of the client");
//                    updateClient(BankApp.input.nextInt());
//                }//if(!getClients().isEmpty())
//                break;
//            case 6:
//                printClients();
//                break;
//            case QUIT:
//                System.out.println("Please come again!");
//                break;
//            default:
//                System.out.println("Invalid input. Please try again.");
//                break;
//        }//switch(choice)
//        
//        if (choice != QUIT)
//            menu();
//    }//menu()
    
    
}//Bank
