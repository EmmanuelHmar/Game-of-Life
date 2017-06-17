package game;

import gameoflife.GameOfLifeBoard;

public class PersonalBoard extends GameOfLifeBoard {

    public PersonalBoard(int width, int height) {
        super(width, height);
    }

    public void turnToLiving(int x, int y){
        boolean[][] board = getBoard();
        board[x][y] = true;
//        System.out.println(board[x][y]); Prints if its dead or alive
    }

    @Override
    public boolean isAlive(int i, int i1) {
        boolean[][] board = getBoard();
        return board[i][i1];
    }   

    @Override
    public void turnToDead(int i, int i1) {

    }

    @Override
    public void initiateRandomCells(double v) {
    }

    @Override
    public int getNumberOfLivingNeighbours(int i, int i1) {
        return 0;
    }

    @Override
    public void manageCell(int i, int i1, int i2) {
    }
}
