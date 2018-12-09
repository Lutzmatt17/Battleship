package server;

import java.util.HashMap;

public class Game {
    HashMap<String, Grid> grids;
    int gridSize;

    public Game(int size) {
        grids = new HashMap<>();
        this.gridSize = size;
    }



}
