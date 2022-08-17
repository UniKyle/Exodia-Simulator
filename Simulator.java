import java.util.*;

/**
 * Simulates games of Exodia and records data on games played, such as
 * win percentage, best cards in opening hands, etc.
 */
public class Simulator {
  List<Card> deck, hand, graveyard;
  Queue<Card> playPrio, discardPrio;
  int library;

  public Simulator(List<Card> deck) {
    this.library = -1;
    this.deck = deck;
    this.hand = new ArrayList<>();
    this.playPrio = new PriorityQueue<>();
    this.discardPrio = new PriorityQueue<>();
    this.draw(5);
    this.graveyard = new ArrayList<Card>();
  }
  
  //determines if collection contains a card of name c
  public boolean containsName(String name, List<Card> list) {
    Card c = new Card(name);
    return list.contains(c);
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

  //removes card from hand and adds it to graveyard
  public void discard(Card c) {
    if (hand.contains(c)) {
      hand.remove(c);
      graveyard.add(c);
    }
  }

  //remove lowest value cards from the hand
  public void chooseDiscard() {
    if (library>=0) {
       
    }
  }

  //adds c to the hand and removes it from the deck
  public void tutor(Card c) {
    if (deck.contains(c)) {
      hand.add(c);
      deck.remove(c);
    }
  }

  //determine the playing priority of c based on the state of 
  //current hand and deck
  public void findPlayPrio(Card c) {
    String name = c.getName();
    if (name == "library" && library == -1) {
      c.setPlayPrio(0);
    }
    else if (name == "toon_table" && !containsName(name, graveyard)) {
      c.setPlayPrio(1);
    }
    else if (name == "terraforming" && (containsName("chicken_game", deck) ||
            containsName("psuedo_space", deck))) {
      c.setPlayPrio(2);
    }
    else if (name == "trade_in") {
      if (containsName("blue_eyes", hand) || containsName("toon_dra", hand)) {
        c.setPlayPrio(3);
      }
      else c.setPlayPrio(7);
    }
    else if (name == "consonance" ) {
      if (containsName("white_stone", hand)) {
        c.setPlayPrio(3);
      }
      else c.setPlayPrio(7);
    }
    else if (name == "chicken_game" || name == "upstart" || name == "one_day") {
      c.setPlayPrio(4);
    }
    else if (name == "psuedo_space") {
      if (containsName("chicken_game", graveyard)) {
        c.setPlayPrio(4);
      }
      else c.setPlayPrio(7);
    }
    else if (name == "void") {
      if (hand.size()>=3) {
        c.setPlayPrio(4);
      }
      else c.setPlayPrio(7);
    }
    else if (name == "pot") {
      c.setPlayPrio(5);
    }
    else if (name == "dealings") {
      c.setPlayPrio(6);
    }
    //unplayable
    else c.setPlayPrio(8);
  }
  
  //plays card if their conditions are met, returns true if successfully played
  //and false otherwise
  public boolean play(Card c) {
    String[] playableList = new String[]{"upstart", "one_day", "chicken_game", 
      "library", "toon_table", "terraforming", "trade_in", "consonance", "void", 
      "pot", "psuedo_space", "dealings"};
    boolean played = false;
    String name = c.getName();

    if (!Arrays.asList(playableList).contains(name)) return played;

    if (library>=3) {
      draw(1);
      library-=3;
    }
    if (name == "upstart" || name == "one_day" || name == "chicken_game") {
      draw(1);
      discard(c);
    }
    else if (name == "library") {
      if (this.library==-1) {
        this.library = 0;
        hand.remove(c);
      }
      else return false;
    }
    else if (name == "toon_table") {
      Card c1 = new Card("toon_table");
      Card c2 = new Card("toon_dra");
      if (!deck.contains(c1) && !deck.contains(c2)) return false;
      while (deck.contains(c1)) {
        deck.remove(c1);
        graveyard.add(c1);
        if (library>=0) library++;
      }
      if (deck.contains(c2)) {
        deck.remove(c2);
        hand.add(c2);
      }
      discard(c);
    }
    else if (name == "terraforming") {
      Card c1 = new Card("chicken_game", 1, 0);
      Card c2 = new Card("psuedo_space", 1, 0);
      if (deck.contains(c1)) {
        hand.add(c1);
        deck.remove(c1);
        discard(c);
      }
      else if (deck.contains(c2)) {
        hand.add(c2);
        deck.remove(c2);
        discard(c);
      }
      else return false;

    }
    else if (name == "dealings") {
      draw(1);
      discard(c);
      chooseDiscard();
    }
    else if (name == "trade_in") {
      Card c1 = new Card("blue_eyes");
      Card c2 = new Card("toon_dra");
      if (hand.contains(c1)) {
        discard(c1);
        discard(c);
        draw(2);
      }
      else if (hand.contains(c2)) {
        discard(c2);
        discard(c);
        draw(2);
      }
      else return false;
    }
    else if (name == "consonance") {
      Card c1 = new Card("white_stone");
      if (hand.contains(c1)) {
        discard(c1);
        discard(c);
        draw(2);
      }
      else return false;
    }
    else if (name == "void") {
      if (hand.size()>=3) {
        draw(1);
      }
      else return false;
    }
    else if (name == "pot") {

    }
    else if (name == "pseudo_space") {
      Card c1 = new Card("chicken_game");
      if (graveyard.contains(c1)) {
        draw(1);
        discard(c);
      }
      else return false;
    }
    //if this point has been reached, card was successfully played
    if (library>=0) library++;
    return true;
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
