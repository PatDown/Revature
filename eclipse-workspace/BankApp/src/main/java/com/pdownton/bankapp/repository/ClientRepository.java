package com.pdownton.bankapp.repository;

import com.pdownton.bankapp.models.Client;
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

public class ClientRepository implements Repository<Client> {
    
    private List<Client> clients;
    Connection connection;
    
    public ClientRepository(Connection conn){
        clients = new ArrayList<>();
        connection = conn;
    }//ClientRepository(Connection)
    
    @Override
    public Client get(int id) throws SQLException{
        String sql = "SELECT * FROM clients WHERE id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();
        
        if (rs.next()){
        
            Client row = new Client();
            row.setID(rs.getInt("id"));
            row.setName(rs.getString("name"));
            return row;
        }//if(rs.next())
        
        return null;
    }//get(int)

    @Override
    public Client findById(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }//findById(String)

    @Override
    public List<Client> getAll() throws SQLException {
        String sql = "SELECT * FROM clients";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        
        while(rs.next()){
            Client row = new Client();
            row.setID(rs.getInt("id"));
            row.setName(rs.getString("name"));
            clients.add(row);
        }//while(rs.next())
        return clients;
    }//getAll()
    
    @Override
    public void save(Client client) throws SQLException {
        String sql = "INSERT INTO clients (string) VALUES (?)";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, client.toString());
        pstmt.executeQuery();
    }//save(Client)

    @Override
    public void update(Client client, String[] params) throws SQLException {
        String sql = "UPDATE clients SET (string) WHERE id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, params[0]);
        pstmt.setInt(2, Integer.valueOf(params[1]));
        pstmt.executeUpdate();
    }//update(Client, String[])

    @Override
    public void delete(Client client) throws SQLException {
        String sql = "DELETE FROM clients WHERE id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1, client.getID());
        pstmt.executeQuery();
    }//delete(Client)
    
}//ClientRepository
