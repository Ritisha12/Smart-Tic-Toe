/**
 * File: UpgradedGame.java
 * Author: Ritisha Perumalla
 * Description: This class manages an upgraded Tic Tac Toe game with customizable grid size and win conditions.
 * It handles game flow, player turns, move validation, and win/draw detection for M consecutive symbols.
 */

package com.McMaster.SE2OP32025;

import java.util.Scanner;

public class UpgradedGame {
    private UpgradedBoard board;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private int winCondition; // M markers in a row
    private Scanner scanner;

    // Initializes the game with a board, players, and win condition
    public UpgradedGame(int gridSize, int winCondition, Scanner scanner,int gameMode) {
        this.board = new UpgradedBoard(gridSize);
        this.winCondition = winCondition;
        this.scanner = scanner;

        switch (gameMode) {
            case 1: // Human vs Human
                this.player1 = new HumanPlayer("Player 1", 'O', scanner);
                this.player2 = new HumanPlayer("Player 2", 'X', scanner);
                break;
            case 2: // Human vs Computer
                this.player1 = new HumanPlayer("Player 1", 'O', scanner);
                this.player2 = new ComputerPlayer("Player 2", 'X');
                break;
            case 3: // Computer vs Computer
                this.player1 = new ComputerPlayer("Player 1", 'O');
                this.player2 = new ComputerPlayer("Player 2", 'X');
                break;
            default:
                System.out.println("Invalid choice. Defaulting to Human vs Computer.");
                this.player1 = new HumanPlayer("Player 1", 'O', scanner);
                this.player2 = new ComputerPlayer("Player 2", 'X');
        }
        this.currentPlayer = player1;
    }

    // Entry point that prompts for game parameters and starts the game
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Ask user grid size (N)
        System.out.print("Enter grid size (N, between 3 and 20): ");
        int gridSize = scanner.nextInt();

        // Ask user win condition (M)
        System.out.print("Enter win condition (M, number of consecutive markers to win): ");
        int winCondition = scanner.nextInt();

        // Validate grid size 
        if (gridSize < 3 || gridSize > 20) {
            System.out.println("Invalid grid size. Must be between 3 and 20.");
            return; 
        }

        // Validate win condition
        if (winCondition < 3 || winCondition > gridSize) {
            System.out.println("Invalid win condition. Must be at least 3 and no greater than N.");
            return;
        }
       
        // Ask user for game mode
        System.out.println("Select game mode:");
        System.out.println("1. Human vs Human");
        System.out.println("2. Human vs Computer");
        System.out.println("3. Computer vs Computer");
        int gameMode = scanner.nextInt();
        
        UpgradedGame game = new UpgradedGame(gridSize, winCondition, scanner,gameMode);
        game.start();
    }

    // Manages the main game loop with turn alternation, move validation, and game state checks
    public void start() {
        boolean gameRunning = true;
        
        // Main game loop
        while (gameRunning) {
            board.displayBoard();
            System.out.println(currentPlayer.getName() + "'s turn (" + currentPlayer.getSymbol() + ")");
            
            // Get move from current player
            int[] move = currentPlayer.getMove(board);
            int row = move[0];
            int col = move[1];

            // Check if move is within board bounds
            if (row < 0 || row >= board.getGridSize() || col < 0 || col >= board.getGridSize()) {
                System.out.println("Invalid move. Please enter values between 1 and " + board.getGridSize());
                continue;
            }

            // Attempt to mark the position and check for win or draw
            if (board.markPosition(row, col, currentPlayer.getSymbol())) {
                
                // Check for win condition
                if (board.Win(currentPlayer.getSymbol(), winCondition)) {
                    board.displayBoard();
                    System.out.println(currentPlayer.getName() + " wins!");
                    gameRunning = false; // end game 

                // Check for draw condition
                } else if (board.isFull()) {
                    board.displayBoard();
                    System.out.println("It's a draw!");
                    gameRunning = false; 

                // Continue game
                } else {
                    switchTurn();
                }

            // Cell already occupied
            } else {
                System.out.println("Cell is already used. Try again.");
            }
        }

        // Ask user for replay
        System.out.print("Play again? (y/n): ");
        String response = scanner.next().toLowerCase();
        if (response.equals("y")) {
            board.reset();
            currentPlayer = player1;
            start();
        }
    }

    // Alternates the current player between player1 and player2
    private void switchTurn() {
        if (currentPlayer == player1) {
            currentPlayer = player2;
        } else {
            currentPlayer = player1;
        }
    }
}
