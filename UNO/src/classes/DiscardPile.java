package classes;

import java.util.ArrayList;
import java.util.Collections;

/**
 * The Discard pile class is used to hold the information of the cards being discarded by the user. It containts the
 * property of an ArrayList of cards in order to be able to dynamically except cards while also being able to shirk when 
 * the pick up pile needs to re-stock.
 * 
 * @author bryceschmisseur
 *
 */

public class DiscardPile
{
	public DiscardPile()
	{	
	}
	
	/**
	 * getTopCard returns the top card of the discard pile.
	 * @return Card that has the contents of the top card of the discard pile
	 */
	public Card getTopCard()
	{
		return disPile.get(0);
	}
	
	/**
	 * addCard takes in a card and adds it to the ArrayList of cards in the correct order
	 * @param aCard
	 */
	public void addCard(Card a)
	{
		//Declares and initializes a new ArrayList of cards 
		ArrayList<Card> newPile = new ArrayList<Card>();
		
		//Adds the new card to the top of the new discard pile
		newPile.add(a);
		
		//For loop to go through the old discard pile and add them to the new pile while maintaining order
		try
		{
			for(int i = 0; i < disPile.size(); i ++)
			{
				newPile.add(disPile.get(i));
			}
		}
		catch(java.lang.NullPointerException e)
		{
			
		}
		
		//Sets the discard pile to the new one that was created
		disPile = newPile;
	}
	
	/**
	 * restock is a method that is called when the pick up pile is empty. This method keeps the top card of the discard pile
	 * and returns an ArrayList of cards with the rest of the cards in the discard pile. 
	 * @return ArrayList<Card> of the Discard Pile
	 */
	public ArrayList<Card> restock()
	{
		//Declares and saves the information of the top card of the discard pile
		Card topCard = disPile.get(0);
		
		//Removes the top card of the pile as to not duplicate it
		disPile.remove(0);
		
		//Shuffles the rest of the deck to be able to automatically be able to put the card right in to the pick up pile
		Collections.shuffle(disPile);
		
		//Creates a copy of the discard pile that will be returned to the pickup pile
		ArrayList<Card> discardPile = disPile;
		
		//Removes all cards from the discard pile
		for(int i = 0; i < disPile.size(); i++)
		{
			disPile.remove(i);
		}
		
		//Returns the top card back to the top of the discard pile
		disPile.add(topCard);
		
		//returns the rest of the contents of the old discard piles
		return discardPile;
	}
	
	//Declares all variables that are used through-out the class
	private ArrayList<Card> disPile;
}
