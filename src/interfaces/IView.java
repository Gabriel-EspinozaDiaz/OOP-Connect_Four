package interfaces;

import util.GameSettings;

/**
 * This interface defines which methods a view must implement.
 * Do not change this code in any way.
 *
 * @author David Symons
 */
public interface IView
{
	// Methods to display a variety of information.
	void displayWelcomeMessage();
	void displayChosenMove(int move);
	void displayMoveRejectedMessage(int move);
	void displayActivePlayer(byte playerID);
	void displayGameStatus(byte gameStatus);
	void displayBoard(IModel model);
	
	// Methods to request user input.
	char requestMenuSelection();
	GameSettings requestGameSettings();
	IPlayer requestPlayerSelection(byte playerId);
	String requestSaveFileName();
}
