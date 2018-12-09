package server;

import common.ConnectionAgent;
import common.MessageListener;
import common.MessageSource;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class BattleServer implements MessageListener {
    private ServerSocket serverSocket;
    private int current;
    private Game game;
    //ConnectionAgent agent;
    private HashMap<String, ConnectionAgent> players;

    public BattleServer(int port) {
        this(port, 10);
    }

    public BattleServer(int port, int size) {
        try {
            this.serverSocket = new ServerSocket(port);
            this.game = new Game(size);
            this.current = 0;
            this.players = new HashMap<>();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void listen() {
        while (!serverSocket.isClosed()) {
            Socket connection = null;
            try {

                //System.out.println("connection agent for client setup");

                connection = serverSocket.accept();
                ConnectionAgent agent = new ConnectionAgent(connection);
                agent.addMessageListener(this);

                //System.out.println("setup complete :)");

            } catch (Exception e) {
                try {
                    connection.close();
                    e.printStackTrace();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
    }

    public void broadcast(String message) {
        for(String player: players.keySet()){

            //System.out.printf("server -> client connection agents: %s\r\n",
            //        message);

            players.get(player).sendMessages(message + "\r\n");
        }
    }

    @Override
    public void messageReceived(String message, MessageSource source) {
        String[] command;

        //System.out.printf("server -> game: %s\r\n", message);

        if(message.contains("/join")) {

            //System.out.println("message contains a join");

            command = message.split(" ");
            players.put(command[1], (ConnectionAgent) source);
            for(String player : players.keySet()) {
                System.out.println(player + " " + players.get(player));
            }
        } else if(message.contains("/play")) {

            //System.out.println("message contains a play");

            if(players.size() < 2) {
                broadcast("Not enough players to play the game.");
            } else {
                broadcast("The game begins. duh duh duhhhhhh");
            }
        } else if(message.contains("/attack")) {

            //System.out.println("message contains an attack");

            command = message.split(" ");
            String toAttack = command[1];
            Integer row = Integer.parseInt(command[2]);
            Integer col = Integer.parseInt(command[3]);
        } else if(message.contains("/quit")) {
            command = message.split(" ");
            broadcast("!!! " + command[1] + " surrendered.");

            // Under the hood, a client's username will be sent after a quit
            // command.
            //System.out.println("before rmMsgListener");
            source.removeMessageListener(this);
            //System.out.println("before srcClosed");
            sourceClosed(players.get(command[1]));
            //System.out.println("before rm");
            players.remove(command[1]);
            //System.out.println("after rm");
        } else if(message.contains("/show")) {

            //System.out.println("message contains a show");

            command = message.split(" ");
            String toShow = command[1];
            String username = command[2];
        }
    }

    @Override
    public void sourceClosed(MessageSource source) {
        source.removeMessageListener(this);
    }
}
