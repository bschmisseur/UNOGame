package classes;

import java.util.ArrayList;

/**
 * Playershand is a class that is used to contain properties and method pertaining the the action and elements in the players
 * hand in a game of UNO. 
 * 
 * @author bryceschmisseur
 *
 */

public class Playershand
{
	/**
	 * Initializes an ArrayList of cards that contains the cards of the player
	 */
	public Playershand()
	{
		playerCard = new ArrayList<Card>();
	}
	
	/**
	 * Getter method for the players cards.
	 * 
	 * @return ArrayList<Card> of the players hand
	 */
	public ArrayList<Card> getHand()
	{
		return playerCard;
	}
	
	/**
	 * compareCard is a method that takes in a card and goes through the current players hand in order to determine
	 * if the player has a card that is playable. 
	 * 
	 * @param card
	 * @return Boolean that is if the player has a card that can be played
	 */
	public Boolean compareCard(Card c)
	{
		Boolean hasMatch = false;
		Card topCard = c;
		
		for(cardNum = 0; cardNum < playerCard.size(); cardNum ++)
		{
			Card pCard = playerCard.get(cardNum);
			
			if(!pCard.getSpecialty().equals("Non"))
			{	
				if(pCard.getSpecialty().equals("Wil"))
				{
					hasMatch = true;
					break;
				}
				
				else if(pCard.getSpecialty().equals("Drw") && pCard.getNum().equals("4"))
				{
					hasMatch = true;
					break;
				}
				
				else if(pCard.getSpecialty().equals("Drw") && pCard.getNum().equals("2"))
				{
					if(pCard.getColor().equals(topCard.getColor()))
					{
						hasMatch = true;
						break;
					}
					
					else if(topCard.getSpecialty().equals("Drw") && topCard.getNum().equals("2"))
					{
						hasMatch = true;
						break; 
					}
				}
				
				else if(pCard.getSpecialty().equals("Ski") || pCard.getSpecialty().equals("Rev"))
				{
					if(pCard.getColor().equals(topCard.getColor()))
					{
						hasMatch = true;
						break;
					}
					
					else if(topCard.getSpecialty().equals("Ski") || topCard.getSpecialty().equals("Rev"))
					{
						hasMatch = true; 
						break;
					}
				}
			}
			
			else if(pCard.getColor().equals(topCard.getColor()))
			{
				hasMatch = true;
				break;
			}
			
			else if(pCard.getNum().equals(topCard.getNum()))
			{
				hasMatch = true;
				break;
			}
			
		}
		
		return hasMatch;
	}
	
	/**
	 * Getter method that is the card found to be a match with in the method of compareCard 
	 * 
	 * @return Index of the card that can be played
	 */
	public int getCardNum()
	{
		return cardNum;
	}
	
	/**
	 * A method to see of the player has UNO or one card in the players had
	 * 
	 * @return Boolean
	 */
	public Boolean hasUno()
	{
		if(playerCard.size() == 1)
		{
			return true;
		}
		
		else 
		{
			return false;
		}
	}
	
	/**
	 * Method to get remove the card from the players hand and return the card that has been played.
	 * @return
	 */
	public Card playCard()
	{
		Card cardPlayed = playerCard.get(cardNum);
		playerCard.remove(cardNum);
		
		return cardPlayed;
	}
	
	/**
	 * Method to take in a card to add it to the players hand. 
	 * 
	 * @param Card
	 */
	public void getNewCard(Card c)
	{
		Card cardRes = c;
		playerCard.add(cardRes);
	}
	
	/**
	 * playerWon is a method to return an integer to determine if the player has won or not. 
	 * 
	 * @return Integer to determine the winner 1 is UNO | 2 is No Winner | 3 is Winner
	 */
	public int playerWon()
	{
		if(playerCard.size() == 1)
		{
			return 1;
		}
		
		else if(playerCard.size() == 0)
		{
			return 3;
		}
		
		else
		{
			return 2;
		}
	}
	
	//Declares all variables that are used through-out the class
	private ArrayList<Card> playerCard;
	private int cardNum;
}
