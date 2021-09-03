package com.pdownton.bankapp.service;

import com.pdownton.bankapp.models.Account;
import com.pdownton.bankapp.models.Bank;
import com.pdownton.bankapp.models.Checking;
import com.pdownton.bankapp.models.Client;
import com.pdownton.bankapp.models.Savings;
import com.pdownton.bankapp.repository.AccountRepository;
import com.pdownton.bankapp.repository.ClientRepository;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Pat Down
 */
public class ClientService {
    
    private ClientRepository clientRepository;
    
    private AccountRepository accountRepository;
    
    public void init(Connection conn) {
        clientRepository = new ClientRepository(conn);
    }//init(Connection)
    
    public Client create(String name) throws SQLException{
        Client client = new Client(name);
        clientRepository.save(client);
        return client;
    }//create(String)
    
    public Client getClient(int id) {
        Client client = new Client();
        try {
            return clientRepository.get(id);
        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }//getClient(int)
    
    public List<Client> getClients(){
        List<Client> clients = new ArrayList<>();
        try {
            clients = clientRepository.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return clients;
    }//getClients
    
    public boolean isClientAccount(int id, String accNum){
        Client client = null;
        try {
            client = clientRepository.get(id);
        } catch (SQLException e){
            e.printStackTrace();
        }//catch(SQLException)
        
        if (client == null)
            return false;
        
        return client.getAccounts().containsKey(accNum);
    }//isClientAccount(int, String)
    
    public boolean isAccount(String accNum){
        Account account = null; 
        try {
            account= accountRepository.findById(accNum);
        } catch (SQLException e) {
            e.printStackTrace();
        }//catch(SQLException)
        
        return account != null;
    }//isAccount(String)
    
    public boolean addAccount(int id, String name, float balance, String type, float interest) throws SQLException{
        Client client = null;
        try {
            client = clientRepository.get(id);
        } catch (SQLException e){
            e.printStackTrace();
        }//catch(SQLException)
        
        if (client == null)
            return false;
        Account account;
        if (type.equalsIgnoreCase("Checking"))
            account = new Checking(balance, id);
        else
            account = new Savings(balance, id, interest);
        
        if (client.getAccounts().isEmpty())
            client.setCurrentAccount(account);
        while(client.getAccounts().containsKey(account.getNumber())){
            if (account.getType().equalsIgnoreCase("checking"))
                account = new Checking(balance, id);
            if (account.getType().equalsIgnoreCase("savings"))
                account = new Savings(balance, id, interest);
        }//while(client.getAccounts().containsKey(account.getNumber()))
        client.getAccounts().put(account.getNumber(), account);
        Bank.accounts.putIfAbsent(account, client);
        clientRepository.save(client);
        return true;
    }//addAccount(int, String, float, String, float)
    
    public boolean deleteAccount(int id, String accNum) throws SQLException {
        Client client = null;
        try {
            client = clientRepository.get(id);
        } catch (SQLException e){
            e.printStackTrace();
        }//catch(SQLException)
        
        if (client == null)
            return false;
        
        if (client.getAccounts().isEmpty())
            return false;
        else {
            if (client.getAccounts().containsKey(accNum)) {
                Account account = client.getAccount(accNum);
                client.getAccounts().remove(accNum);
                if (account == client.getCurrentAccount())
                    client.setCurrentAccount(client.getAccounts().values().stream().findFirst().orElse(null));
                clientRepository.save(client);
                return true;
            } else
                return false;
        }//else
    }//deleteAccount(int, String)
    
    public boolean changeAccount(int id, String accNum){
        Client client = null;
        try {
            client = clientRepository.get(id);
        } catch (SQLException e){
            e.printStackTrace();
        }//catch(SQLException)
        
        if (client == null)
            return false;
                
        if (client.getAccounts().isEmpty() || client.getAccounts().size() < 2)
            return false;
        else {
            if (client.getAccounts().containsKey(accNum)){
                Account account = client.getAccount(accNum);
                client.setCurrentAccount(account);
                return true;
            } else
                return false;
        }//else
    }//changeAccount(int, String)
    
    public boolean transfer(int id, String accNum1, String accNum2, float amount) throws SQLException{
        Client client = null;
        try {
            client = clientRepository.get(id);
        } catch (SQLException e){
            e.printStackTrace();
        }//catch(SQLException)
        Account account1 = accountRepository.findById(accNum1);
        Account account2 = accountRepository.findById(accNum2);
        
        if (client == null || account1 == null || account2 == null)
            return false;
        
        if (validAmount(amount)){
            if (account1.getBalance() < amount)
                return false;
            else {
                account1.setBalance(account1.getBalance() - amount);
                account2.setBalance(account2.getBalance() + amount);
                clientRepository.save(client);
                accountRepository.save(account1);
                accountRepository.save(account2);
                return true;
            }//else
        } else
            return false;
    }//transfer(int, String, String, float)
    
    public boolean removeClient(int id){
        try {
            Client client = clientRepository.get(id);
            clientRepository.delete(client);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }//catch(SQLException)
        
        return false;
    }//removeClient(int)
    
    private boolean validAmount(float amount) {        
        return amount > 0;
    }//validAmount(float)

}//ClientService
