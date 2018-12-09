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
                connection = serverSocket.accept();
                Runnable agent = new ConnectionAgent(connection);

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
            players.get(player).sendMessages(message);
        }
    }

    @Override
    public void messageReceived(String message, MessageSource source) {
        String[] command;

        System.out.printf("got %s\n", message);

        if(message.contains("/join")) {
            command = message.split(" ");
            players.put(command[1], (ConnectionAgent) source);
            game.getPlayerGrids().put(command[1], new Grid());
        } else if(message.contains("/play")) {
            if(players.size() < 2) {
                broadcast("Not enough players to play the game.");
            } else {
                broadcast("The game begins. duh duh duhhhhhh");
            }
        } else if(message.contains("/attack")) {
            command = message.split(" ");
            String toAttack = command[1];
            Integer row = Integer.parseInt(command[2]);
            Integer col = Integer.parseInt(command[3]);
            game.tryHit(game.getPlayerGrids().get(toAttack).getGrid(), row, col);
        } else if(message.contains("/quit")) {

            // Under the hood, a client's username will be sent after a quit
            // command.
            command = message.split(" ");
            source.removeMessageListener(this);
            sourceClosed(players.get(command[1]));
            players.remove(command[1]);
            broadcast("!!! " + command[1] + " surrendered.");
        } else if(message.contains("/show")) {
            command = message.split(" ");
            String toShow = command[1];
            String username = command[2];
            game.display(toShow, username);
        }
    }

    @Override
    public void sourceClosed(MessageSource source) {
        source.removeMessageListener(this);
    }
}
