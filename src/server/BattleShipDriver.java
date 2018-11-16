package server;

public class BattleShipDriver {
    public static void main(String[] args){
        Grid grid = new Grid();
        System.out.println(grid.displayOwnerGrid());
        System.out.println(grid.displayRivalGrid());

        grid.tryHit(grid.getGrid(), 4, 4);
        grid.tryHit(grid.getGrid(), 3, 4);
        grid.tryHit(grid.getGrid(), 2, 4);

        System.out.println(grid.displayOwnerGrid());
        System.out.println(grid.displayRivalGrid());

        Grid grid2 = new Grid(5);
        System.out.println(grid2.displayOwnerGrid());
        System.out.println(grid2.displayRivalGrid());

        Grid grid3 = new Grid(20);
        System.out.println(grid3.displayOwnerGrid());
        System.out.println(grid3.displayRivalGrid());

    }
}
