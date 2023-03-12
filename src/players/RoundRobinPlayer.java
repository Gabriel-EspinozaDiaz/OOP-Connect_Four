package players;

import interfaces.IModel;
import interfaces.IPlayer;
import util.GameSettings;

/**
 * Implementing this player is an intermediate task.
 * See assignment instructions for what to do.
 * If not attempting it, just upload the file as it is.
 *
 * @author s2221276
 */
public class RoundRobinPlayer implements IPlayer
{

	private int rRChoice;

	// A reference to the model, which you can use to get information about
	// the state of the game. Do not use this model to make any moves!
	private IModel model;
	
	// The constructor is called when the player is selected from the game menu.
	public RoundRobinPlayer()
	{
		// You can leave this empty.
	}
	
	// This method is called when a new game is started or loaded.
	// You can use it to perform any setup that may be required before
	// the player is asked to make a move. The second argument tells
	// you if you are playing as player 1 or player 2.
	public void prepareForGameStart(IModel model, byte playerId)
	{
		this.model = model;
		
		this.rRChoice = -1;

	}
	
	// This method is called to ask the player to take their turn.
	// The move they choose should be returned from this method.
	public int chooseMove()
	{
		GameSettings settings = model.getGameSettings(); // a do-while could've fixed the RR vs RR, as rRChoice being set to -1 might've ended the game early

		if (rRChoice == settings.nrRows){
			this.rRChoice = 0;
		} else {
			this.rRChoice++;
		}
		return rRChoice;

		// Until you have implemented this player, it will always concede.

	}
}
