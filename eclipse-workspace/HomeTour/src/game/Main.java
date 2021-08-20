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
    }//main(String[] args)
	
    private static void printCommands(){
        System.out.println(D1);
        System.out.println("Commands");
        System.out.println("\tgo - Use the 'go' command to go to a neighboring room. ");
        System.out.println("\ttake - Use the 'take' command to add items to your inventory.");
        System.out.println("\tplace - Use the 'place' command to place items from your inventory.");
        System.out.println("\tuse - Use the 'use' command to use an item.");
        System.out.println("\tturn - use the 'turn' command to change the direction you are facing.");
        System.out.println("\tmenu - Use the 'menu' command to show these controls again.");
        System.out.println("\tquit - Use the 'quit' command to exit the tour.");
    }//printCommands()
    
    private static void printRoom(Player player) throws NullPointerException{
        System.out.println(D1);
        System.out.println(player.getCurrentRoom().toString());
        player.getCurrentRoom().printItems();
        if (!player.getInventory().isEmpty())
            player.showInventory();
        player.getCurrentRoom().printExits();
        System.out.println(D2);
    }//printRoom(Player player)

    private static String[] collectInput() {
        String[] command = new String[3];
        System.out.println("Enter command: ");
        int i = 0;
        command[0] = input.nextLine().trim().toLowerCase();
        Scanner commandScanner = new Scanner(command[0]);
        commandScanner.useDelimiter(" ");
        i++;
        while (i < 3 && commandScanner.hasNext()){
            command[i] = commandScanner.next().trim();
            i++;
        }// while (i < 3 && commandScanner.hasNext())
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
    }//parse(String[] command, Player player)
}//Main
