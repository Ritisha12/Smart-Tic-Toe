/**
 * File: Board.java
 * Author: Ritisha Perumalla
 * Description: This class represents the Tic Tac Toe game board and creates
 * the game grid, marks positions, checking for wins, and helps reset the 
 * board for a new game.
 */

package com.McMaster.SE2OP32025;

public class Board {
   
    private char[][] grid;
    private int gridSize =3;


    public Board() {
        grid = new char[gridSize][gridSize];
        
        // Ensure all cells are empty at start
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                grid[i][j] = ' ';
            }
        }
    }

    // Displays the initial Tic Tac Toe board in a formatted grid
    public void displayBoard() {
        System.out.println("-------------"); 
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            
            // Print each cell in the row
            for (int j = 0; j < 3; j++) {
                System.out.print(grid[i][j] + " | ");
            }

            System.out.println();
            System.out.println("-------------");
        }
    }

    // Checks if the specified cell is empty and available for marking
    public boolean cellEmpty(int row, int col) {
        return grid[row][col] == ' ';
    }

    // Marks a position on the board with the player's symbol if the cell is empty
    public boolean markPosition(int row, int col, char symbol) {
        if (cellEmpty(row, col)) {
            grid[row][col] = symbol;
            return true;
        }
        return false;
    }

    // Checks if the player with the specified symbol has won the game.
    public boolean Win(char symbol) {
        
        // Check all rows and columns
        for (int i = 0; i < 3; i++) {
            if ((grid[i][0] == symbol && grid[i][1] == symbol && grid[i][2] == symbol) ||
                (grid[0][i] == symbol && grid[1][i] == symbol && grid[2][i] == symbol)) {
                return true;
            }
        }

        // Check diagonals
        if ((grid[0][0] == symbol && grid[1][1] == symbol && grid[2][2] == symbol) ||
            (grid[0][2] == symbol && grid[1][1] == symbol && grid[2][0] == symbol)) {
            return true;
        }
        return false;
    }

    // Checks if all cells on the board are filled
    public boolean isFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                //if found empty cell, return false, board is not full
                if (grid[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true; 
    }

    // Clears all cells on the board, resetting it to a new game
    public void reset() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j] = ' ';
            }
        }
    }

    // Returns the size of the board grid
    public int getGridSize() {
        return gridSize;
    }

    
}


