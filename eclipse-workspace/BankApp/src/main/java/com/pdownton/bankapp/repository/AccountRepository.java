package com.pdownton.bankapp.repository;

import com.pdownton.bankapp.models.Account;
import com.pdownton.bankapp.models.Checking;
import com.pdownton.bankapp.models.Savings;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Pat Down
 */
public class AccountRepository implements Repository<Account>{
    
    private final Map<String, Account> accounts;
    private final Connection connection;

    public AccountRepository(Connection conn) {
        accounts = new HashMap<>();
        connection = conn;
    }//AccountRepository(Connection)
    
    @Override
    public Account get(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }//get(int)

    @Override
    public Account findById(String accNum) throws SQLException {
        String sql = "SELECT * FROM accounts WHERE num = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, accNum);
        ResultSet rs = pstmt.executeQuery();
        
        if (rs.next()){
            Account row;
            if(rs.getString("type").equalsIgnoreCase("Checking"))
                row = new Checking();
            else
                row = new Savings();
            
            row.setNumber(rs.getString("num"));
            row.setBalance(rs.getFloat("balance"));
            row.setType(rs.getString("type"));
            row.setClientID(rs.getInt("customer_id"));
            if (row.getType().equalsIgnoreCase("Savings"))
                row.setInterest(rs.getFloat("interest"));
            return row;
        }//if(rs.next())
        
        return null;
    }//findById(String)
    
    @Override
    public Map<Integer, Account> getClients() {
        throw new UnsupportedOperationException("Not supported yet.");
    }//getClients()
    
    @Override
    public Map<String, Account> getAccounts() throws SQLException {
        String sql = "SELECT * FROM accounts";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        
        while(rs.next()){
            Account row;
            if (rs.getString("type").equalsIgnoreCase("Checking"))
                row = new Checking();
            else
                row = new Savings();
            row.setNumber(rs.getString("num"));
            row.setBalance(rs.getFloat("balance"));
            row.setType(rs.getString("type"));
            row.setClientID(rs.getInt("customer_id"));
            if(row.getType().equalsIgnoreCase("Savings"))
                row.setInterest(rs.getFloat("interest"));
            accounts.put(row.getNumber(), row);
        }//while(rs.next())
        return accounts;
    }//getAccounts()

    @Override
    public void save(Account account) throws SQLException {
        String sql = "INSERT INTO accounts VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, account.getNumber());
        pstmt.setFloat(2, account.getBalance());
        pstmt.setString(3, account.getType());
        pstmt.setInt(4, account.getClientID());
        pstmt.setFloat(5, account.getInterest());
        pstmt.executeQuery();
    }//save(Account)

    @Override
    public void update(Account account, String[] params) throws SQLException {
        String sql = "UPDATE accounts SET ? = ? WHERE num = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, params[0]);
        pstmt.setFloat(2, Float.parseFloat(params[1]));
        pstmt.setString(3, account.getNumber());
        pstmt.executeUpdate();
    }//update(Account, String[)

    @Override
    public void delete(Account account) throws SQLException {
        String sql = "DELETE FROM accounts WHERE STRCMP(num, ?) = 0";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, account.getNumber());
        pstmt.executeQuery();
    }//delete(Account)
    
}//AccountRepository
