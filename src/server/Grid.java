package server;

import java.util.Random;

public class Grid {
    /** Default symbol for a hit. */
    private static final String HIT = "@";

    /** Default symbol for a miss. */
    private static final String MISS = "X";

    /** Default size for a grid. */
    private static final int SIZE = 10;

    /** Minimum size for a grid. */
    private static final int MIN_SIZE = 5;

    /** Used to randomly generate directions. */
    private static final int DIRECTION = 4;

    /** Used to randomly generate directions. */
    private static final int NORTH = 0;

    /** Used to randomly generate directions. */
    private static final int SOUTH = 1;

    /** Used to randomly generate directions. */
    private static final int EAST = 2;

    /** Used to randomly generate directions. */
    private static final int WEST = 3;

    /** 2D string array that represents a player's grid. */
    private String[][] grid;

    /**
     * Creates a grid of a default size.
     */
    public Grid(){
        this(SIZE);
    }

    /**
     * Creates a grid of a specified size.
     * @param size The specified size to create
     */
    public Grid(int size){
        if(size >= MIN_SIZE) {
            makeGrid(size);
        } else {
            System.out.println("Minimum grid size is 5");
            System.exit(1);
        }
    }

    /**
     * Initializes the grid and places all the ships.
     * @param size A specified size to make the 2D array.
     */
    private void makeGrid(int size) {
        this.grid = new String[size][size];
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                grid[i][j] = " ";
            }
        }
        placeCarrier();
        placeBattleship();
        placeCruiser();
        placeSubmarine();
        placeDestroyer();
    }

    /**
     * Updates the grid based on a passed in grid.
     * @param grid The specified grid to update it to.
     */


    /**
     * Returns the 2D array representing our grid.
     * @return The 2D array representing our grid.
     */
    public String[][] getGrid(){
        return grid;
    }

    public void setGrid(String[][] grid) {
        this.grid = grid;
    }

    /**
     * Places a Carrier based on a random location and direction.
     */
    private void placeCarrier() {
        Random rand = new Random();
        boolean placed = false;
        while(!placed) {
            int row = rand.nextInt(grid.length);
            int col = rand.nextInt(grid.length);
            int direction = rand.nextInt(DIRECTION);

            placed = placeShip(Ship.CARRIER, row, col, direction);
        }
    }

    /**
     * Places a Destroyer based on a random location and direction.
     */
    private void placeDestroyer() {
        Random rand = new Random();
        boolean placed = false;
        while(!placed) {
            int row = rand.nextInt(grid.length);
            int col = rand.nextInt(grid.length);
            int direction = rand.nextInt(DIRECTION);

            placed = placeShip(Ship.DESTROYER, row, col, direction);
        }
    }

    /**
     * Places a Cruiser based on a random location and direction.
     */
    private void placeCruiser() {
        Random rand = new Random();
        boolean placed = false;
        while(!placed) {
            int row = rand.nextInt(grid.length);
            int col = rand.nextInt(grid.length);
            int direction = rand.nextInt(DIRECTION);

            placed = placeShip(Ship.CRUISER, row, col, direction);
        }
    }

    /**
     * Places a Battleship based on a random location and direction.
     */
    private void placeBattleship() {
        Random rand = new Random();
        boolean placed = false;
        while(!placed) {
            int row = rand.nextInt(grid.length);
            int col = rand.nextInt(grid.length);
            int direction = rand.nextInt(DIRECTION);

            placed = placeShip(Ship.BATTLESHIP, row, col, direction);
        }
    }

    /**
     * Places a Submarine based on a random location and direction.
     */
    private void placeSubmarine() {
        Random rand = new Random();
        boolean placed = false;
        while(!placed) {
            int row = rand.nextInt(grid.length);
            int col = rand.nextInt(grid.length);
            int direction = rand.nextInt(DIRECTION);

            placed = placeShip(Ship.SUBMARINE, row, col, direction);
        }
    }

    /**
     * Places a specified ship based on a row, column, and direction.
     * @param ship The specified ship.
     * @param row The specified row to start placing the ship.
     * @param col The specified column to start placing the ship.
     * @param direction The specified direction to start building the ship.
     * @return True if the ship was placed, false otherwise.
     */
    private boolean placeShip(Ship ship, int row, int col, int direction) {
        boolean placed = false;
        if (direction == NORTH &&
                noCollision(row, col, direction, ship.getSize())) {

            for (int i = 0; i < ship.getSize(); i++) {
                grid[row][col--] = ship.getSymbol();
            }
            placed = true;
        } else if (direction == SOUTH &&
                noCollision(row, col, direction, ship.getSize())) {

            for (int i = 0; i < ship.getSize(); i++) {
                grid[row][col++] = ship.getSymbol();
            }
            placed = true;
        } else if (direction == EAST &&
                noCollision(row, col, direction, ship.getSize())) {

            for (int i = 0; i < ship.getSize(); i++) {
                grid[row++][col] = ship.getSymbol();
            }
            placed = true;
        } else if (direction == WEST &&
                noCollision(row, col, direction, ship.getSize())) {

            for (int i = 0; i < ship.getSize(); i++) {
                grid[row--][col] = ship.getSymbol();
            }
            placed = true;
        }
        return placed;
    }

    /**
     * Checks that the ship we're trying to place does not collide with any
     * other ships or the walls of the grid.
     * @param row The row to start placing our ship.
     * @param col The column to start placing our ship.
     * @param direction The direction in which we place our ship.
     * @param size The size of the ship we are placing.
     * @return True if their are no collisions, false otherwise.
     */
    private boolean noCollision(int row, int col, int direction, int size) {
        int i = 0;
        boolean rowInBound = true;
        boolean colInBound = true;
        while (rowInBound && colInBound && grid[row][col].equals(" ") && i < size) {
            switch (direction) {
                case NORTH:
                    col--;
                    if(col < 0) {
                        colInBound = false;
                    }
                    break;
                case SOUTH:
                    col++;
                    if(col >= grid.length) {
                        colInBound = false;
                    }
                    break;
                case EAST:
                    row++;
                    if(row >= grid.length) {
                        rowInBound = false;
                    }
                    break;
                case WEST:
                    row--;
                    if(row < 0) {
                        rowInBound = false;
                    }
                    break;
            }
            i++;
        }
        return i == size;
    }

    /**
     * Outputs the owner's grid to the console.
     */
    public String displayOwnerGrid() {
        StringBuilder builder = new StringBuilder();
        String rowSep = "";
        String colSep = " | ";
        for(int i = 0; i < grid.length; i++) {
            if(i == 0) {
                builder.append(String.format("\r\n%7d", i));
            } else {
                builder.append(String.format("%4d", i));
            }
            rowSep += "---+";
        }
        builder.append(String.format("\r\n%5s%s", "+", rowSep));
        for(int j = 0; j < grid.length; j++) {
            builder.append(String.format("\r\n%3d", j));
            for(int sep = 0; sep < grid.length; sep++) {
                builder.append(String.format("%s%s", colSep, grid[j][sep]));
            }
            builder.append(String.format("%s\r\n%5s%s", colSep, "+", rowSep));
        }
        System.out.println(builder.toString());
        return builder.toString();
    }

    /**
     * Outputs an opponent's grid to the console.
     */
    public String displayRivalGrid() {
        StringBuilder builder = new StringBuilder();
        String rowSep = "";
        String colSep = " | ";
        for(int i = 0; i < grid.length; i++) {
            if(i == 0) {
                builder.append(String.format("\r\n%7d", i));
            } else {
                builder.append(String.format("%4d", i));
            }
            rowSep += "---+";
        }
        builder.append(String.format("\r\n%5s%s", "+", rowSep));
        for(int j = 0; j < grid.length; j++) {
            builder.append(String.format("\r\n%3d", j));
            for(int sep = 0; sep < grid.length; sep++) {
                String check = grid[j][sep];
                if(check.equals(HIT) || check.equals(MISS)) {
                    builder.append(String.format("%s%s", colSep, check));
                } else {
                    builder.append(String.format("%s%s", colSep, " "));
                }
            }
            builder.append(String.format("%s\r\n%5s%s", colSep, "+", rowSep));
        }
       return builder.toString();
    }
}
