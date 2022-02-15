/**
 * Card class for use in Simulator. Contains information about cards such as
 * how many cards they draw, how many cards are discarded, special conditions
 * to be allowed to play, etc.
 */
public class Card {
    int draw, discard;
    String requires;


    public Card(int draw, int discard) {
        this.draw = draw;
        this.discard = discard;
    }
    
    public Card(int draw, int discard, String requires) {
        this.draw = draw;
        this.discard = discard;
        this.requires = requires;
    }

}
//TODO: account for special card behavior (dual pot, white stone, toon table,
//psuedospace, etc)