import java.util.Date;

/**
 * Holds the results of a single MLB game, for a single player pitching.
 * 
 * @author Colin McLaughlin & Kenton Van
 *
 */
public class PitchingGame implements Game {

	// Variables
	private Date date; // The day the game was played
	private boolean win; // Indicates if the player won the game
	private boolean qualityStart; // Pitches at least six innings and has 3 earned runs or fewer
	private int earnedRuns; // Number of runs scored without the aid of an error or passed ball
	private int strikeOuts; // Number of batters the pitcher struck out
	private int inningsPitched; // The number of innings the pitcher pitched
	double fantasyPoints; // The number of fantasy points earned during the game

	// Constructor
	PitchingGame(Date date, boolean win, boolean qualityStart, int earnedRuns, int strikeOuts, int inningsPitched) {
		this.date = date;
		this.win = win;
		this.qualityStart = qualityStart;
		this.earnedRuns = earnedRuns;
		this.strikeOuts = strikeOuts;
		this.inningsPitched = inningsPitched;
		this.fantasyPoints = calculateFantasyPoints();
	}

	// Methods
	/**
	 * Calculates the number of fantasy points earned in the game, according to
	 * FanDuel scoring. https://www.fanduel.com/rules
	 * 
	 * W=6pts Quality Start=4pts ER=-3pts SO=3pts IP=3pts
	 * 
	 * @return The number of fantasy points earned in the game
	 */
	private double calculateFantasyPoints() {
		double score = (win) ? 6.0 : 0.0;
		score += (qualityStart) ? 4.0 : 0.0;
		score += earnedRuns * -3.0;
		score += strikeOuts * 3.0;
		score += inningsPitched * 3.0;

		return score;

	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @return the win
	 */
	public boolean isWin() {
		return win;
	}

	/**
	 * @return the qualityStart
	 */
	public boolean isQualityStart() {
		return qualityStart;
	}

	/**
	 * @return the earnedRuns
	 */
	public int getEarnedRuns() {
		return earnedRuns;
	}

	/**
	 * @return the strikeOuts
	 */
	public int getStrikeOuts() {
		return strikeOuts;
	}

	/**
	 * @return the inningsPitched
	 */
	public int getInningsPitched() {
		return inningsPitched;
	}
	
	/**
	 * @return the fantasy points
	 */
	public double getFantasyPoints() {
		return fantasyPoints;
	}

}
