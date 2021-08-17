package game;

import java.util.*;

/**
 *
 * @author Pat Down
 */
public class Main {
    public static Scanner input = new Scanner(System.in);
    public static RoomManager manager;
    public static void main(String[] args) throws NullPointerException{
        manager = new RoomManager();
        manager.init();
        Player player = new Player(manager.getStartingRoom());
        System.out.println("Welcome to the house tour!");
        printCommands();
        printRoom(player);
        parse(collectInput(), player);
    }//main
	
    private static void printCommands(){
        System.out.println();
    }//printCommands
    
    private static void printRoom(Player player) {
        System.out.println(player.getCurrentRoom().toString());
    }//printRoom

    private static String[] collectInput() {
        String[] command = new String[4];
        System.out.println("Enter command: ");
        int i = 0;
        command[0] = input.nextLine().trim().toLowerCase();
        Scanner commandScanner = new Scanner(command[0]);
        while (++i < 4 && commandScanner.hasNext()){
            command[i] = commandScanner.next();
        }//while
        return command;
    }//collectInput

    private static void parse(String[] command, Player player) {
        switch(command[1].trim()){
            case "go":
                player.go(command[2]);
                printRoom(player);
                break;
            case "open":
                player.open();
                break;
            case "look":
                player.look();
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
            case "quit":
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
