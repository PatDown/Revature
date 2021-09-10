package com.pdownton.reimbursementapp.models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Pat Down
 */
public abstract class Account {
    protected int id;
    private String username;
    private String password;
    
    public static List<Integer> ids = new ArrayList<>();

    public Account() {
        super();
    }//Manager()

    public Account(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }//Manager(String, String)
    
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
    
}//Account
