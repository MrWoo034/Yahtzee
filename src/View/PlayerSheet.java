package View;

import Model.ScoringCategories;

import javax.swing.*;
import java.awt.*;

public class PlayerSheet extends JPanel {
    private int id;
    private JLabel name;

    private ScoringBox[] rows;

    /**
     * Requirements 3.8
     *
     * Builds a row on the score sheet for the
     * desired player.  Pass the name to appear
     * at the top of the score sheet.
     *
     * @param name
     */

    public PlayerSheet(String name, int id) {
        super();
        // set player name
        if (name == null) {
            this.name = new JLabel(" ");
        } else {
            this.name = new JLabel(name);
        }
        this.name.setVisible(true);

        this.id = id;

        /*
            Requirement 3.8.1
            Setup the ScoreSheet to show the player
            name and individual scoring boxes as a
            single column.
         */

        // initialize the rows;
        rows = new ScoringBox[View.scoringCategories.length];

        // set panel dimensions
        Dimension dim = getPreferredSize();
        dim.height = ScoreSheet.CELL_HEIGHT;
        dim.width = ScoreSheet.CELL_WIDTH;

        // set the scoring rows

        getComponentLayout();

        setVisible(true);

    }

    /**
     * Requirements 3.8
     *
     * Organizes the "player score sheet" into
     * a single row with the player names and
     * score objects available.
     *
     */
    private void getComponentLayout() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // FIRST ROW
        // ADD PLAYER NAME
        gbc.fill = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(name, gbc);

        // ADD IN THE ROWS FOR
        // THE SCORE SHEET BOXES

        for (int i = 0; i < View.scoringCategories.length; i++) {
            rows[i] = new ScoringBox(View.scoringCategories[i].getValue(), id);
            gbc.gridy = i + 1;
            add(rows[i], gbc);
        }

    }

    /**
     * Requirements 3.8.2.1
     *
     * Sets the potential score for a box
     * on the player sheet
     *
     * @param index Index of the box or
     *              category to be updated
     *
     * @param value Potential value to be
     *              displayed to the user
     */

    protected void setPotential(int index, Integer value) {
        rows[index].setPotential(value);
    }

    /**
     * Requirements 3.8.2.2
     *
     * SEts the final score for a box
     * on the player sheet
     * @param index The scoring box to recrod
     *              the final score for
     * @param value The value to set in the final
     *              score
     * @param color The players color (3.8.2.2.1 - 3.8.2.2.4)
     */

    protected void setFinalScore(int index, Integer value, Color color) {
        rows[index].setFinalScore(value, color);
    }

    protected boolean boxIsOpen (int scoreCat) {
        return rows[scoreCat].isOpen();
    }

}
