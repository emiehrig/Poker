
/**
 * Write a description of class IllegalBetException here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class IllegalBetException extends Exception
{
    // instance variables - replace the example below with your own
    private int bet;
    private int minBet;
    private int maxBet;
    private int playerStash;

    /**
     * Constructor for objects of class IllegalBetException
     */
    public IllegalBetException(int bet, int minBet, int maxBet, int playerStash)
    {
        // initialise instance variables
        this.bet = bet;
        this.minBet = minBet;
        this.maxBet = maxBet;
        this.playerStash = playerStash;
    }

    public String toString()
    {
        if(bet < 0)
        {
            return "Bet cannot be negative";
        }
        else if(bet > playerStash)
        {
            return "Cannot bet more than your current total funds";
        }
        else if(bet < minBet)
        {
            return "Cannot bet less than the minimum of $" + minBet + ". If this was your intention, please check instead.";
        }
        else if(bet > maxBet)
        {
            return "Cannot bet more than the maximum of $" + maxBet;
        }
        else
        {
            return "Your bet was not valid.";
        }
    }
}
