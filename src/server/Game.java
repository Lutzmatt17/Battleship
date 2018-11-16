package server;

import java.util.HashMap;

public class Game {
    private HashMap<String, Grid> players;
    private int gridSize;

    public Game(){
        players = new HashMap<>();
        gridSize = 0;
    }

    public boolean addPlayer(String name, int gridSize) {
        boolean added = false;
        if(players.isEmpty()) {
            players.put(name, new Grid(gridSize));
            this.gridSize = gridSize;
            added = true;
        } else {
            if(!players.containsKey(name)) {
                players.put(name, new Grid(this.gridSize));
                added = true;
            }
        }
        return added;
    }

    public boolean play() {
        return true;
    }
}
