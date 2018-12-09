package common;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ConnectionAgent extends MessageSource implements Runnable {
    private Socket socket;
    private Scanner in;
    private PrintStream out;
    private Thread thread;

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

    public void sendMessages(String message) {

        //System.out.printf("connection agent -> connection agent: %s\r\n",
        //        message);

        out.print(message);

        //System.out.println("sending completed");
    }

    public boolean isConnected() {
        return socket.isConnected();
    }

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

    @Override
    public void run() {
        while(isConnected()) {
            if(!socket.isClosed()) {
                String command = in.nextLine();

                //System.out.printf("to listeners: %s\r\n", command);

                notifyReceipt(command);
                if (command.contains("/quit")) {
                    this.close();
                }
            }
        }
    }
}
