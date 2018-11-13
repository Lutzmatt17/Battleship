package server;

public class Grid {
    private String[][] grid;
    private static final String BATTLESHIP = "B";
    private static final String CARRIER = "C";
    private static final String SUBMARINE = "S";
    private static final String CRUISER = "R";
    private static final String DESTROYER = "D";
    private static final String HIT = "@";
    private static final String MISS = "X";
    private static final int SIZE = 10;

    public Grid(){
        this.grid = new String[SIZE][SIZE];
    }

    public void makeGrid(){

    }

    public String displayOwnerGrid() {
        String sepLine = "  +---+---+---+---+---+---+---+---+---+---+";
        StringBuilder build = new StringBuilder();
        for(int i = 0; i < this.grid.length; i++) {
            build.append("\t");
            build.append(i);
        }
        build.append("\n");
        build.append(sepLine);
        for(int row = 0; row < this.grid.length; row++) {
            build.append("\n");
            build.append(row);
            for(int col = 0; col < this.grid[row].length; col++) {
                build.append(" | ");
                //todo check if it is our player or not
                build.append(grid[row][col]);
            }
            build.append(" |");
            build.append("\n");
            build.append(sepLine);
        }
        return build.toString();
    }

    public String displayRivalGrid() {
        String sepLine = "  +---+---+---+---+---+---+---+---+---+---+";
        StringBuilder build = new StringBuilder();
        for(int i = 0; i < this.grid.length; i++) {
            build.append("\t");
            build.append(i);
        }
        build.append("\n");
        build.append(sepLine);
        for(int row = 0; row < this.grid.length; row++) {
            build.append("\n");
            build.append(row);
            for(int col = 0; col < this.grid[row].length; col++) {
                build.append(" | ");
                //todo check if it is our player or not
                build.append(grid[row][col]);
            }
            build.append(" |");
            build.append("\n");
            build.append(sepLine);
        }
        return build.toString();
    }
}
