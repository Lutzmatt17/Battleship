package server;

public class BattleShipDriver {

    public static void main(String[] args) {
        Grid testGrid = new Grid(5);
        testGrid.displayOwnerGrid();
        testGrid.displayRivalGrid();

        for(int i = 0; i < testGrid.getGrid().length; i++) {
            for(int j = 0; j < testGrid.getGrid().length; j++) {
                System.out.println(String.format("Hitting %d x %d", i, j));
                testGrid.tryHit(testGrid.getGrid(), i, j);
                //testGrid.displayOwnerGrid();
                //testGrid.displayRivalGrid();
            }
        }
        testGrid.displayOwnerGrid();
        testGrid.displayRivalGrid();
    }
}
