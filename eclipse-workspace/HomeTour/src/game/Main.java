package game;

import java.util.*;

/**
 *
 * @author Pat Down
 */
public class Main {
    public static Scanner input = new Scanner(System.in);
    public static Manager manager = new Manager();
    public static String D1 = "===============================================================================================";
    public static String D2 = "-----------------------------------------------------------------------------------------------";
    public static int ROOM_COUNT = 16;
    
    public static void main(String[] args) throws NullPointerException{
        manager.init();
        Player player = new Player(manager.getStartingRoom());
        System.out.println("Welcome to the house tour!");
        printCommands();
        printRoom(player);
        parse(collectInput(), player);
    }//main(String[])
	
    private static void printCommands(){
        System.out.println(D1);
        System.out.println("Commands");
        System.out.println("\tGo - Use the 'go' command to go to a neighboring room. ");
        System.out.println("\tTake - Use the 'take' command to add items to your inventory.");
        System.out.println("\tPlace - Use the 'place' command to place items from your inventory.");
        System.out.println("\tUse - Use the 'use' command to use an item.");
        System.out.println("\tTurn - use the 'turn' command to change the direction you are facing.");
        System.out.println("\tMenu - Use the 'menu' command to show these controls again.");
        System.out.println("\tInfo - Use the 'info' command to view the valid inputs for each command.");
        System.out.println("\tQuit - Use the 'quit' command to exit the tour.");
    }//printCommands()
    
    private static void commandInfo(String command){
        System.out.println(D2 + "\nValid inputs for '" + command + "' command");
        switch(command){
            case "go":
                System.out.println("North  South  East  West  Up  Down");
                break;
            case "take":
            case "place":
                manager.getItems().values().forEach(i ->{
                    if (i.isTakeable())
                        System.out.print(i.getName() + "  ");
                });
                System.out.println();
                break;
            case "use":
                manager.getItems().values().forEach(i -> {
                    System.out.print(i.getName() + "  ");
                });
                System.out.println();
                break;
            case "turn":
                System.out.println("North  South  East  West  Right  Left  Around");
                break;
            case "info":
                System.out.println("Go  Take  Place  Use  Turn  Menu  Info  Quit");
                break;
            case "menu":
            case "quit":
                System.out.println("No additional inputs required.");
                break;
            default:
                System.out.println(command + " is not a command.");
                break;
        }//switch(command)
    }//commandInfo(String)
    
    private static void printRoom(Player player) {
        System.out.println(D1);
        System.out.println(player.getCurrentRoom().toString());
        player.getCurrentRoom().printItems();
        if (!player.getInventory().isEmpty())
            player.showInventory();
        player.getCurrentRoom().printExits();
        System.out.println(D2);
    }//printRoom(Player)

    private static String[] collectInput() {
        String[] command = new String[3];
        System.out.println("Enter command: ");
        int i = 0;
        command[0] = input.nextLine().trim().toLowerCase();
        Scanner commandScanner = new Scanner(command[0]);
        commandScanner.useDelimiter(" ");
        while (++i < 3 && commandScanner.hasNext())
            command[i] = commandScanner.next().trim();
        return command;
    }//collectInput()

    private static void parse(String[] command, Player player) {
        switch(command[1]){
            case "go":
                player.go(command[2]);
                break;
            case "look":
                System.out.println(D2);
                player.look(command[2]);
                break;
            case "take":
                System.out.println(D2);
                player.take(command[2]);
                break;
            case "place":
                System.out.println(D2);
                player.place(command[2]);
                break;
            case "use":
                System.out.println(D2);
                player.use(command[2]);
                break;
            case "turn":
                System.out.println(D2);
                player.turn(command[2]);
                break;
            case "menu":
                printCommands();
                break;
            case "info":
                commandInfo(command[2]);
                break;
            case "quit":
                System.out.println(D1);
                System.out.println("Thanks for visiting!");
                break;
            default:
                System.out.println("Can't recognize command. Please enter a valid command.");
                break;
        }//switch(command[1])
        if (!command[0].equals("quit")){
            printRoom(player);
            parse(collectInput(), player);
        }//if (!command[0].equals("quit"))
    }//parse(String[], Player)
}//Main
