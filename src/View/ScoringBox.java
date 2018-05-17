package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ScoringBox extends JPanel{
    private int catId;
    private int playerId;
    private boolean isOpen = true;
    private JLabel value = new JLabel(" ");
    public static final Color POTENTIAL_COLOR = new Color(145,145,145);
    public static final Color CELL_BACKGROUND = new Color(255, 255, 200);

    public static ScoreListener scoreListener;

    /**
     * Requirement 3.8.1
     *
     * Initialize individual scoring box
     * for a score sheet.  Boxes make up
     * a player column.
     */

    public ScoringBox(int catId, int playerId) {
        super();

        this.catId = catId;
        this.playerId = playerId;

        this.value.setVisible(false);

        // set the cell dimensions
        Dimension dim = getPreferredSize();
        dim.height = ScoreSheet.CELL_HEIGHT;
        dim.width = ScoreSheet.CELL_WIDTH;
        setMinimumSize(dim);
        setPreferredSize(dim);
        setSize(dim);

        // Set the background color
        setBackground(CELL_BACKGROUND);

        // Add a border to the cell
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Set the layout manager and
        // add the value label to the
        // cell
        setLayout(new BorderLayout());
        add(value, BorderLayout.CENTER);

        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                scoreListener.scoreRecorded(catId, playerId);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        setVisible(true);

    }


    /**
     * Requirement 3.8.2.1
     *
     * Allows the box to show the calculated
     * potential score.
     *
     * @param value
     */
    protected void setPotential(Integer value) {
        if (value != null) {
            if (value.intValue() == 0) {
                this.value.setText(" ");
            } else {
                this.value.setText(value.toString());
                this.value.setVisible(true);
                this.value.setForeground(POTENTIAL_COLOR);
            }
        }
    }

    /**
     * Requirement 3.8.2.2
     *
     * Allows the box to record a final score
     *
     * @param value Final value of the scoring box
     */

    protected void setFinalScore(Integer value, Color color) {
        this.value.setText(value.toString());
        this.value.setVisible(true);
        /* Requirement 3.8.2.2.1 - 3.8.2.2.4
              Sets player score to color
         */
        this.value.setForeground(color);
        this.isOpen = false;
    }


    public boolean isOpen() {
        return this.isOpen;
    }

}
