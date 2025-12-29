/**
 * File: Player.java
 * Author: Ritisha Perumalla
 * Description: This is an abstract class that represents a player in the Tic Tac Toe game.
 * It defines common properties and behaviors for both human and computer players.
 */

package com.McMaster.SE2OP32025;

public abstract class Player {
    private String playerName;
    private char symbol;

    // Constructor that initializes player name and symbol
    public Player(String playerName, char symbol) {
        this.playerName = playerName;
        this.symbol = symbol;
    }

    //getter methods
    public String getName() {
        return playerName;
    }

    public char getSymbol() {
        return symbol;
    }

    // Abstract method for getting the player's move, implemented by subclasses
    public abstract int[] getMove(Board board);
}


