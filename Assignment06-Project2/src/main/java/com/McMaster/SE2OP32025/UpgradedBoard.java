/**
 * File: UpgradedBoard.java
 * Author: Ritisha Perumalla
 * Description: This class extends Board to provide an upgraded Tic Tac Toe board with customizable grid size.
 * It supports variable grid sizes (3-20) and win detection for M consecutive symbols.
 */

package com.McMaster.SE2OP32025;

public class UpgradedBoard extends Board {
   
    private char[][] grid;
    private int gridSize;

    // Constructs a board with specified grid size (validated between 3 and 20)
    public UpgradedBoard(int gridSize) {
        if (gridSize < 3 || gridSize > 20) {
            throw new IllegalArgumentException("Grid size must be between 3 and 20.");
        }
        this.gridSize = gridSize;
        grid = new char[gridSize][gridSize];
        reset(); // Initialize the board with empty spaces
    }

    // Mark a position if empty
    public boolean markPosition(int row, int col, char symbol) {
        // Validate that row and column are within grid bounds
        if (row < 0 || row >= gridSize || col < 0 || col >= gridSize) {
            return false;
        }
        
        // Mark the cell if it's empty
        if (grid[row][col] == ' ') {
            grid[row][col] = symbol;
            return true;
        }
        return false;
    }

    // Displays the board with formatted grid 
    public void displayBoard() {
        int size = getGridSize(); 

        // Build the horizontal separator 
        StringBuilder separator = new StringBuilder();
        for (int i = 0; i < size; i++) {
            separator.append("----");
        }
        // Print each row with separators
        for (int i = 0; i < size; i++) {
            System.out.println(separator);
            System.out.print("| ");
            for (int j = 0; j < size; j++) {
                System.out.print(grid[i][j] + " | ");
            }
            System.out.println();
        }
        System.out.println(separator); // final separator line
    }
    
    // Checks if the specified cell is empty and available for marking
    public boolean cellEmpty(int row, int col) {
        return grid[row][col] == ' ';
    }

    // Checks if M consecutive symbols exist starting from (row,col) in a given direction
    private boolean hasConsecutive(int row, int col, int dRow, int dCol, char symbol, int M) {
        for (int k = 0; k < M; k++) {
            int r = row + k * dRow;
            int c = col + k * dCol;
            if (r < 0 || r >= gridSize || c < 0 || c >= gridSize || grid[r][c] != symbol) {
                return false;
            }
        }
        return true;
    }

    // Determines if a player has won by checking for M consecutive symbols in all directions
    public boolean Win(char symbol, int M) {
        // Try each cell as a starting point
        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                // Check in 4 directions: right, down, diagonal, anti-diagonal
                if (hasConsecutive(row, col, 0, 1, symbol, M) || hasConsecutive(row, col, 1, 0, symbol, M) ||   
                    hasConsecutive(row, col, 1, 1, symbol, M) || hasConsecutive(row, col, 1, -1, symbol, M)) { 
                    return true;
                }
            }
        }
        return false;
    }

    // Checks if the board is completely filled
    public boolean isFull() {
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                
                //if found empty cell, return false, board is not full
                if (grid[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    // Clears the board by resetting all cells to empty
    public void reset() {
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                grid[i][j] = ' ';
            }
        }
    }

    // Returns the size of the board grid
    public int getGridSize() {
        return gridSize;
    }
}


