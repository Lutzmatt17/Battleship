package server;

public enum Ship {
    CARRIER("C", 5),
    BATTLESHIP("B", 4),
    CRUISER("R", 3),
    SUBMARINE("S", 3),
    DESTROYER("D", 2);

    private String symbol;
    private int size;

    private Ship(String symbol, int size) {
        this.symbol = symbol;
        this.size = size;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getSize() {
        return size;
    }
}
