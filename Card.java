/** Card.java
*
* This is the class is where new card objects can be created.
* @author Shree Murthy
* @author Student ID: 2374658
* @author shmurthy@chapman.edu
* CPSC 231-01 - Dr. Stevens
* Assignment - MP3 Warfare
* @version 1.0
*/

/** This class should instantiate a new card object when called.
 * It should create a object with a card value and suit type.
 * This card class should be accompanied by a Deck class.
 */
public class Card{
    /** Member Variable to set value of Card */
    private int m_cardVal;
    /** Member Variable to set the type of Suit. Represent each suit type as numbers 1-4. */
    private int  m_suit;

    /** The default constructor
    * Set the card value and suit type to 0
    */
    public Card(){
      m_cardVal = 0;
      m_suit = 0;
    }

    /** Overloaded constructor - creates new card given card value and suit
    * @param cardV int representing value of card (2-10, J = 11, Q = 12, K = 13, A = 14)
    * @param suit int representing suit type (1 = Heart, 2 = Space, etc)
    */
    public Card(int cardV, int suit){
      m_cardVal = cardV;
      m_suit = suit;
    }


    /** Set the value of the Card.
    * @param cardValue int representing value to be set (2-10, 11 = J, 12 = Q, etc)
    */
    public void setCardValue(int cardValue){
      m_cardVal = cardValue;
    }

    /** Get value of card
    * @return An int representing value of card
    */
    public int getCardValue(){
      return m_cardVal;
    }

    /** Set suit type
    * @param suit int representing suit of card (1 = Heart, 2 = Space, etc)
    */
    public void setCardSuit(int suit){
      m_suit = suit;
    }
    /** Get value of card
    * @return An int representing suit  of card
    */
    public int getCardSuit(){
      return m_suit;
    }

    /** Equals method to compare two Card objects
    * @param other Card representing another card object
    * @return boolean representing if two cards are equal
    */
    public boolean equals(Card other){
      return (m_cardVal == other.m_cardVal && m_suit == other.m_suit);
    }

    /** A toString method that displays your card object.
          * The suit member variable must be converted into its respective string type
          * (i.e. 1 should be displayed as Heart(s) and so on).
    * @return Suit type and Value of card in a string format
    */
    public String toString(){
      if(m_suit == 1){
        return "Suit: Hearts" + " Value: " + m_cardVal;
      }
      else if(m_suit == 2){
        return "Suit: Spades" + " Value: " + m_cardVal;
      }
      else if(m_suit == 3){
        return "Suit: Clubs" + " Value: " + m_cardVal;
      }
      else
        return "Suit: Diamonds" + " Value: " + m_cardVal;
    }

    
  }
