/** Deck.java
*
* This is the class is where new deck can be created.
* @author Shree Murthy
* @author Student ID: 2374658
* @author shmurthy@chapman.edu
* CPSC 231-01 - Dr. Stevens
* Assignment - MP3 Warfare
* @version 1.0
*/

import java.util.*;

/** This class should instantiate a new deck object when called.
 * It should create a deck of 52 cards of the Card type.
 * The Deck should have a pre-created Card class.
 */

public class Deck{

  /**A Card member variable. Should be used when creating the deck.*/
  private Card m_card;
  /**A linkedList with the Card type that will act as the deck.*/
  private LinkedList<Card> m_deck = new LinkedList<Card>();
  /**Member variable that can help indicate the size of the deck.*/
  private int size;

  /** Default constructor that will create the deck.
  *
  */
  public Deck(){
    for(int i = 2; i <= 14; i++){
      for(int j = 1; j <= 4; j++){
        m_card = new Card(i, j);
        m_deck.add(m_card);
      }
    }
    size = m_deck.size();
  }

  /** Deals out one random card from the deck at a time.
  * After this method is called then the card must be removed from the deck.
  * @return Card
  */
  public Card deal(){
    int position = (int)(Math.random()*size);
    m_card = m_deck.get(position);
    m_deck.remove(position);
    size = m_deck.size();
    return m_card;
  }


}
