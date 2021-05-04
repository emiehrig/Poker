import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class Player
{
    protected ArrayList<Card> hand;
    private String name;
    private int stash;
    private JPanel cardPanel; //TODO: Implement this.

    /**
     * Constructor for objects of class Player
     */
    public Player()
    {
        hand = new ArrayList<Card>();
        name = "Player";
        setStash(500);
        setCardPanel();
    }

    /**
     * Overloaded constructor for custom name and amount of money to bet with
     */
    public Player(String name, int stash)
    {
        hand = new ArrayList<Card>();
        this.name = name;
        setStash(stash);
        setCardPanel();
    }

    /**
     * Get the player's name.
     * @return The player's name.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Get the amount of money the player has available to bet
     * @return The amount of money the player has to bet
     */
    public int getStash()
    {
        return stash;
    }

    public JPanel getCardPanel()
    {
        return cardPanel;
    }

    public void setName(String name)
    {
        if(name != null && name.trim().length() != 0)
        {
            this.name = name;
        }
    }

    /**
     * Set the amount of money the player has available to bet, must be >= 0
     * @param stash the player's stash amount.
     */
    public void setStash(int stash)
    {
        if (stash >= 0)
        {
            this.stash = stash;
        }
    }

    public void setCardPanel()
    {
        cardPanel = new JPanel();
        cardPanel.setLayout(new FlowLayout());
    }

    public void resetCardPanel()
    {
        for(int i = (hand.size() - 1);  i >= 0; i--)
        {
            cardPanel.remove(i);
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
        }
        else
        {
            card.hide();
        }

        hand.add(card);
        Collections.sort(hand);
        cardPanel.add(card.getCardLabel());
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


    /**
     * Checks hand type and assigns value to it for scoring purposes.
     * @return the value of the hand, based on hand type and additional weighted calculations.
     */
    public int scoreHand()
    {
        CheckHand checkHand = new CheckHand();
        int handValue = checkHand.valueHand(hand);

        return handValue;
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
