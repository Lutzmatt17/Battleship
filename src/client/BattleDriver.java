package client;

import java.util.Scanner;

/**
 * Runs the client-side version of the Battleship project.
 * @author Matt Lutz and Brandon Townsend
 * @version December 2018
 */
public class BattleDriver {

    public static void main(String[] args) {
        if(args.length == 3) {
            BattleClient client = new BattleClient(args[0],
                    Integer.parseInt(args[1]), args[2]);
            Scanner input = new Scanner(System.in);
            boolean quit = false;
            while(!quit) {
                String command = input.nextLine();
                if(command.toLowerCase().equals("/quit")) {
                    //System.out.println("got a quit in driver");
                    quit = true;
                    input.close();
                }

                //System.out.printf("Command to send: %s\r\n", command);

                client.send(command);
            }
        } else {
            System.out.println("Usage: java BattleDriver <hostname> <port> " +
                    "<username>");
            System.exit(1);
        }
    }
}
