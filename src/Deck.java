import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Deck
{
    private ArrayList<Card> cards = new ArrayList<>();

    /**
     * Constructor for objects of class Deck
     */
    public Deck()
    {
        for(Suit suit : Suit.values())
        {
            for (int i = 2; i < 15; i++)
            {
                try
                {
                    if (i < 11)
                    {
                        cards.add(new Card(suit, i, true, Integer.toString(i)));
                    }
                    else if (i == 11)
                    {
                        cards.add(new Card(suit, 11, true, "Jack"));
                    }
                    else if (i == 12)
                    {
                        cards.add(new Card(suit, 12, true, "Queen"));
                    }
                    else if (i == 13)
                    {
                        cards.add(new Card(suit, 13, true, "King"));
                    }
                    else
                    {
                        cards.add(new Card(suit, 14, true, "Ace"));
                    }
                }
                catch(IOException e)
                {
                    System.exit(0);
                }
            }
        }
        Collections.shuffle(cards);
    }

    /**
     * Report on the size of the remaining cards in the deck
     * @return The number of cards left in the deck
     */
    public int cardsLeftInDeck()
    {
        return cards.size();
    }

    /**
     * Deal a card from the deck
     * @return A Card from the deck
     */
    public Card deal()
    {
        return cards.remove(0);
    }

    /**
     * Returns 52 pick-up
     */
    @Override
    public String toString()
    {
        String cardString = "";
        for (Card card : cards)
        {
            cardString += card.toString() + "\n";
        }
        return cardString;
    }
}
