package server;

/**
 * Enumeration built to help with the construction of ship objects.
 * @author Matt Lutz and Brandon Townsend
 * @version December 2018
 */
public enum Ship {
    CARRIER("C", 5),
    BATTLESHIP("B", 4),
    CRUISER("R", 3),
    SUBMARINE("S", 3),
    DESTROYER("D", 2);

    /** The String that represents a specified ship. */
    private String symbol;

    /** The size of a specified ship. */
    private int size;

    /**
     * Constructor used to build a specified ship.
     * @param symbol String that represents a ship.
     * @param size Size of ship.
     */
    private Ship(String symbol, int size) {
        this.symbol = symbol;
        this.size = size;
    }

    /**
     * Returns the string that represents a specified ship.
     * @return The string that represents a specified ship.
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Returns the size of a specified ship.
     * @return The size of a specified ship.
     */
    public int getSize() {
        return size;
    }
}
