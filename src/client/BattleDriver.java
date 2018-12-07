package client;

import java.util.Scanner;

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
                    quit = true;
                    input.close();
                }
                client.send(command);
            }
        } else {
            System.out.println("Usage: java BattleDriver <hostname> <port> " +
                    "<username>");
            System.exit(1);
        }
    }
}
