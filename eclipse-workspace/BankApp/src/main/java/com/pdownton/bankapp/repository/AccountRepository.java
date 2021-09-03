package com.pdownton.bankapp.repository;

import com.pdownton.bankapp.models.Account;
import com.pdownton.bankapp.models.Checking;
import com.pdownton.bankapp.models.Savings;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Pat Down
 */
public class AccountRepository implements Repository<Account>{
    
    List<Account> accounts;
    Connection connection;

    public AccountRepository(Connection conn) {
        accounts = new ArrayList<>();
        connection = conn;
    }//AccountRepository(Connection)
    
    @Override
    public Account get(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }//get(int)

    @Override
    public Account findById(String accNum) throws SQLException {
        String sql = "SELECT * FROM clients WHERE account_num = (string)";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, accNum);
        ResultSet rs = pstmt.executeQuery();
        
        if (rs.next()){
            Account row;
            if(rs.getString("type").equalsIgnoreCase("Checking"))
                row = new Checking();
            else
                row = new Savings();
            
            row.setNumber(rs.getString("account_num"));
            row.setBalance(rs.getFloat("balance"));
            row.setType(rs.getString("type"));
            row.setClientID(rs.getInt("clientID"));
            if (row.getType().equalsIgnoreCase("Savings"))
                row.toSavings().setInterest(rs.getFloat("interest"));
            return row;
        }//if(rs.next())
        
        return null;
    }//findById(String)

    @Override
    public List<Account> getAll() throws SQLException {
        String sql = "SELECT * FROM accounts";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        
        while(rs.next()){
            Account row;
            if (rs.getString("type").equalsIgnoreCase("Checking"))
                row = new Checking();
            else
                row = new Savings();
            row.setNumber(rs.getString("account_num"));
            row.setBalance(rs.getFloat("balance"));
            row.setType(rs.getString("type"));
            row.setClientID(rs.getInt("customer_id"));
            if(row.getType().equalsIgnoreCase("Savings"))
                row.toSavings().setInterest(rs.getFloat("interest"));
            accounts.add(row);
        }//while(rs.next())
        return accounts;
    }//getAll()

    @Override
    public void save(Account account) throws SQLException {
        String sql = "INSERT INTO accounts VALUES (string)";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, account.toString());
        pstmt.executeQuery();
    }//save(Account)

    @Override
    public void update(Account account, String[] params) throws SQLException {
        String sql = "UPDATE accounts SET (string) WHERE (string)";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, params[0]);
        pstmt.setString(2, params[1]);
        pstmt.executeUpdate();
    }//update(Account, String[])

    @Override
    public void delete(Account account) throws SQLException {
        String sql = "DELETE FROM accounts WHERE account_num = (string)";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, account.getNumber());
        pstmt.executeQuery();
    }//delete(Account)
    
}//AccountRepository
