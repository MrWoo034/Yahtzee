/*
 * 3.1.	 The GUI should be a window
 * 3.1.1.	Window controls
 * 3.1.1.1.	Minimize - User should be able to minimize the game window to the taskbar.
 * 3.1.1.2.	Maximize - User should be able to maximize the game window.
 * 3.1.1.3.	Close - User should be able to close the game from the window
 * JFrame fulfills all of these requirements
 */

package View;

import Controller.Controller;
import Model.ScoringCategories;
import Model.TurnInfo;

import javax.swing.JFileChooser;

import javax.sound.sampled.*;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;

public class View extends JFrame {

    /****************** View Components ******************/

    // View contains a controller for starting a game
    static Controller controller = new Controller();

    // TurnInfo to store the turn information coming from the model
    private TurnInfo turnInfo;

    // bool to tell if game is in progress
    public static boolean gameStarted = false;

    // static color object for reference by all GUI components
    public static final Color WINDOW_COLOR = new Color(66, 125, 180);

    // Dice Roll sound along with boolean for enabling/disabling - Req. 3.3
    private Clip diceRoll;
    boolean soundOn = true;

    // StartPanel used as the main content window when the app starts - Req. 3.5
    private StartPanel startPanel = new StartPanel();

    // Main menu bar -- Req 3.1.2 -> 3.1.3
    private MenuBar menuBar = new MenuBar();

    // Main Game Panel
    private GamePanel gamePanel;

    // Array to hold player names;
    private String[] names;


    // create window dimension - Req - 3.1.4.1
    private Dimension windowDimensions = new Dimension(600, 700);

    // used to track the bonus yahtzee
    // a player may score.
    private int bonus = 0;

    // holds the current player's ID
    private int currentPlayer = 0;

    // static ScoringCategories array to store categories coming from the model
    protected static final ScoringCategories[] scoringCategories = ScoringCategories.values();

    // file chooser for Save and Load activities
    JFileChooser fileChooser;

    /****************** End of View Components ******************/


    /****************** Constructor ******************/

    public View() {

        // setup the window title
        this.setTitle("YAHTZEE! by Solstice");

        // setup the background color using the global default
        this.setBackground(WINDOW_COLOR);

        // disable resizing per Req. 3.1.4.1.1
        this.setResizable(false);

        // setup window to terminate the program on close
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // set the window dimension attributes to make it permanently fixed per Req. 3.1.4.1
        this.setMinimumSize(windowDimensions);
        this.setMaximumSize(windowDimensions);
        this.setSize(windowDimensions);

        // setup menu bar per Reqs. 3.1.2 & 3.1.3
        this.setJMenuBar(menuBar);

        // add the StartPanel that serves as the main menu per Req. 3.5
        this.add(startPanel);

        // load the sound clip for use per Req. 3.3
        this.loadClip();

        // make the window visible
        this.setVisible(true);

        // setup new game listener object for handling a new game request
        startPanel.setNewGameListener(new NewGameListener() {
            @Override
            public void newGameEvent(NewGameEvent event) {
                newGame(event.getNumPlayers(), event.getNames());
            }
        });


        // setup a file chooser for use in the Save and Load activities per Reqs. 3.1.2.2 & 3.1.2.3
        fileChooser = new JFileChooser();
        fileChooser.addChoosableFileFilter(new YahtzeeFileFilter());

        // add event handler for main menu New Game menu item per Req 3.1.2.1
        menuBar.newGameMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gamePanel.setVisible(false);
                startPanel.startPanelReset();
//                startPanel.hidePlayerEntry();
                startPanel.showPlayerSelect();
                startPanel.setVisible(true);
            }
        });

        // add event handler for file menu Save Game menu item per Req 3.1.2.2
        menuBar.saveGameMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fileChooser.showSaveDialog(View.this) == JOptionPane.OK_OPTION) {
                    try {
                        controller.saveToFile(fileChooser.getSelectedFile());
                    } catch (IOException e1) {
                        JOptionPane.showConfirmDialog(View.this, "Error Saving to File: " + e1.getMessage(), "Failed to Save Game",
                                JOptionPane.WARNING_MESSAGE);
                        e1.printStackTrace();
                    }
                }
            }
        });

        // add event handler for file menu Load Game menu item per Req 3.1.2.3
        menuBar.loadGameMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fileChooser.showOpenDialog(View.this) == JOptionPane.OK_OPTION) {
                    try {
                        remove(gamePanel);
                        repaint(0, 0, 600, 700);
                        loadGame(controller.loadFromFile(fileChooser.getSelectedFile()));
                    } catch (IOException e1) {
                        JOptionPane.showConfirmDialog(View.this, "Error Loading Game: " + e1.getMessage(), "Failed to Load Game",
                                JOptionPane.WARNING_MESSAGE);
                        e1.printStackTrace();
                    }
                }
            }
        });

        // add event handler for file menu Load Game menu item per Req 3.1.1.3
        menuBar.exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // add event handler for option menu Sound Off menu item per Req 3.1.3.1
        menuBar.soundOffRadio.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                stop();
                soundOn = false;
            }
        });

        // add event handler for option menu Sound On menu item per Req 3.1.3.1
        menuBar.soundOnRadio.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                soundOn = true;
            }
        });

        // add event handler for option menu color selection menu item per Req 3.1.3.2
        menuBar.defaultRadio.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                Die.setColorScheme("default");
                colorSchemeUpdate();

            }
        });

        // add event handler for option menu color selection menu item per Req 3.1.3.2
        menuBar.whiteRadio.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                Die.setColorScheme("white");
                colorSchemeUpdate();

            }
        });

        // add event handler for option menu color selection menu item per Req 3.1.3.2
        menuBar.greenRadio.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                Die.setColorScheme("green");
                colorSchemeUpdate();

            }
        });

        // add event handler for option menu color selection menu item per Req 3.1.3.2
        menuBar.orangeRadio.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                Die.setColorScheme("orange");
                colorSchemeUpdate();
            }
        });

        // add event handler for option menu color selection menu item per Req 3.1.3.2
        menuBar.pinkRadio.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                Die.setColorScheme("pink");
                colorSchemeUpdate();

            }

        });

        // add event handler for option menu color selection menu item per Req 3.1.3.2
        menuBar.twilightRadio.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                Die.setColorScheme("twilight");
                colorSchemeUpdate();

            }
        });

        // add event handler for option menu color selection menu item per Req 3.1.3.2
        menuBar.violetRadio.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                Die.setColorScheme("violet");
                colorSchemeUpdate();
            }
        });

        // add event handler for option menu color selection menu item per Req 3.1.3.2
        menuBar.watermelonRadio.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                Die.setColorScheme("watermelon");
                colorSchemeUpdate();
            }
        });

        // add event handler for StartPanel menu items
        this.addStartPanelListener(new StartPanelListener());

        // draw the window with all of the above components
        pack();

    }
    /****************** End of Constructor ******************/


    /****************** Methods & Handlers ******************/

    /**
     * Helper method for updating the dice asset image file references after a color scheme change
     */
    public void colorSchemeUpdate() {
        if (View.gameStarted) {
            for (int i = 0; i < 5; i++) {
                gamePanel.diceRollPanel.dicePanel.setDice(i, turnInfo.getDice().get(i));
            }
            diceRepaint();
        }
    }

    /**
     * Another helper method for redrawing the dice after the color scheme for the dice image assets changes
     */
    public void diceRepaint() {
        gamePanel.diceRollPanel.dicePanel.die1.repaint();
        gamePanel.diceRollPanel.dicePanel.die2.repaint();
        gamePanel.diceRollPanel.dicePanel.die3.repaint();
        gamePanel.diceRollPanel.dicePanel.die4.repaint();
        gamePanel.diceRollPanel.dicePanel.die5.repaint();
    }

    /**
     * Hides the StartPanel when visible
     */
    public void hideStartPanel() {
        startPanel.setVisible(false);
    }

    /**
     * Starts a new game with the number of players and names from the controller
     * @param numPlayers
     * @param names
     */
    public void newGame(int numPlayers, String[] names) {
        currentPlayer = 0;
        gamePanel = new GamePanel(numPlayers, names);
        controller.newGame(numPlayers, names);

        showGamePanel();
    }

    /**
     * Loads the game state retreived from the file into the GUI and initializes the game.
     * @param theTurns
     */
    private void loadGame(TurnInfo[] theTurns) {

        names = controller.getNames();

        currentPlayer = theTurns[0].getPlayerId();

        turnInfo = theTurns[currentPlayer];

        gamePanel = new GamePanel(theTurns, names);

        gamePanel.diceRollPanel.dicePanel.setVisible(true);

        showGamePanel();

    }

    /**
     * Shows the GamePanel from various contexts of the app
     */
    public void showGamePanel() {

        hideStartPanel();
        this.add(gamePanel);
        gamePanel.setVisible(true);
        this.revalidate();
        this.repaint();

        // setup a new roll listener for the diceRollPanel - part of Req. 3.2
        this.gamePanel.diceRollPanel.setRollListener(new RollListener() {
            @Override
            public void diceRolled(RollEvent roll) { //handles a dice roll
                turnInfo = controller.takeTurn(currentPlayer, roll.getDice(), null);
                if (soundOn == true) {
                    play(); // play the roll sound Req. 3.3
                }
                for (int i = 0; i < 5; i++) {
                    gamePanel.diceRollPanel.dicePanel.setDice(i, turnInfo.getDice().get(i));
                }

                // populate the table with the possible scores the dice can produce
                gamePanel.scoreSheet.setPotentialScores(turnInfo.getPlayerId(), turnInfo.getPotentialScores());

                Integer rollNum = new Integer(turnInfo.getCurrentRoll());

                // block rolling once the person has rolled a 3rd time
                if (rollNum > 3) {
                    gamePanel.hideRollButton();
                    gamePanel.showRecordLabel();
                }

                // update the game info panel with the current roll and round numbers
                gamePanel.setRoll(rollNum, turnInfo.getCurrentRound());
            }
        });

        // setup listener for clicking on one of the possible scores you can record
        ScoringBox.scoreListener = new ScoreListener() {
            @Override
            public void scoreRecorded(int scoreCat, int playerId) {
                // if this is a valid score we can set...

                if (turnInfo != null) {

                    Integer[] temp = turnInfo.getPotentialScores();
                    int tempPlayer = turnInfo.getPlayerId();

                    if (setScore(temp, scoreCat, playerId)) {
                        // check to see if this was a bonus yathzee

                        if (bonus > 0) {
                            controller.setBonusScore(bonus);
                            gamePanel.scoreSheet.setFinalScore(temp[bonus], bonus, playerId);
                            bonus = 0;
                        }

                        turnInfo = controller.setScore(scoreCat, playerId);

                        gamePanel.scoreSheet.setFinalScore(temp[scoreCat], scoreCat, playerId);
                        gamePanel.scoreSheet.setPotentialScores(tempPlayer, turnInfo.getPotentialScores());

                        // check to see if the totals have been recorded
                        // for all upper or lower boxes
                        temp = turnInfo.getTalliedScores();

                        if (turnInfo.getOpenUpperTotals() == 6 && gamePanel.scoreSheet.boxIsOpen(playerId, ScoringCategories.UPPER_TOTAL.getValue())) {

                            controller.setUpperTotals(tempPlayer);

                            // set pre-bonus upper total
                            scoreCat = ScoringCategories.UPPER_TOTAL.getValue();
                            gamePanel.scoreSheet.setFinalScore(temp[scoreCat], scoreCat, tempPlayer);

                            // set bonus
                            scoreCat = ScoringCategories.UPPER_BONUS.getValue();
                            gamePanel.scoreSheet.setFinalScore(temp[scoreCat], scoreCat, tempPlayer);

                            // set upper total + bonus
                            scoreCat = ScoringCategories.UPPER_TOTAL_W_BONUS.getValue();
                            gamePanel.scoreSheet.setFinalScore(temp[scoreCat], scoreCat, tempPlayer);
                        }

                        if (turnInfo.getOpenLowerTotals() == 7 && gamePanel.scoreSheet.boxIsOpen(playerId, ScoringCategories.LOWER_TOTAL.getValue())) {

                            controller.setLowerTotals(tempPlayer);

                            // set lower total
                            scoreCat = ScoringCategories.LOWER_TOTAL.getValue();
                            gamePanel.scoreSheet.setFinalScore(temp[scoreCat], scoreCat, tempPlayer);

                        }

                        if (turnInfo.getOpenLowerTotals() == 7 && turnInfo.getOpenUpperTotals() == 6) {
                            controller.setGrandTotalScore(playerId);

                            scoreCat = ScoringCategories.GRAND_TOTAL.getValue();
                            gamePanel.scoreSheet.setFinalScore(temp[scoreCat], scoreCat, tempPlayer);
                        }

                        currentPlayer = turnInfo.getPlayerId();

                        gamePanel.setPlayer(new Integer(currentPlayer));
                        gamePanel.setRoll(new Integer(turnInfo.getCurrentRoll()), new Integer(turnInfo.getCurrentRound()));
                        gamePanel.setRound(new Integer(turnInfo.getCurrentRound()));

                        gamePanel.hideRecordLabel();
                        gamePanel.showRollButton();
                    }

                }
            }
        };
    }

    public void addStartPanelListener(ActionListener listenForStartPanelButtons) {

        startPanel.addActionListener(listenForStartPanelButtons);
    }

    /**
     * Requirement 3.3.load
     * <p>
     * Loads the clip to played for
     * dice rolling sounds
     */

    public void loadClip() {
        try {
            String fileName = "res/dice.wav";
            File file = new File(fileName);
            if (file.exists()) {
                AudioInputStream sound = AudioSystem.getAudioInputStream(file);
                diceRoll = AudioSystem.getClip();
                diceRoll.open(sound);
            } else {
                throw new RuntimeException("Sound File Not Found " + fileName);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Input / Output Sound Error: " + e);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
            System.out.println("Unsupported File Type: " + e);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
            System.out.println("Line was unavailable: " + e);
        }
    }

    /**
     * Requirement 3.3
     * <p>
     * Plays the actual sound clip
     * when the die are rolled.
     */

    public void play() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    diceRoll.setFramePosition(0);
                    diceRoll.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * Requirement 3.1.3.1
     * <p>
     * Stops sound in the event that a user disables
     * sounds.  Also sets a flag that en / dis sounds
     * from being played.
     */

    public void stop() {
        diceRoll.stop();
    }

    /**
     *
     */

    public class StartPanelListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("New Game")) {

                startPanel.hideMainMenuComps();
                startPanel.showPlayerSelect();
                repaint();

            } else if (e.getActionCommand().equals("Load Game")) {
                if (fileChooser.showOpenDialog(View.this) == JOptionPane.OK_OPTION) {
                    try {
                        loadGame(controller.loadFromFile(fileChooser.getSelectedFile()));
                    } catch (IOException e1) {
                        JOptionPane.showConfirmDialog(View.this, "Error Loading Game: " + e1.getMessage(), "Failed to Load Game",
                                JOptionPane.WARNING_MESSAGE);
                        e1.printStackTrace();
                    }
                }
            } else if (e.getActionCommand().equals("Show Scores")) {
                //todo add Show Scores activities
            } else if (e.getActionCommand().equals("2")) {
                startPanel.setNumPlayers(2);
                startPanel.showPlayerEntry();
            } else if (e.getActionCommand().equals("3")) {
                startPanel.setNumPlayers(3);
                startPanel.showPlayerEntry();
            } else if (e.getActionCommand().equals("4")) {
                startPanel.setNumPlayers(4);
                startPanel.showPlayerEntry();
            }
        }


    }

    /**
     * SWITCH STATEMENT
     * <p>
     * Looks at the score category and current player
     * to determine if the user has clicked on a valid
     * cell.  If valid cell is found, and the user
     * confirms their entry, returns TRUE.  Otherwise
     * the return will be FALSE.
     *
     * @param scoreCat The ROW or ScoringCategory the
     *                 user is trying to push a score
     *                 too.
     * @param playerId The PlayerID (Column ID) of the
     *                 scoresheet that was selected.
     *                 If Player 2 selects Player 3, a
     *                 warning should be displayed.
     * @return
     */

    private boolean setScore(Integer[] scores, int scoreCat, int playerId) {
        if (playerId != this.currentPlayer) {
            JOptionPane.showMessageDialog(this, "You must choose your own score column!", "Invalid Selection", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        // check to see if we can still score in this box.
        // if the box is TRUE, we can score.
        if (gamePanel.scoreSheet.boxIsOpen(playerId, scoreCat)) {


            // basic error handling.
            // some scores come back as
            // null if they were not
            // calculated (i.e. bonus
            // yathzee boxes)

            if (scores == null) {
                return false;
            }

            if (scores[scoreCat] == null) {
                scores[scoreCat] = 0;
            }

            switch (scoringCategories[scoreCat]) {
                // break out of cases the player cannot score from
                // i.e. they cannot record a score in
                case UPPER_TOTAL:
                    break;
                case UPPER_TOTAL_W_BONUS:
                    break;
                case UPPER_BONUS:
                    break;
                case LOWER_TOTAL:
                    break;
                case GRAND_TOTAL:
                    break;
                case BONUS_YAHTZEE_ONE: {
                    if (scores[scoreCat].intValue() > 0) {
                        bonusPrompt(ScoringCategories.BONUS_YAHTZEE_ONE.getValue());
                        return false;
                    } else {
                        return false;
                    }
                }

                case BONUS_YAHTZEE_TWO: {
                    if (scores[scoreCat].intValue() > 0) {
                        bonusPrompt(ScoringCategories.BONUS_YAHTZEE_TWO.getValue());
                        return false;
                    } else {
                        return false;
                    }
                }

                case BONUS_YAHTZEE_THREE: {
                    if (scores[scoreCat].intValue() > 0) {
                        bonusPrompt(ScoringCategories.BONUS_YAHTZEE_THREE.getValue());
                        return false;
                    } else {
                        return false;
                    }
                }
                // Default covers all boxes we can score
                // go ahead and show the confirm dialog.
                default: {
                    return scoreConfirmDialog(scores[scoreCat], scoreCat);
                }
            }
            return false;
        } else {
            return false;
        }

    }

    /**
     * Internal method.  Creates an option dialog
     * that asks the user to confirm their score
     * entry.  Returns true if the user confirms
     * their entry.  Returns false if the user
     * cancels their entry or selects no.
     *
     * @param score    Integer of the score / value
     *                 the user is trying to enter.
     * @param scoreCat The category the user is
     *                 trying to enter a score into
     *                 e.g. ACES, TWOS, etc.
     * @return TRUE if user confirms entry, FALSE
     * if users does not.
     */

    private boolean scoreConfirmDialog(Integer score, int scoreCat) {
        int answer = JOptionPane.showConfirmDialog(this, "Are you sure you wish to score "
                        + score.toString() + " in your " + CategoryPanel.labels[scoreCat + 1].getText().toLowerCase() + " ?",
                "Score Confirm", JOptionPane.YES_NO_OPTION);

        return answer == JOptionPane.YES_OPTION;
    }

    private void bonusPrompt(int index) {
        bonus = index;
        JOptionPane.showMessageDialog(this, "WOO!  You get to score another category of your choice! " +
                "Make another selection!", "Bonus Yahtzee!", JOptionPane.INFORMATION_MESSAGE);
        return;
    }


    public static String[] getWinnerFromController() {
        return controller.getWinner();
    }
}