package server;

public class BattleShipDriver {
    public static void main(String[] args){
        Grid grid = new Grid();
        grid.makeGrid();
        System.out.println(grid.displayOwnerGrid());
        System.out.println();
        System.out.println(grid.displayRivalGrid());

        grid.tryHit(grid.getGrid(), 5, 5);
        grid.tryHit(grid.getGrid(), 3, 4);
        grid.tryHit(grid.getGrid(), 2, 6);
        System.out.println(grid.displayOwnerGrid());
        System.out.println();
        System.out.println(grid.displayRivalGrid());
    }
}
