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
    private JPanel dealerCardArea; //TODO: Move this to Player.
    private JLabel playerName, playerStash, playerScore;
    private JLabel dealerName, dealerStash, dealerScore;
    private JLabel potLabel, statusLabel, resultsLabel;
    private JLabel welcomeMessage;
    private JLabel firstDealerCard; //TODO: Delete.
    private JButton playBtn, playAgainBtn, betBtn, hitBtn, standBtn;
    private JTextField betField;
    private JLabel betLabel;
    private JMenuItem newGameItem, quitItem, aboutItem;

    // declare instance fields (player, dealer, etc.)
    private Player player;
    private Dealer dealer;

    private int pot = 0;
    private int bet = 0;

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
        frame = new JFrame("BlackJack");
        JPanel contentPane = new JPanel(new BorderLayout());
        frame.setContentPane(contentPane);
        contentPane.setBorder(new EmptyBorder(6, 6, 6, 6));

        // construct North Flow Layout and its components
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new FlowLayout());

        welcomeMessage = new JLabel("<html>Welcome to Blackjack!<br>" + "\n" +
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
        hitBtn = new JButton("Hit");
        standBtn = new JButton("Stand");

        // set initial visibilities
        betField.setVisible(false);
        betBtn.setVisible(false);
        hitBtn.setVisible(false);
        standBtn.setVisible(false);

        // add action listeners
        playBtn.addActionListener(this);
        betBtn.addActionListener(this);
        hitBtn.addActionListener(this);
        standBtn.addActionListener(this);

        // add buttons to panel

        southPanel.add(playBtn);
        southPanel.add(betField);
        southPanel.add(betBtn);
        southPanel.add(hitBtn);
        southPanel.add(standBtn);

        contentPane.add(southPanel, BorderLayout.SOUTH);

        // construct West BoxLayout and its components
        JPanel westPanel = new JPanel();
        westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.Y_AXIS));

        playerName = new JLabel("Player");
        playerStash = new JLabel("Stash: $0");
        playerScore = new JLabel("Score: 0");

        westPanel.add(playerName);
        westPanel.add(Box.createVerticalStrut(3));
        westPanel.add(playerStash);
        westPanel.add(Box.createVerticalStrut(3));
        westPanel.add(playerScore);

        playerName.setVisible(false);
        playerStash.setVisible(false);
        playerScore.setVisible(false);

        contentPane.add(westPanel, BorderLayout.WEST);

        // construct East BoxLayout and its components
        JPanel eastPanel = new JPanel();
        eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.Y_AXIS));

        dealerName = new JLabel("Dealer");
        dealerStash = new JLabel("Stash: $0");
        dealerScore = new JLabel("Score: 0");


        eastPanel.add(dealerName);
        eastPanel.add(Box.createVerticalStrut(3));
        eastPanel.add(dealerStash);
        eastPanel.add(Box.createVerticalStrut(3));
        eastPanel.add(dealerScore);


        dealerName.setVisible(false);
        dealerStash.setVisible(false);
        dealerScore.setVisible(false);

        contentPane.add(eastPanel, BorderLayout.EAST);

        // Construct playing area
        JPanel cardGrid = new JPanel();
        cardGrid.setLayout(new GridLayout(2,1));

        //TODO: Refactor this as CardArea has been moved to Player.
        //dealerCardArea = new JPanel();
        //dealerCardArea.setLayout(new FlowLayout());

        //playerCardArea = new JPanel();
        //playerCardArea.setLayout(new FlowLayout());

        //cardGrid.add(dealerCardArea);
        //cardGrid.add(playerCardArea);

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
     * Provides player with the option to enter their name or use a default name.
     */
    private void createPlayer()
    {
        String newName = JOptionPane.showInputDialog(frame, "Please enter your name and click OK. Click cancel to continue with the default name",
                "Enter Name", JOptionPane.OK_OPTION);

        if(newName == null)
        {
            player = new Player();
        }
        else
        {
            player = new Player(newName, 500);
            playerName.setText(newName);
        }

        dealer = new Dealer(player.getStash() * 5);
    }

    /**
     * Begin the game of 5-card stud.
     */
    private void startGame()
    {
        //TODO: Refactor.
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
            //displayGame(); //TODO: Refactor.
        }
//        if(event.getSource() == betBtn)
//        {
//            getBets();
//        }
//        if(event.getSource() == hitBtn)
//        {
//            playerTurn();
//        }
//        if(event.getSource() == standBtn)
//        {
//            dealerTurn();
//        }
//        if(event.getSource() == aboutItem)
//        {
//            showAbout();
//        }
//        if(event.getSource() == quitItem)
//        {
//            System.exit(0);
//        }
    }
}
