package com.pdownton.reimbursementapp.repository;

import com.pdownton.reimbursementapp.models.Account;
import com.pdownton.reimbursementapp.models.Employee;
import com.pdownton.reimbursementapp.models.Manager;
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
    private final Connection connection;

    public AccountRepository(Connection conn) {
        super();
        connection = conn;
    }//EmployeeRepository(Connection)
    
    @Override
    public Account get(int id) throws SQLException {
        String sql = "SELECT * FROM accounts WHERE id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();
        
        if (rs.next()){
            Account row;
            if (id > 100)
                row = new Employee();
            else
                row = new Manager();
            row.setId(id);
            row.setUsername(rs.getString("username"));
            row.setPassword(rs.getString("password"));
            row.setName(rs.getString("name"));
            return row;
        }//if (rs.next())
        else
            return null; 
    }//get(int)

    @Override
    public List<Account> getAll() throws SQLException {
        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT * FROM accounts";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()){
            Account row;
            if (rs.getInt("id") > 100)
                row = new Employee();
            else
                row = new Manager();
            row.setId(rs.getInt("id"));
            row.setUsername(rs.getString("username"));
            row.setPassword(rs.getString("password"));
            row.setName(rs.getString("name"));
            accounts.add(row);
        }//while (rs.next())
        return accounts;
    }//getAll()

    @Override
    public void save(Account a) throws SQLException {
        String sql = "INSERT INTO accounts VALUES(?, ?, ?, ?)";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1, a.getId());
        pstmt.setString(2, a.getUsername());
        pstmt.setString(3, a.getPassword());
        pstmt.setString(4, a.getName());
        pstmt.executeQuery();
    }//save(Account)

    @Override
    public void update(Account a, String[] params) throws SQLException {
        String sql = "UPDATE accounts SET ? WHERE id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, params[0]);
        pstmt.setInt(2, a.getId());
        pstmt.executeUpdate();
    }//update(Account, String[])

    @Override
    public void delete(Account a) throws SQLException {
        String sql = "DELETE FROM accounts WHERE id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1, a.getId());
        ResultSet rs = pstmt.executeQuery();
    }//delete(Account)
}//AccountRepository
