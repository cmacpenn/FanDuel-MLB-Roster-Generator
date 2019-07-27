import java.util.Date;

/**
 * Hold the results of a single MLB game, for a single player.
 * 
 * @author Colin McLaughlin & Kenton Van
 *
 */
public class Game {

	// Variables - batters
	private Date date; // The day the game was played TODO Date class
	private int singles; // The number of singles the player batted
	private int doubles; // The number of doubles the player batted
	private int triples; // The number of triples the player batted
	private int homeRuns; // The number of home runs the player batted
	private int runsBattedIn; // The number of runs batted in
	private int runs; // The number of runs
	private int walks; // The number of walks a player received. (AKA base on balls).
	private int stolenBases; // The number of stolen bases
	private int hitByPitches; // The number of times the player was hit by a pitch

	// Variables - pitchers
	private boolean win; // Indicates if the player won the game
	private boolean qualityStart; // Pitches at least six innings and has 3 earned runs or fewer
	private int earnedRuns; // Number of runs scored without the aid of an error or passed ball
	private int strikeOuts; // Number of batters the pitcher struck out
	private int inningsPitched; // The number of innings the pitcher pitched

	// Variables - both
	double fantasyPoints; // The number of fantasy points earned during the game

	// Constructor
	Game(Date date, int singles, int doubles, int triples, int homeRuns, int runsBattedIn, int runs, int walks,
			int stolenBases, int hitByPitches, boolean win, boolean qualityStart, int earnedRuns, int strikeOuts,
			int inningsPitched) {
		this.date = date;
		this.singles = singles;
		this.doubles = doubles;
		this.triples = triples;
		this.homeRuns = homeRuns;
		this.runsBattedIn = runsBattedIn;
		this.runs = runs;
		this.walks = walks;
		this.stolenBases = stolenBases;
		this.hitByPitches = hitByPitches;
		this.win = win;
		this.qualityStart = qualityStart;
		this.earnedRuns = earnedRuns;
		this.strikeOuts = strikeOuts;
		this.inningsPitched = inningsPitched;
		this.fantasyPoints = calculateFantasyPoints();
	}

	// Methods

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @return the singles
	 */
	public int getSingles() {
		return singles;
	}

	/**
	 * @return the doubles
	 */
	public int getDoubles() {
		return doubles;
	}

	/**
	 * @return the triples
	 */
	public int getTriples() {
		return triples;
	}

	/**
	 * @return the homeRuns
	 */
	public int getHomeRuns() {
		return homeRuns;
	}

	/**
	 * @return the runsBattedIn
	 */
	public int getRunsBattedIn() {
		return runsBattedIn;
	}

	/**
	 * @return the runs
	 */
	public int getRuns() {
		return runs;
	}

	/**
	 * @return the walks
	 */
	public int getWalks() {
		return walks;
	}

	/**
	 * @return the stolenBases
	 */
	public int getStolenBases() {
		return stolenBases;
	}

	/**
	 * @return the hitByPitch
	 */
	public int getHitByPitches() {
		return hitByPitches;
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
	 * Calculates the number of fantasy points earned in the game, according to
	 * FanDuel scoring. https://www.fanduel.com/rules
	 * 
	 * 1B=3pts 2B=6pts 3B=9pts HR=12pts RBI=3.5pts R=3.2pts BB=3pts SB=6pts HBP=3pts
	 * W=6pts Quality Start=4pts ER=-3pts SO=3pts IP=3pts
	 * 
	 * @return The number of fantasy points earned in the game
	 */
	private double calculateFantasyPoints() {
		double score = singles * 3.0;
		score += doubles * 6.0;
		score += triples * 9.0;
		score += homeRuns * 12.0;
		score += runsBattedIn * 3.5;
		score += runs * 3.2;
		score += walks * 3.0;
		score += stolenBases * 6.0;
		score += hitByPitches * 3.0;
		score += (win) ? 6.0 : 0.0;
		score += (qualityStart) ? 4.0 : 0.0;
		score += earnedRuns * -3.0;
		score += strikeOuts * 3.0;
		score += inningsPitched * 3.0;

		return score;

	}

}
