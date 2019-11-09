package classes;

/**
 * Card is a class used as a model to outline the properties of the cards that are in the UNO game.
 * Each card has a specified color number and for wild cards different specialties.
 * 
 * @author bryceschmisseur
 *
 */

public class Card
{
	/**
	 * Constructor to take in all attributes of a card and assigns them to the specified properties of Card.
	 * @param color
	 * @param number
	 * @param specialty
	 */
	public Card(String color, String number, String specialty)
	{
		this.color = color;
		this.number = number;
		this.specialty = specialty;
	}

	/**
	 * Getters and Setters for properties of the class
	 * @return String of property 
	 * @param String of property 
	 */
	public String getColor()
	{
		return color;
	}

	public String getNum()
	{
		return number;
	}
	
	public String getSpecialty()
	{
		return specialty; 
	}

	public void setColor(String a)
	{
		color = a;
	}
	
	/**
	 * A custom toString method in order to print out the card description in the correct format
	 * @return String of cards properties
	 */
	@Override
	public String toString()
	{
		// If the card specialty is either a skip or reverse the card must be printed with no number 
		if(specialty.equals("Ski") || specialty.equals("Rev"))
		{
			return (color + " " + specialty);
		}
		
		//If the specialty of the card is Draw it must print out the correct number of card that are given to the player
		else if(specialty.equals("Drw"))
		{
			if(Integer.parseInt(number) == 2)
			{
				return (color + " Draw 2");
			}
			else if(Integer.parseInt(number) == 4)
			{
				return ("Draw 4");
			}
		}
		
		//If the card is a wild card it must not be printed out with color or number
		else if(specialty.equals("Wil"))
		{
			return ("Wild Card");
		}
		
		//Any other card is printed with both a color and number and no specialty
		else
		{
			return (color + " " + number);
		}	
		
		return "Error";
	}


	//Declaration of private used variables that are used in the class
	private String color;
	private String number;
	private String specialty;
}
