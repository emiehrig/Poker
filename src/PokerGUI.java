import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This is the main application of the Poker game.
 */
public class PokerGUI implements ActionListener
{
    private static final double VERSION = 1.0;
    //TODO: Finish refactoring this class to suit 5-card stud.
    // declare GUI components
    private JFrame frame;
    private JPanel cardGrid;
    private JLabel playerName, playerStash;
    private JLabel dealerName, dealerStash;
    private JLabel potLabel, statusLabel, resultsLabel;
    private JLabel welcomeMessage;
    private JButton playBtn, playAgainBtn, betBtn, checkBtn, callBtn, raiseBtn, foldBtn;
    private JTextField betField;
    private JLabel betLabel;
    private JMenuItem newGameItem, quitItem, aboutItem;

    // declare instance fields (player, dealer, etc.)
    private Player player = new Player();
    private Dealer dealer = new Dealer(player.getStash() * 5);

    private int pot = 0;
    private int bet = 0;
    private static final int ANTE = 10;
    private static final int MAX_BET = 10;
    private static final int MIN_BET = 1;

    /**
     * Constructor for objects of class GraphicalUserInterface
     */
    public PokerGUI()
    {
        makeFrame();
    }

    /**
     * Static void main method to run the application outside of the BlueJ object bench.
     */
    public static void main(String[] args)
    {
        PokerGUI pokerGUI = new PokerGUI();
    }

    /**
     * Creates the Swing frame and content.
     */
    public void makeFrame()
    {
        // construct the main content pane
        frame = new JFrame("5-Card Stud");
        JPanel contentPane = new JPanel(new BorderLayout());
        frame.setContentPane(contentPane);
        contentPane.setBorder(new EmptyBorder(6, 6, 6, 6));

        // construct North Flow Layout and its components
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new FlowLayout());

        welcomeMessage = new JLabel("<html>Welcome to 5-Card Stud!<br>" + "\n" +
                "Click Play to begin the game!</html>");
        potLabel = new JLabel("Pot: $0");
        statusLabel = new JLabel("Status: Testing");
        resultsLabel = new JLabel("Results: Hopeful");

        northPanel.add(welcomeMessage);
        northPanel.add(potLabel);
        northPanel.add(Box.createHorizontalStrut(3));
        northPanel.add(statusLabel);
        northPanel.add(Box.createHorizontalStrut(3));
        northPanel.add(resultsLabel);

        potLabel.setVisible(false);
        statusLabel.setVisible(false);
        resultsLabel.setVisible(false);

        contentPane.add(northPanel, BorderLayout.NORTH);

        // construct South Flow Layout and its components
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new FlowLayout());

        // create buttons
        playBtn = new JButton("Play");
        betField = new JTextField(3);
        betBtn = new JButton("Bet");
        checkBtn = new JButton("Check");
        callBtn = new JButton("Call");
        raiseBtn = new JButton("Raise");
        foldBtn = new JButton("Fold");

        // set initial visibilities
        betField.setVisible(false);
        betBtn.setVisible(false);
        checkBtn.setVisible(false);
        callBtn.setVisible(false);
        raiseBtn.setVisible(false);
        foldBtn.setVisible(false);

        // add action listeners
        playBtn.addActionListener(this);
        betBtn.addActionListener(this);
        checkBtn.addActionListener(this);
        callBtn.addActionListener(this);
        raiseBtn.addActionListener(this);
        foldBtn.addActionListener(this);

        // add buttons to panel

        southPanel.add(playBtn);
        southPanel.add(betField);
        southPanel.add(betBtn);
        southPanel.add(checkBtn);
        southPanel.add(callBtn);
        southPanel.add(raiseBtn);
        southPanel.add(foldBtn);

        contentPane.add(southPanel, BorderLayout.SOUTH);

        // construct West BoxLayout and its components
        JPanel westPanel = new JPanel();
        westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.Y_AXIS));

        playerName = new JLabel("Player");
        playerStash = new JLabel("Stash: $0");

        westPanel.add(playerName);
        westPanel.add(Box.createVerticalStrut(3));
        westPanel.add(playerStash);

        playerName.setVisible(false);
        playerStash.setVisible(false);

        contentPane.add(westPanel, BorderLayout.WEST);

        // construct East BoxLayout and its components
        JPanel eastPanel = new JPanel();
        eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.Y_AXIS));

        dealerName = new JLabel("Dealer");
        dealerStash = new JLabel("Stash: $0");

        eastPanel.add(dealerName);
        eastPanel.add(Box.createVerticalStrut(3));
        eastPanel.add(dealerStash);

        dealerName.setVisible(false);
        dealerStash.setVisible(false);

        contentPane.add(eastPanel, BorderLayout.EAST);

        // Construct playing area
        cardGrid = new JPanel();
        cardGrid.setLayout(new GridLayout(2,1));

        //TODO: Refactor this as CardArea has been moved to Player.

        cardGrid.add(dealer.getCardPanel());
        cardGrid.add(player.getCardPanel());

        contentPane.add(cardGrid, BorderLayout.CENTER);

        makeMenuBar(frame);

        frame.pack();
        frame.setVisible(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    /**
     * Create the menu bar.
     */
    private void makeMenuBar(JFrame frame)
    {
        JMenuBar menubar = new JMenuBar();
        frame.setJMenuBar(menubar);

        JMenu fileMenu = new JMenu("File");
        JMenu helpMenu = new JMenu("Help");

        menubar.add(fileMenu);
        menubar.add(helpMenu);

        newGameItem = new JMenuItem("New Game");
        quitItem = new JMenuItem("Quit");
        aboutItem = new JMenuItem("About");

        newGameItem.addActionListener(this);
        quitItem.addActionListener(this);
        aboutItem.addActionListener(this);

        fileMenu.add(newGameItem);
        fileMenu.add(quitItem);
        helpMenu.add(aboutItem);
    }

    /**
     * Displays main game content such as player information, game status information, and input fields and buttons for the initial game state.
     */
    private void displayGame()
    {
        // display player and dealer information
        playerName.setVisible(true);
        playerStash.setVisible(true);
        playerStash.setText("Stash: $" + player.getStash());

        dealerName.setVisible(true);
        dealerStash.setVisible(true);
        dealerStash.setText("Stash: $" + dealer.getStash());

        // display other information for the initial betting phase
        potLabel.setVisible(true);
        statusLabel.setVisible(true);
        statusLabel.setText("Status: Betting");

        // display buttons for the initial betting phase
        betField.setVisible(true);
        betBtn.setVisible(true);
        checkBtn.setVisible(true);
        foldBtn.setVisible(true);
    }

    /**
     * Provides player with the option to enter their name or use a default name.
     */
    private void createPlayer()
    {
        String newName = JOptionPane.showInputDialog(frame, "Please enter your name and click OK. Click cancel to continue with the default name",
                "Enter Name", JOptionPane.OK_OPTION);

        if(newName == null)
        {
            player.setName("Player");
            playerName.setText("Player");
        }
        else
        {
            player.setName(newName);
            playerName.setText(newName);
        }
    }

    /**
     * Begin the game of 5-card stud.
     */
    private void startGame()
    {
        player.setStash(player.getStash() - ANTE);
        dealer.setStash(dealer.getStash() - ANTE);
        pot = ANTE * 2;
        potLabel.setText("Pot: $" + pot);
        playerStash.setText("Stash: $" + player.getStash());
        dealerStash.setText("Stash: $" + dealer.getStash());

        player.receiveCard(dealer.deal(), true);
        dealer.receiveCard(dealer.deal(), false);
        player.receiveCard(dealer.deal(), true);
        dealer.receiveCard(dealer.deal(), true);
    }

    /**
     * Finish the round and deal new cards after a round of bets, unless players already have 5 cards.
     */
    private void finishRound()
    {
        if(player.hand.size() < 5)
        {
            player.receiveCard(dealer.deal(), true);
            dealer.receiveCard(dealer.deal(), true);
        }
        else
        {
            determineWinner();
        }
    }

    public void determineWinner()
    {
        dealer.showAllCards();

        if(player.scoreHand() > dealer.scoreHand())
        {
            player.setStash(player.getStash() + pot);
            declareWinner("You win!");
        }
        else if(dealer.scoreHand() > player.scoreHand())
        {
            dealer.setStash(dealer.getStash() + pot);
            declareWinner("Dealer wins!");
        }
        else if(player.scoreHand() == dealer.scoreHand())
        {
            statusLabel.setText("Hand Over");
            resultsLabel.setText("Results: Tie");

            int playAgain = JOptionPane.showConfirmDialog(frame, "Whoops, you tied. How did that happen? You have $" + player.getStash() + " left. Would you like to play another round?",
                    "Play Again?", JOptionPane.YES_NO_OPTION);

            if(playAgain == JOptionPane.YES_OPTION)
            {
                resetHand();
            }
            else
            {
                System.exit(0);
            }
        }
    }

    /**
     * Clears the hand and graphical cards from the playing field, and resets GUI components for betting.
     * Refactored into its own method to deal with pushes not declaring a winner and returning money from the pot.
     */
    private void resetHand()
    {
        dealer.resetCardPanel();
        player.resetCardPanel();

        // clear hands, reset deck, and set up the next betting phase
        dealer.clearHand();
        player.clearHand();
        dealer.resetDeck();

        betField.setVisible(true);
        betBtn.setVisible(true);
        checkBtn.setVisible(true);
        foldBtn.setVisible(true);
        statusLabel.setText("Status: Betting");
        resultsLabel.setVisible(false);

        // repaint the frame so that the cards removed earlier are also removed from the GUI display
        frame.repaint();
    }


    public void getBets()
    {
        String currentBet = betField.getText();

        // ensure error handling for non-integer input
        try
        {
            bet = Integer.decode(currentBet);
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(frame,
                    "You did not enter a valid bet. Please enter a numeric bet between " + MIN_BET + " and " + MAX_BET,
                    "Invalid Bet", JOptionPane.OK_OPTION);
            return;
        }

        try
        {
            if(bet < MIN_BET || bet > MAX_BET || bet > player.getStash())
            {
                throw new IllegalBetException(bet, MIN_BET, MAX_BET, player.getStash());
            }
            else
            {
                player.setStash(player.getStash() - bet);
                pot += bet;
            }
        }
        catch(IllegalBetException ex)
        {
            JOptionPane.showMessageDialog(frame, ex.toString(), "IllegalBetException", JOptionPane.OK_OPTION);
            return;
        }

        if(dealer.getStash() >= bet)
        {
            dealer.setStash(dealer.getStash() - bet);
            pot += bet;
        }
        else
        {
            pot += dealer.getStash();
            dealer.setStash(0);
        }

        bet = 0;
        potLabel.setText("Pot: $" + pot);
        playerStash.setText("Stash: $" + player.getStash());
        dealerStash.setText("Stash: $" + dealer.getStash());
    }

    private void declareWinner(String winnerMsg)
    {
        pot = 0;
        potLabel.setText("Pot: $" + pot);
        dealerStash.setText("Stash: $" + dealer.getStash());
        playerStash.setText("Stash: $" + player.getStash());
        resultsLabel.setText("Results: " + winnerMsg);
        resultsLabel.setVisible(true);
        statusLabel.setText("Status: Winner Declared");

        // check that both players still have money
        if(player.getStash() == 0)
        {
            JOptionPane.showMessageDialog(frame, "You lost everything. Maybe it's time to think about other pursuits than gambling?",
                    "Oops - Total Loss", JOptionPane.OK_OPTION);
            System.exit(0);
        }
        if(dealer.getStash() == 0)
        {
            JOptionPane.showMessageDialog(frame, "Congratulations, you took everything from the House. You should consider playing the lottery now.",
                    "Congratulations - Total Win", JOptionPane.OK_OPTION);
            System.exit(0);
        }


        int playAgain = JOptionPane.showConfirmDialog(frame, winnerMsg + " You have $" + player.getStash() + " left. Would you like to play another round?",
                "Play Again?", JOptionPane.YES_NO_OPTION);

        if(playAgain == JOptionPane.YES_OPTION)
        {
            resetHand();
            startGame();
        }
        else if(playAgain == JOptionPane.NO_OPTION)
        {
            System.exit(0);
        }
    }

    /**
     * Shows the About menu with version information.
     */
    private void showAbout()
    {
        JOptionPane.showMessageDialog(frame, "Version: " + VERSION, "About", JOptionPane.OK_OPTION);
    }

    /**
     * Implementation of the ActionListener interface.
     */
    public void actionPerformed(ActionEvent event)
    {
        if(event.getSource() == playBtn || event.getSource() == newGameItem)
        {
            // hide welcome screen components
            welcomeMessage.setVisible(false);
            playBtn.setVisible(false);

            createPlayer();
            displayGame();
            startGame();
        }
        if(event.getSource() == betBtn)
        {
            getBets();
            finishRound();
        }
        if(event.getSource() == checkBtn)
        {
            finishRound();
        }
        if(event.getSource() == foldBtn)
        {
            determineWinner();
        }
        if(event.getSource() == aboutItem)
        {
            showAbout();
        }
        if(event.getSource() == quitItem)
        {
            System.exit(0);
        }
    }
}
