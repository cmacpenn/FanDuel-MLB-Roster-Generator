import java.util.ArrayList;

/**
 * Contains the results of the past MLB games played by the player
 * 
 * @author Colin McLaughlin & Kenton Van
 *
 */
public class PlayerHistory {

	// Variables
	private ArrayList<Game> games; // Holds the MLB games the player has played
	private double averageFantasyPointsPerGame; // Average fantasy points across all games
	private double minimumFantasyPointsPerGame; // Minimum fantasy points across all games
	private double maximumFantasyPointsPerGame; // Maximum fantasy points across all games

	// Constructor
	/**
	 * Constructor for PlayerHistory
	 * 
	 * @param games The array of games the player has played in.
	 */
	PlayerHistory(ArrayList<Game> games) {
		this.games = games;
		averageFantasyPointsPerGame = calculateAverageFantasyPoints();
		minimumFantasyPointsPerGame = calculateMinimumFantasyPoints();
		maximumFantasyPointsPerGame = calculateMaximumFantasyPoints();
	}

	// Methods
	/**
	 * Calculates the average fantasy points earned per game.
	 * 
	 * @return The average fantasy points earned per game.
	 */
	private double calculateAverageFantasyPoints() {
		double sum = 0.0;
		for (Game game : games) {
			sum += game.getFantasyPoints();
		}
		return sum / games.size();
	}

	/**
	 * Calculates the minimum fantasy points earned per game.
	 * 
	 * @return The minimum fantasy points earned per game.
	 */
	private double calculateMinimumFantasyPoints() {
		// Checking to make sure that we've played at least one game so it doesn't error
		// If they haven't played any games, then assign it a zero (they're going to get
		// dropped anyway)
		double min = 0;
		if (games.size() >= 1) {
			min = games.get(0).getFantasyPoints();
		}
		for (Game game : games) {
			if (min > game.getFantasyPoints()) {
				min = game.getFantasyPoints();
			}
		}
		return min;
	}

	/**
	 * Calculates the maximum fantasy points earned per game.
	 * 
	 * @return The maximum fantasy points earned per game.
	 */
	private double calculateMaximumFantasyPoints() {
		double max = 0.0;
		for (Game game : games) {
			if (max < game.getFantasyPoints()) {
				max = game.getFantasyPoints();
			}
		}
		return max;
	}

	/**
	 * Getter for games
	 * 
	 * @return The array of games the player has played in.
	 */
	public ArrayList<Game> getGames() {
		return this.games;
	}

	/**
	 * @return the averageFantasyPointsPerGame
	 */
	public double getAverageFantasyPointsPerGame() {
		return averageFantasyPointsPerGame;
	}

	/**
	 * @return the minimumFantasyPointsPerGame
	 */
	public double getMinimumFantasyPointsPerGame() {
		return minimumFantasyPointsPerGame;
	}

	/**
	 * @return the maximumFantasyPointsPerGame
	 */
	public double getMaximumFantasyPointsPerGame() {
		return maximumFantasyPointsPerGame;
	}
}
