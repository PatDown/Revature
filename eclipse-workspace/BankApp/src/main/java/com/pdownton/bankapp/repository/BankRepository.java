package com.pdownton.bankapp.repository;

import com.pdownton.bankapp.models.Account;
import com.pdownton.bankapp.models.Bank;
import com.pdownton.bankapp.models.Client;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Pat Down
 */


public class BankRepository implements Repository<Bank> {
    
    Map<Account, Client> accountsClients;
    Connection connection;
    
    public BankRepository(Connection conn){
        accountsClients = new HashMap<>();
        connection = conn;
    }//BankRepository(Connection)
    
    @Override
    public Bank get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }//get(int)

    @Override
    public Bank findById(String s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }//findById(String)

    @Override
    public List<Bank> getAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }//getAll()

    @Override
    public void save(Bank bank) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }//save(Bank)

    @Override
    public void update(Bank bank, String[] params) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }//update(Bank, String[])

    @Override
    public void delete(Bank bank) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }//delete(Bank)
    
}//BankRepository
