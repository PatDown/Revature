package com.pdownton.bankapp.models;

import com.pdownton.bankapp.BankApp;
import java.util.*;//Map, HashMap

/**
 *
 * @author Pat Down
 */
public class Client {
    public static final int BACK = 8;
    private final int id = (int)(Math.random()*(1000)+1);
    private String name;
    private final Map<String, Account> accounts;
    private static Account currentAccount;
    
    public Client(){
        super();
        name = "";
        accounts = new HashMap<>();
    }//Client()

    public Client(String name) {
        super();
        this.name = name;
        accounts = new HashMap<>();
    }//Client(String)

    public int getID() {
        return id;
    }//getID()

    public String getName() {
        if(this == null)
            return null;
        return name;
    }//getName()

    public void setName(String name) {
        this.name = name;
    }//setName(String)

    public Map<String, Account> getAccounts() {
        return accounts;
    }//getAccounts()
    
    public Account getAccount(String accNum){
        Account account = null;
        if (getAccounts().containsKey(accNum))
            return getAccounts().get(accNum);
        else{
            for(Account a : Bank.accounts.keySet()){
                if (a.getNumber().equals(accNum))
                    account = a;
            }
        }
        return account;
    }//getAccount(String)
    
    public boolean isClientAccount(String accNum){
        return getAccounts().containsKey(accNum);
    }//isClientAccount(String)
    
    public boolean isAccount(String accNum){
        return Bank.accounts.keySet().stream().anyMatch(a -> (a.getNumber().equals(accNum)));
    }//isAccount(String)
    
    public Account getCurrentAccount(){
        return currentAccount;
    }//getCurrentAccount()
    
    public void setCurrentAccount(Account account){
        currentAccount = account;
    }//setCurrentAccount(Account)
    
    public String findAccount(){
        System.out.println("Enter account number:");
        return BankApp.input.nextLine().trim();
    }//findAccount()
    
    public void clientMenu(){
        System.out.println(BankApp.DIVIDER);
        if (getCurrentAccount() != null)
            System.out.println(getCurrentAccount().toString());
        System.out.println("Client Menu\n"
                + "1. Deposit\n"
                + "2. Withdraw\n"
                + "3. Transfer funds\n"
                + "4. Add new account\n"
                + "5. Change account\n"
                + "6. Remove account\n"
                + "7. View accounts\n"
                + "8. Back\n"
                + "Enter selection:");
        int choice = BankApp.input.nextInt();
        BankApp.input.nextLine();
        
        switch(choice){
            case 1:
                if (!getAccounts().isEmpty()){
                    System.out.println("Enter amount:");
                    getCurrentAccount().deposit(BankApp.input.nextFloat());
                    BankApp.input.nextLine();
                } else {
                    System.out.println("No accounts.");
                }//else
                break;
            case 2:
                if (!getAccounts().isEmpty()){
                    System.out.println("Enter amount:");
                    getCurrentAccount().withdraw(BankApp.input.nextFloat());
                    BankApp.input.nextLine();
                } else {
                    System.out.println("No accounts.");
                }//else
                break;
            case 3:
                printAccounts();
                if (!getAccounts().isEmpty())
                    transfer(getAccount(findAccount()), getAccount(findAccount()));
                break;
            case 4:
                createAccount();
                break;
            case 5:
                printAccounts();
                if(!getAccounts().isEmpty())
                    changeAccount(findAccount());
                break;
            case 6:
                printAccounts();
                if (!getAccounts().isEmpty())
                    deleteAccount(findAccount());
                break;
            case 7:
                printAccounts();
                break;
            case BACK:
                break;
            default:
                System.out.println("Invalid input. Please try again.");
                break;
        }//switch(choice)
        if (choice != BACK)
            clientMenu();
    }//clientMenu()
    
    public void createAccount(){
        System.out.println("Enter starting balance:");
        float balance = BankApp.input.nextFloat();
        BankApp.input.nextLine();
        
        System.out.println("Enter account type:\n1. Checking\n2. Savings\n3. Go Back");
        int type = BankApp.input.nextInt();
        BankApp.input.nextLine();
        switch(type){
            case 1:
                Account checking = new Checking(balance);
                addAccount(checking, -1);
                break;
            case 2:
                System.out.println("Enter the interest rate:");
                float interest = BankApp.input.nextFloat();
                BankApp.input.nextLine();
                Account savings = new Savings(balance, interest);
                addAccount(savings, interest);
                break;
            case 3:
                break;
            default:
                System.out.println("Invalid input. Please start again.");
                createAccount();
                break;
        }//switch(type)
        
    }//createAccount()
    
    public void addAccount(Account account, float interest){
        while (isClientAccount(account.getNumber())){
            if (interest == -1)
                account = new Checking(account.getBalance());
            else
                account = new Savings(account.getBalance(), interest);
        }//while (isClientAccount(account.getNumber()))
        if (getAccounts().isEmpty())
            setCurrentAccount(account);
        getAccounts().put(account.getNumber(), account);
        Bank.accounts.putIfAbsent(account, this);
    }//addAccount(Account, float)
    
    public void deleteAccount(String accNum){
        if (isClientAccount(accNum)) {
            Account account = getAccounts().get(accNum);
            getAccounts().remove(accNum);
            if (account == getCurrentAccount())
                setCurrentAccount(getAccounts().values().stream().findFirst().orElse(null));
        } else
            System.out.println("Account " + accNum + " could not be found.");
    }//deleteAccount(String)
    
    public void changeAccount(String accNum){
        if (isClientAccount(accNum))
            setCurrentAccount(getAccount(accNum));
        else
            System.out.println("Account " + accNum + " could not be found.");
    }//changeAccount(String)
    
    public void transfer(Account acc1, Account acc2){
        if (acc1 == null || acc2 == null)
            System.out.println("One of these accounts could not be found.");
        else {
            System.out.println("Enter amount:");
            float amount = BankApp.input.nextFloat();
            BankApp.input.nextLine();
            if (acc1.getBalance() < amount)
                System.out.println("Cannot complete transfer");
            else{
                acc1.withdraw(amount);
                acc2.deposit(amount);
                System.out.printf("Transferred $%.2f from %s to %s.\n", amount, acc1.getNumber(), acc2.getNumber());
            }//else
        }//else
    }//transfer(Account, Account)
    
    public void printAccounts(){
        if (!getAccounts().isEmpty()){
            getAccounts().values().forEach(c -> {
                System.out.println(c.toString());
            });
        } else 
            System.out.println("No accounts. Returning to menu.");
    }//printAccounts()
    
    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append("Client ID: ");
        s.append(id);
        s.append(" | Account holder: ");
        s.append(name);
        return s.toString();
    }//toString override
    
}//Client
