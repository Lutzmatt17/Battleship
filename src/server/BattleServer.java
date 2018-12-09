package server;

import common.ConnectionAgent;
import common.MessageListener;
import common.MessageSource;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class BattleServer implements MessageListener {
    private ServerSocket serverSocket;
    private int current;
    private Game game;
    private boolean gameInProgress;
    private ArrayList<String> usernames;
    private HashMap<String, ConnectionAgent> players;

    public BattleServer(int port) {
        this(port, 10);
    }

    public BattleServer(int port, int size) {
        try {
            this.serverSocket = new ServerSocket(port);
            this.game = new Game(size);
            this.gameInProgress = false;
            this.current = 0;
            this.usernames = new ArrayList<>();
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

    private void joinReceived(String message, MessageSource source) {
        String[] command;

        command = message.split(" ");
        players.put(command[1], (ConnectionAgent) source);
        usernames.add(command[1]);
        game.getPlayerGrids().put(command[1], new Grid());
        broadcast(String.format("!!! %s has joined", command[1]));
    }

    private void playReceived(String message, MessageSource source) {
        String[] command;

        if(players.size() < 2) {
            broadcast("Not enough players to play the game.");
        } else {
            broadcast("The game begins.");
        }
    }

    private void attackReceived(String message, MessageSource source) {
        String[] command;

        command = message.split(" ");
        String toAttack = command[1];
        int row = Integer.parseInt(command[2]);
        int col = Integer.parseInt(command[3]);
        game.tryHit(toAttack, row, col);
        broadcast(String.format("Shots fired at %s by %s", command[1],
                command[4]));
    }

    private void quitReceived(String message, MessageSource source) {
        String[] command;

        command = message.split(" ");
        broadcast("!!! " + command[1] + " surrendered.");
        sourceClosed(players.get(command[1]));
        usernames.remove(command[1]);
        players.remove(command[1]);
    }

    private void showReceived(String message, MessageSource source) {
        String[] command;

        command = message.split(" ");
        String toShow = command[1];
        String username = command[2];
        ((ConnectionAgent) source).sendMessages(game.display(toShow, username));
    }

    @Override
    public void messageReceived(String message, MessageSource source) {
        if(message.contains("/join")) {
            joinReceived(message, source);
        } else if(message.contains("/play") && !gameInProgress) {
            playReceived(message, source);
            gameInProgress = true;
            broadcast(String.format("%s it is your turn", usernames.get(current)));
        } else if(gameInProgress) {
            boolean turnOver = false;
            if (message.contains(usernames.get(current)) && gameInProgress) {
                if (message.contains("/attack")) {
                    attackReceived(message, source);
                    turnOver = true;
                } else if (message.contains("/quit")) {
                    quitReceived(message, source);
                    turnOver = true;
                } else if (message.contains("/show")) {
                    showReceived(message, source);
                }
            }
            if(turnOver) {
                current++;
                if(current >= usernames.size()) {
                    current = 0;
                }
                if(usernames.size() == 1) {
                    gameInProgress = false;
                    broadcast(String.format("%s has won!", usernames.get(0)));
                } else {
                    broadcast(String.format("%s it is your turn", usernames.get(current)));
                }
            }
        } else if(message.contains("/attack") || message.contains("/quit") ||
                    message.contains("/show")){
            broadcast("Game not in progress.");
        }
    }

    @Override
    public void sourceClosed(MessageSource source) {
        source.removeMessageListener(this);
    }
}
