package server;

public class Grid {
    private String[][] grid;
    private Ship ship;
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
        build.append(" ");
        for(int i = 0; i < this.grid.length; i++) {
            build.append("   ");
            build.append(i);
        }
        build.append("\n");
        build.append(sepLine);
        for(int row = 0; row < this.grid.length; row++) {
            build.append("\n");
            build.append(row);
            for(int col = 0; col < this.grid[row].length; col++) {
                build.append(" | ");
                build.append(grid[row][col]);
            }
            build.append(" |\n");
            build.append(sepLine);
        }
        return build.toString();
    }

    public String displayRivalGrid() {
        String sepLine = "  +---+---+---+---+---+---+---+---+---+---+";
        StringBuilder build = new StringBuilder();
        build.append(" ");
        for(int i = 0; i < this.grid.length; i++) {
            build.append("   ");
            build.append(i);
        }
        build.append("\n");
        build.append(sepLine);
        for(int row = 0; row < this.grid.length; row++) {
            build.append("\n");
            build.append(row);
            for(int col = 0; col < this.grid[row].length; col++) {
                build.append(" | ");
                String check = grid[row][col];
                if(check.equals(MISS) || check.equals(HIT)) {
                    build.append(check);
                } else {
                    build.append(" ");
                }
            }
            build.append(" |\n");
            build.append(sepLine);
        }
        return build.toString();
    }
}
