import java.util.*;

//import Cards.OneOfs;

/**
 * Simulates games of Exodia and records data on games played, such as
 * win percentage, best cards in opening hands, etc.
 */
public class Simulator {
  List<Card> deck, hand, graveyard;
  int library;

  public Simulator(List<Card> deck) {
    this.library = -1;
    this.deck = deck;
    this.hand = new ArrayList<>();
    this.draw(5);
    this.graveyard = new ArrayList<Card>();
  }
  
  //removes 0-indexed card from deck and adds it to hand numCards times
  public void draw(int numCards) {
    Random r = new Random();
    Card randCard;

    //adds card to hand and removes it from deck
    for (int i=0; i<numCards; i++) {
      randCard = deck.get(r.nextInt(deck.size()));
      hand.add(randCard);
      deck.remove(randCard);
    }
  }

  //removes lowest value cards from the hand
  public void discard(int numCards) {

  }

  //adds c to the hand and removes it from the deck
  public void tutor(Card c) {
    if (deck.contains(c)) {
      hand.add(c);
      deck.remove(c);
    }
  }
  //plays card if their conditions are met, returns true if successfully played
  //and false otherwise
  public boolean play(Card c) {
    String[] playableList = new String[]{"upstart", "one_day", "chicken_game", 
      "library", "toon_table", "terraforming", "trade_in", "consonance", "void", 
      "pot", "psuedo_space"};
    boolean played = false;
    String name = c.getName();

    if (!Arrays.asList(playableList).contains(name)) return played;
    played = true;
    
    if (name == "upstart" || name == "one_day" || name == "chicken_game") {
      played = true;
      draw(1);
      graveyard.add(c);
      hand.remove(c);
    }
    else if (name == "library") {
      if (this.library==-1) {
        played = true;
        this.library = 0;
        hand.remove(c);
      }
    }
    else if (name == "toon_table") {
      Card c1 = new Card("toon_table", 0, 0);
      Card c2 = new Card("toon_dra", 0, 0);
      while (deck.contains(c1)) {
        deck.remove(c1);
      }
      if (deck.contains(c2)) {
        deck.remove(c2);
        hand.add(c2);
      }
    }
    else if (name == "terraforming") {
      Card c1 = new Card("chicken_game", 1, 0);
      Card c2 = new Card("psuedo_space", 1, 0);
      if (deck.contains(c1)) {
        hand.add(c1);
        deck.remove(c1);
        graveyard.add(c);
      }
      else if (deck.contains(c2)) {
        hand.add(c2);
        deck.remove(c2);
        graveyard.add(c);
      }
    }
    else if (name == "trade_in" || name == "consonance") {

    }
    else if (name == "void") {

    }
    else if (name == "pot") {

    }
    else if (name == "pseudo_space") {

    }
    return played;
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
