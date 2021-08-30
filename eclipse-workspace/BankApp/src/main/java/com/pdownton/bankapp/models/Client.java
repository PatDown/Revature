package com.pdownton.bankapp.models;

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
    
//    public String findAccount(){
//        BankApp.input.nextLine();
//        System.out.println("Enter account number:");
//        return BankApp.input.nextLine().trim();
//    }//findAccount()
    
//    public void clientMenu(){
//        System.out.println(BankApp.DIVIDER);
//        if (getCurrentAccount() != null)
//            System.out.println(getCurrentAccount().toString());
//        System.out.println("Client Menu\n"
//                + "1. Deposit\n"
//                + "2. Withdraw\n"
//                + "3. Transfer funds\n"
//                + "4. Add new account\n"
//                + "5. Change account\n"
//                + "6. Remove account\n"
//                + "7. View accounts\n"
//                + "8. Back\n"
//                + "Enter selection:");
//        int choice = BankApp.input.nextInt();
//        
//        switch(choice){
//            case 1:
//                if (!getAccounts().isEmpty())
//                    getCurrentAccount().deposit(validAmount());
//                else
//                    System.out.println("No accounts.");
//                break;
//            case 2:
//                if (!getAccounts().isEmpty())
//                    getCurrentAccount().withdraw(validAmount());
//                else
//                    System.out.println("No accounts.");
//                break;
//            case 3:
//                printAccounts();
//                if (!getAccounts().isEmpty())
//                    transfer(getAccount(findAccount()), getAccount(findAccount()));
//                break;
//            case 4:
//                createAccount();
//                break;
//            case 5:
//                printAccounts();
//                if(!getAccounts().isEmpty() && getAccounts().size() > 1)
//                    changeAccount(findAccount());
//                else
//                    System.out.println("Cannot switch accounts. Current account is the only account on file.");
//                break;
//            case 6:
//                printAccounts();
//                if (!getAccounts().isEmpty())
//                    deleteAccount(findAccount());
//                break;
//            case 7:
//                printAccounts();
//                break;
//            case BACK:
//                break;
//            default:
//                System.out.println("Invalid input. Please try again.");
//                break;
//        }//switch(choice)
//        if (choice != BACK)
//            clientMenu();
//    }//clientMenu()
    
    public Account createAccount(float balance, String type) {
        if (validAmount(balance) && type.equalsIgnoreCase("checking"))
            return new Checking(balance);
        return new Checking(0);
    }//createAccount(float, String)
    
    public Account createAccount(float balance, String type, float interest) {
        if (validAmount(balance) && type.equalsIgnoreCase("savings") && validAmount(interest))
            return new Savings(balance, interest);
        return new Savings(0, 0);
        
    }//createAccount(float, String, float)
    
    public String addAccount(Account account){
        if (getAccounts().isEmpty())
            setCurrentAccount(account);
        while(getAccounts().containsKey(account.getNumber())){
            if (account.getType().equalsIgnoreCase("checking"))
                account = (Checking) account;
            if (account.getType().equalsIgnoreCase("savings"))
                account = (Savings) account;
        }//while(getAccounts().containsKey(account.getNumber()))
        getAccounts().put(account.getNumber(), account);
        Bank.accounts.putIfAbsent(account, this);
        return String.format("Account #%s added to client%s", account.getNumber(), getID());
    }//addAccount(Account)
    
    public String deleteAccount(String accNum){
        if (getAccounts().isEmpty())
            return "No accounts.";
        else {
            if (isClientAccount(accNum)) {
                Account account = getAccounts().get(accNum);
                getAccounts().remove(accNum);
                if (account == getCurrentAccount())
                    setCurrentAccount(getAccounts().values().stream().findFirst().orElse(null));
                return String.format("Account #%s removed from client %s", accNum, getID());
            } else
                return String.format("Account #%s could not be found.", accNum);
        }//else
    }//deleteAccount(String)
    
    public String changeAccount(String accNum){
        if (getAccounts().isEmpty())
            return "No accounts.";
        else if (getAccounts().size() == 1)
            return "Cannot switch accounts. Current account is the only account on file.";
        else {
            if (isClientAccount(accNum)){
                setCurrentAccount(getAccount(accNum));
                return String.format("Switched to account #%s", accNum);
            } else
                return String.format("Account #%s could not be found.", accNum);
        }//else
    }//changeAccount(String)
    
    public String transfer(Account acc1, Account acc2, float amount){
        if (acc1 == null || acc2 == null)
            return "One of these accounts could not be found.";
        else {
            if (validAmount(amount)){
                if (acc1.getBalance() < amount)
                    return "Invalid amount specified. Cannot complete transfer";
                else {
                    acc1.withdraw(amount);
                    acc2.deposit(amount);
                    return String.format("Transferred $%.2f from %s to %s.", amount, acc1.getNumber(), acc2.getNumber());
                }//else
            } else
                return "Invalid amount specified. Cannot complete transfer";
        }//else
    }//transfer(Account, Account, float)
    
    public String printAccounts(){
        StringBuilder accountList = new StringBuilder();
        if (!getAccounts().isEmpty()){
            getAccounts().values().forEach(account -> {
                accountList.append(account.toString());
                accountList.append("\n");
            });
            return accountList.toString();
        } else 
            return "No accounts. Returning to menu.";
    }//printAccounts()
    
    public boolean validAmount(float amount) {        
        return amount > 0;
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
