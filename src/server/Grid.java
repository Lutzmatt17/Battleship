package server;

import java.util.Random;

public class Grid {
    private static final String HIT = "@";
    private static final String MISS = "X";
    private static final int SIZE = 10;
    private String[][] grid;

    public Grid(){
        this(SIZE);
    }

    public Grid(int size){
        if(size >= 5) {
            makeGrid(size);
        } else {
            System.out.println("Minimum grid size is 5");
            System.exit(1);
        }
    }

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

    public void updateGrid(String[][] grid){
        this.grid = grid;
    }

    public String[][] getGrid(){
        return grid;
    }

    /**
     * Method that tries to hit a ship on the specified opponents grid based off of the specified row or col coordinates
     * @param oppGrid - Opponents grid
     * @param row - x coordinate of attack
     * @param col - y coordinate of attack
     * @return true or false depending on whether the attack was a hit or not
     */
    public boolean tryHit(String[][] oppGrid, int row, int col){
        boolean result = false;
        if(oppGrid[row][col].equals("B") || oppGrid[row][col].equals("C") ||
                oppGrid[row][col].equals("D") || oppGrid[row][col].equals("R") || oppGrid[row][col].equals("S")){
            oppGrid[row][col] = HIT;
            result = true;
        }else{
            oppGrid[row][col] = MISS;
        }
        updateGrid(oppGrid);
        return result;
    }

    private void placeCarrier() {
        Random rand = new Random();
        boolean placed = false;
        while(!placed) {
            int row = rand.nextInt(grid.length);
            int col = rand.nextInt(grid.length);
            int direction = rand.nextInt(4);

            placed = placeShip(Ship.CARRIER, row, col, direction);
        }
    }

    private void placeDestroyer() {
        Random rand = new Random();
        boolean placed = false;
        while(!placed) {
            int row = rand.nextInt(grid.length);
            int col = rand.nextInt(grid.length);
            int direction = rand.nextInt(4);

            placed = placeShip(Ship.DESTROYER, row, col, direction);
        }
    }

    private void placeCruiser() {
        Random rand = new Random();
        boolean placed = false;
        while(!placed) {
            int row = rand.nextInt(grid.length);
            int col = rand.nextInt(grid.length);
            int direction = rand.nextInt(4);

            placed = placeShip(Ship.CRUISER, row, col, direction);
        }
    }

    private void placeBattleship() {
        Random rand = new Random();
        boolean placed = false;
        while(!placed) {
            int row = rand.nextInt(grid.length);
            int col = rand.nextInt(grid.length);
            int direction = rand.nextInt(4);

            placed = placeShip(Ship.BATTLESHIP, row, col, direction);
        }
    }

    private void placeSubmarine() {
        Random rand = new Random();
        boolean placed = false;
        while(!placed) {
            int row = rand.nextInt(grid.length);
            int col = rand.nextInt(grid.length);
            int direction = rand.nextInt(4);

            placed = placeShip(Ship.SUBMARINE, row, col, direction);
        }
    }

    private boolean placeShip(Ship ship, int row, int col, int direction) {
        boolean placed = false;
        // Building north
        if (direction == 0 &&
                noCollision(row, col, direction, ship.getSize())) {

            for (int i = 0; i < ship.getSize(); i++) {
                grid[row][col--] = ship.getSymbol();
            }
            placed = true;
            // Building south
        } else if (direction == 1 &&
                noCollision(row, col, direction, ship.getSize())) {

            for (int i = 0; i < ship.getSize(); i++) {
                grid[row][col++] = ship.getSymbol();
            }
            placed = true;
            // Building east
        } else if (direction == 2 &&
                noCollision(row, col, direction, ship.getSize())) {

            for (int i = 0; i < ship.getSize(); i++) {
                grid[row++][col] = ship.getSymbol();
            }
            placed = true;
            // Building west
        } else if (direction == 3 &&
                noCollision(row, col, direction, ship.getSize())) {

            for (int i = 0; i < ship.getSize(); i++) {
                grid[row--][col] = ship.getSymbol();
            }
            placed = true;
        }
        return placed;
    }

    private boolean noCollision(int row, int col, int direction, int size) {
        int i = 0;
        boolean rowInBound = true;
        boolean colInBound = true;
        while (rowInBound && colInBound && grid[row][col].equals(" ") && i < size) {
            switch (direction) {
                case 0:
                    col--;
                    if(col < 0) {
                        colInBound = false;
                    }
                    break;
                case 1:
                    col++;
                    if(col >= grid.length) {
                        colInBound = false;
                    }
                    break;
                case 2:
                    row++;
                    if(row >= grid.length) {
                        rowInBound = false;
                    }
                    break;
                case 3:
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

    public String displayOwnerGrid() {
        StringBuilder builder = new StringBuilder();
        String rowSep = "";
        String colSep = " | ";
        for(int i = 0; i < grid.length; i++) {
            if(i == 0) {
                builder.append(String.format("\n%7d", i));
            } else {
                builder.append(String.format("%4d", i));
            }
            rowSep += "---+";
        }
        builder.append(String.format("\n%5s%s", "+", rowSep));
        for(int j = 0; j < grid.length; j++) {
            builder.append(String.format("\n%3d", j));
            for(int sep = 0; sep < grid.length; sep++) {
                builder.append(String.format("%s%s", colSep, grid[j][sep]));
            }
            builder.append(String.format("%s\n%5s%s", colSep, "+", rowSep));
        }
        return builder.toString();
    }

    public String displayRivalGrid() {
        StringBuilder builder = new StringBuilder();
        String rowSep = "";
        String colSep = " | ";
        for(int i = 0; i < grid.length; i++) {
            if(i == 0) {
                builder.append(String.format("\n%7d", i));
            } else {
                builder.append(String.format("%4d", i));
            }
            rowSep += "---+";
        }
        builder.append(String.format("\n%5s%s", "+", rowSep));
        for(int j = 0; j < grid.length; j++) {
            builder.append(String.format("\n%3d", j));
            for(int sep = 0; sep < grid.length; sep++) {
                String check = grid[j][sep];
                if(check.equals(HIT) || check.equals(MISS)) {
                    builder.append(String.format("%s%s", colSep, check));
                } else {
                    builder.append(String.format("%s%s", colSep, " "));
                }
            }
            builder.append(String.format("%s\n%5s%s", colSep, "+", rowSep));
        }
        return builder.toString();
    }
}
