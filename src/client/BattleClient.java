package client;

import common.ConnectionAgent;
import common.MessageListener;
import common.MessageSource;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Class that implements the client-side logic for this Battleship project.
 * @author Matt Lutz and Brandon Townsend
 * @version December 2018
 */
public class BattleClient extends MessageSource implements MessageListener {
    private InetAddress host;
    private int port;
    private String username;
    private ConnectionAgent agent;

    /**
     * Builds a new BattleClient object based on the parameters listed.
     * @param hostname The hostname to which the client will be connecting to.
     * @param port The port to which the client will be connecting to.
     * @param username The username the client has chosen.
     */
    public BattleClient(String hostname, int port, String username) {
        try {
            this.host = InetAddress.getByName(hostname);
        } catch (UnknownHostException | SecurityException e) {
            System.out.println("Error occurred while constructing a player");
        }
        this.port = port;
        this.username = username;
        this.addMessageListener(
                new PrintStreamMessageListener(System.out));
        connect();
        this.send("/join");
    }

    /**
     * Connects a ConnectionAgent to this client and connects the client to
     * the server.
     */
    public void connect() {
        try {
            agent = new ConnectionAgent(new Socket(this.host, this.port));
            this.agent.addMessageListener(this);
            this.send("");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Sends a user's input to the server.
     * @param message The input from the user.
     */
    public void send(String message) {
        this.agent.sendMessages(message + " " + this.username + "\r\n");
    }

    /**
     * Receives a message from the server.
     * @param message The message received by the subject
     * @param source  The source from which this message originated (if needed).
     */
    @Override
    public void messageReceived(String message, MessageSource source) {
        this.notifyReceipt(message);
    }

    /**
     * Closes this BattleClient object off from the server.
     * @param source The <code>MessageSource</code> that does not expect more messages.
     */
    @Override
    public void sourceClosed(MessageSource source) {
        closeMessageSource();
        this.agent.removeMessageListener(this);
        this.agent.getThread().interrupt();
    }
}
