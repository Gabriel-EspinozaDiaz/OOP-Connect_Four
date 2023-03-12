package game;

import interfaces.*;
import players.*;
import util.GameSettings;
import util.InputUtil;

/**
 * This class is used to interact with the user.
 * It has been partially implemented, but needs to be completed by you.
 *
 * @author s2221276
 */
public class TextView implements IView
{
	public void displayWelcomeMessage()
	{
		System.out.println("Welcome to Connect Four!");
	}
	
	public void displayChosenMove(int move)
	{
		System.out.println("Selected move: " + move);
	}
	
	public void displayMoveRejectedMessage(int move)
	{
		System.out.println("The move (" + move + ") was rejected, please try again.");
	}
	
	public void displayActivePlayer(byte playerID)
	{
		System.out.println("\nPlayer " + playerID + " is next!");
	}
	
	public void displayGameStatus(byte gameStatus)
	{
		System.out.print("\nGame status: ");
		
		switch(gameStatus)
		{
			case IModel.GAME_STATUS_ONGOING: System.out.println("The game is in progress."); break;
			case IModel.GAME_STATUS_WIN_1: System.out.println("Player 1 has won!"); break;
			case IModel.GAME_STATUS_WIN_2: System.out.println("Player 2 has won!"); break;
			case IModel.GAME_STATUS_TIE: System.out.println("The game has ended in a tie!"); break;
			default : System.out.println("Error: Invalid/unknown game status"); break;
		}
	}
	
	public void displayBoard(IModel model) // VARIABLE GAME IMPLEMENTED
	{
		System.out.println("\n-------- BOARD --------");
		
		int nrRows = model.getGameSettings().nrRows;
		int nrCols = model.getGameSettings().nrCols;

		String[] slots = new String[nrRows*nrCols];

		int i = 0;

		while (i<nrRows*nrCols){
			for (int r = 0; r<nrRows; r++){ // for each row
				for (int c = 0; c<nrCols; c++){ // for each column in row[r]
					if (model.getPieceIn(c,r) == 0) {
						slots[i] = "_";
					} else if (model.getPieceIn(c,r) == 1) {
						slots[i] = "X";
					} else if (model.getPieceIn(c,r) == 2) {
						slots[i] = "O";
					}

					i++; // moves onto the next slot within the nested loop

				}
				for (int f = 0; f < nrCols; f++) { // Writes out a new column

					System.out.print(slots[f + r * nrCols] + " "); // Reminder: r * nrCols adds a row's worth of slots for every already present

				}
				if (r != nrRows-1) {
					System.out.print("\n");
				}
			}

			}

		} // remember that c and r are switched. Don't try and swap them unless necessary.

		// Here is an example of how the output should look:
		//_ _ O O _ _ X
		//_ _ X O _ _ X
		//_ O X X _ _ O
		//_ X X O _ X O
		//X O O X O O O
		//X O X X X O X

	
	public char requestMenuSelection()
	{
		// Display menu options.
		System.out.println("\n-------- MENU --------");
		System.out.println("(1) Start new game");
		System.out.println("(2) Resume saved game");
		System.out.println("(3) Change game settings");
		System.out.println("(4) Change players");
		
		// Request and return user input.
		System.out.print("Select an option and confirm with enter or use any other key to quit: ");
		return InputUtil.readCharFromUser();
	}
	
	public String requestSaveFileName()
	{
		System.out.println("\n-------- LOAD GAME --------");
		System.out.print("File name (e.g. Save.txt): ");
		return InputUtil.readStringFromUser();
	}
	
	public GameSettings requestGameSettings()
	{
		System.out.println("\n-------- GAME SETTINGS --------");
		
		// Replace these lines with code that asks the user to enter the values.
		//System.out.println("This feature has not yet been implemented, using default settings instead.");
		System.out.println("How many rows would you like?");

		int nrRows = InputUtil.readIntFromUser();

		while (nrRows < IModel.MIN_ROWS || nrRows > IModel.MAX_ROWS) {
			System.out.println("Cannot take fewer than 3 or more than 10 rows. Please choose another number");

			nrRows = InputUtil.readIntFromUser();

		}

		System.out.println("How many columns would you like?");
		int nrCols = InputUtil.readIntFromUser();

		while (nrCols < IModel.MIN_COLS || nrCols > IModel.MAX_COLS) {
			System.out.println("Cannot take fewer than 3 or more than 10 columns. Please choose another number");

			nrCols = InputUtil.readIntFromUser();

		}

		System.out.println("How many slots does a player need to connect in order to win?");
		int streak = InputUtil.readIntFromUser();

		while (streak < 3 || streak > 10) {
			System.out.println("Cannot take fewer than 3 or more than 10 slots for a streak. Please choose another number");

			streak = InputUtil.readIntFromUser();

		}
		
		// Wrap the selected settings in a GameSettings instance and return (leave this code here).
		return new GameSettings(nrRows, nrCols, streak);
	}
	
	public IPlayer requestPlayerSelection(byte playerId)
	{
		System.out.println("\n-------- CHOOSE PLAYER " + playerId + " --------");
		System.out.println("(1) HumanPlayer");
		System.out.println("(2) RoundRobinPlayer");
		System.out.println("(3) WinDetectingPlayer");
		System.out.println("(4) CompetitivePlayer");
		
		// Request user input.
		System.out.print("Select an option and confirm with enter (invalid input will select a HumanPlayer): ");
		char selectedPlayer = InputUtil.readCharFromUser();
		
		// Instantiate the selected player class.
		switch(selectedPlayer)
		{
			case '2': return new RoundRobinPlayer();
			case '3': return new WinDetectingPlayer();
			case '4': return new CompetitivePlayer();
			default: return new HumanPlayer();
		}
	}
}

