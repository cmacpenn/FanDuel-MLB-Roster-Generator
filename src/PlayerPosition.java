/**
 * Enum for the different MLB positions a player can hold in a FanDuel game.
 * 
 * @author Colin McLaughlin & Kenton Van
 *
 */
public enum PlayerPosition {
	PITCHER("Pitcher"), CATCHERORFIRSTBASE("CatcherOrFirstBase"), SECONDBASE("SecondBase"), THIRDBASE("ThirdBase"), 
	SHORTSTOP("ShortStop"), OUTFIELD("OutField");
	
	private final String position;
	
	PlayerPosition(String position){
		this.position = position;
	}
	
	public String getPlayerPosition() {
		return position;
	}
	
	@Override
    	public String toString() {
		return this.getPlayerPosition();
	}
	
}
