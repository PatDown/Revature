package com.pdownton.bankapp.service;

import com.pdownton.bankapp.models.Account;
import com.pdownton.bankapp.models.Client;
import com.pdownton.bankapp.repository.AccountRepository;
import com.pdownton.bankapp.repository.ClientRepository;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Pat Down
 */
public class ClientService {
    
    private final ClientRepository clientRepository;
    
    private final AccountRepository accountRepository;
    
    public ClientService(Connection conn){
        clientRepository = new ClientRepository(conn);
        accountRepository = new AccountRepository(conn);
    }//ClientService(Connection)
    
    public Client create(Client client) throws SQLException{
        clientRepository.save(client);
        return client;
    }//create(String)
    
    public Client getClient(int id) {
        Client client = null;
        try {
            client = clientRepository.get(id);
        } catch (SQLException e){
            e.printStackTrace();
        }//catch (SQLException)
        return client;
    }//getClient(int)
    
    public List<Client> getClients(){
        List<Client> clients = new ArrayList<>();
        try {
            for (Client client : clientRepository.getClients().values())
                clients.add(client);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return clients;
    }//getClients()
    
    public boolean updateClient(int id, String newName){
        Client client;
        try {
            client = clientRepository.get(id);
            if (client == null)
                return false;
            clientRepository.update(client, new String[]{newName});
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }//catch (SQLException)
        return true;
    }//updateClient(int, String)
    
    public boolean removeClient(int id){
        try {
            Client client = clientRepository.get(id);
            clientRepository.delete(client);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }//catch(SQLException)
        
        return true;
    }//removeClient(int)
    
    public Collection<Account> findAccounts(Client client, float[] bounds){
        Collection<Account> accounts = new ArrayList<>();
        try{
            accounts = accountRepository.getAccounts().values();
        } catch (SQLException e){
            e.printStackTrace();
        }
        for (Account account : accounts){
            if (account.getClientID() != client.getID())
                accounts.remove(account);
            if (account.getBalance() > bounds[0] || account.getBalance() < bounds[1])
                accounts.remove(account);
        }
        
        return accounts;
    }//
    /*
    
    
    
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
    */
        
    private boolean validAmount(float amount) {        
        return amount > 0;
    }//validAmount(float)

    
}//ClientService
