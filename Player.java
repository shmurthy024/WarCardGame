/** Player.java
*
* This is the class that creates player objects.
* @author Shree Murthy
* @author Student ID: 2374658
* @author shmurthy@chapman.edu
* CPSC 231-01 - Dr. Stevens
* Assignment - MP3 Warfare
* @version 1.0
*/


import java.util.*;

/** This class must create a player that holds a unique deck of 26 cards.
 * This class should also collect cards, flip a card from their deck,
 * and has a method that deals with who wins. This is class must function properly
 * if the rest of the classes are supposed to succeed.
 *
 */
public class Player{

  /**A predefined variable that will create a LinkedList of the Card type.*/
  private LinkedList<Card> playerDeck;
  /**A integer variable called player. Make sure to update this variable through an acessor if you create more than one player.*/
  private int player;

  /** Constructor creates two player objects and their respective decks.
  * @param deck Deck - by passing a deck into this constructor you will create two
  * unique decks. Make sure to use the deal method from Deck.java.
  */
  public Player(Deck deck){
    player = 1;
    playerDeck = new LinkedList<Card>();

    for(int i = 0; i < 26; i++){
      Card temp = deck.deal();
      playerDeck.add(temp);
    }
  }

  /** This method will set the player's number.
  * @param num int - represents number to set the player type.
  */
  public void setPlayer(int num){
    player = num;
  }

  /** Method that gets the number of the player.
  * @return an int value representing the player number.
  */
  public int getPlayer(){
    return player;
  }

  /** Get the size of the players deck
  * @return size of LinkedList
  */
  public int getSize(){
    return playerDeck.size();
  }

  /** Flip a random card from the players deck and remove it from the deck before returning it.
  * @return the Card you flipped
  */
  public Card flip(){
    if(playerDeck.size() > 0){
      Card card = playerDeck.getFirst();
      playerDeck.removeFirst();
      return card;
    }
    else
      return null;

  }

  /** Collect one of the player's hand.
  * Add the cards to the player's deck randomly to not cause infinite loops.
  */
  public void collect(LinkedList<Card> list){
    int position = 0;
    int size = list.size();
    for(int i = 0; i < size; i++){
      position = (int)(Math.random()*list.size());
      playerDeck.add(list.get(position));
      list.remove(position);
    }
  }

  /** Overriden method. This will collect both players' hand.
  * Add them randomly to the deck.
  */
  public void collect(LinkedList<Card> list, LinkedList<Card> myList){
    int position = 0;
    int size = list.size();
    for(int i = 0; i < size; i++){
      position = (int)(Math.random()*list.size());
      playerDeck.add(list.get(position));
      list.remove(position);
    }
    size = myList.size();
    for(int i = 0; i < size; i++){
      position = (int)(Math.random()*myList.size());
      playerDeck.add(myList.get(position));
      myList.remove(position);
    }
  }

  /** If a player's deck reaches 52 return true since this player has won.
  *@return boolean type indicating if the player has won.
  */
  public boolean hasWon(){
    if(playerDeck.size() == 52)
      return true;
    else
      return false;
  }

}
