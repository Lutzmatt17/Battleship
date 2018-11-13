package client;

import common.MessageListener;
import common.MessageSource;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class BattleClient extends MessageSource implements MessageListener {
    private InetAddress host;
    private int port;
    private String username;

    public BattleClient(String hostname, int port, String username) {
        try {
            this.host = InetAddress.getByName(hostname);
        } catch (UnknownHostException | SecurityException e) {
            System.out.println("Error occurred while constructing a player");
        }
        this.port = port;
        this.username = username;

    }

    public void connect() {

    }

    public void send(String message) {

    }

    @Override
    public void messageReceived(String message, MessageSource source) {

    }

    @Override
    public void sourceClosed(MessageSource source) {

    }
}
