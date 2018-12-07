package client;

import common.ConnectionAgent;
import common.MessageListener;
import common.MessageSource;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class BattleClient extends MessageSource implements MessageListener {
    private InetAddress host;
    private int port;
    private String username;
    private ConnectionAgent agent;

    public BattleClient(String hostname, int port, String username) {
        try {
            this.host = InetAddress.getByName(hostname);
        } catch (UnknownHostException | SecurityException e) {
            System.out.println("Error occurred while constructing a player");
        }
        this.port = port;
        this.username = username;
        connect();
    }

    public void connect() {
        try {
            agent = new ConnectionAgent(new Socket(this.host, this.port));
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Reads input from the user.
     * @param message The input from the user.
     */
    public void send(String message) {
        this.agent.sendMessages(message);
    }

    @Override
    public void messageReceived(String message, MessageSource source) {

    }

    @Override
    public void sourceClosed(MessageSource source) {
        this.agent.close();
    }
}
