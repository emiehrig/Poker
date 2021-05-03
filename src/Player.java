import javax.swing.*;
import java.util.ArrayList;

public class Player
{
    protected ArrayList<Card> hand;
    private String name;
    private int stash;
    private JPanel cardArea; //TODO: Implement this.

    /**
     * Constructor for objects of class Player
     */
    public Player()
    {
        hand = new ArrayList<Card>();
        name = "Player";
        setStash(500);
    }

    /**
     * Overloaded constructor for custom name and amount of money to bet with
     */
    public Player(String name, int stash)
    {
        hand = new ArrayList<Card>();
        this.name = name;
        setStash(stash);
    }

    /**
     * Get the amount of money the player has available to bet
     * @return The amount of money the player has to bet
     */
    public int getStash()
    {
        return stash;
    }

    public JPanel getCardArea()
    {
        return cardArea;
    }

    /**
     * Set the amount of money the player has available to bet, must be >= 0
     */
    public void setStash(int stash)
    {
        if (stash >= 0)
        {
            this.stash = stash;
        }
    }

    /**
     * Get a card from the dealer
     * @param card A card from the deck of cards
     * @param visibility Set the card face-up (true) or face-down (false)
     */
    public void receiveCard(Card card, boolean visibility)
    {
        if (visibility)
        {
            card.show();
            hand.add(card);
        }
        else
        {
            card.hide();
            hand.add(card);
        }
    }

    /**
     * Flips all cards face-up
     */
    public void showAllCards()
    {
        for (Card card : hand)
        {
            card.show();
        }
    }


    public void scoreHand()
    {
        //TODO: Add documentation regarding scoring of the hand as per 5-card stud rules.
        //TODO: Refactor for 5-card stud.
        //TODO: Add correct return type (void is temporary).
    }

    /**
     * Clear the player's hand, for use after one round of Blackjack
     */
    public void clearHand()
    {
        hand.clear();
    }

    /**
     * Return a string representing the player and the amount of money they have available
     */
    @Override
    public String toString()
    {
        String player = name + " has $" + stash + "\n";

        for(Card c : hand)
        {
            player += c.toString() + "\n";
        }
        return player;
    }
}
