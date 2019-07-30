/**
 * This class holds information on individual MLB players
 * 
 * @author Colin McLaughlin & Kenton Van
 *
 */
public class Player {

	// Variables
	private int id; // The FanDuel ID
	private String name; // The name of the player
	private int salary; // The salary (cost) of the player for a given game. Set by FanDuel
	private PlayerPosition position; // The position the player is eligible to play for, according to FanDuel
	private PlayerHistory history; // The performance of the player in previous games

	// Constructor
	Player(int id, String name, int salary, PlayerPosition position, PlayerHistory history) {
		this.id = id;
		this.name = name;
		this.salary = salary;
		this.position = position;
		this.history = history;
	}

	// Methods
	/**
	 * Getter for id.
	 * 
	 * @return The player's FanDuel ID.
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Getter for name.
	 * 
	 * @return The player's name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Getter for salary.
	 * 
	 * @return The salary for a given game.
	 */
	public int getSalary() {
		return salary;
	}

	/**
	 * Getter for position.
	 * 
	 * @return The position a player is eligible to play for.
	 */
	public PlayerPosition getPosition() {
		return position;
	}

	/**
	 * Getter for history.
	 * 
	 * @return The historical game results.
	 */
	public PlayerHistory getHistory() {
		return history;
	}

}
