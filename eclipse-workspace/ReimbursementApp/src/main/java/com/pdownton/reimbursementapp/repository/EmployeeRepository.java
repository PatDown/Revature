package com.pdownton.reimbursementapp.repository;

import com.pdownton.reimbursementapp.models.Employee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Pat Down
 */
public class EmployeeRepository implements Repository<Employee> {
    
    private final Connection connection;

    public EmployeeRepository(Connection conn) {
        super();
        connection = conn;
    }//EmployeeRepository(Connection)
    
    @Override
    public Employee get(int id) throws SQLException {
        String sql = "SELECT * FROM employee WHERE id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();
        
        if (rs.next()){
            Employee row = new Employee();
            row.setId(id);
            row.setUsername(rs.getString("username"));
            row.setPassword(rs.getString("password"));
            return row;
        }//if (rs.next())
        else
            throw new UnsupportedOperationException("Not supported yet."); 
    }//get(int)

    @Override
    public List<Employee> getAll() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM employee";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()){
            Employee row = new Employee();
            row.setId(rs.getInt("id"));
            row.setUsername(rs.getString("username"));
            row.setPassword(rs.getString("password"));
            employees.add(row);
        }//while (rs.next())
        return employees;
    }//getAll()

    @Override
    public void save(Employee e) throws SQLException {
        String sql = "INSERT INTO employee VALUES(?, ?, ?)";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1, e.getId());
        pstmt.setString(2, e.getUsername());
        pstmt.setString(3, e.getPassword());
        pstmt.executeQuery();
    }//save(Employee)

    @Override
    public void update(Employee e, String[] params) throws SQLException {
        String sql = "UPDATE employee SET ? WHERE id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, params[0]);
        pstmt.setInt(2, e.getId());
        pstmt.executeUpdate();
    }//update(Employee, String[])

    @Override
    public void delete(Employee e) throws SQLException {
        String sql = "DELETE FROM employee WHERE id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1, e.getId());
        ResultSet rs = pstmt.executeQuery();
    }//delete(Employee)
    
    
    
}//EmployeeRepository
