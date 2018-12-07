package server;

public class BattleShipDriver {

    /** Offline demo version
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
     */

    public static void main(String[] args) {
        BattleServer server = null;
        switch (args.length) {
            case 2:
                server = new BattleServer(Integer.parseInt(args[0]),
                        Integer.parseInt(args[1]));
                break;
            case 1:
                server = new BattleServer(Integer.parseInt(args[0]));
                break;
            default:
                System.out.println("Usage: java .server.BattleShipDriver <port> " +
                        "[board size]");
                System.exit(1);
        }
        try {
            server.listen();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
