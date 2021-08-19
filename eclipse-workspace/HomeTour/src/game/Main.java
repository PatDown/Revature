package game;

import java.util.*;

/**
 *
 * @author Pat Down
 */
public class Main {
    public static Scanner input = new Scanner(System.in);
    public static Manager manager = new Manager();
    public static String D1 = "=============================================";
    public static String D2 = "---------------------------------------------";
    public static int ROOM_COUNT = 16;
    
    public static void main(String[] args) throws NullPointerException{
        
        manager.init();
        Player player = new Player(manager.getStartingRoom());
        System.out.println("Welcome to the house tour!");
        printCommands();
        printRoom(player);
        parse(collectInput(), player);
    }//main
	
    private static void printCommands(){
        System.out.println("Commands");
        System.out.println("go - Use the 'go' command to go to a neighboring room. ");
    }//printCommands
    
    private static void printRoom(Player player) throws NullPointerException{
        System.out.println(D1);
        System.out.println(player.getCurrentRoom().toString());
        player.getCurrentRoom().printItems();
        player.getCurrentRoom().printExits();
        System.out.println(D2);
    }//printRoom

    private static String[] collectInput() {
        String[] command = new String[3];
        System.out.println("Enter command: ");
        int i = 0;
        command[0] = input.nextLine().trim().toLowerCase();
        Scanner commandScanner = new Scanner(command[0]);
        commandScanner.useDelimiter(" ");
        while (i < 3 && commandScanner.hasNext()){
            command[++i] = commandScanner.next().trim();
        }//while
        
        return command;
    }//collectInput

    private static void parse(String[] command, Player player) {
        switch(command[1]){
            case "go":
                player.go(command[2]);
                break;
            case "open":
                player.open(command[2]);
                break;
            case "look":
                player.look(command[2]);
                break;
            case "take":
                player.take(command[2]);
                break;
            case "place":
                player.place(command[2]);
                break;
            case "use":
                player.use(command[2]);
                break;
            case "turn":
                player.turn(command[2]);
                break;
            case "menu":
                printCommands();
                break;
            case "inventory":
                player.showInventory();
                break;
            case "quit":
                System.out.println(D1);
                System.out.println("Thanks for visiting!");
                break;
            default:
                System.out.println("Can't recognize command. Please enter a valid command.");
                break;
        }//switch
        if (!command[0].equals("quit")){
            printRoom(player);
            parse(collectInput(), player);
        }//if
    }//parse
}//Main
