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

    public Card[] initDeck() {
        Card[] deck;
        //c1-c5 exodia pieces
        Card c1 = new Card("right_leg", 0, 0);
        Card c2 = new Card("left_leg", 0, 0);
        Card c3 = new Card("right_arm", 0, 0);
        Card c4 = new Card("left_arm", 0, 0);
        Card c5 = new Card("forbidden_one", 0, 0);
        Card c6 = new Card("blue_eyes", 0, 0);
        Card c7 = new Card("toon_dra", 0, 0);
        Card c8 = new Card("upstart", 1, 0);
        Card c9 = new Card("upstart", 1, 0);
        Card c10 = new Card("upstart", 1, 0);
        return deck;
        String[] oneOfs = new String[]{ , "toon_dra", "terraforming", "pot", "one_day"};
        //names of cards with 2x copies in the deck
        String[] twoOfs = new String[]{"blue_eyes", "cards_of_consonance"};
        //names of cards with 3x copies in the deck
        String[] threeOfs = new String[]{"white_stone", "library", "upstart", "toon_table",
        "dealings", "trade_in", "void", "psuedo_space", "chicken_game"};
    }

}
//TODO: account for special card behavior (dual pot, white stone, toon table,
//psuedospace, etc)