package server;

public class BattleShipDriver {
    public static void main(String[] args){
        Grid grid = new Grid();
        grid.makeGrid();
        System.out.println(grid.displayOwnerGrid());
        System.out.println(grid.displayRivalGrid());
    }
}
