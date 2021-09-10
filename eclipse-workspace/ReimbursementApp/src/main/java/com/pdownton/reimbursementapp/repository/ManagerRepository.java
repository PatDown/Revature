package com.pdownton.reimbursementapp.repository;

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
public class ManagerRepository implements Repository<Manager> {
    
    private final Connection connection;

    public ManagerRepository(Connection conn) {
        super();
        connection = conn;
    }//ManagerRepository(Connection)
    
    @Override
    public Manager get(int id) throws SQLException {
        String sql = "SELECT * FROM manager WHERE id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();
        
        if (rs.next()){
            Manager row = new Manager();
            row.setUsername(rs.getString("username"));
            row.setPassword(rs.getString("password"));
            return row;
        }//if (rs.next())
        else
            throw new UnsupportedOperationException("Not supported yet."); 
    }//get(int)

    @Override
    public List<Manager> getAll() throws SQLException {
        List<Manager> managers = new ArrayList<>();
        String sql = "SELECT * FROM manager";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()){
            Manager row = new Manager();
            row.setUsername(rs.getString("username"));
            row.setPassword(rs.getString("password"));
        }//while (rs.next())
        return managers;
    }//getAll()

    @Override
    public void save(Manager m) throws SQLException {
        String sql = "INSERT INTO manager VALUES(?, ?, ?)";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1, m.getId());
        pstmt.setString(2, m.getUsername());
        pstmt.setString(3, m.getPassword());
        pstmt.executeQuery();
    }//save(Manager)

    @Override
    public void update(Manager m, String[] params) throws SQLException {
        String sql = "UPDATE manager SET ? WHERE id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, params[0]);
        pstmt.setInt(2, m.getId());
        pstmt.executeUpdate();
    }//update(Manager, String[])

    @Override
    public void delete(Manager m) throws SQLException {
        String sql = "DELETE FROM manager WHERE id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1, m.getId());
        ResultSet rs = pstmt.executeQuery();        
    }//delete(Manager)
    
}//ManagerRepository
