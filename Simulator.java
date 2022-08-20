import java.util.*;

/**
 * Simulates games of Exodia and records data on games played, such as
 * win percentage, best cards in opening hands, etc.
 */
public class Simulator {
  List<Card> deck, hand, graveyard;
  List<Card> playPrio, discardPrio;
  int library;

  public Simulator(List<Card> deck) {
    this.library = -1;
    this.deck = deck;
    this.hand = new ArrayList<>();
    this.playPrio = new ArrayList<>();
    this.discardPrio = new ArrayList<>();
    this.graveyard = new ArrayList<>();
    this.draw(5);
  }
  
  //determines if collection contains a card of name c
  public boolean containsName(String name, List<Card> list) {
    Card c = new Card(name);
    return list.contains(c);
  }

  //removes deck.size()-indexed card from deck and adds it to hand, repeated numCards times
  public void draw(int numCards) {
    if (deck.size()<1) return;
    Card c;

    //adds card to hand and removes it from deck
    for (int i=0; i<numCards; i++) {
      c = deck.get(deck.size()-1);
      findPlayPrio(c);
      findDiscardPrio(c);
      hand.add(c);
      deck.remove(deck.size()-1);
      System.out.println("drew " + c.getName() + " play prio of " + c.playPrio);
    }
    //sorts hand based on playing priority
    for (Card card : hand) {
      findPlayPrio(card);
    }
    Collections.sort(hand, (a,b) -> a.playPrio<b.playPrio ? -1 : a.playPrio==b.playPrio ? 0 : 1);
    
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
    Collections.sort(hand, (a,b) -> a.discardPrio<b.discardPrio ? -1 : a.discardPrio==b.discardPrio ? 0 : 1);
    discard(hand.get(0));
    //resort hand
    Collections.sort(hand, (a,b) -> a.playPrio<b.playPrio ? -1 : a.playPrio==b.playPrio ? 0 : 1);
  }

  //adds c to the hand and removes it from the deck
  public void tutor(Card c) {
    if (deck.contains(c)) {
      findPlayPrio(c);
      findDiscardPrio(c);
      hand.add(c);
      deck.remove(c);
      for (Card card : hand) {
        findPlayPrio(card);
      }
      Collections.sort(hand, (a,b) -> a.playPrio<b.playPrio ? -1 : a.playPrio==b.playPrio ? 0 : 1);
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
            containsName("pseudo_space", deck))) {
      c.setPlayPrio(2);
    }
    else if (name == "trade_in") {
      if (containsName("blue_eyes", hand) || containsName("toon_dra", hand)) {
        c.setPlayPrio(3);
      }
      else c.setPlayPrio(8);
    }
    else if (name == "consonance" ) {
      if (containsName("white_stone", hand)) {
        c.setPlayPrio(3);
      }
      else c.setPlayPrio(8);
    }
    else if (name == "chicken_game") {
      c.setPlayPrio(4);
    }
    else if (name == "upstart" || name == "one_day") {
      c.setPlayPrio(5);
    }
    else if (name == "pseudo_space") {
      if (containsName("chicken_game", graveyard)) {
        c.setPlayPrio(5);
      }
      else c.setPlayPrio(8);
    }
    else if (name == "void") {
      if (hand.size()>=3) {
        c.setPlayPrio(5);
      }
      else c.setPlayPrio(8);
    }
    else if (name == "pot") {
      c.setPlayPrio(6);
    }
    else if (name == "dealings") {
      c.setPlayPrio(7);
    }
    //unplayable
    else c.setPlayPrio(9);
  }

  //determine the discard priority of c based on the state of 
  //current hand and deck
  public void findDiscardPrio(Card c) {
    String name = c.getName();

    if (name == "white_stone") {
      if (!containsName("toon_dra", deck) && !containsName("blue_eyes", deck)) {
        if (containsName("consonance", deck)) {
          c.setDiscardPrio(2);
        }
      }
      if (containsName("toon_dra", deck) || containsName("blue_eyes", deck)) {
        c.setDiscardPrio(0);
      }
    }
    else if (name == "library") {
      c.setDiscardPrio(1);
    }
    else if (name == "consonance") {
      if (!containsName("white_stone", deck)) {
        c.setDiscardPrio(1);
      }
      else c.setDiscardPrio(2);
    }
    else if (name == "trade_in") {
      if (!containsName("blue_eyes", deck) && !containsName("toon_dra", deck)) {
        c.setDiscardPrio(1);
      }
      else c.setDiscardPrio(2);
    }
    else if (name == "terraforming") {
      if (!containsName("chicken game", deck) && !containsName("pseudo_space", deck)) {
        c.setDiscardPrio(2);
      }
    }
    else if (name == "toon_table") {
      c.setDiscardPrio(1);
    }
    else if (name == "pseudo_space") {
      if (!containsName("chicken_game", graveyard)) {
        c.setDiscardPrio(2);
      }
    }
    else if (name == "toon_dra" || name == "blue_eyes") {
      if (!containsName("trade_in", deck)) {
        c.setDiscardPrio(1);
      }
      else c.setDiscardPrio(2);
    }
    //should not be discarded 
    else c.setDiscardPrio(3);
  }

  //plays card if their conditions are met, returns true if successfully played
  //and false otherwise
  public boolean play(Card c) {
    String[] playableList = new String[]{"upstart", "one_day", "chicken_game", 
      "library", "toon_table", "terraforming", "trade_in", "consonance", "void", 
      "pot", "pseudo_space", "dealings"};
    boolean played = false;
    String name = c.getName();

    if (!Arrays.asList(playableList).contains(name)) return played;

    if (library>=3) {
      draw(1);
      library-=3;
    }
    if (name == "upstart" || name == "one_day" || name == "chicken_game") {
      discard(c);
      draw(1);
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
        discard(c);
        deck.remove(c2);
        hand.add(c2);
      }
    }
    else if (name == "terraforming") {
      Card c1 = new Card("chicken_game", 1, 0);
      Card c2 = new Card("pseudo_space", 1, 0);
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
        discard(c);
      }
      else return false;
    }
    else if (name == "pot") {
      List<Card> potDraws = new ArrayList<>();
      //look at top 3 cards of the deck, draw the highest priority and shuffle
      //the others back into the deck
      discard(c);
      for (int i=0; i<3; i++) {
        Card c1 = deck.get(deck.size()-1);
        findPlayPrio(c1);
        potDraws.add(c1);
        deck.remove(deck.size()-1);
      }
      Collections.sort(potDraws, (a,b) -> a.playPrio<b.playPrio ? -1 : a.playPrio==b.playPrio ? 0 : 1);
      System.out.println("pot chose " + potDraws.get(0));
      hand.add(potDraws.get(0));
      deck.add(potDraws.get(1));
      deck.add(potDraws.get(2));
      Collections.shuffle(deck);
    }
    else if (name == "pseudo_space") {
      if (containsName("chicken_game", graveyard)) {
        draw(1);
        discard(c);
        graveyard.remove(new Card("chicken_game"));
      }
      //play without drawing cards to count library
      else if (library>=0) discard(c);
      else return false;
    }
    //if this point has been reached, card was successfully played
    if (library>=0) library++;
    if (library>=3) {
      draw(1); 
      library-=3; 
      System.out.println("drew off of library");
    }
    for (Card card : hand) {
      findPlayPrio(card);
    }
    Collections.sort(hand, (a,b) -> a.playPrio<b.playPrio ? -1 : a.playPrio==b.playPrio ? 0 : 1);
    System.out.println("played " + c.getName() + "\n");
    return true;
  }

  public void simulateGame() {
    Card c = hand.get(0);
    while (true) {
      try {
        Thread.sleep(1000);
      }
      catch(InterruptedException ex) {
        Thread.currentThread().interrupt();
      }
      c = hand.get(0);
      if (containsName("right_arm", hand) && containsName("left_arm", hand) &&
          containsName("right_leg", hand) && containsName("left_leg", hand) &&
          containsName("forbidden_one", hand)) {
        System.out.println("You win! POG");
        return;
      }
      System.out.println("attempting to play " + c.getName() + " priority of " + c.playPrio);
      if (!play(c)) break;
    }
    System.out.println("You lost! sadge");
  }

  public static void main(String[] args) {
    //initialize deck list
    List<Card> deck = new ArrayList<Card>();
    deck = Card.initDeck();
    Simulator s = new Simulator(deck);

    System.out.println("Starting hand: ");
    for (Card c : s.hand) {
      System.out.println(c.getName());
    }
    s.simulateGame();
    System.out.println("Ending hand: ");
    for (Card c : s.hand) {
      System.out.println(c.getName());
    }
    
  }
}
