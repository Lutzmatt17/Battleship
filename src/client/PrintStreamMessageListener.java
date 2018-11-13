package client;

import common.MessageListener;
import common.MessageSource;
import java.io.PrintStream;

public class PrintStreamMessageListener implements MessageListener {
    private PrintStream out;

    private PrintStreamMessageListener(PrintStream out) {
        this.out = out;
    }

    @Override
    public void messageReceived(String message, MessageSource source) {

    }

    @Override
    public void sourceClosed(MessageSource source) {

    }
}
