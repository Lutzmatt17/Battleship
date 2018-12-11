package client;

import common.MessageListener;
import common.MessageSource;
import java.io.PrintStream;

/**
 * Class responsible for writing messages to a PrintStream for users.
 * @author Matt Lutz and Brandon Townsend
 * @version December 2018
 */
public class PrintStreamMessageListener implements MessageListener {

    /** The PrintStream object to which we are printing to. */
    private PrintStream out;

    /**
     * Builds a new PrintStreamMessageListener object based on a specified
     * PrintStream.
     * @param out Where we are printing out to.
     */
    public PrintStreamMessageListener(PrintStream out) {
        this.out = out;
    }

    /**
     * Printing the message received from the subject.
     * @param message The message received by the subject
     * @param source  The source from which this message originated (if needed).
     */
    @Override
    public void messageReceived(String message, MessageSource source) {
        out.print(message + "\r\n");
    }

    /**
     * Closing this PrintStreamListener by removing it from the subject's
     * list of observers.
     * @param source The <code>MessageSource</code> that does not expect more messages.
     */
    @Override
    public void sourceClosed(MessageSource source) {
        source.removeMessageListener(this);
    }
}
