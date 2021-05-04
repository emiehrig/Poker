

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.io.IOException;
import java.util.Collections;

/**
 * The test class CheckHandTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class CheckHandTest
{
    Card card1, card2, card3, card4, card5, card6, card7, card8, card9, card10;
    ArrayList<Card> hand = new ArrayList<>();
    ArrayList<Card> hand2 = new ArrayList<>();
    CheckHand scoreHand = new CheckHand();

    /**
     * Default constructor for test class CheckHandTest
     */
    public CheckHandTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        Card card1, card2, card3, card4, card5, card6, card7, card8, card9, card10;
        ArrayList<Card> hand = new ArrayList<>();
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
        Card card1, card2, card3, card4, card5, card6, card7, card8, card9, card10;
        ArrayList<Card> hand = new ArrayList<>();
    }

    @Test
    public void isRoyalFlushTest()
    {
        // arrange
        try
        {
            card1 = new Card(Suit.Spades, 10, true, "10");
            card2 = new Card(Suit.Spades, 11, true, "Jack");
            card3 = new Card(Suit.Spades, 12, true, "Queen");
            card4 = new Card(Suit.Spades, 13, true, "King");
            card5 = new Card(Suit.Spades, 14, true, "Ace");
        }
        catch(IOException ex)
        {
            System.exit(0);
        }

        hand.add(card1);
        hand.add(card2);
        hand.add(card3);
        hand.add(card4);
        hand.add(card5);
        //act
        boolean isRoyalFlush = scoreHand.isRoyalFlush(hand);

        // assert
        assertTrue(isRoyalFlush);
    }

    @Test
    public void isFlushTest()
    {
        // arrange
        try
        {
            card1 = new Card(Suit.Spades, 2, true, "2");
            card2 = new Card(Suit.Spades, 8, true, "8");
            card3 = new Card(Suit.Spades, 5, true, "5");
            card4 = new Card(Suit.Spades, 7, true, "7");
            card5 = new Card(Suit.Spades, 14, true, "Ace");
        }
        catch(IOException ex)
        {
            System.exit(0);
        }

        hand.add(card1);
        hand.add(card2);
        hand.add(card3);
        hand.add(card4);
        hand.add(card5);
        //act
        boolean isFlush = scoreHand.isFlush(hand);

        // assert
        assertTrue(isFlush);
    }

    @Test
    public void isStraightTest()
    {
        // arrange
        try
        {
            card1 = new Card(Suit.Spades, 10, true, "10");
            card2 = new Card(Suit.Clubs, 11, true, "Jack");
            card3 = new Card(Suit.Spades, 12, true, "Queen");
            card4 = new Card(Suit.Diamonds, 13, true, "King");
            card5 = new Card(Suit.Spades, 14, true, "Ace");

            card6 = new Card(Suit.Spades, 2, true, "2");
            card7 = new Card(Suit.Clubs, 3, true, "3");
            card8 = new Card(Suit.Spades, 4, true, "4");
            card9 = new Card(Suit.Diamonds, 5, true, "5");
            card10 = new Card(Suit.Spades, 6, true, "6");
        }
        catch(IOException ex)
        {
            System.exit(0);
        }

        hand.add(card1);
        hand.add(card2);
        hand.add(card3);
        hand.add(card4);
        hand.add(card5);

        hand2.add(card6);
        hand2.add(card7);
        hand2.add(card8);
        hand2.add(card9);
        hand2.add(card10);

        //act
        boolean isStraight = scoreHand.isStraight(hand);
        boolean isStraight2 = scoreHand.isStraight(hand2);

        // assert
        assertTrue(isStraight);
        assertTrue(isStraight2);
    }

    @Test
    public void isFourOfKindTest()
    {
        // arrange
        try
        {
            card1 = new Card(Suit.Spades, 3, true, "3");
            card2 = new Card(Suit.Clubs, 4, true, "4");
            card3 = new Card(Suit.Hearts, 4, true, "4");
            card4 = new Card(Suit.Diamonds, 4, true, "4");
            card5 = new Card(Suit.Spades, 4, true, "4");

            card6 = new Card(Suit.Spades, 5, true, "5");
            card7 = new Card(Suit.Clubs, 5, true, "5");
            card8 = new Card(Suit.Spades, 4, true, "4");
            card9 = new Card(Suit.Diamonds, 4, true, "4");
            card10 = new Card(Suit.Diamonds, 5, true, "5");
        }
        catch(IOException ex)
        {
            System.exit(0);
        }

        hand.add(card1);
        hand.add(card2);
        hand.add(card3);
        hand.add(card4);
        hand.add(card5);

        hand2.add(card6);
        hand2.add(card7);
        hand2.add(card8);
        hand2.add(card9);
        hand2.add(card10);

        // also tests our sorting
        Collections.sort(hand);
        Collections.sort(hand2);
        //act
        boolean isFour = scoreHand.isFourOfKind(hand);
        boolean isFour2 = scoreHand.isFourOfKind(hand2);

        // assert
        assertTrue(isFour);
        assertFalse(isFour2);
    }

    @Test
    public void isThreeOfKindTest()
    {
        // arrange
        try
        {
            card1 = new Card(Suit.Spades, 3, true, "3");
            card2 = new Card(Suit.Clubs, 3, true, "3");
            card3 = new Card(Suit.Hearts, 4, true, "4");
            card4 = new Card(Suit.Diamonds, 5, true, "5");
            card5 = new Card(Suit.Hearts, 3, true, "3");

            card6 = new Card(Suit.Spades, 5, true, "5");
            card7 = new Card(Suit.Clubs, 5, true, "5");
            card8 = new Card(Suit.Spades, 4, true, "4");
            card9 = new Card(Suit.Diamonds, 4, true, "4");
            card10 = new Card(Suit.Diamonds, 2, true, "2");
        }
        catch(IOException ex)
        {
            System.exit(0);
        }

        hand.add(card1);
        hand.add(card2);
        hand.add(card3);
        hand.add(card4);
        hand.add(card5);

        hand2.add(card6);
        hand2.add(card7);
        hand2.add(card8);
        hand2.add(card9);
        hand2.add(card10);

        // also tests our sorting
        Collections.sort(hand);
        Collections.sort(hand2);
        //act
        boolean isThree = scoreHand.isThreeOfKind(hand);
        boolean isThree2 = scoreHand.isThreeOfKind(hand2);

        // assert
        assertTrue(isThree);
        assertFalse(isThree2);
    }

    @Test
    public void isTwoPairsTest()
    {
        // arrange
        try
        {
            card1 = new Card(Suit.Spades, 3, true, "3");
            card2 = new Card(Suit.Clubs, 3, true, "3");
            card3 = new Card(Suit.Hearts, 4, true, "4");
            card4 = new Card(Suit.Diamonds, 7, true, "7");
            card5 = new Card(Suit.Hearts, 7, true, "7");

            card6 = new Card(Suit.Spades, 5, true, "5");
            card7 = new Card(Suit.Clubs, 5, true, "5");
            card8 = new Card(Suit.Spades, 4, true, "4");
            card9 = new Card(Suit.Diamonds, 5, true, "5");
            card10 = new Card(Suit.Diamonds, 2, true, "2");
        }
        catch(IOException ex)
        {
            System.exit(0);
        }

        hand.add(card1);
        hand.add(card2);
        hand.add(card3);
        hand.add(card4);
        hand.add(card5);

        hand2.add(card6);
        hand2.add(card7);
        hand2.add(card8);
        hand2.add(card9);
        hand2.add(card10);

        // also tests our sorting
        Collections.sort(hand);
        Collections.sort(hand2);
        //act
        boolean isTwoPairs = scoreHand.isTwoPairs(hand);
        boolean isTwoPairs2 = scoreHand.isTwoPairs(hand2);

        // assert
        assertTrue(isTwoPairs);
        assertFalse(isTwoPairs2);
    }

    @Test
    public void isPairTest()
    {
        // arrange
        try
        {
            card1 = new Card(Suit.Spades, 3, true, "3");
            card2 = new Card(Suit.Clubs, 5, true, "5");
            card3 = new Card(Suit.Hearts, 4, true, "4");
            card4 = new Card(Suit.Diamonds, 8, true, "8");
            card5 = new Card(Suit.Hearts, 5, true, "5");

            card6 = new Card(Suit.Spades, 2, true, "2");
            card7 = new Card(Suit.Clubs, 3, true, "3");
            card8 = new Card(Suit.Spades, 4, true, "4");
            card9 = new Card(Suit.Diamonds, 5, true, "5");
            card10 = new Card(Suit.Diamonds, 8, true, "8");
        }
        catch(IOException ex)
        {
            System.exit(0);
        }

        hand.add(card1);
        hand.add(card2);
        hand.add(card3);
        hand.add(card4);
        hand.add(card5);

        hand2.add(card6);
        hand2.add(card7);
        hand2.add(card8);
        hand2.add(card9);
        hand2.add(card10);

        // also tests our sorting
        Collections.sort(hand);
        Collections.sort(hand2);
        //act
        boolean isPair = scoreHand.isPair(hand);
        boolean isPair2 = scoreHand.isPair(hand2);

        // assert
        assertTrue(isPair);
        assertFalse(isPair2);
    }
}