import java.util.Date;

/**
 * Hold the results of a single MLB game, for a single player.
 * 
 * @author Colin McLaughlin & Kenton Van
 *
 */
public interface Game {

	/**
	 * Gets the date of the game
	 * @return The date of the game.
	 */
	public Date getDate();
	
	/**
	 * @return the fantasy points
	 */
	public double getFantasyPoints();

	
}
