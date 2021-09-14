package com.pdownton.reimbursementapp.repository;

import com.pdownton.reimbursementapp.models.Reimbursement;
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
public class ReimbursementRepository implements Repository<Reimbursement> {
    
    private final Connection connection;

    public ReimbursementRepository(Connection conn) {
        super();
        connection = conn;
    }//ReimbursementRepository(Connection)
    
    @Override
    public Reimbursement get(int id) throws SQLException {
        String sql = "SELECT * FROM requests WHERE id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();
        
        if (rs.next()){
            Reimbursement row = new Reimbursement();
            row.setId(id);
            row.setAmount(rs.getFloat("amount"));
            row.setReason(rs.getString("reason"));
            row.setStatus(rs.getString("status"));
            row.setEmployeeId(rs.getInt("employee_id"));
            row.setMessage(rs.getString("message"));
            return row;
        }//if (rs.next())
        else
            throw new UnsupportedOperationException("Not supported yet."); 
    }//get(int)

    @Override
    public List<Reimbursement> getAll() throws SQLException {
        List<Reimbursement> reimbursements = new ArrayList<>();
        String sql = "SELECT * FROM requests";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()){
            Reimbursement row = new Reimbursement();
            row.setId(rs.getInt("id"));
            row.setAmount(rs.getFloat("amount"));
            row.setReason(rs.getString("reason"));
            row.setStatus(rs.getString("status"));
            row.setEmployeeId(rs.getInt("employee_id"));
            row.setMessage(rs.getString("message"));
            reimbursements.add(row);
        }//while (rs.next())
        return reimbursements;
    }//getAll()

    @Override
    public void save(Reimbursement r) throws SQLException {
        String sql = "INSERT INTO requests VALUES(?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1, r.getId());
        pstmt.setFloat(2, r.getAmount());
        pstmt.setString(3, r.getReason());
        pstmt.setString(4, r.getStatus());
        pstmt.setInt(5, r.getEmployeeId());
        pstmt.setString(6, r.getMessage());
        pstmt.executeQuery();
    }//save(Reimbursement)

    @Override
    public void update(Reimbursement r, String[] params) throws SQLException {
        String sql = "UPDATE requests SET status=?, message=? WHERE id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, params[0]);
        pstmt.setString(2, params[1]);
        pstmt.setInt(3, r.getId());
        pstmt.executeQuery();
    }//update(Reimbursement, String[])

    @Override
    public void delete(Reimbursement r) throws SQLException {
        String sql = "DELETE FROM requests WHERE id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1, r.getId());
        ResultSet rs = pstmt.executeQuery();        
    }//delete(Reimbursement)
    
}//ReimbursementRepository
