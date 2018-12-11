package server;

import common.ConnectionAgent;
import common.MessageListener;
import common.MessageSource;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Implementation of the server-side logic for a game of battleship.
 * @author Matt Lutz and Brandon Townsend
 * @version December 2018
 */
public class BattleServer implements MessageListener {

    /** The socket for which our server is built from. */
    private ServerSocket serverSocket;

    /** Keeps track of the current player's turn. */
    private int current;

    /** Game object to contain the logic of the battleship game. */
    private Game game;

    /** Keeps track of whether there is a game in progress. */
    private boolean gameInProgress;

    /** List of usernames for players.*/
    private ArrayList<String> usernames;

    /** List of usernames and their respective ConnectionAgents.*/
    private HashMap<String, ConnectionAgent> players;

    /**
     * Builds a new BattleServer based off of a specified port.
     * @param port The port from which we build a BattleServer.
     */
    public BattleServer(int port) {
        this(port, 10);
    }

    /**
     * Builds a BattleServer based off a specified port and grid size.
     * @param port The port from which we build a BattleServer.
     * @param size The size in which our grids should be built.
     */
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

    /**
     * Continually listens until our server is manually closed.
     */
    public void listen() {
        while (!serverSocket.isClosed()) {
            Socket connection = null;
            try {

                // accepts new connections and adds our server as a listener
                // to a created ConnectionAgent.
                connection = serverSocket.accept();
                ConnectionAgent agent = new ConnectionAgent(connection);
                agent.addMessageListener(this);
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

    /**
     * Broadcasts a specified message to all current players.
     * @param message The message to broadcast.
     */
    public void broadcast(String message) {
        for(String player: players.keySet()){
            players.get(player).sendMessages(message + "\r\n");
        }
    }

    /**
     * Executes code for when we receive a 'join' command.
     * @param message The message containing the command.
     * @param source The source from which the message originated.
     */
    private void joinReceived(String message, MessageSource source) {
        String[] command;

        command = message.split(" ");
        players.put(command[1], (ConnectionAgent) source);
        usernames.add(command[1]);
        game.addPlayer(command[1]);
        broadcast(String.format("!!! %s has joined", command[1]));
    }

    /**
     * Executes code for when we receive a 'play' command.
     * @param message The message containing the command.
     * @param source The source from which the message originated.
     */
    private void playReceived(String message, MessageSource source) {
        if(players.size() < 2) {
            broadcast("Not enough players to play the game.");
        } else {
            broadcast("The game begins.");
        }
    }

    /**
     * Executes code for when we receive a 'attack' command.
     * @param message The message containing the command.
     * @param source The source from which the message originated.
     */
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

    /**
     * Executes code for when we receive a 'quit' command.
     * @param message The message containing the command.
     * @param source The source from which the message originated.
     */
    private void quitReceived(String message, MessageSource source) {
        String[] command;

        command = message.split(" ");
        broadcast("!!! " + command[1] + " surrendered.");
        game.removePlayer(command[1]);
        sourceClosed(players.get(command[1]));
        usernames.remove(command[1]);
        players.remove(command[1]);
    }

    /**
     * Executes code for when we receive a 'show' command.
     * @param message The message containing the command.
     * @param source The source from which the message originated.
     */
    private void showReceived(String message, MessageSource source) {
        String[] command;

        command = message.split(" ");
        String toShow = command[1];
        String username = command[2];
        ((ConnectionAgent) source).sendMessages(game.display(toShow, username));
    }

    /**
     * Executes specified commands based on what message is received.
     * @param message The message received by the subject
     * @param source  The source from which this message originated (if needed).
     */
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
                    String[] toCheck = message.split(" ");
                    if(game.sunkenShips(toCheck[1])) {
                        broadcast(String.format("!!! %s has lost all their " +
                                "ships", toCheck[1]));
                        game.removePlayer(toCheck[1]);
                        sourceClosed(players.get(toCheck[1]));
                        usernames.remove(toCheck[1]);
                        players.remove(toCheck[1]);
                    }
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
                    broadcast(String.format("GAME OVER: %s wins!",
                            usernames.get(0)));
                } else {
                    broadcast(String.format("%s it is your turn", usernames.get(current)));
                }
            }
        } else if(message.contains("/attack") || message.contains("/quit") ||
                    message.contains("/show")){
            broadcast("Game not in progress.");
        }
    }

    /**
     * Removes the server as a listener for the ConnectionAgent.
     * @param source The <code>MessageSource</code> that does not expect more messages.
     */
    @Override
    public void sourceClosed(MessageSource source) {
        source.removeMessageListener(this);
    }
}
