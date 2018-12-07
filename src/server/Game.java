package server;

import java.util.HashMap;

public class Game {
    HashMap<String, Grid> grids;

    public Game(int size) {
        grids = new HashMap<>();
    }

    public void play() {
    }

    public boolean move(String player, String message){
        if(message.contains("/attack")){
            grids.get(player).tryHit(grids.get(),)
        }
    }
}
