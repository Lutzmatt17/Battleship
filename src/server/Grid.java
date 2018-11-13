package server;

import java.util.Random;

public class Grid {
    private String[][] grid;
    private Ship ship;

    private static final String HIT = "@";
    private static final String MISS = "X";

    private static final int SIZE = 10;

    public Grid(){
        this.grid = new String[SIZE][SIZE];
    }

    public void makeGrid() {
        Random rand = new Random();

        int x = rand.nextInt(SIZE);
        int y = rand.nextInt(SIZE);
        int direction = rand.nextInt((4 - 1) + 1) + 1;
        for(int i = 0; i < SIZE; i++){
            for(int j = 0; j < SIZE; j++){
                grid[i][j] = " ";
            }
        }

        //will build a ship north

        if (direction == 1 && (y - 4) >= 0) {
            grid[x][y] = ship.CARRIER.getSymbol();
            grid[x][y - 1] = ship.CARRIER.getSymbol();
            grid[x][y - 2] = ship.CARRIER.getSymbol();
            grid[x][y - 3] = ship.CARRIER.getSymbol();
            grid[x][y - 4] = ship.CARRIER.getSymbol();

            //will build a ship south
        } else if (direction == 2 && (y + 4) < SIZE) {
            grid[x][y] = ship.CARRIER.getSymbol();
            grid[x][y + 1] = ship.CARRIER.getSymbol();
            grid[x][y + 2] = ship.CARRIER.getSymbol();
            grid[x][y + 3] = ship.CARRIER.getSymbol();
            grid[x][y + 4] = ship.CARRIER.getSymbol();
            //will build a ship west
        } else if (direction == 3 && (x - 4) >= 0) {
            grid[x][y] = ship.CARRIER.getSymbol();
            grid[x - 1][y] = ship.CARRIER.getSymbol();
            grid[x - 2][y] = ship.CARRIER.getSymbol();
            grid[x - 3][y] = ship.CARRIER.getSymbol();
            grid[x - 4][y] = ship.CARRIER.getSymbol();
            //will build a ship east
        } else if (direction == 4 && (x + 4) < SIZE) {
            grid[x][y] = ship.CARRIER.getSymbol();
            grid[x + 1][y] = ship.CARRIER.getSymbol();
            grid[x + 2][y] = ship.CARRIER.getSymbol();
            grid[x + 3][y] = ship.CARRIER.getSymbol();
            grid[x + 4][y] = ship.CARRIER.getSymbol();
        }
    }

    private boolean placeBattleship() {
        boolean placed = false;
        Random rand = new Random();
        int x = rand.nextInt(SIZE);
        int y = rand.nextInt(SIZE);
        int direction = rand.nextInt((4 - 1) + 1) + 1;

        // will build a ship north
        if (direction == 1 && (y - 3) >= 0) {
            grid[x][y] = ship.BATTLESHIP.getSymbol();
            grid[x][y - 1] = ship.BATTLESHIP.getSymbol();
            grid[x][y - 2] = ship.BATTLESHIP.getSymbol();
            grid[x][y - 3] = ship.BATTLESHIP.getSymbol();
            placed = true;

        //will build a ship south
        } else if (direction == 2 && (y + 3) < SIZE) {
            grid[x][y] = ship.BATTLESHIP.getSymbol();
            grid[x][y + 1] = ship.BATTLESHIP.getSymbol();
            grid[x][y + 2] = ship.BATTLESHIP.getSymbol();
            grid[x][y + 3] = ship.BATTLESHIP.getSymbol();
            placed = true;

        //will build a ship west
        } else if (direction == 3 && (x - 3) >= 0) {
            grid[x][y] = ship.BATTLESHIP.getSymbol();
            grid[x - 1][y] = ship.BATTLESHIP.getSymbol();
            grid[x - 2][y] = ship.BATTLESHIP.getSymbol();
            grid[x - 3][y] = ship.BATTLESHIP.getSymbol();
            placed = true;

        //will build a ship east
        } else if (direction == 4 && (x + 3) < SIZE) {
            grid[x][y] = ship.BATTLESHIP.getSymbol();
            grid[x + 1][y] = ship.BATTLESHIP.getSymbol();
            grid[x + 2][y] = ship.BATTLESHIP.getSymbol();
            grid[x + 3][y] = ship.BATTLESHIP.getSymbol();
            placed = true;
        }
        return placed;
    }

    private boolean placeSubmarine() {
        boolean placed = false;
        Random rand = new Random();
        int x = rand.nextInt(SIZE);
        int y = rand.nextInt(SIZE);
        int direction = rand.nextInt((4 - 1) + 1) + 1;

        // will build a ship north
        if (direction == 1 && (y - 2) >= 0) {
            grid[x][y] = ship.SUBMARINE.getSymbol();
            grid[x][y - 1] = ship.SUBMARINE.getSymbol();
            grid[x][y - 2] = ship.SUBMARINE.getSymbol();
            placed = true;

            //will build a ship south
        } else if (direction == 2 && (y + 2) < SIZE) {
            grid[x][y] = ship.SUBMARINE.getSymbol();
            grid[x][y + 1] = ship.SUBMARINE.getSymbol();
            grid[x][y + 2] = ship.SUBMARINE.getSymbol();
            placed = true;

            //will build a ship west
        } else if (direction == 3 && (x - 2) >= 0) {
            grid[x][y] = ship.SUBMARINE.getSymbol();
            grid[x - 1][y] = ship.SUBMARINE.getSymbol();
            grid[x - 2][y] = ship.SUBMARINE.getSymbol();
            placed = true;

            //will build a ship east
        } else if (direction == 4 && (x + 2) < SIZE) {
            grid[x][y] = ship.SUBMARINE.getSymbol();
            grid[x + 1][y] = ship.SUBMARINE.getSymbol();
            grid[x + 2][y] = ship.SUBMARINE.getSymbol();
            placed = true;
        }
        return placed;
    }

    public String displayOwnerGrid() {
        String sepLine = "  +---+---+---+---+---+---+---+---+---+---+";
        StringBuilder build = new StringBuilder();
        build.append(" ");
        for(int i = 0; i < this.grid.length; i++) {
            build.append("   ");
            build.append(i);
        }
        build.append("\n");
        build.append(sepLine);
        for(int row = 0; row < this.grid.length; row++) {
            build.append("\n");
            build.append(row);
            for(int col = 0; col < this.grid[row].length; col++) {
                build.append(" | ");
                build.append(grid[row][col]);
            }
            build.append(" |\n");
            build.append(sepLine);
        }
        return build.toString();
    }

    public String displayRivalGrid() {
        String sepLine = "  +---+---+---+---+---+---+---+---+---+---+";
        StringBuilder build = new StringBuilder();
        build.append(" ");
        for(int i = 0; i < this.grid.length; i++) {
            build.append("   ");
            build.append(i);
        }
        build.append("\n");
        build.append(sepLine);
        for(int row = 0; row < this.grid.length; row++) {
            build.append("\n");
            build.append(row);
            for(int col = 0; col < this.grid[row].length; col++) {
                build.append(" | ");
                String check = grid[row][col];
                if(check.equals(MISS) || check.equals(HIT)) {
                    build.append(check);
                } else {
                    build.append(" ");
                }
            }
            build.append(" |\n");
            build.append(sepLine);
        }
        return build.toString();
    }
}
