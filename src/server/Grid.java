package server;

public class Grid {
    private String[][] grid;
    private static final String BATTLESHIP = "B";
    private static final String CARRIER = "C";
    private static final String SUBMARINE = "S";
    private static final String CRUISER = "R";
    private static final String DESTROYER = "D";
    private static final int SIZE = 10;

    public Grid(){
        this.grid = new String[SIZE][SIZE];
    }

    public String[][] makeGrid(){
        String gridPiece1 = "+---+";
        String gridPiece2 = "|\t|";
        String subGrid = gridPiece1 + gridPiece2 + "\n" + gridPiece1;
        for(int i = 0; i < 10; i ++){
            for(int j = 0; j < 10; j++){
                grid[i][j] = subGrid;
            }
        }
        System.out.println(grid);
        return grid;
    }
}
