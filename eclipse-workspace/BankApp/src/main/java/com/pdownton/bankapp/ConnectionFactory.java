package com.pdownton.bankapp;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 *
 * @author Pat Down
 */
public class ConnectionFactory {
    
    private static Connection connection;
    
    private ConnectionFactory(){
        
    }//ConnectionFactory()
    
    public static Connection getConnection(){
        if (connection == null){
            try {
                Properties props = new Properties();
                FileReader connProps = new FileReader("src/main/resources/connection.properties");
                props.load(connProps);
                
                String connString = "jdbc:mariadb://" +
                        props.getProperty("endpoint") + ":" +
                        props.getProperty("port") + "/" +
                        props.getProperty("dbname") + "?user=" +
                        props.getProperty("username") + "&password=" +
                        props.getProperty("password");
                
                connection = DriverManager.getConnection(connString);
            } catch (Exception e) {
                e.printStackTrace();
            } //catch
        }//if (connection == null)
        return connection;
    }//getConnection()
}//ConnectionFactory
