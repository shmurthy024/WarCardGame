import java.util.*;

public class PlayerMethods{



  public LinkedList<Card> flip(LinkedList<Card> playerDeck){
    LinkedList<Card> firstThree = new LinkedList<Card>();
    Card card1 = playerDeck.getFirst();
    firstThree.add(card1);
    playerDeck.removeFirst();
    Card card2 = playerDeck.getFirst();
    firstThree.add(card2);
    playerDeck.removeFirst();
    Card card3 = playerDeck.getFirst();
    firstThree.add(card3);
    playerDeck.removeFirst();
    return firstThree;
  }

  public void collect(LinkedList<Card> list, LinkedList<Card> playerDeck){
    int position = 0;
    int size = list.size();
    for(int i = 0; i < size; i++){
      position = (int)(Math.random()*list.size());
      playerDeck.add(list.get(position));
      list.remove(position);
    }
  }

  public boolean hasWon(LinkedList<Card> playerDeck){
    if(playerDeck.size() == 52)
      return true;
    else
      return false;
  }

  public Card war(LinkedList<Card> playerDeck){
    Card temp = playerDeck.getFirst();
    playerDeck.removeFirst();
    return temp;
  }
}
