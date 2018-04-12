import java.util.Random;

/**
 * @author Vedanshi Patel
 *
 * Computer class is a derived method of BattleshipPlayer base class. 
 *
 */
public class Computer extends BattleshipPlayer 
{
	Random compGenerator = new Random();
	
	/**
	 * Constructor method for Computer class. 
 	 * It stores a character symbol for SHIP_MARK and GRENADE_MARK
	 */
	public Computer()
	{
		super('S', 'G');
	}
	
	/* (non-Javadoc)
	 * @see BattleshipPlayer#setup()
	 * 
	 * Method that allows computer to randomly generate a coordinates to place to place ships or grenades.
	 * Player will be asked to place 6 ships and 4 grenades with in 8*8 grid.
	 * coordinate is a character followed by an integer.
	 * one ship or one grenade can be placed in each position. If position is occupied by opponent player (user) then cannot place at the same place.
	 * Result is stored in player's battleshipGround array.
	 */
	public void setup()
	{
		while (getShips()>0 || getGrenades()>0)
		{
			int x = compGenerator.nextInt(7);
			int y= compGenerator.nextInt(7);
			
			if (isCoordinatesUsed (x, y, BattleshipPlayer.sharedBattleGround) == false)
				placeYourDefence(x, y);
		}
	}
	
	/* (non-Javadoc)
	 * @see BattleshipPlayer#turn(BattleshipPlayer)
	 * 
	 * Method allows each player to take turn, where each player places his/her rocket.
	 * asks player to enter a coordinate to place his/her rocket.
	 * according to where rocket has been placed actions are taken.
	 * (ex. if it's hits a ship, or rocket lands on a grenade, or rocket lands on nothing)
	 */
	public void turn(BattleshipPlayer opponent)
	{
		if ((getSkipCounter()==0))
		{
			int x = compGenerator.nextInt(7);
			int y = compGenerator.nextInt(7);
			
			System.out.println("Enter position of computer's rocket: " + (char)(y+65) + (char)(x+49)); 
			
			play(x, y, opponent);

		}
	}
	
}
