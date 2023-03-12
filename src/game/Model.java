package game;

import interfaces.IModel;
import util.GameSettings;

/**
 * This class represents the state of the game.
 * It has been partially implemented, but needs to be completed by you.
 *
 * @author s2221276
 */
public class Model implements IModel
{

	// A reference to the game settings from which you can retrieve the number
	// of rows and columns the board has and how long the win streak is.
	private GameSettings settings;
	private byte[][] board;
	private byte player;
	private int turnsPassed;
	private byte concedeStatus;
	
	// The default constructor.
	public Model()
	{
		// You probably won't need this.
	}
	
	// A constructor that takes another instance of the same type as its parameter.
	// This is called a copy constructor.
	public Model(IModel model)
	{
		// You may (or may not) find this useful for advanced tasks.
	}
	
	// Called when a new game is started on an empty board.
	public void initNewGame(GameSettings settings) // VARIABLE GAME IMPLEMENTED
	{

		this.settings = settings;

		this.board = new byte[settings.nrRows][settings.nrCols];

		this.player = 1;

		this.turnsPassed = 0;

		for (int r = 0; r < settings.nrRows; r++){
			for (int c = 0; c < settings.nrCols; c++) {
				board[r][c] = board[0][0];
			}

		}

		// This method still needs to be extended.
	}
	
	// Called when a game state should be loaded from the given file.
	public void initSavedGame(String fileName)
	{
		// This is an advanced feature. If not attempting it, you can ignore this method.
	}
	
	// Returns whether or not the passed in move is valid at this time.
	public boolean isMoveValid(int move)
	{
		if (move == IModel.CONCEDE_MOVE) { // If the move is -1, this stops the next if statement from blocking the computer
			return true;
		} else if (move >= settings.nrCols || move <= -2){
			return false;
		} else if (board[0][move] != 0) { // If the move's top spot is occupied, reject the move
			return false;
		} else {
			return true;
	}
	}
	
	// Actions the given move if it is valid. Otherwise, does nothing.
	public void makeMove(int move) // VARIABLE GAME IMPLEMENTED
	{
		if (move == -1) {
			concedeStatus = player;
		} else {
			boolean columnEmpty = true;

			for (int i = 0; i < settings.nrRows; i++) {
				if (getPieceIn(move, i) != 0) {
					board[i - 1][move] = getActivePlayer();
					columnEmpty = false;
					break;
				}
			}
			if (columnEmpty) {
				board[settings.nrRows-1][move] = getActivePlayer();
			}
			if (player == 1) {
				player = 2;
			} else {
				player = 1;
			}

			turnsPassed++;

		}

	}
	
	// Returns one of the following codes to indicate the game's current status.
	// IModel.java in the "interfaces" package defines constants you can use for this.
	// 0 = Game in progress
	// 1 = Player 1 has won
	// 2 = Player 2 has won
	// 3 = Tie (board is full and there is no winner)
	public byte getGameStatus() // VARIABLE GAME IMPLEMENTED
	{
		int vCount = 0;
		int hCount = 0;
		int dLCount = 0;
		int dRCount = 0;

		/*for (int r = 0; r < settings.nrRows; r++) { // Vertical Streak
			dRCount = 0;
			for (int c = 0; c < settings.nrCols; c++){
				if (board[r][c] == ;) {

				}

			}

		} for (int s = 0; s < settings.nrRows*settings.nrCols; s++) { // Horizontal Streak
			if

		} for () { // Diagonal Left Streak
			if

		} for () { // Diagonal Right Streak
			if
	} */
		if (turnsPassed == settings.nrRows*settings.nrCols){
			return IModel.GAME_STATUS_TIE;
		} else if (concedeStatus == 1) {
			return IModel.GAME_STATUS_WIN_2;
	} else if (concedeStatus == 2) {
			return IModel.GAME_STATUS_WIN_1;


		} else {
			return IModel.GAME_STATUS_ONGOING;

	}

	}
	
	// Returns the number of the player whose turn it is.
	public byte getActivePlayer()
	{


		// Assuming it is always the turn of player 1.
		return player;
	}
	
	// Returns the owner of the piece in the given row and column on the board.
	// Return 1 or 2 for players 1 and 2 respectively or 0 for empty cells.
	public byte getPieceIn(int row, int column)
	{
// reminder, byte dtype returns an integer between -128 and 127

		return board[column][row];

	}

	
	// Returns a reference to the game settings, from which you can retrieve the
	// number of rows and columns the board has and how long the win streak is.
	public GameSettings getGameSettings()
	{
		return settings;
	}
	
	// =========================================================================
	// ================================ HELPERS ================================
	// =========================================================================
	
	// You may find it useful to define some helper methods here.
	
}
