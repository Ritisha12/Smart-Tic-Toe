/**
 * File: Game.java
 * Author: Ritisha Perumalla
 * Description: This class manages the main game logic for Tic Tac Toe.
 * It controls the game flow, player turns, and win/draw conditions. It
 * also handles restarting the game upon completion.
 * 
 */

package com.McMaster.SE2OP32025;

import java.util.Scanner;

public class Game {
    private Board board;
    private Player player1;
    private Player player2;
    private Player currentPlayer; // shows which players turn it is

    // Initializes the game with two players, human and computer
    public Game(Scanner scanner) {
        //setting up game
        board = new Board();
        
        // Ask user for game mode
        System.out.println("Choose game mode:");
        System.out.println("1. Human vs Human");
        System.out.println("2. Human vs Computer");
        System.out.println("3. Computer vs Computer");
        int gameMode = scanner.nextInt();

        // Set players based on chosen game mode
        switch (gameMode) {
            case 1: // Human vs Human
                player1 = new HumanPlayer("Player 1", 'O', scanner);
                player2 = new HumanPlayer("Player 2", 'X', scanner);
                break;
            case 2: // Human vs Computer
                player1 = new HumanPlayer("Player 1", 'O', scanner);
                player2 = new ComputerPlayer("Player 2", 'X');
                break;
            case 3: // Computer vs Computer
                player1 = new ComputerPlayer("Player 1", 'O');
                player2 = new ComputerPlayer("Player 2", 'X');
                break;
            default:
                System.out.println("Invalid choice. Defaulting to Human vs Computer.");
                player1 = new HumanPlayer("Player 1", 'O', scanner);
                player2 = new ComputerPlayer("Player 2", 'X');
        }
        
        currentPlayer = player1; // player 1 starts
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);   // Create scanner in main
        Game game = new Game(scanner);
        game.start();
    }

    // Runs the main game, handling player moves and checking win/draw conditions
    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean gameRunning = true;

        while (gameRunning) { 

            //prints current grid
            board.displayBoard();

            //shows who's turn it is
            System.out.println(currentPlayer.getName() + "'s turn (" + currentPlayer.getSymbol() + ")");

            // move input
            int[] move = currentPlayer.getMove(board);
            int row = move[0];
            int col = move[1];

            // Validate move is within bounds
            int gridSize = board.getGridSize();
                if (row < 0 || row >= gridSize || col < 0 || col >= gridSize) {
                    System.out.println("Invalid move. Please enter values between 1 and " + gridSize);
                    continue;
                }

            //marking the board
            if (board.markPosition(row, col, currentPlayer.getSymbol())) {
                // check win
                if (board.Win(currentPlayer.getSymbol())) {
                    board.displayBoard();
                    System.out.println(currentPlayer.getName() + " wins!");
                    gameRunning = false; // stop loop
                }
                // check if players draw
                else if (board.isFull()) {
                    board.displayBoard();
                    System.out.println("It's a draw!");
                    gameRunning = false; // stop loop
                }
                // Otherwise, keep playing
                else {
                    switchTurn();
                }
            } else {
                System.out.println("Cell is already used. Try again.");
            }
        }

        // after loop ends, ask if user wants to play again
        System.out.print("Do you want to play again? (y/n): ");
        
        String restartResponse = scanner.next().toLowerCase();
        if (restartResponse.equals("y")) {
            board.reset();   // clear the board
            start();         // restarts the game 
        }
        
    }

    // Switches the current player turn between player1 and player2.
    private void switchTurn() {
       if (currentPlayer == player1) {
            currentPlayer = player2;
       } else {
            currentPlayer = player1;
       }
    }


}
