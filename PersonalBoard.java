package game;

import gameoflife.GameOfLifeBoard;
import java.util.Random;

//43.4

public class PersonalBoard extends GameOfLifeBoard {

    //Setup the constructor
    public PersonalBoard(int width, int height) {
        super(width, height);
    }

    //Set the input location to alive
    public void turnToLiving(int x, int y) {
        boolean[][] board = getBoard();//Get the board from the given pre-framework
        if (x < 0 || x >= super.getWidth() || y < 0 || y >= super.getHeight()) {
            return; //Nothing happens if location is outside board
        }

        board[x][y] = true;
    }

    //Set the location to dead
    @Override
    public void turnToDead(int x, int y) {
        boolean[][] board = getBoard();

        if (x < 0 || x >= super.getWidth() || y < 0 || y >= super.getHeight()) {
            return;
        }

        board[x][y] = false;
    }

    //Returns true if the input location is alive, False otherwise
    @Override
    public boolean isAlive(int x, int y) {
        boolean[][] board = getBoard();

        if (x < 0 || x >= super.getWidth() || y < 0 || y >= super.getHeight()) {
            return false; //False if location is outside board
        }

        return board[x][y];
    }

    //The input value percentage is set to alive
    @Override
    public void initiateRandomCells(double v) {
        if (v == 0.0 || v == 1.0) {
            initiateFullOrNoCells(v);//Go to these methods
        } else {
            initiateOtherCells(v);
        }
    }

    //If input is 1 every cell lives, all dies if 0
    private void initiateFullOrNoCells(double v) {
        for (int i = 0; i <= super.getHeight(); i++) {
            for (int j = 0; j <= super.getWidth(); j++) {
                if (v == 0.0) {
                    turnToDead(i, j);//Kill all if 0
                } else if (v == 1) {
                    turnToLiving(i, j);//Wake all if 1
                }
            }
        }
    }

    //The input % is alive (approximate)
    private void initiateOtherCells(double v) {
        Random r = new Random();
        int cellCount = super.getHeight() * super.getWidth();//How many cells are there
        double limit = v * 100;//Multiply by 100, ex: 0.2 becomes 20
        int howMany = (cellCount * (int) limit) / 100;//Number of cells we need to wake
        int count = 0;

        while (count < howMany) {
            for (int i = 0; i <= super.getHeight(); i++) {
                for (int j = 0; j <= super.getWidth(); j++) {

                    if (count >= howMany) {
                        break;//Break if count becomes more than number of cells we need
                    }

                    //1) Wake random cells
                    //2) These cell locations have to be dead else count will increase on already alive cells
                    if (1 + r.nextInt(10) >= 3 && !isAlive(i, j)) {
                        turnToLiving(i, j);
                        count++;
                    }
                }
            }
        }
    }

    //Returns the number of living neighbours
    @Override
    public int getNumberOfLivingNeighbours(int x, int y) {
        int count = 0;

        //Whatever the given location, the same loop procedure applies.
        for (int i = x - 1; i <= y + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                try {
                    //Skip current loop if points are equal to given input
                    if (j == x && i == y) {
                        continue;
                    }

                    //Skip current loop if location out of bounds
                    if (i < 0 || i > super.getWidth() || j < 0 || j > super.getHeight()) {
                        continue;
                    }

                    //Skip current loop if the loop tests for more height than required
                    if (i > y + 1) {
                        continue;
                    }

                    //Count increases if the testing location is alive
                    if (isAlive(j, i)) {
                        count++;
                    }

                } catch (Exception ignored) {
                    //Exception is ignored
                }
            }
        }
        return count;
    }

    @Override
    public void manageCell(int x, int y, int livingNeighbours) {
        boolean[][] board = getBoard();

        //Cel dies if less than 2 neighbours are alive
        if (livingNeighbours < 2) {
            board[x][y] = false;
        } else if (livingNeighbours == 2) {
            //Nothing happens in case of 2
        } else if (livingNeighbours > 3) {
            board[x][y] = false;//Also dies if > 3
        } else if (livingNeighbours == 3 && !board[x][y]) {
            board[x][y] = true;
        }
    }
}
