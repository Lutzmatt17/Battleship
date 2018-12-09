package client;

import common.MessageListener;
import common.MessageSource;
import java.io.PrintStream;

public class PrintStreamMessageListener implements MessageListener {
    private PrintStream out;

    public PrintStreamMessageListener(PrintStream out) {
        this.out = out;
    }

    @Override
    public void messageReceived(String message, MessageSource source) {
        out.print(message);
    }

    @Override
    public void sourceClosed(MessageSource source) {
        source.removeMessageListener(this);
    }
}
