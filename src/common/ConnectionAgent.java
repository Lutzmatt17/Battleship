package common;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ConnectionAgent extends MessageSource implements Runnable {
    private Socket socket;
    private Scanner in;
    private PrintStream out;
    private Thread thread;

    public ConnectionAgent(Socket socket) {
        this.socket = socket;
    }

    public void sendMessages(String message) {

    }

    public boolean isConnected() {
        return false;
    }

    public void close() {

    }

    @Override
    public void run() {

    }
}
