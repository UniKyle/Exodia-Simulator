import java.util.*;

/**
 * Card class for use in Simulator. Contains information about cards such as
 * how many cards they draw, how many cards are discarded, special conditions
 * to be allowed to play, etc.
 */
public class Card {
    String name;
    int draw, discard;
    String requires;


    public Card(String name, int draw, int discard) {
        this.name = name;
        this.draw = draw;
        this.discard = discard;
    }
    
    public Card(int draw, int discard, String requires) {
        this.draw = draw;
        this.discard = discard;
        this.requires = requires;
    }

    public String getName() {
        return this.name;
    }

    public boolean equals(Object obj) {
        if (obj==null) return false;
        if (obj == this) return true;
        if (!(obj instanceof Card)) return false;
        Card c = (Card) obj;
        return this.name == c.name;
    }

    public static List<Card> initDeck() {
        List<Card> deck = new ArrayList<>();
        //c1-c5 exodia pieces
        Card c1 = new Card("right_leg", 0, 0);
        Card c2 = new Card("left_leg", 0, 0);
        Card c3 = new Card("right_arm", 0, 0);
        Card c4 = new Card("left_arm", 0, 0);
        Card c5 = new Card("forbidden_one", 0, 0);
        Card c6 = new Card("blue_eyes", 0, 0);
        Card c7 = new Card("white_stone", 0, 0);
        Card c8 = new Card("library", 0, 0);
        Card c9 = new Card("toon_dra", 0, 0);
        Card c10 = new Card("upstart", 1, 0);
        Card c11 = new Card("toon_table", 0, 0);
        Card c12 = new Card("terraforming", 0, 0);
        Card c13 = new Card("dealings", 1, 1);
        Card c14 = new Card("trade_in", 2, 0);
        Card c15 = new Card("consonance", 2, 0);
        Card c16 = new Card("void", 1, 0);
        Card c17 = new Card("pot", 1, 0);
        Card c18 = new Card("one_day", 1, 0);
        Card c19 = new Card("psuedo_space", 1, 0);
        Card c20 = new Card("chicken_game", 1, 0);

        
        Card[] oneOfs = new Card[]{c1, c2, c3, c4, c5, c9, c12, c17, c18};
        //names of cards with 2x copies in the deck
        Card[] twoOfs = new Card[]{c6, c15};
        //names of cards with 3x copies in the deck
        Card[] threeOfs = new Card[]{c7, c8, c10, c11, c13, c14, c16, c19, c20};
        for (Card c : oneOfs) {
            deck.add(c);
        }
        for (Card c : twoOfs) {
            deck.add(c);
            deck.add(c);
        }
        for (Card c : threeOfs) {
            deck.add(c);
            deck.add(c);
            deck.add(c);
        }
        Collections.shuffle(deck);
        return deck;
    }

}
//TODO: account for special card behavior (dual pot, white stone, toon table,
//psuedospace, etc)
//put more in the card class, less in simulator?
    //draw with condition as a method
    //all special card behavior = different method?