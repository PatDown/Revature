package com.pdownton.bankapp.models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Pat Down
 */
public class BankTest {
    public BankTest() {
    }//BankTest()
    
    @BeforeAll
    public static void setUpClass() {
        
    }//setUpClass()
    
    @AfterAll
    public static void tearDownClass() {
    }//tearDownClass()
    
    @BeforeEach
    public void setUp() {
    }//setUp()
    
    @AfterEach
    public void tearDown() {
        
    }//tearDown()

    /**
     * Test of printClients method, of class Bank.
     */
    @Test
    public void testPrintClients() {
        System.out.println("printClients");
        Bank instance = new Bank();
        Client client1 = new Client("John Doe");
        Client client2 = new Client("Jane Deer");
        Client client3 = new Client("Jordan Dane");
        
        instance.clients.put(client1.getID(), client1);
        instance.clients.put(client2.getID(), client2);
        instance.clients.put(client3.getID(), client3);
        
        String expResult = "";
        expResult = instance.clients.values().stream().map(c -> c.toString() + "\n").reduce(expResult, String::concat);
        String result = instance.printClients();
        assertEquals(expResult, result);
        System.out.print(result);
    }//testPrintClients()
    
    /**
     * Test of createClient method, of class Bank.
     */
    @Test
    public void testCreateClient(){
        System.out.println("createClient");
        String name = "John Doe";
        Bank instance = new Bank();
        Client result = instance.createClient(name);
        int id = result.getID();
        
        instance.clients.put(id, result);
        
        assertNotNull(result);
        assertEquals(result, instance.getClient(id));
        System.out.println(result.toString());
    }//testCreateClient()
    
    /**
     * Test of addClient method, of class Bank.
     */
    @Test
    public void testAddClient() {
        System.out.println("addClient");
        Client client = new Client("John Doe");
        Bank instance = new Bank();
        
        String expResult = "New client added.";
        String result = instance.addClient(client);
        
        assertEquals(expResult, result);
        assertEquals(client, instance.getClient(client.getID()));
        
        System.out.println(client.toString());
    }//testAddClient

    /**
     * Test of removeClient method, of class Bank.
     */
    @Test
    public void testRemoveClient() {
        System.out.println("removeClient");
        Bank instance = new Bank();
        Client client = new Client("John Doe");
        int id = client.getID();
        instance.clients.put(id, client);
        String expResult = "Client " + id + " removed.";
        String result = instance.removeClient(id);
        assertEquals(expResult, result);
        System.out.println(result);
    }//testRemoveClient()
    
    /**
     * Test of removeClient method, of class Bank.
     */
    @Test
    public void testRemoveInvalidClient() {
        System.out.println("removeClient - Invalid ID");
        int id = 0;
        Bank instance = new Bank();
        String expResult = "Cannot find client with ID " + id;
        String result = instance.removeClient(id);
        assertEquals(expResult, result);
        System.out.println(result);
    }//testRemoveInvalidClient()

    /**
     * Test of updateClient method, of class Bank.
     */
    @Test
    public void testUpdateClient() {
        System.out.println("updateClient");
        String name = "Jane Deer";
        Client client = new Client("John Doe");
        int id = client.getID();
        Bank instance = new Bank();
        
        instance.clients.put(id, client);
        String expResult = "Updated client " + id;
        String result = instance.updateClient(id, name);
        assertEquals(expResult, result);
        System.out.println(result);
    }//testUpdateClient()
    
    /**
     * Test of updateClient method, of class Bank.
     */
    @Test
    public void testUpdateInvalidClient() {
        System.out.println("updateClient");
        int id = 0;
        String name = "Jane Deer";
        Bank instance = new Bank();
        String expResult = "Cannot find client with ID " + id;
        String result = instance.updateClient(id, name);
        assertEquals(expResult, result);
        System.out.println(result);
    }//testUpdateInvalidClient()

    /**
     * Test of changeClient method, of class Bank.
     */
    @Test
    public void testChangeClient() {
        System.out.println("changeClient");
        Bank instance = new Bank();
        Client client1 = new Client("John Doe");
        Client client2 = new Client ("Jane Deer");
        
        instance.clients.put(client1.getID(), client1);
        instance.clients.put(client2.getID(), client2);
        
        instance.setCurrentClient(client1);
        int id = client2.getID();
        String expResult = String.format("Changed from client %d to client %d.", client1.getID(), id);
        String result = instance.changeClient(id);
        assertEquals(expResult, result);
        System.out.println(result);
    }//testChangeClient
    
    /**
     * Test of changeClient method, of class Bank.
     */
    @Test
    public void testChangeClientEmpty() {
        System.out.println("changeClient - empty client list");
        int id = 0;
        Bank instance = new Bank();
        String expResult = String.format("Cannot find client with ID %d.", id);
        String result = instance.changeClient(id);
        assertEquals(expResult, result);
        System.out.println(result);
    }//testChangeClientEmpty()
    
}
