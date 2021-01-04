/** Game.java
*
* This is the method that conducts the actual game..
* @author Shree Murthy
* @author Student ID: 2374658
* @author shmurthy@chapman.edu
* CPSC 231-01 - Dr. Stevens
* Assignment - MP3 Warfare
* @version 1.0
*/

import java.util.*;

/** This method will cover the logic of the game.
* There should be methods to help you with who wins battles and wars.
* Make sure edge cases are fulfilled.
*/
public class Game{

  /**Contains the number of total battles*/
  private int numOfBattles;
  /**Contains the number of total wars*/
  private int numOfWars;
  /**Contains the number of total double wars*/
  private int numOfDoubleWars;
  /**Helps with declaring which player number won*/
  private int winner;
  /**Player type that will hold Player 1 info*/
  private Player newPlayer;
  /**Player type that will hold player 2 info*/
  private Player newPlayerTwo;
  /**LinkedList that will hold player 1 hand*/
  private LinkedList<Card> playerHandOne;
  /**LinkedList that will hold player 2 hand*/
  private LinkedList<Card> playerHandTwo;

  /** Default constructor that creates both player objects and instantiates other variables.
  *
  */
  public Game(){
    Deck deck = new Deck();
    newPlayer = new Player(deck);
    newPlayerTwo = new Player(deck);
    newPlayerTwo.setPlayer(2);
    numOfWars = 0;
    numOfBattles = 0;
    numOfDoubleWars = 0;
  }

  /** Call the total battles
  *@return int of numOfBattles variable
  */
  public int numBattle(){
    return numOfBattles;
  }

  /** Gets the number of Wars
  *@return numOfWars variable
  */
  public int numWar(){
    return numOfWars;
  }

  /** Gets the number of double wars
  * @return number of double wars
  */
  public int numDoubleWars(){
    return numOfDoubleWars;
  }

  /** Hands all the playing logic. This method should account for any weird edge cases that may arise.
  * Moreover, it should use helper methods to make sure everything transitions smoothly.
  * IF NEEDED: created arrays/linkedlists to help hold important information
  */
  public void play(){
    for(int i = 0; i < Integer.MAX_VALUE; i++){
      playerHandOne = hand(newPlayer);
      playerHandTwo = hand(newPlayerTwo);
      Card median = median(playerHandOne);
      Card medianTwo = median(playerHandTwo);
      Card[] arr = new Card[playerHandOne.size()];
      arr = playerHandOne.toArray(arr);
      Card[] arrTwo = new Card[playerHandTwo.size()];
      arrTwo = playerHandTwo.toArray(arrTwo);
      WarLogger.getInstance().logBattle(i + 1, WarLogger.P1, arr);
      WarLogger.getInstance().logBattle(i + 1, WarLogger.P2, arrTwo);
      if(median == null || medianTwo == null){
        if(median == null){
            newPlayerTwo.collect(playerHandTwo);
            break;
        }
        else{
          newPlayer.collect(playerHandOne);
          break;
        }

      }
      else if(median.getCardValue() > medianTwo.getCardValue()){
        numOfBattles++;
        newPlayer.collect(playerHandTwo, playerHandOne);
        WarLogger.getInstance().logBattleOutcome(i + 1, WarLogger.P1);
      }
      else if(median.getCardValue() < medianTwo.getCardValue()){
        numOfBattles++;
        newPlayerTwo.collect(playerHandOne, playerHandTwo);
        WarLogger.getInstance().logBattleOutcome(i + 1, WarLogger.P2);
      }
      else{
        if(newPlayer.getSize() == 0){
          numOfBattles++;
          WarLogger.getInstance().logBattleOutcome(i + 1, WarLogger.P1);
          newPlayerTwo.collect(playerHandOne, playerHandTwo);
        }
        else if(newPlayerTwo.getSize() == 0){
          numOfBattles++;
          WarLogger.getInstance().logBattleOutcome(i + 1, WarLogger.P2);
          newPlayerTwo.collect(playerHandTwo, playerHandOne);
        }
        else{
          numOfWars++;
          war(newPlayer, newPlayerTwo);

        }
      }
      if(newPlayer.hasWon() == true){
        System.out.println("Player 1 has won");
        setWinner(1);
        break;
      }
      else if(newPlayerTwo.hasWon() == true){
        System.out.println("Player 2 has won");
        setWinner(2);
        break;
      }
      else
        "".isEmpty();
    }
  }


  /** This method will handle logic for what happens when there is a war.
  * @param one representing Player object
  * @param two representing Player object
  */
  public void war(Player one, Player two){

    Card warOne = one.flip();
    Card warTwo = two.flip();
    playerHandOne.add(warOne);
    playerHandTwo.add(warTwo);
    if(warOne.getCardValue() == warTwo.getCardValue()){
        numOfDoubleWars++;
        war(one, two);
      }
      if(warOne.getCardValue() > warTwo.getCardValue()){
        one.collect(playerHandTwo, playerHandOne);
        WarLogger.getInstance().logWarOutcome(numWar(), WarLogger.P1);
      }
      else{
        two.collect(playerHandOne, playerHandTwo);
        WarLogger.getInstance().logWarOutcome(numWar(), WarLogger.P2);

    }

}


  /** Method creates the players hand
  * @return LinkedList of the card type that will be the player's hands
  * @param p represents Player to gain a hand of cards
  */
  public LinkedList<Card> hand(Player p){
    LinkedList<Card> player = new LinkedList<Card>();
    if(p.getSize() == 3){
      for(int i = 0; i < 3; i++){
        player.add(p.flip());

      }
    }
    else if(p.getSize() == 2){
      for(int i = 0; i < 2; i++){
        player.add(p.flip());
      }
    }
    else{
        player.add(p.flip());
    }
    sort(player);
    return player;
  }


  /** Sorts the players hand from lowest to highest
  * @return LinkedList of the hand in order
  * @param sortMe represents LinkedList that needs to be sorted
  */
  public LinkedList<Card> sort(LinkedList<Card> sortMe){
    Card temp = sortMe.get(0);
    if(sortMe.size() == 1){
      return sortMe;
    }
    else if(sortMe.size() == 2){
      if(temp.getCardValue() > sortMe.get(1).getCardValue()){
        sortMe.set(0, sortMe.get(1));
        sortMe.set(1, temp);
        return sortMe;
      }
      else
        return sortMe;
    }
    else{
      temp = sortMe.get(1);
      if((temp.getCardValue() >= sortMe.get(0).getCardValue()) && (temp.getCardValue() <= sortMe.get(2).getCardValue())){
        return sortMe;
      }
      else if((temp.getCardValue() >= sortMe.get(0).getCardValue()) && (temp.getCardValue() >= sortMe.get(2).getCardValue())){
        sortMe.set(1, sortMe.get(2));
        sortMe.set(2, temp);
      }
      else if((temp.getCardValue() <= sortMe.get(0).getCardValue()) && (temp.getCardValue() <= sortMe.get(2).getCardValue())){
        sortMe.set(1, sortMe.get(0));
        sortMe.set(0, temp);
        temp = sortMe.get(2);
        sortMe.set(2, sortMe.get(1));
        sortMe.set(1, temp);

      }
      else if(((temp.getCardValue() <= sortMe.get(0).getCardValue()) && (temp.getCardValue() >= sortMe.get(2).getCardValue()))){
        sortMe.set(2, sortMe.get(0));
      }
      else{
        "".isEmpty(); //do nothing. just closes if statement
      }
      return sortMe;
    }
  }


  /** This method will return the middle card from the list or if the player's hand is less than three
  * it will return the Max value in the hand.
  * @return a Card that is median card
  */
  public Card median(LinkedList<Card> list){
    if(list.size() == 3){
      return list.get(1);
    }
    else if(list.size() == 2){
      return list.get(1);
    }
    else
      return list.get(0);

  }

  /** Set the winner
  * @param num of the player number who won
  */
  public void setWinner(int num){
    winner = num;
  }

  /** Get the winner
  * @return int of the winner number
  */
  public int getWinner(){
    return winner;
  }

}
