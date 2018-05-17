package View;

import Controller.Controller;
import Model.TurnInfo;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel {
    DiceRollPanel diceRollPanel = new DiceRollPanel();
    ScoreSheet scoreSheet;
    JLabel roundLabel, round, rollLabel, roll, playerLabel, player, recordLabel;
    String[] names;

    /**
     * Used to setup a new game from scratch with
     * default values.
     *
     * @param numPlayers
     * @param names
     */

    public GamePanel(int numPlayers, String[] names) {
        super();
        this.setBackground(View.WINDOW_COLOR);
        this.setForeground(Color.white);

        this.names = names;

        // initiate a new score sheet for the
        // appropriate number of players with
        // the custom entered names.
        scoreSheet = new ScoreSheet(numPlayers, names);

        getLayoutComponents();
    }

    /**
     * Requirement 3.1.2.3
     *
     * This constructor creates a new GamePanel from
     * the saved game state, setting up the appropriate
     * score sheet values, states, and information for
     * round, player, and dice.
     *
     * @param theTurns Array of TurnInfo for each player that
     *                 captures their tallied scores.
     * @param names     Array of Strings that gives the name
     *                  of the players to be displayed on the
     *                  ScoreSheet
     */


    public GamePanel (TurnInfo[] theTurns, String[] names) {
        super();
        this.setBackground(View.WINDOW_COLOR);
        this.setForeground(Color.WHITE);

        this.names = names;

        // initiate a new score sheet for the
        // appropriate number of players with
        // the custom entered names.
        scoreSheet = new ScoreSheet(names.length, names);

        int currentPlayer = theTurns[0].getPlayerId();
        Integer currentRound = new Integer(theTurns[0].getCurrentRound());
        Integer currentRoll = new Integer(theTurns[0].getCurrentRoll());

        scoreSheet.setPotentialScores(currentPlayer, theTurns[currentPlayer].getPotentialScores());


        for (int i = 0; i < names.length; i++) {
            Integer[] talliedScores = theTurns[i].getTalliedScores();
            for (int j = 0; j < View.scoringCategories.length; j++) {
                if (talliedScores[j] == null)
                    continue;
                if (talliedScores[j] >= 0) {
                    scoreSheet.setFinalScore(talliedScores[j], j, i);
                }
            }
        }

        getLayoutComponents();

        diceRollPanel.loadGame(theTurns[0].getDice());
        diceRollPanel.dicePanel.setVisible(true);
        diceRollPanel.setVisible(true);
        setRound(currentRound);
        setRoll(currentRoll, currentRound);
        setPlayer(currentPlayer);

    }

    /**
     * Private interior method for redundant component
     * additions.
     */

    private void getLayoutComponents() {

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.recordLabel = new JLabel(" \n");
        this.recordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.setBackground(View.WINDOW_COLOR);
        this.recordLabel.setForeground(Color.RED);
        this.add(recordLabel);

        this.add(diceRollPanel);

        // set up some basic info to show to the user.

        JPanel infoPanel = new JPanel();
        infoPanel.setMaximumSize(new Dimension(600, 100));
        infoPanel.setBackground(View.WINDOW_COLOR);
        infoPanel.setForeground(Color.WHITE);
        infoPanel.setLayout(new GridBagLayout());
        GridBagConstraints infoGBC = new GridBagConstraints();

        roundLabel = new JLabel("Round Number: ");
        roundLabel.setForeground(Color.WHITE);
        round = new JLabel("1");
        round.setForeground(Color.WHITE);


        rollLabel = new JLabel("Roll Number: ");
        rollLabel.setForeground(Color.WHITE);

        roll = new JLabel ("1");
        roll.setForeground(Color.WHITE);


        playerLabel = new JLabel ("Current Player: ");
        playerLabel.setForeground(Color.WHITE);

        player = new JLabel(names[0]);
        player.setForeground(Color.WHITE);


        infoGBC.gridy = 0;
        infoGBC.gridx = 0;

        infoPanel.add(roundLabel, infoGBC);
        infoGBC.gridx++;
        infoPanel.add(round, infoGBC);

        infoGBC.gridy++;
        infoGBC.gridx = 0;

        infoPanel.add(rollLabel, infoGBC);
        infoGBC.gridx++;
        infoPanel.add(roll, infoGBC);

        infoGBC.gridy++;
        infoGBC.gridx = 0;

        infoPanel.add(playerLabel, infoGBC);
        infoGBC.gridx++;
        infoPanel.add(player, infoGBC);

        infoGBC.gridy++;
        infoGBC.gridx = 0;

        add(infoPanel);

        // add the sheet to the gamePanel;
        this.add(scoreSheet);
    }

    /**
     * Requirements 3.7.3
     *
     * Sets the current player ID for display
     * on the GUI
     * @param playerId
     */

    protected void setPlayer(int playerId) {
        player.setText(this.names[playerId]);
    }

    /**
     * Requirements 3.7.2
     *
     * Sets the current roll number for
     * display on the GUI
     * @param roll
     * @param roundNumber
     */

    protected void setRoll(Integer roll, Integer roundNumber) {
        if (roundNumber.intValue() >= 14) {
            this.round.setText("Game Over!");
            diceRollPanel.setVisible(false);
            diceRollPanel.rollStartButton.setVisible(false);
            return;
        } else {
            diceRollPanel.setVisible(true);


            if (roll.intValue() == 1) {
                diceRollPanel.dicePanel.setVisible(false);
                diceRollPanel.dicePanel.die1.setBorder(null);
                diceRollPanel.dicePanel.die2.setBorder(null);
                diceRollPanel.dicePanel.die3.setBorder(null);
                diceRollPanel.dicePanel.die4.setBorder(null);
                diceRollPanel.dicePanel.die5.setBorder(null);
            }

            if (roll.intValue() == 4) {
                this.roll.setForeground(Color.RED);
                this.roll.setText("RECORD SCORE");
                return;
            } else if (roll.intValue() == 3) {
                this.roll.setForeground(Color.ORANGE);
            } else {
                this.roll.setForeground(Color.WHITE);
            }
            this.roll.setText(roll.toString());
        }
    }

    /**
     * Requirements 3.7.1
     *
     * Sets the current round number for
     * display on the GUI
     * @param round
     */

    protected void setRound (Integer round) {
        if (round.intValue() == 13) {
            this.round.setForeground(Color.ORANGE);
        }
        if (round.intValue() >= 14) {
            String[] winner = View.getWinnerFromController();
            if (winner.length == 1) {
                JOptionPane.showMessageDialog(this, winner[0] + " won!", "Winner!", JOptionPane.PLAIN_MESSAGE);
            } else {
                String tieString = "";
                for (int i = 0; i < winner.length; i++) {
                    tieString += winner[i];
                    if (i < winner.length - 1) tieString += ", ";
                    if (i == winner.length - 2) tieString += "and ";
                }
                JOptionPane.showMessageDialog(this, "There was a tie between " + tieString + "!", "Winner!", JOptionPane.PLAIN_MESSAGE);
            }
        }
        this.round.setText(round.toString());
    }

    /**
     * Sets a piece of text to visible that
     * informs the user they must record their
     * score.
     */

    protected void hideRecordLabel() {
        this.recordLabel.setText(" \n");
    }

    /**
     * Sets a piece of text to invisible
     */

    protected void showRecordLabel() {
        this.recordLabel.setText("Final Roll - You Must Record a Score!");
    }

    protected void hideRollButton() {
        diceRollPanel.hideRollButton();
    }

    protected void showRollButton() {
        diceRollPanel.showRollButton();
    }

}
