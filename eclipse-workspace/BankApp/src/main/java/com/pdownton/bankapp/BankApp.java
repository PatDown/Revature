package com.pdownton.bankapp;

import com.pdownton.bankapp.models.Bank;
import java.util.Scanner;

public class BankApp {
    public static Scanner input = new Scanner(System.in);
    public static final String DIVIDER = "=========================================";
    public static void main(String[] args) {
        Bank bank = new Bank();
        bank.init();
    }//main
     
}//BankAppStart
