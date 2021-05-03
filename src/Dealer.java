public class Dealer extends Player
{
    private Deck deck;

    /**
     * Constructor for objects of class Dealer
     */
    public Dealer(int stash)
    {
        super("Dealer", stash);
        deck = new Deck();
    }

    /**
     * Takes a card from the deck and returns it
     * Used in conjunction with the Player's receiveCard method
     * Example: player.receiveCard(dealer.deal());
     * @return A card from the deck
     */
    public Card deal()
    {
        if (deck.cardsLeftInDeck() != 0)
        {
            return deck.deal();
        }
        return null;
    }

    /**
     * Get a full, shuffled deck back to the dealer
     */
    public void resetDeck()
    {
        deck = new Deck();
    }


}
