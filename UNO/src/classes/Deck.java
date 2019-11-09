package classes;

import java.io.File;
import java.io.FileNotFoundException;

/*
 * Author: Bryce Schmisseur
 * Date: 30 October 2018
 * This is my own work
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * By reading a file the Deck class holds a Array of cards that contains all playable card in the UNO Game.
 * This class is used as the Pick Up pile in the game of UNO. Also the Deck class contains methods to re-stock 
 * and shuffle the cards throughout the game.
 * 
 * @author bryceschmisseur
 *
 */

public class Deck
{
	/**
	 * Constructor that initializes the ArrayList of cards by reading in the file, 'CardList.in' and creats card object for
	 * all cards in the UNO game. 
	 */
	public Deck()
	{
		//Initializes the File and the ArrayList
		File file = new File("CardList.in");
		deckCon = new ArrayList<Card>();
		
		try
		{
			//Creates a scanner object to read in the contents of the file
			@SuppressWarnings("resource")
			Scanner scan = new Scanner(file);
			
			while(scan.hasNextLine())
			{
				//Gather all information of the card from the line
				String line = scan.nextLine();
				String cCol = line.substring(0, 3);
				String cNum = (line.substring(4, 5));
				String cSpe = line.substring(6);
				
				//Initialize variables that are not needed for the card
				if(cCol.contains(" "))
				{
					cCol = null;
				}
				
				if(cNum.contains(" "))
				{
					cNum = null;
				}
				
				
				if(cSpe.contains(" "))
				{
					cSpe = null;
				}
				
				//Add the card to the deck
				deckCon.add(new Card(cCol, cNum, cSpe));
			}
		} 
		catch (FileNotFoundException e)
		{
			System.out.println("ERROR FILE NOT FOUND!");
		}
	}
	
	/**
	 * Method to return the top card of the pick up pile
	 * @return Card of the top of the deck
	 */
	public Card getTopCard()
	{
		return deckCon.get(0);
	}
	
	/**
	 * Getter method for the deck of cards
	 * @return ArrayList<Card> containing the contents of the deck
	 */
	public ArrayList<Card> getDeck()
	{
		return deckCon;
	}
	
	/**
	 * Method that uses the method within collections to mix the deck of cards in a random way.
	 */
	public void shuffle()
	{
		Collections.shuffle(deckCon);
	}
	
	/**
	 * DealCard is a method called when a player picks up a card from the pile. it will give the top card to the player
	 * and then removes the card from the pick up pile
	 * @param Playershand of the player that is receiving the card
	 */
	public void dealCard(Playershand playersHand)
	{
		playersHand.getNewCard(deckCon.get(0));
		deckCon.remove(0);
	}
	
	/**
	 * playCard is a method that is used to to play the top card to the discard pile when necessary. 
	 * @return Card that is the new top card
	 */
	public Card playCard()
	{
		Card topCard = deckCon.get(0);
		deckCon.remove(0);
		return topCard;
	}
	
	/**
	 * amountOfCard is used to check to see if the pick up pile is close to being out of cards.
	 * @return Integer of the size of the Deck
	 */
	public int amountOfCards()
	{
		return deckCon.size();
	}
	
	/**
	 * restock is a method that takes in the discard pile in order to take the cards and put them back into the pick up pile.
	 * @param discardPile
	 */
	public void restock(ArrayList<Card> discardPile)
	{	
		for(int i = 0; i < discardPile.size(); i++)
		{
			deckCon.add(discardPile.get(i));
		}
	}
	
	private ArrayList<Card> deckCon;
}
