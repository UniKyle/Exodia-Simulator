import java.util.*;

//import Cards.OneOfs;

/**
 * Simulates games of Exodia and records data on games played, such as
 * win percentage, best cards in opening hands, etc.
 */
public class Simulator {
  List<Card> deck, hand, graveyard;

  public Simulator(List<Card> deck) {
      this.deck = deck;
      this.hand = new ArrayList<>();
      this.draw(5);
      this.graveyard = new ArrayList<Card>();
  }
  
  //removes card from deck and adds to hand numCards times
  public void draw(int numCards) {
      Random r = new Random();
      Card randCard;

      //adds card to hand and removes it from deck
      for (int i=0; i<numCards; i++) {
        randCard = deck.get(r.nextInt(deck.size()));
        hand.add(randCard);
        Collections.swap(deck, deck.indexOf(randCard), deck.size()-1);
        deck.remove(deck.size()-1);
      }
  }

  //removes lowest value cards from the hand
  public void discard(int numCards) {

  }

  //plays card if their conditions are met
  public void play(Card c) {
    String name = c.getName();
    if (name == "upstart" || name == "one_day" || name == "chicken_game") {
      draw(1);
      graveyard.add(c);
    }
    else if (name == "library") {

    }
    else if (name == "toon_table") {

    }
    else if (name == "terraforming") {

    }
    else if (name == "trade_in" || name == "consonance") {

    }
    else if (name == "void") {

    }
    else if (name == "pot") {

    }
    else if (name == "pseudo_space") {

    }
  }

  public static void main(String[] args) {
    //initialize deck list
    List<Card> deck = new ArrayList<Card>();
    deck = Card.initDeck();
    Simulator s = new Simulator(deck);

    for (Card c : s.hand) {
      System.out.println(c.getName());
    }
    
  }
}
