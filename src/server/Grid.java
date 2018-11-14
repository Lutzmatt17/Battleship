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
        for(int i = 0; i < SIZE; i++){
            for(int j = 0; j < SIZE; j++){
                grid[i][j] = " ";
            }
        }
    }

    public void makeGrid() {
        boolean result = false;
        /*
        while(!result){
            if(placeCarrier() && placeBattleship() && placeCruiser() && placeSubmarine() && placeDestroyer()){
                result = true;
            }
        }
        */
        while(!result){
            if(noCollision()) {
                result = placeCarrier();
            }
        }

        result = false;
        while(!result){
            if(noCollision()) {
                result = placeBattleship();
            }
        }

        result = false;
        while(!result){
            if(noCollision()) {
                result = placeCruiser();
            }
        }

        result = false;
        while(!result){
            if(noCollision()) {
                result = placeSubmarine();
            }
        }

        result = false;
        while(!result){
            if(noCollision()) {
                result = placeDestroyer();
            }
        }

    }

    public void updateGrid(String[][] grid){
        this.grid = grid;
    }

    public String[][] getGrid(){
        return grid;
    }

    public boolean noCollision(){
        return true;
    }

    /**
     * Method that tries to hit a ship on the specified opponents grid based off of the specified x or y coordinates
     * @param oppGrid - Opponents grid
     * @param x - x coordianate of attack
     * @param y - y coordinate of attack
     * @return true or false depending on whether the attack was a hit or not
     */
    public boolean tryHit(String[][] oppGrid, int x, int y){
        boolean result = false;
        if(oppGrid[x][y].equals("B") || oppGrid[x][y].equals("C") ||
                oppGrid[x][y].equals("D") || oppGrid[x][y].equals("R") || oppGrid[x][y].equals("S")){
            oppGrid[x][y] = HIT;
            result = true;
        }else{
            oppGrid[x][y] = MISS;
        }
        updateGrid(oppGrid);
        return result;
    }

    public boolean placeCarrier() {
        boolean result = false;
        Random rand = new Random();
        int x = rand.nextInt(SIZE);
        int y = rand.nextInt(SIZE);
        int direction = rand.nextInt((4 - 1) + 1) + 1;
        //will build a ship south
        if (direction == 1 && (y - 4) >= 0) {
            grid[x][y] = ship.CARRIER.getSymbol();
            grid[x][y - 1] = ship.CARRIER.getSymbol();
            grid[x][y - 2] = ship.CARRIER.getSymbol();
            grid[x][y - 3] = ship.CARRIER.getSymbol();
            grid[x][y - 4] = ship.CARRIER.getSymbol();
            result = true;

            //will build a ship north
        } else if (direction == 2 && (y + 4) < SIZE) {
            grid[x][y] = ship.CARRIER.getSymbol();
            grid[x][y + 1] = ship.CARRIER.getSymbol();
            grid[x][y + 2] = ship.CARRIER.getSymbol();
            grid[x][y + 3] = ship.CARRIER.getSymbol();
            grid[x][y + 4] = ship.CARRIER.getSymbol();
            result = true;
            //will build a ship west
        } else if (direction == 3 && (x - 4) >= 0) {
            grid[x][y] = ship.CARRIER.getSymbol();
            grid[x - 1][y] = ship.CARRIER.getSymbol();
            grid[x - 2][y] = ship.CARRIER.getSymbol();
            grid[x - 3][y] = ship.CARRIER.getSymbol();
            grid[x - 4][y] = ship.CARRIER.getSymbol();
            result = true;
            //will build a ship east
        } else if (direction == 4 && (x + 4) < SIZE) {
            grid[x][y] = ship.CARRIER.getSymbol();
            grid[x + 1][y] = ship.CARRIER.getSymbol();
            grid[x + 2][y] = ship.CARRIER.getSymbol();
            grid[x + 3][y] = ship.CARRIER.getSymbol();
            grid[x + 4][y] = ship.CARRIER.getSymbol();
            result = true;
        }

        return result;
    }

    public boolean placeDestroyer() {
        boolean result = false;
        Random rand = new Random();
        int x = rand.nextInt(SIZE);
        int y = rand.nextInt(SIZE);
        int direction = rand.nextInt((4 - 1) + 1) + 1;
        //will build a ship north
        if (direction == 1 && (y - 1) >= 0) {
            grid[x][y] = ship.DESTROYER.getSymbol();
            grid[x][y - 1] = ship.DESTROYER.getSymbol();

            result = true;

            //will build a ship south
        } else if (direction == 2 && (y + 1) < SIZE) {
            grid[x][y] = ship.DESTROYER.getSymbol();
            grid[x][y + 1] = ship.DESTROYER.getSymbol();

            result = true;
            //will build a ship west
        } else if (direction == 3 && (x - 1) >= 0) {
            grid[x][y] = ship.DESTROYER.getSymbol();
            grid[x - 1][y] = ship.DESTROYER.getSymbol();

            result = true;
            //will build a ship east
        } else if (direction == 4 && (x + 1) < SIZE) {
            grid[x][y] = ship.DESTROYER.getSymbol();
            grid[x + 1][y] = ship.DESTROYER.getSymbol();

            result = true;
        }

        return result;
    }

    public boolean placeCruiser() {
        boolean result = false;
        Random rand = new Random();
        int x = rand.nextInt(SIZE);
        int y = rand.nextInt(SIZE);
        int direction = rand.nextInt((4 - 1) + 1) + 1;
        //will build a ship north
        if (direction == 1 && (y - 2) >= 0) {
            grid[x][y] = ship.CRUISER.getSymbol();
            grid[x][y - 1] = ship.CRUISER.getSymbol();
            grid[x][y - 2] = ship.CRUISER.getSymbol();

            result = true;

            //will build a ship south
        } else if (direction == 2 && (y + 2) < SIZE) {
            grid[x][y] = ship.CRUISER.getSymbol();
            grid[x][y + 1] = ship.CRUISER.getSymbol();
            grid[x][y + 2] = ship.CRUISER.getSymbol();


            result = true;
            //will build a ship west
        } else if (direction == 3 && (x - 2) >= 0) {
            grid[x][y] = ship.CRUISER.getSymbol();
            grid[x - 1][y] = ship.CRUISER.getSymbol();
            grid[x - 2][y] = ship.CRUISER.getSymbol();

            result = true;
            //will build a ship east
        } else if (direction == 4 && (x + 2) < SIZE) {
            grid[x][y] = ship.CRUISER.getSymbol();
            grid[x + 1][y] = ship.CRUISER.getSymbol();
            grid[x + 2][y] = ship.CRUISER.getSymbol();

            result = true;
        }

        return result;
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
