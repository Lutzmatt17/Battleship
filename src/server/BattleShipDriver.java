package server;

public class BattleShipDriver {
    public static void main(String[] args){
        Grid grid = new Grid();
        String grid2[][] = grid.makeGrid();
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                System.out.print(grid2[i][j]);
            }
        }

    }
}
