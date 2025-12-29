/**
 * File: HumanPlayer.java
 * Author: Ritisha Perumalla
 * Description: This class represents the human player in the Tic Tac Toe game.
 * It extends the Player class and gets move input directly from the user.
 */

package com.McMaster.SE2OP32025;
import java.util.Scanner;

public class HumanPlayer extends Player {
    private Scanner scanner;

    // Initializes the human player with a name, symbol, and scanner for input
    public HumanPlayer(String playerName, char symbol, Scanner scanner) {
        super(playerName, symbol);
        this.scanner = scanner;
    }

   @Override
    public int[] getMove(Board board) {
        while (true) {
            System.out.print("Enter your move as (row,col): ");
            String input = scanner.next();

            // Remove parentheses and split by comma
            input = input.replace("(", "").replace(")", "");
            String[] parts = input.split(",");

            
            // Validate format
            if (parts.length != 2) {
                System.out.println("Invalid format. Please re-enter.");
                continue;
            }

            // Parse the row and column
            try {
                int row = Integer.parseInt(parts[0].trim()) - 1;
                int col = Integer.parseInt(parts[1].trim()) - 1;

                return new int[]{row, col};
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please re-enter.");
            }
        }
    }

}


