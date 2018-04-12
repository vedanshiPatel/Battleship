

/**
 * @author Vedanshi Patel
 * 
 * BattleshipPlayer class is a base class for User and Computer classes. 
 */
public abstract class BattleshipPlayer 
{
	private int ships = 6; // each player gets 6 ships
	private int grenades = 4; // each player gets 4 grenades
	private int nbOfShipsLeft = 6; // used as a counter to keep track of number of ships left during the game
	private int skipCounter = 0; 
	private boolean myTurn = true;
	private int nbOfSkippedTurns = 0; // to keep track of number to skipped turns for that player when they hit a grenade
	
	public final char SHIP_MARK;
	public final char GRENADE_MARK;
	
	private char [][] battleGround = new char [8][8]; // array where each player stores their ships and grenades
	protected static char [][] sharedBattleGround = new char [8][8]; // array used to make sure if that coordinates is available to place ships and grenades (for Computer player)
	protected static char [][] finalBattle = new char [8][8]; // array which shows each attacks and their results (used for both players)
		
	
	/**
	 * BattleshipPlayer constructor
	 * @param markShip a character symbol, which represents player's ship
	 * @param markGrenade a character symbol, which represents player's grenade
	 */
	public BattleshipPlayer (char markShip, char markGrenade)
	{
		this.SHIP_MARK = markShip;
		this.GRENADE_MARK = markGrenade;
	}
	
	/**
	 * @return an integer value, number of ships remaining as player starts to place them in a battleGround
	 */
	public int getShips(){
		return ships;
	}
	
	/**
	 * @return an integer value, number of grenades remaining as player starts to place them in a battleGround
	 */
	public int getGrenades(){
		return grenades;
	}
	
	/**
	 * @return an integer value, number of ships remaining after each attacks
	 */
	public int getNbOfShipsLeft(){
		return nbOfShipsLeft;
	}

	/**
	 * @return an integer value, number of skipCounters for the player who hit the grenade
	 * when player hits a grenade skipCoutner is set to 2, and in each following play skipCoutner gets minus by 1. 
	 * Player can take turn again only when skipCounter is set back to 0.
	 */
	public int getSkipCounter (){
		return skipCounter;
	}
	
	/**
	 * @return a boolean value, it checks if player can take his/her turn. 
	 * player can take turn when myTurn is set to true.
	 */
	public boolean getMyTurn(){
		return myTurn;
	}
	
	/**
	 * @param turn a boolean value, to change the value of myTurn to true or false.
	 */
	public void setMyTurn(boolean turn){
		myTurn = turn;
	}
	
	/**
	 * @return an integer value, counted number of skipped turns each time player hits a grenade.
	 */
	public int getNbOfSkippedTurns(){
		return nbOfSkippedTurns;
	}
	
	/**
	 * printGrid is a method used to print 2-D array.
	 * @param battleGroundArray an 2-D array, an array that needs to be printed
	 */
	public static void printGrid (char [][] battleGroundArray)
	{
		for (int i=0; i<8; i++)
		{
			for (int j=0; j<8; j++)
			{
				System.out.print(battleGroundArray [i][j]);
			}
			System.out.println();
		}
	}
	
	/**
	 * setup method is abstract, where player will be allowed to place their ships and grenades. 
	 * Method that asks user for the coordinates, and if it's valid player's ship or grenade will be place.
	 * Player will be asked to place 6 ships and 4 grenades with in 8*8 grid.
	 * coordinate is a character followed by an integer.
	 * one ship or one grenade can be placed in each position.
	 * Result is stored in player's battleshipGround array.
	 */
	public abstract void setup();
	
	/**
	 * A method used to check if entered coordinate is out of grid (8*8)
	 * @param x an integer, represents an integer number entered in a coordinate - vertical value on the grid 
	 * @param y an integer, represents a character entered in a coordinate - Horizontal character value on the grid 
	 * 						(character that's being converted to an integer according to the grid)
	 * @return a boolean value, if true=out of grid, false = not out of grid
	 */
	public boolean isOutOfGrid (int x, int y)
	{
		if ((x<0) || (x>=8) || (y<0) || (y>=8)){
			System.out.println("Sorry, coordinate is out of grid. Try again!");
			return true;
		}
		else 
			return false;
		
	}
	
	/**
	 * A method to check if entered coordinates are already used or not.
	 * @param x an integer, represents an integer number entered in a coordinate - vertical value on the grid 
	 * @param y an integer, represents a character entered in a coordinate - Horizontal character value on the grid 
	 * 						(character that's being converted to an integer according to the grid)
	 * @param battleGroundGrid an 2-D character array, that's being check
	 * @return a boolean value, if true = already used before; false = yet not used
	 */
	public boolean isCoordinatesUsed (int x, int y, char [][] battleGroundGrid) // to make sure entered coordinates are not already used
	{
		if(battleGroundGrid[x][y] =='\u0000' || battleGroundGrid[x][y] == '-'){
			return false;
		}
		else if (battleGroundGrid[x][y] =='*')
			return true;
		else{
			return true;
		}
		 	 
	}
	
	/**
	 * A method to check if entered coordinates are already used or not. To determine it uses called player's battleGround values.
	 * This is a overloading method.
	 * @param x an integer, represents an integer number entered in a coordinate - vertical value on the grid 
	 * @param y an integer, represents a character entered in a coordinate - Horizontal character value on the grid 
	 * 						(character that's being converted to an integer according to the grid)
	 * @return a boolean value, if true = already used before; false = yet not used
	 */
	public boolean isCoordinatesUsed (int x, int y) // to make sure entered coordinates are not already used
	{
		return(isCoordinatesUsed(x,y,battleGround));
		 	 
	}
	
	/**
	 * A method used to place ship/grenade. It stores the symbol of player's ship/grenade at entered coordinate in each player's battleGround array. 
	 * Then, for the same coordinate sharedBattleGround, marks it with character 'O', 'O' as a coordinate is already occupied.
	 * 
	 * @param x an integer, represents an integer number entered in a coordinate - vertical value on the grid 
	 * @param y an integer, represents a character entered in a coordinate - Horizontal character value on the grid 
	 * 						(character that's being converted to an integer according to the grid)
	 */
	public void placeYourDefence (int x, int y)
	{
		if(getShips()>0)
		{
			battleGround[x][y] = SHIP_MARK;
			sharedBattleGround[x][y] = 'O'; // 'O' as a coordinate is already occupied.
			ships--;
		}
		else
		{
			battleGround[x][y] = GRENADE_MARK;
			sharedBattleGround[x][y] = 'O';
			grenades--;
		}
	}
	
	/**
	 * Method that fills the finalBattle array with '-' (full 8*8 grid).
	 */
	public static void prepareForFinalBattle(){
		for (int i=0; i<8; i++)
		{
			for (int j=0; j<8; j++)
				finalBattle [i][j] = '-';
		}
	}
	
	/**
	 * Method that checks if entered coordinate contains grenade.
	 * @param x an integer, represents an integer number entered in a coordinate - vertical value on the grid 
	 * @param y an integer, represents a character entered in a coordinate - Horizontal character value on the grid 
	 * 						(character that's being converted to an integer according to the grid)
	 * @param opponent a BattleshipPlayer you will be playing against.
	 * @return a boolean, true=grenade is present, false = grenade is not present
	 */
	private boolean isThereGrenade (int x, int y, BattleshipPlayer opponent){
		return (opponent.battleGround[x][y] == opponent.GRENADE_MARK || battleGround[x][y] == GRENADE_MARK);	
	}
	
	/**
	 * Method that checks if entered coordinate contains ship
	 * @param x an integer, represents an integer number entered in a coordinate - vertical value on the grid 
	 * @param y an integer, represents a character entered in a coordinate - Horizontal character value on the grid 
	 * 						(character that's being converted to an integer according to the grid)
	 * @param opponent a BattleshipPlayer you will be playing against.
	 * @return a boolean, true=ship is present, false = ship is not present
	 */
	private boolean isThereShip (int x, int y, BattleshipPlayer opponent){
		return (opponent.battleGround[x][y] == opponent.SHIP_MARK || battleGround[x][y] == SHIP_MARK);	
	}
	
	/**
	 * Method that takes action when entered coordinated contains grenade.
	 * It will allow cause player to skip his/her next turn, and opponent player will take turn twice. 
	 * Then, entered coordinate will be mark with grenade symbol according to whom it belongs. 
	 * player's number of skipped turns will be added by 1. 
	 * @param x an integer, represents an integer number entered in a coordinate - vertical value on the grid 
	 * @param y an integer, represents a character entered in a coordinate - Horizontal character value on the grid 
	 * 						(character that's being converted to an integer according to the grid)
	 * @param opponent a BattleshipPlayer you will be playing against.
	 */
	private void grenade (int x, int y, BattleshipPlayer opponent){
		System.out.println("BOOM! Grenade.");
		if (opponent.battleGround[x][y] == opponent.GRENADE_MARK) // if grenade belongs to opponent then their grenade symbol will appear on the finalBattle grid.
			finalBattle[x][y] = opponent.GRENADE_MARK;
		else if (battleGround[x][y] == GRENADE_MARK) // if grenade belongs to you then your grenade symbol will appear on the finalBattle grid.
			finalBattle[x][y] = this.GRENADE_MARK;

		skipCounter = 2; 
		nbOfSkippedTurns++;
	}
	
	/**
	 * Method used when entered coordinate (position of rocket) hits a ship.
	 * Then, entered coordinate will be mark with ship symbol according to whom it belongs.
	 * That ship will sink and according to whom that their belongs number of ships will be deducted by 1. 
	 * @param x an integer, represents an integer number entered in a coordinate - vertical value on the grid 
	 * @param y an integer, represents a character entered in a coordinate - Horizontal character value on the grid 
	 * 						(character that's being converted to an integer according to the grid)
	 * @param opponent a BattleshipPlayer you will be playing against.
	 */
	private void shipHit (int x, int y, BattleshipPlayer opponent){
		System.out.println("Ship Hit.");
		if (opponent.battleGround[x][y] == opponent.SHIP_MARK ){
			finalBattle[x][y] = opponent.SHIP_MARK;
			opponent.nbOfShipsLeft--;
		}	
		else if (this.battleGround[x][y] == this.SHIP_MARK){
			finalBattle[x][y] = this.SHIP_MARK;
			this.nbOfShipsLeft--;
		}
			
		
	}
	
	/**
	 * Method that is used when the rocket falls on a coordinate where there is nothing, then nothing happens. 
	 * That position will be marks with '*' in a finalBattle array. 
	 * @param x an integer, represents an integer number entered in a coordinate - vertical value on the grid 
	 * @param y an integer, represents a character entered in a coordinate - Horizontal character value on the grid 
	 * 						(character that's being converted to an integer according to the grid)
	 */
	private void nothing (int x, int y){
		System.out.println("nothing.");
		finalBattle[x][y] = '*';
	}
	
	/**
	 * Method used to display number to ships left for each player, during the Battleship game. 
	 * @param opponent a BattleshipPlayer you will be playing against.
	 */
	private void printData(BattleshipPlayer opponent)
	{
		int userShipsLeft = 0;
		int compShipsLeft = 0;

		if(this.SHIP_MARK == 's'){ 
			userShipsLeft = getNbOfShipsLeft();
			compShipsLeft = opponent.getNbOfShipsLeft();
		}
		else
		{
			userShipsLeft = opponent.getNbOfShipsLeft();
			compShipsLeft = getNbOfShipsLeft();
		}
		
		System.out.println("User's #ships left:" + userShipsLeft + " Computer's #ships left:" + compShipsLeft + "\n");
	}
	
	/**
	 * Method which is used when player places rocket's position and depending on which coordinate is entered, actions are taken. 
	 * - If the rocket falls on a coordinate where there is nothing, then nothing happens.
	 * - If rocket hits a ship, then that ship sinks. 
	 * - If rocket hits a grenade, then the player loses one turn. 
	 * - If the rocket falls on a coordinate that has been called before, regardless of what was there before, nothing happens. (Ex: a grenade can only explode once).");
	 * Then, final grid will be displayed, and players data as how many ships are left. 
	 * @param x an integer, represents an integer number entered in a coordinate - vertical value on the grid 
	 * @param y an integer, represents a character entered in a coordinate - Horizontal character value on the grid 
	 * 						(character that's being converted to an integer according to the grid)
	 * @param opponent a BattleshipPlayer you are playing against.
	 */
	public void play (int x, int y, BattleshipPlayer opponent)
	{
		if (isCoordinatesUsed(x, y,BattleshipPlayer.finalBattle)) 
			System.out.println("Position is already called.");
		else if (isThereGrenade(x, y, opponent))
			grenade(x, y, opponent);
		else if (isThereShip(x, y, opponent))
			shipHit(x, y, opponent);
		else
			nothing(x, y);
		
		if (opponent.getSkipCounter()!= 0) // if opponent's skipped counter is not 0 due grenade was hit, then opponent's skipped counter will be minus by 1. 
			opponent.skipCounter--;
		
		
		printGrid(finalBattle); // prints finalBattle array showing each player's actions
		
		printData(opponent); // prints data for each player (number of ships left)
		
		//calculates skipped counter when both players have hit grenade back to back.
		if (opponent.getSkipCounter() != 0 & getSkipCounter() != 0) 
		{
			opponent.skipCounter--;
			this.skipCounter--;
		}
		
		//calculates skipped counter when opponent player have hit grenade, and current player gets their turn twice in a row.
		if (opponent.getSkipCounter() != 0)
		{
			setMyTurn(true);
			opponent.setMyTurn(false);
		}
		else 
		{
			setMyTurn(false); // opponent will get his/her turn in next play. 
			opponent.setMyTurn(true);
		}
	}
	
	/**
	 * This is an abstract method, it allows each player to take turn, where each player places his/her rocket.
	 * asks player to enter a coordinate to place his/her rocket.
	 * according to where rocket has been placed actions are taken.
	 * (ex. if it's hits a ship, or rocket lands on a grenade, or rocket lands on nothing)
	 * @param opponent a BattleshipPlayer you are playing against. 
	 */
	public abstract void turn(BattleshipPlayer opponent);
	
	/**
	 * A method which shows where both players have stored their ships and grenades.
	 * @param player1 a BattleshipPlayer
	 * @param player2 a BattleshipPlayer
	 * @return a 2-Dimension character array
	 */
	public static char [][] showResult(BattleshipPlayer player1, BattleshipPlayer player2)
	{
		char [][] showResult = new char [8][8];
		
		for (int i=0; i<8; i++) // copying user's
		{
			for (int j=0; j<8; j++)
			{
				if (player1.battleGround[i][j]!= '\u0000')
					showResult[i][j]=player1.battleGround[i][j];
			}
		}
		
		for (int i=0; i<8; i++) // copying computer's
		{
			for (int j=0; j<8; j++)
			{
				if (player2.battleGround[i][j]!= '\u0000')
					showResult[i][j]=player2.battleGround[i][j];
			}
		}
		
		for (int i=0; i<8; i++) // copying computer's
		{
			for (int j=0; j<8; j++)
			{
				if (showResult[i][j] == '\u0000')
					showResult[i][j]='-';
			}
		}
		
		return showResult;
	}
	
}
