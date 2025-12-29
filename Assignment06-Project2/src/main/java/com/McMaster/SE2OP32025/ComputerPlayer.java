/**
 * File: ComputerPlayer.java
 * Author: Ritisha Perumalla
 * Description: This class represents the computer player in the Tic Tac Toe game.
 * It extends the Player class and implements random move generator for the computer.
 */

package com.McMaster.SE2OP32025;
import java.util.Random;

public class ComputerPlayer extends Player {
    private Random random = new Random();

    // Initializes the computer player with a name and symbol
    public ComputerPlayer(String playerName, char symbol) {
        super(playerName, symbol);
    }

    // Generates a random valid move on the board
    @Override
    public int[] getMove(Board board) {
        while (true) {
            int gridSize = board.getGridSize();
            // Generate random row and column
            int row = random.nextInt(gridSize);
            int col = random.nextInt(gridSize);
            
            // Return move if cell is empty
            if (board.cellEmpty(row, col)) {
                System.out.println(getName() + " plays at (" + (row + 1) + ", " + (col + 1) + ")");

                return new int[]{row, col};
            }
        }
    }
}


