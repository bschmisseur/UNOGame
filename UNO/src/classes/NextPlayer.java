package classes;

/**
 * NextPlayer is a class to not only determine but also set the next player when the game is on going.
 * 
 * @author bryceschmisseur
 *
 */

public class NextPlayer
{
	/**
	 * Constructor to initializes all properties of the class
	 */
	public NextPlayer()
	{
		playerNum = 1;
		direction = 1;	
	}
	
	/**
	 * Getter method for the property of playerNum
	 * @return Player Number of the next player
	 */
	public int getNextPlayer()
	{
		return playerNum;
	}
	
	/**
	 * If that first card that is turned over is a wild card then this method is called to determine to whom is being affected.
	 * 
	 * @param Card
	 */
	public void startCard(Card card)
	{
		if(card.getSpecialty().equals("Ski") || card.getSpecialty().equals("Drw"))
		{
			playerNum = 1;
		}
		
		else if(card.getSpecialty().equals("Rev"))
		{
			playerNum = 3;
		}
		
		else
		{
			playerNum = 0;
		}
	}
	
	/**
	 * cardPlayed method takes in the card that has been played in order to increment the current player
	 * 
	 * @param card
	 */
	public void cardPlayed(Card card)
	{
		String special = card.getSpecialty();
		
		//If its a draw, wild, or skip played it will go two players ahead
		if(special.equals("Drw") || special.equals("Ski"))
		{
			if(direction == 1)
			{
				if(playerNum == 0 || playerNum == 1)
				{
					playerNum += 2;
				}
				
				else if(playerNum == 2)
				{
					playerNum = 0;
				}
				
				else if(playerNum == 3)
				{
					playerNum = 1;
				}		
			}
			
			else
			{
				if(playerNum == 3 || playerNum == 2)
				{
					playerNum -= 2;
				}
				
				else if(playerNum == 1)
				{
					playerNum = 3;
				}
				
				else if(playerNum == 0)
				{
					playerNum = 2;
				}	
			}
		}
		
		//If the card is a reverse then it has to change direction then go the the next player
		else if(special.equals("Rev") || special.equals("Non") || special.equals("Wil"))
		{
			if(special.equals("Rev"))
			{
				direction *= (-1);
			}
			
			if(direction == 1)
			{
				if(playerNum == 1 || playerNum == 2 || playerNum == 0)
				{
					playerNum++;
				}
				
				else if(playerNum == 3)
				{
					playerNum = 0;
				}	
			}
			
			else
			{
				if(playerNum == 1 || playerNum == 2 || playerNum == 3)
				{
					playerNum--;
				}
				
				else if(playerNum == 0)
				{
					playerNum = 3;
				}	
			}
		}		
	}
	
	/**
	 * displayPlayer is a method that returns the number of the current player in order to print it out. 
	 * 
	 * @return Player Number
	 */
	public int displayPlayer()
	{
		if(playerNum == 0)
		{
			return 1;
		}
		
		else if(playerNum == 1)
		{
			return 2;
		}
		
		else if(playerNum == 2)
		{
			return 3;
		}
		
		else
		{
			return 4;
		}
	}
	
	/**
	 * If if the discard pile has not changed but the players turn was complete cardPlayed determines the next player. 
	 */
	public void cardPlayed()
	{
		//If the direction is positive then the player number will increase by one
		if(direction == 1)
		{
			if(playerNum == 1 || playerNum == 2 || playerNum == 0)
			{
				playerNum++;
			}
			
			else if(playerNum == 3)
			{
				playerNum = 0;
			}	
		}
		
		//If the direction is opposite then the player number will decrement
		else
		{
			if(playerNum == 1 || playerNum == 2 || playerNum == 3)
			{
				playerNum--;
			}
			
			else if(playerNum == 0)
			{
				playerNum = 3;
			}	
		}
	}
	
	/**
	 * getAffectedPlayer is called when a specialty card is played and it needs to be determine who gets the effect of the 
	 * card played. 
	 * 
	 * @return index of the affected player
	 */
	public int getAffectedPlayer()
	{
		affectedPlayer = playerNum;
		
		if(direction == 1)
		{
			if(playerNum == 1 || playerNum == 2 || playerNum == 0)
			{
				affectedPlayer++;
			}
			
			else if(playerNum == 3)
			{
				affectedPlayer = 0;
			}	
		}
		
		else
		{
			if(playerNum == 1 || playerNum == 2 || playerNum == 3)
			{
				affectedPlayer--;
			}
			
			else if(playerNum == 0)
			{
				affectedPlayer = 3;
			}	
		}
		
		return affectedPlayer;
	}
	
	/**
	 * displayAffectedPlayer is a method that returns the number of the current player in order to print it out. 
	 * 
	 * @return Number of the affected Player
	 */
	public int displayAffectedPlayer()
	{
		if(affectedPlayer == 0)
		{
			return 1;
		}
		
		else if(affectedPlayer == 1)
		{
			return 2;
		}
		
		else if(affectedPlayer == 2)
		{
			return 3;
		}
		
		else
		{
			return 4;
		}
	}
	
	//Declares all variables that are used through-out the class
	private int playerNum;
	private int direction;
	private int affectedPlayer;
}
