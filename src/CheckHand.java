import java.util.ArrayList;
/**
 * A class whose purpose is to rank the various poker hands.
 *
 * @author Erik Miehrig
 * @version 2021.05.04.01
 */
public class CheckHand
{
    /**
     * Constructor for objects of class CheckHand
     */
    public CheckHand()
    {
    }

    /**
     * Values a hand based on what it is, as well as adjusting it for card values etc.
     * @param hand the hand to value.
     * @return the value of the hand.
     */
    public int valueHand(ArrayList<Card> hand)
    {
        int value = 0;

        if(isRoyalFlush(hand))
        {
            value = valueRoyalFlush(hand);
        }
        else if(isFlush(hand) && isStraight(hand))
        {
            value = valueStraightFlush(hand);
        }
        else if(isStraight(hand))
        {
            value = valueStraight(hand);
        }
        else if(isFourOfKind(hand))
        {
            value = valueFourOfKind(hand);
        }
        else if(isFullHouse(hand))
        {
            value = valueFullHouse(hand);
        }
        else if(isThreeOfKind(hand))
        {
            value = valueThreeOfKind(hand);
        }
        else if(isTwoPairs(hand))
        {
            value = valueTwoPairs(hand);
        }
        else if(isPair(hand))
        {
            value = valuePair(hand);
        }
        else
        {
            value = valueHighCard(hand);
        }

        return value;
    }

    /**
     * Sets the value for a royal flush.
     * @param hand the hand to adjust.
     * @return the adjusted value.
     */
    public int valueRoyalFlush(ArrayList<Card> hand)
    {
        int value = 800;
        return value;
    }

    /**
     * Adjusts the value for a straight flush.
     * @param hand the hand to adjust.
     * @return the adjusted value.
     */
    public int valueStraightFlush(ArrayList<Card> hand)
    {
        int value = 700;
        value += valueHighCard(hand);
        return value;
    }

    /**
     * Adjusts the value of a hand containing a straight.
     * @param hand the hand to adjust.
     * @return the adjusted value.
     */
    public int valueStraight(ArrayList<Card> hand)
    {
        int value = 600;
        value += hand.get(4).getValue();
        return value;
    }

    /**
     * Adjusts the hand value for four of a kind.
     * @param hand the hand to adjust.
     * @return the adjusted value.
     */
    public int valueFourOfKind(ArrayList<Card> hand)
    {
        int value = 500;
        int fourKind = hand.get(2).getValue();
        value += fourKind;

        return value;
    }

    /**
     * Adjusts the hand value for a full house.
     * @param hand the hand to adjust.
     * @return the adjusted value.
     */
    public int valueFullHouse(ArrayList<Card> hand)
    {
        int value = 400;
        int threeKind = 0;
        int pair = 0;

        if(hand.get(0).getValue() == hand.get(1).getValue() && hand.get(0).getValue() == hand.get(2).getValue())
        {
            threeKind = hand.get(0).getValue() * 3;
            pair = hand.get(3).getValue() * 2;
            value += threeKind + pair;
        }
        else
        {
            pair = hand.get(0).getValue() * 2;
            threeKind = hand.get(2).getValue() * 3;
            value += threeKind + pair;
        }

        return value;
    }

    /**
     * Adjusts the value of a hand containing three of a kind.
     * @param hand the hand to adjust.
     * @return the adjusted value.
     */
    public int valueThreeOfKind(ArrayList<Card> hand)
    {
        int value = 300;
        int threeKind = hand.get(2).getValue() * 3;

        value += threeKind;

        return value;
    }

    /**
     * Adjusts the hand value for hands with two pairs.
     * @param hand the hand to adjust.
     * @return the adjusted value.
     */
    private int valueTwoPairs(ArrayList<Card> hand)
    {
        int value = 200;
        int pair = 0;

        for(int i = 0; i < hand.size() - 1; i++)
        {
            if(hand.get(i).getValue() == hand.get(i + 1).getValue())
            {
                pair = hand.get(i).getValue() * 2;
                value += pair;
            }
        }

        return value;
    }

    /**
     * Adjust hand value for a hand containing a pair.
     * @param hand the hand to adjust.
     * @return the adjusted value.
     */
    private int valuePair(ArrayList<Card> hand)
    {
        int value = 100;
        int pair = 0;

        for(int i = 0; i < hand.size() - 1; i++)
        {
            if(hand.get(i).getValue() == hand.get(i + 1).getValue())
            {
                pair = hand.get(i).getValue() * 2;
                value += pair;
            }
        }

        return value;
    }

    /**
     * adjust hand value based on the high card.
     * @param hand the hand to adjust.
     * @return the modified hand value.
     */
    public int valueHighCard(ArrayList<Card> hand)
    {
        int value = hand.get(hand.size() - 1).getValue();
        return value;
    }

    /**
     * Checks if there is a royal flush.
     * @param hand the hand to check.
     * @return true if there is a royal flush, otherwise false.
     */
    public boolean isRoyalFlush(ArrayList<Card> hand)
    {
        if(!isFlush(hand))
        {
            return false;
        }

        int sum = 0;

        if(hand.get(4).getValue() != 14)
        {
            return false;
        }
        else
        {
            for(Card card : hand)
            {
                sum += card.getValue();
            }

            if(sum == 60)
            {
                return true;
            }
        }

        return false;
    }

    /**
     * checks if the hand contains a Flush (all the same suit).
     * @param hand the hand to check.
     * @return true if the hand contains a flush, otherwise false.
     */
    public boolean isFlush(ArrayList<Card> hand)
    {
        if(hand.size() != 5)
        {
            return false;
        }

        Suit suit = hand.get(0).getSuit();

        for(Card card : hand)
        {
            if(card.getSuit() != suit)
            {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the hand contains a straight.
     * @param hand the hand to check.
     * @return true if the hand contains a straight.
     */
    public boolean isStraight(ArrayList<Card> hand)
    {
        if(hand.size() != 5)
        {
            return false;
        }

        int sum = 0;
        int firstValue = hand.get(0).getValue();

        // Card values should be unique in a straight, so summing should give us a unique value
        // if we know the high or low card (as with the ace).
        if(hand.get(4).getValue() == 14)
        {
            for(Card card : hand)
            {
                sum += card.getValue();
            }

            if(sum == 60)
            {
                return true;
            }
            else if(sum == 28)
            {
                return true;
            }

        }

        // Check for consecutive values if there is no ace.
        if(hand.get(4).getValue() != 14)
        {
            for (int i = 1; i < 5; i++)
            {
                if(hand.get(i).getValue() != (firstValue + 1))
                {
                    return false;
                }

                firstValue++;
            }

            return true;
        }

        return false;
    }

    /**
     * Checks if the hand contains four of a kind.
     * @param hand the hand to check.
     * @return true if there is four of a kind, otherwise false.
     */
    public boolean isFourOfKind(ArrayList<Card> hand)
    {
        // minimum of 4 cards needed.
        if(hand.size() < 4)
        {
            return false;
        }

        // get the lowest card and highest card's value.
        int lowCard = hand.get(0).getValue();
        int highCard = hand.get(hand.size() - 1).getValue();

        // if the hand has 4 cards, low and high values should be the same.
        // sorted by value, the two in between should also be the same, so we should not need to do any additional checking.
        if(hand.size() == 4)
        {
            if(lowCard != highCard)
            {
                return false;
            }
        }
        else
        {
            // with 5 cards in hand, if the second card in the hand doesn't match the high value, or 4th card doesn't match the low value, it can't be 4-of-a-kind.
            if(hand.get(3).getValue() != lowCard && hand.get(1).getValue() != highCard)
            {
                return false;
            }
        }

        // if none of our previous checks failed, we should have 4 of a kind.
        return true;
    }

    /**
     * Checks if the hand contains a full house.
     * @param hand the hand to check.
     * @return true if the hand contains a full house, otherwise false.
     */
    public boolean isFullHouse(ArrayList<Card> hand)
    {
        // must have a full hand for a full house
        if(hand.size() != 5)
        {
            return false;
        }

        int lowCard = hand.get(0).getValue();
        int highCard = hand.get(hand.size() - 1).getValue();

        // check if the middle card matches the low card, if true check that the next card up matches the high card. Full house.
        if(hand.get(2).getValue() == lowCard)
        {
            if(hand.get(3).getValue() == highCard)
            {
                return true;
            }
        }
        // check if the middle card matches the high card, if true check that the second card in hand matches the low card. Full house.
        else if(hand.get(2).getValue() == highCard)
        {
            if(hand.get(1).getValue() == lowCard)
            {
                return true;
            }
        }

        // if our previous checks failed we don't have a full house.
        return false;
    }

    /**
     * Checks if there is three of a kind.
     * @param hand the hand to check.
     * @return true if there is three of a kind, otherwise false.
     */
    public boolean isThreeOfKind(ArrayList<Card> hand)
    {
        if(hand.size() < 3)
        {
            return false;
        }

        if(hand.size() == 5 && isFullHouse(hand))
        {
            return false;
        }

        // get the middle card's value (high card if only 3 cards in hand). Card 3 will always match in 3 of a kind.
        int middleCard = hand.get(2).getValue();

        // pick low card with 3 cards in hand, if no match not 3 of a kind.
        if(hand.size() == 3 && hand.get(0).getValue() != middleCard)
        {
            return false;
        }
        // if hand has more than 3 cards and the middle card doesn't match either card next to it, we don't have 3 of a kind.
        else if(hand.size() > 3 && hand.get(1).getValue() != middleCard && hand.get(3).getValue() != middleCard)
        {
            return false;
        }

        if(hand.size() == 5 && hand.get(4).getValue() != middleCard && hand.get(0).getValue() != middleCard)
        {
            return false;
        }

        // if none of our checks failed, return true.
        return true;
    }

    /**
     * Checks if the hand has two pairs.
     * @param hand the hand to check.
     * @return true if there are two pairs, otherwise false.
     */
    public boolean isTwoPairs(ArrayList<Card> hand)
    {
        // must have at least 4 cards.
        if(hand.size() < 4)
        {
            return false;
        }

        if(hand.size() == 5 && isFullHouse(hand))
        {
            return false;
        }

        int card1 = hand.get(0).getValue();
        int card2 = hand.get(1).getValue();
        int card3 = hand.get(2).getValue();
        int card4 = hand.get(3).getValue();

        if(hand.size() == 4)
        {
            // check if each pair matches.
            if(card1 == card2 && card3 == card4)
            {
                return true;
            }
        }
        else
        {
            int card5 = hand.get(4).getValue();

            if(card1 == card2 && card3 == card4)
            {
                return true;
            }
            else if(card1 == card2 && card4 == card5)
            {
                return true;
            }
            else if(card2 == card3 && card4 == card5)
            {
                return true;
            }
        }

        return false;
    }

    /**
     * Checks if the hand has a pair.
     * @param hand the hand to check.
     * @return true if there is one pair, otherwise false.
     */
    public boolean isPair(ArrayList<Card> hand)
    {
        if(isTwoPairs(hand) || isThreeOfKind(hand) || isFourOfKind(hand) || isFullHouse(hand))
        {
            return false;
        }

        for(int i = 0; i < hand.size() - 1; i++)
        {
            if(hand.get(i).getValue() == hand.get(i + 1).getValue())
            {
                return true;
            }
        }

        return false;
    }
}