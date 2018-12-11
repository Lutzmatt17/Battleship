package server;

import java.util.HashMap;

/**
 * Class that contains the logic for the game of Battleship.
 * @author Matt Lutz and Brandon Townsend
 * @version December 2018
 */
public class Game {
    /** Default symbol for a hit. */
    private static final String HIT = "@";

    /** Default symbol for a miss. */
    private static final String MISS = "X";

    /** Mapping of a username to each grid. */
    private HashMap<String, Grid> playerGrids;

    /** Size to build the grids. */
    private int gridSize;

    /**
     * Constructor for a Game object.
     * @param size The size of grid to be built.
     */
    public Game(int size) {
        playerGrids = new HashMap<>();
        this.gridSize = size;
    }


    /**
     * Method that tries to hit a ship on the specified opponents grid based off of the specified row or col coordinates
     * @param player - Opponents grid
     * @param row - x coordinate of attack
     * @param col - y coordinate of attack
     * @return true or false depending on whether the attack was a hit or not
     */
    public boolean tryHit(String player, int row, int col){
        boolean result = false;
        String[][] oppGrid = playerGrids.get(player).getGrid();
        if(oppGrid[row][col].equals(Ship.BATTLESHIP.getSymbol()) ||
                oppGrid[row][col].equals(Ship.CARRIER.getSymbol()) ||
                oppGrid[row][col].equals(Ship.SUBMARINE.getSymbol()) ||
                oppGrid[row][col].equals(Ship.CRUISER.getSymbol()) ||
                oppGrid[row][col].equals(Ship.DESTROYER.getSymbol())){
            oppGrid[row][col] = HIT;
            result = true;
        }else{
            oppGrid[row][col] = MISS;
        }

        playerGrids.get(player).setGrid(oppGrid);
        return result;
    }

    /**
     * Returns true if all of a player's ships has been sunk.
     * @param player The player whom ships we are checking.
     * @return True if all the ships have been sunk, false otherwise.
     */
    public boolean sunkenShips(String player){
        boolean result = false;
        int counter = 0;
        String grid[][] = playerGrids.get(player).getGrid();
        for(int i = 0; i < gridSize; i++){
            for(int j = 0; j < gridSize; j++){
                if (grid[i][j].equals(HIT)){
                    counter++;
                }
            }
        }

        if (counter == 17){
            result = true;
        }

        return result;
    }

    /**
     * Displays a grid of a specified player.
     * @param toShow The grid of the player the user would like to see.
     * @param userName The username of the person issuing the command.
     * @return A String representation of a player's grid.
     */
    public String display(String toShow, String userName){
        if(toShow.equals(userName)){
            return playerGrids.get(userName).displayOwnerGrid();
        }else{
            return playerGrids.get(toShow).displayRivalGrid();
        }
    }

    /**
     * Returns the HashMap of player grids.
     * @return The HashMap of player grids.
     */
    public HashMap<String, Grid> getPlayerGrids() {
        return playerGrids;
    }

    /**
     * Adds a player to the game of battleship.
     * @param player The name of the player to add.
     */
    public void addPlayer(String player) {
        playerGrids.put(player, new Grid(gridSize));
    }

    /**
     * Removes of player from the game of battleship.
     * @param player The name of the player to remove.
     */
    public void removePlayer(String player){
        playerGrids.remove(player);
    }
}
