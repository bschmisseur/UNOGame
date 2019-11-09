package classes;

import java.util.Random;

/**
 * The Driver class is used to emulate a game of UNO and also display all the information to the user.
 * This is done by using the classes and methods that are declared in the project.
 * @author bryceschmisseur
 *
 */

public class Driver
{
	public static void main(String[] args) throws InterruptedException
	{
		//Initializes variables that will be used throughout the program
		stopGame = false;
		NextPlayer nP = new NextPlayer();
		Random rand = new Random();
		
		//Creates and Shuffles deck
		Deck deck = new Deck();
		deck.shuffle();
		
		//Creates a discard Deck
		DiscardPile disDeck = new DiscardPile();
		
		//Creates players
		Playershand player1 = new Playershand();
		Playershand player2 = new Playershand();
		Playershand player3 = new Playershand();
		Playershand player4 = new Playershand();
		
		//Creates a list of player to later determine the direction and next player
		orderOfPlayers = new Playershand[]{player1, player2, player3, player4};
		
		//Deals card to the players
		for(int i = 0; i < 7; i ++)
		{
			deck.dealCard(player1);
			deck.dealCard(player2);
			deck.dealCard(player3);
			deck.dealCard(player4);
		}
		
		//Return all Hands to the users
		System.out.println("*********************************************************");
		System.out.println("*                                                       *");
		System.out.println("*                    Welcome to UNO!                    *");
		System.out.println("*                                                       *");
		System.out.println("*********************************************************\n");
		System.out.print("Dealing and shuffing cards now"); 
		Thread.sleep(1000);
		System.out.print("."); 
		Thread.sleep(1000);
		System.out.print("."); 
		Thread.sleep(1000);
		System.out.println(".\n"); 
		Thread.sleep(1000);
		System.out.println("Player 1 Cards: " + player1.getHand());
		System.out.println("Player 2 Cards: " + player2.getHand());
		System.out.println("Player 3 Cards: " + player3.getHand());
		System.out.println("Player 4 Cards: " + player4.getHand() + "\n");
		
		//Places first card on the discard pile and displays it
		disDeck.addCard(deck.playCard());
		nP.startCard(disDeck.getTopCard());
		System.out.println("The top card is: " + disDeck.getTopCard() + "\n");
		
		//Creates Loop until there is a winner
		while(!stopGame)
		{
			//Creates and displays the player
			Playershand currentPlayer = orderOfPlayers[nP.getNextPlayer()];			
			System.out.printf("It is Player %d's turn \n", nP.displayPlayer());
			System.out.println("Players hand: " + currentPlayer.getHand());
			playersTurnNum = nP.displayPlayer();
			
			//if the player has a card that can be played
			if(currentPlayer.compareCard(disDeck.getTopCard()))
			{
				//Finds and plays the card from the players hand
				Card currentCard = currentPlayer.playCard();
				System.out.println("Card Played is: " + currentCard);
				disDeck.addCard(currentCard);
				
				//Checks if the current player has 1 or less cards
				if(currentPlayer.playerWon() == 1)
				{
					//If the player only has one card then it has a 50/50 chance of calling uno
					int randomNum = rand.nextInt(2);
					
					if(randomNum == 1)
					{
						System.out.printf("Player %d did not call UNO and has to pick up a card! \n", nP.displayPlayer());
						System.out.printf("The card was: " + deck.getTopCard());
						currentPlayer.getNewCard(deck.playCard());
					}
					
					else if(randomNum == 0)
					{
						System.out.printf("Player %d called Uno! \n", nP.displayPlayer());
					}
				}
				
				else if(currentPlayer.playerWon() == 3)
				{
					//If the current player has no card then the game will stop
					stopGame = true; 
				}
				
				//Plays out the effect of the special card that is played
				if(!disDeck.getTopCard().getSpecialty().equals("Non"))
				{
					//If the needs to be a color change it will generate a random color
					if(disDeck.getTopCard().getSpecialty().equals("Wil") || 
							(disDeck.getTopCard().getSpecialty().equals("Drw") && disDeck.getTopCard().getNum().equals("4")))
					{
						int randColor = rand.nextInt(4);
						String color = "";
						
						switch(randColor)
						{
							case 0:
								disDeck.getTopCard().setColor("Red");
								color = "Red";
							case 1:
								disDeck.getTopCard().setColor("Gre");
								color = "Green";
							case 2:
								disDeck.getTopCard().setColor("Blu");
								color = "Blue";
							case 3:
								disDeck.getTopCard().setColor("Yel");
								color = "Yellow";
						}
						System.out.printf("Player %d changed the color to " + color + "\n", nP.displayPlayer());
					}
					
					//If a draw card is played it will determine the player and deal the specified amount of cards
					if(disDeck.getTopCard().getSpecialty().equals("Drw"))
					{
						Playershand affectedPlayer = orderOfPlayers[nP.getAffectedPlayer()];
						int numOfCard = Integer.parseInt(disDeck.getTopCard().getNum());
						
						for(int i = 0; i < numOfCard; i++)
						{
							deck.dealCard(affectedPlayer);
						}
						
						System.out.printf("Player %d has to pick up %d cards! \n", nP.displayAffectedPlayer(), numOfCard);
					}
				}
				nP.cardPlayed(currentCard);
			}
			
			//Player has no match
			else
			{
				System.out.printf("Player %d had to pick up a card!\n", nP.displayPlayer());
				System.out.println("The card was: " + deck.getTopCard());
				currentPlayer.getNewCard(deck.playCard());
				nP.cardPlayed();
			}
			
			//Checks if the deck is close to being empty
			if(deck.amountOfCards() <= 5)
			{
				deck.restock(disDeck.restock());
			}
			
			//Displays the top card for the next player
			System.out.println("");
			System.out.println("New top card is: " + disDeck.getTopCard());
			System.out.println(" ");

		}
		
		//Determine the Player that won and announces the winner
		System.out.printf("Player %d's has won the game!!\n", playersTurnNum);
	}
	
	//Declares all variables that are used through-out the class
	private static Playershand[] orderOfPlayers;
	private static Boolean stopGame;
	private static int playersTurnNum;
}
