import java.util.*;
import static org.junit.Assert.*;
import org.junit.*;

//for testing purposes while project is in development
//to be deleted upon project completion
public class Tester {
    Simulator s;

    @Before
    public void setup() {
        List<Card> deck = new ArrayList<Card>();
        deck = Card.initDeck();
        this.s = new Simulator(deck);
    }

    @Test
    public void testEquals() {
        Card c1 = new Card("toon_table", 0, 0);
        Card c2 = new Card("toon_table", 2, 1);
        assertEquals(c1, c2);
    }

    @Test
    public void testRemove() {
        Card testCard = new Card("toon_table", 0, 0);
        s.hand.add(0, testCard);
        s.hand.add(testCard);
        assertEquals(7, s.hand.size());
        while (s.hand.contains(testCard)) {
            s.hand.remove(testCard);
        }
        assertEquals(5, s.hand.size());
    }

    @Test
    public void testTutor() {
        Card testCard = new Card("pot", 1, 0);
        assertTrue(s.deck.contains(testCard));
        s.tutor(testCard);
        assertTrue(s.hand.contains(testCard));
        assertEquals(34, s.deck.size());
    }

    @Test
    public void testToonTable() {
        Card testCard = new Card("toon_table");
        s.tutor(testCard);
        s.tutor(testCard);
        s.play(testCard);
        assertTrue(s.hand.size()==7);
        assertTrue(s.hand.contains(testCard));
        assertEquals(9, testCard.playPrio);
        assertTrue(!s.deck.contains(testCard));
        assertTrue(s.containsName("toon_dra", s.hand));
    }

}
