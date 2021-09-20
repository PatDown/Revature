package com.pdownton.reimbursementapp.models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Pat Down
 */
@Entity
@Table(name="accounts")
public abstract class Account {
    @Id
    @Column
    protected int id;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String name;
    
    public static List<Integer> ids = new ArrayList<>();

    public Account() {
        super();
    }//Account()

    public Account(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }//Account(String, String)
    
    public Account(String username, String password, String name){
        super();
        this.username = username;
        this.password = password;
        this.name = name;
    }//Account(String, String, String)

    public Account(int id, String username, String password, String name) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
    }//Account(int, String, String, String)
    
    public int getId(){
        return id;
    }//getId()
    
    public void setId(int id){
        this.id = id;
    }//setId(int)

    public String getUsername() {
        return username;
    }//getUsername()

    public void setUsername(String username) {
        this.username = username;
    }//setUsername(String)

    public String getPassword() {
        return password;
    }//getPassword()

    public void setPassword(String password) {
        this.password = password;
    }//setPassword(String)

    public String getName() {
        return name;
    }//getName()

    public void setName(String name) {
        this.name = name;
    }//setName(String)
    
    @Override
    public String toString(){
        StringBuilder aBuilder = new StringBuilder();
        aBuilder.append(String.format("Username: %s | ", username));
        aBuilder.append(String.format("Password: %s | ", password));
        aBuilder.append(String.format("Name: %s", name));
        if (id < 100)
            aBuilder.append(" | Manager");
        return aBuilder.toString();
    }//toString()
}//Account
