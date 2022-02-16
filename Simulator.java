import java.text.CompactNumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import Cards.OneOfs;

/**
 * Simulates games of Exodia and records data on games played, such as
 * win percentage, best cards in opening hands, etc.
 */
public class Simulator {
  ArrayList<Card> deck, hand, graveyard;

  public Simulator(ArrayList<Card> deck) {
      this.deck = deck;
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

  public static void main(String[] args) {
    //initialize deck list
    ArrayList<Card> deckList = new ArrayList<Card>();
    //names of cards with 1x copy in the deck
    String[] oneOfs = new String[]{"right_leg", "left_leg", "right_arm", "left_arm",
    "forbidden_one", "toon_dra", "terraforming", "pot", "one_day"};
    //names of cards with 2x copies in the deck
    String[] twoOfs = new String[]{"blue_eyes", "cards_of_consonance"};
    //names of cards with 3x copies in the deck
    String[] threeOfs = new String[]{"white_stone", "library", "upstart", "toon_table",
    "dealings", "trade_in", "void", "psuedo_space", "chicken_game"};

    Card c;
    //add one-ofs to deck list
    for (String name : oneOfs) {
      c = new CompactNumberFormat(decimalPattern, symbols, compactPatterns, pluralRules))
      deckList.add(c);
    }
    Simulator exodia = new Simulator()
    
  }
}
