import java.util.HashMap;

/**
 * Simulates games of Exodia and records data on games played, such as
 * win percentage, best cards in opening hands, etc.
 */
public class Simulator {
  HashMap<String, Integer> deck, hand, graveyard;

  public Simulator(HashMap<String, Integer> deck) {
      this.deck = deck;
      this.hand = 
  }
  
  //removes card from deck and adds to hand numCards times
  public void draw(int numCards) {
      int rand
      for (int i=0; i<numCards; i++) {

      }
  }

  public static void main(String[] args) {

  }
}
