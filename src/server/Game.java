package server;

import java.util.HashMap;

public class Game {
    /** Default symbol for a hit. */
    private static final String HIT = "@";

    /** Default symbol for a miss. */
    private static final String MISS = "X";

    private HashMap<String, Grid> playerGrids;

    int gridSize;

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



    public String display(String toShow, String userName){
        if(toShow.equals(userName)){
            return playerGrids.get(userName).displayOwnerGrid();
        }else{
            return playerGrids.get(toShow).displayRivalGrid();
        }
    }

    public HashMap<String, Grid> getPlayerGrids() {
        return playerGrids;
    }

    public void removePlayer(String player){
        playerGrids.remove(player);
    }
}
