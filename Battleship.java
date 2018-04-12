
/**
 *Battleship class is a main class, through which Battleship game is being played by both players. 
 * 
 * @author: Vedanshi Patel
 * 
 * Purpose: In this assignment, Battleship game is created. There are two players User and Computer. 
 * Each player places their 6-ships and 4-grenades in a 8x8 grid; then both players takes turn to attack opponent.
 * To win, player have to sink all opponent's ships. 
 * Rules: 1) If the rocket falls on a coordinate where there is nothing, then nothing happens.
 * 		  2) If rocket hits a ship, then that ship sinks. 
 * 		  3) If rocket hits a grenade, then the player loses one turn. 
 * 		  4) If the rocket falls on a coordinate that has been called before, regardless of what was there before, nothing happens. (Ex: a grenade can only explode once).");
		
 *
 */
public class Battleship 
{

	public static void main(String[] args) 
	{
		
		
		User user = new User(); // user: new User object is created
		Computer comp = new Computer(); //comp: new Computer object is created
		
		System.out.print("************************************************************************************************************************************************");
		System.out.println("\nGame Instruction:" + 
		"\n - Each player can store their 6-SHIPS and 4-GRENADES in 8x8 grid." +
		"\n - Horizontal grid represents a character (A to H). Vertical grid represents an integer (1 to 8)." +
		"\n - When you enter a coordinate to place your ship/grenade/rocket, to enter a character is followed by an integer." +
		"\n - After placing all your ships and grenades, each player will get their turns to attack a rocket." + 
		"\n - If the rocket falls on a coordinate where there is nothing, then nothing happens (*)." +
		"\n - If rocket hits a ship, then that ship sinks." + 
		"\n - If rocket hits a grenade, then the player loses one turn." +
		"\n - If the rocket falls on a coordinate that has been called before, regardless of what was there before, nothing happens. (Ex: a grenade can only explode once).");
		System.out.println("************************************************************************************************************************************************");
		
		System.out.println("\nHi Let's Play Battleship!\n");
		
		// user-player places their ships and grenades.
		user.setup();
		// comp-player places their ships and grenades.
		comp.setup();
			
		
		System.out.println("\nOK, the computer placed its ships and grenades at rendom. \tLet's play! \n");
		
		
		BattleshipPlayer.prepareForFinalBattle();
		
		System.out.println();
		
		while ((user.getNbOfShipsLeft()>0) & (comp.getNbOfShipsLeft()>0)) // game will be continue until both players loses all their ships (out of 6)
		{
			if (user.getMyTurn()==true)	
				user.turn(comp);
			else if (comp.getMyTurn()==true)
				comp.turn(user);	
		}
		
		if (user.getNbOfShipsLeft()==0) // if user's number of ships reaches 0, the computer wins.
			System.out.println("Sorry! Computer Win.");
		if (comp.getNbOfShipsLeft()==0) // if comp's number of ships reaches 0, the user wins.
			System.out.println("YOU WIN!\n");
		
		System.out.println("This is how Battle Ground looked like. \n(Yours: ship=s , grenade=g) \n(Computer's: ship=S, grenade=G) \n");
		BattleshipPlayer.printGrid(BattleshipPlayer.showResult(user, comp));
		
		System.out.println("\nNumber of turns skipped by User:" + user.getNbOfSkippedTurns());
		System.out.println("Number of turns skipped by Computer:" + comp.getNbOfSkippedTurns());
		
	 // *** End of Program *** //
		
		
	}

}


