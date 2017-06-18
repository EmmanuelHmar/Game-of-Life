package game;

import gameoflife.GameOfLifeBoard;

public class PersonalBoard extends GameOfLifeBoard {

    public PersonalBoard(int width, int height) {
        super(width, height);
    }

    public void turnToLiving(int x, int y){
        boolean[][] board = getBoard();
        board[x][y] = true;
    }

    @Override
    public boolean isAlive(int i, int i1) {
        boolean[][] board = getBoard();
        return board[i][i1];
    }   

    @Override
    public void turnToDead(int i, int i1) {
		boolean[][] = getBoard();
		board[i][i1] = true;
    }

    @Override
    public void initiateRandomCells(double v) {
		if(v==0.0 || v==1.0) {
            randomCellsActivator(v);
        } else if (v == 0.5) {
            initiateHalfCells(v);
        }
	}
	
	private void randomCellsActivator(double v) {
        for (int i = 0; i < super.getHeight(); i++) {
            for (int j = 0; j < super.getWidth(); j++) {
                if (v == 0.0) {
                    turnToDead(i, j);
                } else if (v == 1) {
                    turnToLiving(i, j);
                }
                }
            }
        }

    private void initiateHalfCells(double v) {
        Random r = new Random();
        int cellCount = super.getHeight() * super.getWidth();
        int count = 0;

        while (count < cellCount/2) {
            for (int i = 0; i < super.getHeight(); i++) {
                for (int j = 0; j < super.getWidth(); j++) {

                    if (1 + r.nextInt(10) >= 5) {
                        turnToLiving(i, j);
                        count++;
                    }

                    if (count >= cellCount / 2) {
                        break;
                    }
                }
            }
        }
    }


    @Override
    public int getNumberOfLivingNeighbours(int i, int i1) {
        return 0;
    }

    @Override
    public void manageCell(int i, int i1, int i2) {
    }
}
