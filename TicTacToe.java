package ticTacToe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
	
	//Globally accssible to all the methods
	//Static, so that we don't need to make an object to access this each time
	
	static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
	static ArrayList<Integer> computerPositions = new ArrayList<Integer>();
	
	public static void main(String[] args) {

	//2D array with three rows, three columns
		char[][] gameBoard = {
				{' ', '|', ' ', '|', ' '},
				{'-', '+', '-', '+', '-'},
				{' ', '|', ' ', '|', ' '},
				{'-', '+', '-', '+', '-'},
				{' ', '|', ' ', '|', ' '}
		};
		
		//Printing out the game board using two for loops.
		/*
		 * For each row inside the game board,
		 * and then for each symbol inside a row
		 */
		
		printGameBoard(gameBoard);
		
		/*A while loop to ask the user for their choice of board position,
		 * take in the user's choice as PlayerSqChoice,
		 * and then places the CPU position.
		*/
		while(true) {
			Scanner input = new Scanner(System.in);
			System.out.print("Enter your choice of square (1-9): ");
			int PlayerSqChoice = input.nextInt();
			
			//Ensures that the player's chosen position does not overwrite another position.
			while(playerPositions.contains(PlayerSqChoice) || computerPositions.contains(PlayerSqChoice)) {
				System.out.println("Position already taken! Try again");
				PlayerSqChoice = input.nextInt();
			}
			
			//Changes one of the "space" character elements of the 2D array (on the game board) to an X or an O
			//System.out.println(PlayerSqChoice);
			
			placePiece(gameBoard, PlayerSqChoice, "player");
			
			//Checks result after each player move
			//Prints the relevant message from the checkWinner method;
			String result = checkWinner();
			if(result.length() > 0) {
				System.out.println(result);
				break;
			}
			
			//Allows the comouter to put a symbol in a random position on the board
			Random rand = new Random();
			int ComputerSqChoice = rand.nextInt(9) + 1; //From 1 to 9
			
			//Ensures that the computer's chosen position does not overwrite another position.
			while(playerPositions.contains(ComputerSqChoice) || computerPositions.contains(ComputerSqChoice)) {
				ComputerSqChoice = rand.nextInt(9) + 1;
			}
			
			//Passes the random ComputerSqChoice into the placePiece method
			placePiece(gameBoard, ComputerSqChoice, "Computer");
			
			//Prints a new line for formatting purposes
			//System.out.println();
			//Prints the game board again, with the user's symbol in the chosen location
			printGameBoard(gameBoard);
			
			//Checks result after each CPU move
			//Prints the relevant message from the checkWinner method;
			result = checkWinner();
			if(result.length() > 0) {
				System.out.println(result);
				break;
			}
		}

	}
	
	//Method to print the game board. Takes in a 2D char array called "gameBoard", from the main method
	
	public static void printGameBoard(char[][] gameBoard) {
		
		for(char[] row : gameBoard) {
			for(char c : row) {
				//Print out the symbol
				System.out.print(c);
			}
			System.out.println(); //Prints each row on a new line (by printing a new line after each row)
		}	
	}
	
	public static void placePiece(char[][] gameBoard, int sqChoice, String user) {
		
		//Creating an initializing a default symbol (each symbol is a space when the game starts)
		char symbol = ' ';
		
		if(user.equals("player")) {
			symbol = 'X';
			playerPositions.add(sqChoice);
		}
		else if(user.equals("Computer")) {
			symbol = 'O';
		}
		
			switch(sqChoice) {
				case 1:
					gameBoard[0][0] = symbol;
					break;
				case 2:
					gameBoard[0][2] = symbol;
					break;
				case 3:
					gameBoard[0][4] = symbol;
					break;
				case 4:
					gameBoard[2][0] = symbol;
					break;
				case 5:
					gameBoard[2][2] = symbol;
					break;
				case 6:
					gameBoard[2][4] = symbol;
					break;
				case 7:
					gameBoard[4][0] = symbol;
					break;
				case 8:
					gameBoard[4][2] = symbol;
					break;
				case 9:
					gameBoard[4][4] = symbol;
					break;
		}
	}
	
	public static String checkWinner() {
		
		/*Winning conditions.
		 * These are the positions of user choices, any one of which will result in a win.
		 */
		List topRow = Arrays.asList(1, 2, 3);
		List middleRow = Arrays.asList(4, 5, 6);
		List bottomRow = Arrays.asList(7, 8, 9);
		
		List leftCol = Arrays.asList(1, 4, 7);
		List middleCol = Arrays.asList(2, 5, 8);
		List rightCol = Arrays.asList(3, 6, 9);
		
		List rightDescDiag = Arrays.asList(1, 5, 9);
		List leftDescDiag = Arrays.asList(3, 5, 7);
		
		//Creating a list of lists
		List<List> winningConditions = new ArrayList<List>();
		
		//Adding all of the individual winning condition lists to the overall winningConditions list
		winningConditions.add(topRow);
		winningConditions.add(middleRow);
		winningConditions.add(bottomRow);
		winningConditions.add(leftCol);
		winningConditions.add(middleCol);
		winningConditions.add(rightCol);
		winningConditions.add(rightDescDiag);
		winningConditions.add(leftDescDiag);
		
		for(List l : winningConditions) {
			if(playerPositions.containsAll(l)) {
				return "You win!";
			}
			else if(computerPositions.contains(l)) {
				return "The computer wins! You moron! Hahahaaaa";
			}
			//If the board is full, i.e., if the user's and CPU's positions add up to 9
			else if(playerPositions.size() + computerPositions.size() == 9) {
				return "It's a draw.";
			}
			else {
				return null;
			}
		}
		return null;
		
	}

}
