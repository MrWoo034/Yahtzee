package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartPanel extends JPanel {

    /****************** Start Panel Components ******************/
    //Welcome label
    private static ImageIcon logo = new ImageIcon(new ImageIcon("res/yahtzee-600w.png").getImage().getScaledInstance(600, 350, Image.SCALE_SMOOTH));
    JLabel titleLabel = new JLabel();

    JPanel buttonPanel = new JPanel();
    //main menu buttons
    JButton newGameButton = new JButton("New Game");
    JButton loadGameButton = new JButton("Load Game");


    // Used to setup the player name entry
    // and number of player select panels.
    private JPanel playerSelectPanel = new JPanel();
    private JPanel playerEntryPanel = new JPanel();

    // Requirement 3.5.1 Components

    JButton twoPlayers = new JButton("Two Players");
    JButton threePlayers = new JButton("Three Players");
    JButton fourPlayers = new JButton("Four Players");

    private int numPlayers = 0;

    NewGameListener gameListener;

    // Requirement 3.5.1.1 Components

    JTextField[] playerNames = new JTextField[4];

    JLabel enterPlayer1 = new JLabel("Enter a name for Player 1 below:");
    JLabel enterPlayer2 = new JLabel("Enter a name for Player 2 below:");
    JLabel enterPlayer3 = new JLabel("Enter a name for Player 3 below:");
    JLabel enterPlayer4 = new JLabel("Enter a name for Player 4 below:");

    JButton submitBtn = new JButton("Begin!");


    //constructor
    public StartPanel() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(View.WINDOW_COLOR);

        buttonPanel.setLayout(new GridLayout(3, 1));
        titleLabel.setIcon(logo);
        titleLabel.setAlignmentX(CENTER_ALIGNMENT);
        newGameButton.setActionCommand("New Game");
        newGameButton.setFont(new Font("Arial", Font.BOLD, 24));
        loadGameButton.setActionCommand("Load Game");
        loadGameButton.setFont(new Font("Arial", Font.BOLD, 24));
        buttonPanel.setBackground(View.WINDOW_COLOR);
        buttonPanel.add(newGameButton);
        buttonPanel.add(loadGameButton);

        this.add(titleLabel);

        // NUM OF PLAYERS SELECT PANEL

        twoPlayers.setFont(new Font("Arial", Font.BOLD, 48));
        threePlayers.setFont(new Font("Arial", Font.BOLD, 48));
        fourPlayers.setFont(new Font("Arial", Font.BOLD, 48));

        playerNames[0] = new JTextField("Snoopy");
        playerNames[1] = new JTextField("Bryan");
        playerNames[2] = new JTextField("Marco");
        playerNames[3] = new JTextField("Leif");

        playerSelectPanel.setLayout(new GridLayout(3, 1));
        playerSelectPanel.setAlignmentX(CENTER_ALIGNMENT);
        playerSelectPanel.setBackground(View.WINDOW_COLOR);
        playerSelectPanel.add(twoPlayers);
        playerSelectPanel.add(threePlayers);
        playerSelectPanel.add(fourPlayers);

        twoPlayers.setActionCommand("2");
        threePlayers.setActionCommand("3");
        fourPlayers.setActionCommand("4");

        // PLAYER NAME ENTRY PANEL


        this.playerEntryPanel.setLayout(new GridLayout(8, 1));
        this.playerEntryPanel.setBackground(View.WINDOW_COLOR);

        for (int i = 0; i < 4; i++) {
            playerNames[i].setHorizontalAlignment(JTextField.CENTER);
        }
        enterPlayer1.setAlignmentX(CENTER_ALIGNMENT);
        enterPlayer2.setAlignmentX(CENTER_ALIGNMENT);
        enterPlayer3.setAlignmentX(CENTER_ALIGNMENT);
        enterPlayer4.setAlignmentX(CENTER_ALIGNMENT);

        JPanel player1panel = new JPanel();
        player1panel.setBackground(View.WINDOW_COLOR);
        player1panel.setLayout(new BoxLayout(player1panel, BoxLayout.Y_AXIS));
        enterPlayer1.setForeground(Color.WHITE);
        enterPlayer1.setFont(new Font("Arial", Font.BOLD, 24));
        player1panel.add(enterPlayer1);
        player1panel.add(playerNames[0]);
        playerEntryPanel.add(player1panel);

        JPanel player2panel = new JPanel();
        player2panel.setBackground(View.WINDOW_COLOR);
        player2panel.setLayout(new BoxLayout(player2panel, BoxLayout.Y_AXIS));
        enterPlayer2.setForeground(Color.WHITE);
        enterPlayer2.setFont(new Font("Arial", Font.BOLD, 24));
        player2panel.add(enterPlayer2);
        player2panel.add(playerNames[1]);
        playerEntryPanel.add(player2panel);

        JPanel player3panel = new JPanel();
        player3panel.setBackground(View.WINDOW_COLOR);
        player3panel.setLayout(new BoxLayout(player3panel, BoxLayout.Y_AXIS));
        enterPlayer3.setForeground(Color.WHITE);
        enterPlayer3.setFont(new Font("Arial", Font.BOLD, 24));
        player3panel.add(enterPlayer3);
        player3panel.add(playerNames[2]);
        playerEntryPanel.add(player3panel);

        JPanel player4panel = new JPanel();
        player4panel.setBackground(View.WINDOW_COLOR);
        player4panel.setLayout(new BoxLayout(player4panel, BoxLayout.Y_AXIS));
        enterPlayer4.setForeground(Color.WHITE);
        enterPlayer4.setFont(new Font("Arial", Font.BOLD, 24));
        player4panel.add(enterPlayer4);
        player4panel.add(playerNames[3]);
        playerEntryPanel.add(player4panel);

        playerEntryPanel.add(submitBtn);


        /*
        Requirement 3.5.1.1

        Listener that passes player names back to
        the view for creation in the model.

         */

        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String[] names = new String[numPlayers];
                for (int i = 0; i < names.length; i++) {
                    names[i] = playerNames[i].getText();
                }

                gameListener.newGameEvent(new NewGameEvent(e, names, numPlayers));
            }
        });

        this.add(buttonPanel);

    }

    public void addActionListener(ActionListener startPanelListener) {
        newGameButton.addActionListener(startPanelListener);
        loadGameButton.addActionListener(startPanelListener);
        twoPlayers.addActionListener(startPanelListener);
        threePlayers.addActionListener(startPanelListener);
        fourPlayers.addActionListener(startPanelListener);
        submitBtn.addActionListener(startPanelListener);

    }

    /**
     * Requirement 3.5.1
     * <p>
     * Adds the panel that allows the player
     * to select the appropriate number of
     * players.
     */

    public void showPlayerSelect() {
        this.remove(titleLabel);
        playerSelectPanel.setVisible(true);
        this.add(playerSelectPanel);

    }

    /**
     * Requirement 3.5.1.1
     * <p>
     * Adds the appropriate JTextFields so the
     * user can add custom names for the game
     * to be played.
     */

    public void showPlayerEntry() {

        this.playerSelectPanel.setVisible(false);

        playerNames[0].setVisible(true);
        enterPlayer1.setVisible(true);

        playerNames[1].setVisible(true);
        enterPlayer2.setVisible(true);

        // CHECK FOR MORE THAN 2 PLAYERS
        if (numPlayers > 2) {
            playerNames[2].setVisible(true);
            enterPlayer3.setVisible(true);
        } else {
            playerNames[2].setVisible(false);
            enterPlayer3.setVisible(false);
        }
        // CHECK FOR MORE THAN 3 PLAYERS
        if (numPlayers > 3) {
            playerNames[3].setVisible(true);
            enterPlayer4.setVisible(true);
        } else {
            playerNames[3].setVisible(false);
            enterPlayer4.setVisible(false);
        }

        this.add(playerEntryPanel);

        playerEntryPanel.setVisible(true);

    }

    /**
     * Requirement 3.5.2
     * <p>
     * Used to set the game listener that allows
     * us to pass the names and number of players
     * back to the Model for game creation.
     *
     * @param gameListener
     */

    public void setNewGameListener(NewGameListener gameListener) {
        this.gameListener = gameListener;
    }

    /**
     * Requirement 3.1.2.1
     * <p>
     * Ties into the new game file menu requirement.
     * Help method that resets the StartPanel to a
     * blank slate so normal flow path can be followed.
     */

    public void startPanelReset() {
        this.removeAll();
    }

    /**
     * Requirement 3.5.1
     * <p>
     * Sets global var to number of players selected
     * by user -- to be passed to model for game
     * creation.
     *
     * @param numPlayers
     */

    public void setNumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
    }

    public void hideMainMenuComps() {
        this.buttonPanel.setVisible(false);
    }
}
