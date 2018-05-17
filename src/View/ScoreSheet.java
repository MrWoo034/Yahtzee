package View;

import Model.Player;
import Model.ScoringCategories;

import javax.swing.*;
import java.awt.*;

public class ScoreSheet extends JPanel {


    private PlayerSheet[] player;
    private CategoryPanel categoryPanel;
    // setup height and width of individual cells
    public static final int CELL_HEIGHT = 17;
    public static final int CELL_WIDTH = 80;
    // setup the height of the column
    public static final int COLUMN_HEIGHT = (View.scoringCategories.length + 1) * CELL_HEIGHT;
    public static final int COLUMN_WIDTH = CELL_WIDTH;

    private static Color[] playerColors = {Color.RED, Color.GREEN, Color.BLUE, Color.MAGENTA};

    public ScoreSheet(int numPlayers, String[] names) {
        super();
        this.setBackground(View.WINDOW_COLOR);
        this.setMaximumSize(new Dimension(600, 390));
        // initializes the column that
        // contains the scoring heading
        // text / categories.
        categoryPanel = new CategoryPanel();

        // Initialize player sheets for
        // appropriate number of players
        player = new PlayerSheet[numPlayers];
        for (int i = 0; i < numPlayers; i++) {
            player[i] = new PlayerSheet(names[i], i);
        }

        Dimension dim = getPreferredSize();
        dim.width = COLUMN_WIDTH;
        dim.height = COLUMN_HEIGHT;
        setPreferredSize(dim);

        getLayoutComponents();

        setVisible(true);

    }

    /**
     * Requirement 3.8.1
     * <p>
     * Setups the score sheet to show the labels
     * for the different scoring rows.  Adds the
     * required text to the labels, and lays items
     * out in a grid bag layout (increment gridy to
     * increase the area where the labels are applied).
     */

    private void getLayoutComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.CENTER;

        add(categoryPanel, gbc);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.LINE_END;

        // Add the players to the layout

        for (int i = 0; i < player.length; i++) {
            gbc.gridx++;
            gbc.gridy = 0;
            add(player[i], gbc);
        }

    }

    /**
     * Requirement 3.8.2.1
     * <p>
     * Shows the potential scores to the user
     * on the GUI.
     *
     * @param playerIndex Current Player
     * @param scores      The array of scores (Integer[])
     *                    that should be displayed.
     */

    protected void setPotentialScores(int playerIndex, Integer[] scores) {

        PlayerSheet currPlayer = player[playerIndex];
        // WE PASS NULL WHEN GOING FROM ONE PLAYER
        // TO THE NEXT SO THAT WE REMOVE THEIR POTENTIAL
        // SCORES FROM THE LIST
        if (scores == null) {
            for (int i = 0; i < View.scoringCategories.length; i++) {
                if (currPlayer.boxIsOpen(i)) {
                    currPlayer.setPotential(i, new Integer(0));
                }
            }
            // ELSE IF NOT NULL, MUST BE THIS PLAYERS TURN
            // SO WE WANT TO PUSH THE POTENTIALS TO THE SHEET
        } else {
            for (int i = 0; i < View.scoringCategories.length; i++) {
                if (currPlayer.boxIsOpen(i)) {
                    currPlayer.setPotential(i, scores[View.scoringCategories[i].getValue()]);
                }
            }
        }


    }

    /**
     * Requirement 3.8.2.2
     * <p>
     * Sets the final score for a category per
     * the user requirements.
     *
     * @param score
     * @param playerIndex
     * @param scoreCat
     */
    protected void setFinalScore(Integer score, int scoreCat, int playerIndex) {
        player[playerIndex].setFinalScore(scoreCat, score, playerColors[playerIndex]);
    }

    /**
     * Checks to see if a score box is open
     * to avoid duplicate entries or overwrites.
     *
     * @param playerIndex Player making the check.
     * @param scoreCat    Category checking (where
     *                    category is a row on the
     *                    scoresheet).
     * @return
     */

    protected boolean boxIsOpen(int playerIndex, int scoreCat) {
        return player[playerIndex].boxIsOpen(scoreCat);
    }
}
