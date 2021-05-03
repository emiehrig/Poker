import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * The Card class represents a standard playing card as typically used in card games.
 * @author Erik Miehrig
 * @version 2021.04.28.01
 */

public class Card
{
    private Suit suit;
    private int value;
    private boolean visible;
    private String name = new String();

    private BufferedImage face;
    private BufferedImage back;
    JLabel cardLabel;

    public Card(Suit suit, int value, boolean visible, String name) throws IOException
    {
        this.suit = suit;
        if (value >= 1 && value <= 11)
        {
            this.value = value;
        }
        this.visible = visible;
        this.name = name;

        String fileName = "png/" + name.toLowerCase() + "_of_" + suit.name().toLowerCase() + ".png";
        face = ImageIO.read(new File(fileName));
        back = ImageIO.read(new File("png/back.png"));
    }

    /**
     * Get the card's suit
     * @return The card's suit
     */
    public Suit getSuit()
    {
        return suit;
    }

    /**
     * Get the card's value
     * @return An integer value of the card, from 1-11 (aces can be high or low)
     */
    public int getValue()
    {
        return value;
    }

    /**
     * Get the card's name
     * @return The name of the card
     */
    public String getName()
    {
        return name;
    }

    /**
     * Check if the card is face-up or face-down
     * @return True if the card is face-up, false if face-down
     */
    public boolean isVisible()
    {
        return visible;
    }

    /**
     * Gets the card's label.
     * @return the Card object's label.
     */
    public JLabel getCardLabel()
    {
        return cardLabel;
    }

    /**
     * Sets the label for the card depending on visibility.
     */
    public void setCardLabel()
    {
        if(isVisible())
        {
            ImageIcon icon = new ImageIcon(face);
            cardLabel.setIcon(icon);
        }
        else
        {
            ImageIcon icon = new ImageIcon(back);
            cardLabel.setIcon(icon);
        }

        cardLabel.setBackground(Color.WHITE);
        cardLabel.setOpaque(true);
    }

    /**
     * Sets the card as face-up
     */
    public void show()
    {
        visible = true;
    }

    /**
     * Sets the card as face-down
     */
    public void hide()
    {
        visible = false;
    }

    /**
     * Overrides the toString() and prints the representation of this card, depending on visibility.
     * @return The card description, i.e. "Ace of Spades" if face-up, "Hidden Card" if face-down.
     */
    @Override
    public String toString()
    {
        String cardDescription = new String();

        if (visible)
        {
            cardDescription = name + " of " + suit.name();
        }
        else
        {
            cardDescription = "Hidden Card";
        }

        return cardDescription;
    }

    /**
     * Overrides equals method of object with our own implementation.
     * @param obj The object to compare to this one.
     * @return True if the cards match suit, name, and value, otherwise false.
     */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        else if (!(obj instanceof Card))
        {
            return false;
        }
        // It's a card, transform it into one
        Card that = (Card) obj;
        // Compare suit, value, and name
        if (this.getValue() == that.getValue() &&
                this.getSuit() == that.getSuit() &&
                this.getName().equals(that.getName()))
        {
            return true;
        }
        return false;
    }

    public String getMemoryAddress()
    {
        return super.toString();
    }
}