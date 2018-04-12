import java.util.Scanner;

/**
 * @author Vedanshi Patel
 *
 * User class is a derived method of BattleshipPlayer base class. 
 */
public class User extends BattleshipPlayer 
{	
	
	Scanner input = new Scanner (System.in);
	
 	/**
 	 * Constructor method for User class. 
 	 * It stores a character symbol for SHIP_MARK and GRENADE_MARK
 	 */
 	public User ()
	{
		super('s', 'g');
	}
	
	/**
	 * Method is used to check if entered coordinate is valid or not. 
	 * It makes sure that entered coordinate has is a String with string length equals to 2, NOT any random values. 
	 * @param location a string, entered coordinate.
	 * @return a boolean, true=coordinate is valid, false=coordinate is not valid
	 */
	public boolean isCoordinateValid (String location) // to make sure entered coordinated is valid
	{
		if (location.length()!=2)
		{
			System.out.println("Sorry, entered coordinates are NOT valid. Try again.");
			return false;
		}
		else
			return true;
	}
	
	/* (non-Javadoc)
	 * @see BattleshipPlayer#setup()
	 * 
	 * Method that asks user for the coordinates, and if it's valid player's ship or grenade will be place.
	 * Player will be asked to place 6 ships and 4 grenades with in 8*8 grid.
	 * coordinate is a character followed by an integer.
	 * one ship or one grenade can be placed in each position.
	 * Result is stored in player's battleshipGround array.
	 * 
	 */
	public void setup()
	{
		while (getShips()>0 || getGrenades()>0)
		{
			if(getShips()>0)
				System.out.print("Enter the coordinates for your ship #" + (7-getShips()) +": ");
			else
				System.out.print("Enter the coordinates for your grenade#" + (5-getGrenades()) + ": ");
			
			String coordinates = input.next();
			
			coordinates = coordinates.toUpperCase();
			
			if (isCoordinateValid(coordinates))
			{
				int x = (coordinates.charAt(1)-49);
				int y = (coordinates.charAt(0)-65);
				
				if (isOutOfGrid(x,y))
					continue;
				else if (isCoordinatesUsed(x, y))
				{
					System.out.println("Sorry, coordinate is already used. Try again!");
					continue;
				}
				else
					placeYourDefence(x,y);
			}
			else
				continue;
		}
	}
	
	/* (non-Javadoc)
	 * @see BattleshipPlayer#turn(BattleshipPlayer)
	 * 
	 * Method allows each player to take turn, where each player places his/her rocket.
	 * asks player to enter a coordinate to place his/her rocket.
	 * according to where rocket has been placed actions are taken.
	 * (ex. if it's hits a ship, or rocket lands on a grenade, or rocket lands on nothing)
	 * 
	 */
	public void turn(BattleshipPlayer opponent)
	{		
			if (getSkipCounter() == 0) // user turn will be skipped if hit opponent's grenade(comp).
			{
				System.out.print("Enter position of your rocket: ");
				String coordinates = input.next();
				
				coordinates = coordinates.toUpperCase();
				
				int x = (coordinates.charAt(1)-49);
				int y = (coordinates.charAt(0)-65);
				
				if (isCoordinateValid(coordinates)==false || (isOutOfGrid(x,y))){
					setMyTurn(true);
					opponent.setMyTurn(false);
					return;
				}
				else 
					play(x, y, opponent);
			}
		
	}

}
