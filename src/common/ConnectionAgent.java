package common;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * Class responsible for sending messages to and receiving messages from
 * remote hosts.
 * @author Matt Lutz and Brandon Townsend
 * @version December 2018
 */
public class ConnectionAgent extends MessageSource implements Runnable {

    /** The socket from which we build this ConnectionAgent. */
    private Socket socket;

    /** The Scanner from which we receive input based on the socket. */
    private Scanner in;

    /** PrintStream that is connected to where we send messages. */
    private PrintStream out;

    /** This ConnectionAgent's thread to help with concurrency. */
    private Thread thread;

    /**
     * Creates a new ConnectionAgent object based on a socket passed into it.
     * @param socket The socket from which we build our ConnectionAgent.
     */
    public ConnectionAgent(Socket socket) {
        try {
            this.socket = socket;
            in = new Scanner(this.socket.getInputStream());
            out = new PrintStream(this.socket.getOutputStream());
            thread = new Thread(this);
            thread.start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Returns this ConnectionAgent's thread.
     * @return This ConnectionAgent's thread.
     */
    public Thread getThread() {
        return this.thread;
    }

    /**
     * Used to send a message to the ConnectionAgent's observers.
     * @param message The message to send to the observers.
     */
    public void sendMessages(String message) {
        out.print(message);
    }

    /**
     * Returns true if the socket is connected, false otherwise.
     * @return True if socket is connected, false otherwise.
     */
    public boolean isConnected() {
        return socket.isConnected();
    }

    /**
     * Closes this ConnectionAgent and all of its working parts.
     */
    public void close() {
        try {
            closeMessageSource();
            socket.close();
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        } finally {
            in.close();
            out.close();
            thread.interrupt();
        }
    }

    /**
     * Runs the thread that exists in this ConnectionAgent
     */
    @Override
    public void run() {
        while(isConnected() && !socket.isClosed()) {
            String command = in.nextLine();

            //System.out.printf("to listeners: %s\r\n", command);

            notifyReceipt(command);
            if (command.contains("/quit")) {
                this.close();
            }
        }
    }
}
