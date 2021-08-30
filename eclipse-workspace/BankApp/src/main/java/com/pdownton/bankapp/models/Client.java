package com.pdownton.bankapp.models;

import com.pdownton.bankapp.BankApp;
import java.util.*;//Map, HashMap

/**
 *
 * @author Pat Down
 */
public class Client {
    public static final int BACK = 8;
    private final int id = (int)(Math.random()*(900)+100);
    private String name;
    private final Map<String, Account> accounts;
    private Account currentAccount;
    
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
            account = getAccounts().get(accNum);
        else {
            for(Account acc : Bank.accounts.keySet())
                if (acc.getNumber().equals(accNum))
                    account = acc;
        }//else
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
        BankApp.input.nextLine();
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
        
        switch(choice){
            case 1:
                if (!getAccounts().isEmpty())
                    getCurrentAccount().deposit(validAmount());
                else
                    System.out.println("No accounts.");
                break;
            case 2:
                if (!getAccounts().isEmpty())
                    getCurrentAccount().withdraw(validAmount());
                else
                    System.out.println("No accounts.");
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
                if(!getAccounts().isEmpty() && getAccounts().size() > 1)
                    changeAccount(findAccount());
                else
                    System.out.println("Cannot switch accounts. Current account is the only account on file.");
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
    
    public void createAccount() {
        System.out.print("Balance - ");
        float balance = validAmount();
        
        System.out.println("Enter account type:\n1. Checking\n2. Savings\n3. Go Back");
        int type = BankApp.input.nextInt();
        switch(type){
            case 1:
                Account checking = new Checking(balance);
                addAccount(checking, -1);
                break;
            case 2:
                System.out.println("Enter the interest rate:");
                float interest = BankApp.input.nextFloat();
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
        }//while(isClientAccount(account.getNumber()))
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
            float amount = validAmount();
            if (acc1.getBalance() < amount)
                System.out.println("Cannot complete transfer");
            else {
                acc1.withdraw(amount);
                acc2.deposit(amount);
                System.out.printf("Transferred $%.2f from %s to %s.\n", amount, acc1.getNumber(), acc2.getNumber());
            }//else
        }//else
    }//transfer(Account, Account)
    
    public void printAccounts(){
        if (!getAccounts().isEmpty()){
            getAccounts().values().forEach(account -> {
                System.out.println(account.toString());
            });
        } else 
            System.out.println("No accounts. Returning to menu.");
    }//printAccounts()
    
    public float validAmount() {
        System.out.println("Enter amount:");
        float amount = BankApp.input.nextFloat();
            
        while(amount <= 0) {
            System.out.println("Invalid amount entered.\nEnter amount:");
            amount = BankApp.input.nextFloat();
        }//while(amount <= 0) 
        
        return amount;
    }//validAmount()
    
    @Override
    public String toString(){
        StringBuilder clientString = new StringBuilder();
        clientString.append("Client ID: ");
        clientString.append(getID());
        clientString.append(" | Account holder: ");
        clientString.append(getName());
        return clientString.toString();
    }//toString override
    
}//Client
