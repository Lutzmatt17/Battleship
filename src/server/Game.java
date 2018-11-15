package server;

public class Game {
    private Grid grid;


    public Game(){
        grid = new Grid();
    }

    public Grid getGrid() {
        return grid;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }
}
