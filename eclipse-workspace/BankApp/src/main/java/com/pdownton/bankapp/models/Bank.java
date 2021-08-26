package com.pdownton.bankapp.models;

import com.pdownton.bankapp.BankApp;
import java.util.*;//HashMap, Map

/**
 *
 * @author Pat Down
 */
public class Bank {
    public static final int QUIT = 7;
    public Map<Integer, Client> clients = new HashMap<>();
    public static Map<Account, Client> accounts = new HashMap<>();
    private Client currentClient;
    
    public void init() {
        menu();
    }//init()
    
    public Client getCurrentClient(){
        return currentClient;
    }//getCurrentClient()
    
    public void setCurrentClient(Client client){
        currentClient = client;
    }//setCurrentClient(Client)
    
    public Map<Integer, Client> getClients(){
        return clients;
    }//getClients()
    
    public Client getClient(int id){
        return getClients().get(id);
    }//getClient(int)
    
    public void printClients(){
        if (!getClients().isEmpty()){
            getClients().values().forEach(c -> {
                System.out.println(c.toString());
            });
        } else 
            System.out.println("No clients. Returning to menu.");
    }//printClients()
    
    public void createClient(){
        System.out.println("Enter a name:");
        String name = BankApp.input.nextLine().trim();
        Client client = new Client(name);
        addClient(client);
    }//createClient()
    
    public void addClient(Client client){
        while(isClient(client.getID())){
            client = new Client(client.getName());
        }//while(isClient(client.getID()))
        if (getClients().isEmpty())
            setCurrentClient(client);
        getClients().put(client.getID(), client);
    }//addClient(Client)
    
    public void removeClient(int id){
        if (isClient(id))
            getClients().remove(id);
        else
            System.out.println("Cannot find client with ID " + id +". Returning to menu.");
    }//removeClient(int)
    
    public void clientUpdateMenu(){
        System.out.println("What needs updating?\n"
                + "1. Name\n"
                + "2. Back\n"
                + "Enter choice:");
    }//clientUpdateMenu()
    
    public void updateClient(int id){
        if (isClient(id)){
            Client client = getClients().get(id);
            clientUpdateMenu();
            switch(BankApp.input.nextInt()){
                case 1:
                    System.out.println("Enter new name:");
                    client.setName(BankApp.input.nextLine().trim());
                    break;
                case 2:
                    break;
                default:
                    System.out.println("Invalid input. Please try again.");
                    updateClient(id);
                    break;
            }//switch(BankApp.input.nextInt())
        } else {
            System.out.println("Cannot find client with ID " + id +". Returning to menu.");
        }//else
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
    
    public void menu(){
        
        System.out.println(BankApp.DIVIDER
                + "\nMain Menu\n"
                + "1. Client Menu\n"
                + "2. Add new client\n"
                + "3. Remove client\n"
                + "4. Change client\n"
                + "5. Update client\n"
                + "6. View clients\n"
                + "7. Exit\n"
                + "Enter selection:");       
        
        int choice = BankApp.input.nextInt();
        BankApp.input.nextLine();
        
        switch(choice){
            case 1:
                if (getClients().isEmpty())
                    System.out.println("No clients.");
                else{
                    getCurrentClient().clientMenu();
                }//else
                break;
            case 2:
                createClient();
                break;
            case 3:
                printClients();
                System.out.println("Enter the ID of the client");
                removeClient(BankApp.input.nextInt());
                BankApp.input.nextLine();
                break;
            case 4:
                printClients();
                System.out.println("Enter the ID of the client");
                changeClient(BankApp.input.nextInt());
                BankApp.input.nextLine();
                break;
            case 5:
                printClients();
                System.out.println("Enter the ID of the client");
                updateClient(BankApp.input.nextInt());
                BankApp.input.nextLine();
                break;
            case 6:
                printClients();
                break;
            case QUIT:
                System.out.println("Please come again!");
                break;
            default:
                System.out.println("Invalid input. Please try again.");
                break;
        }//switch(choice)
        
        if (choice != QUIT)
            menu();
        
    }//menu()
    
}//Bank
