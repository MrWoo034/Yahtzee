package View;

import javax.swing.*;
import java.awt.*;

public class CategoryPanel extends JPanel {

    protected static JLabel[] labels;

    public CategoryPanel() {
        this.setBackground(View.WINDOW_COLOR);
        this.setForeground(Color.WHITE);
        labels = new JLabel[View.scoringCategories.length + 1];
        getLayoutComponents();

        setVisible(true);

    }

    private void getLayoutComponents () {

        Dimension dim = getPreferredSize();

        dim.width = ScoreSheet.CELL_WIDTH * 2;
        dim.height = ScoreSheet.COLUMN_HEIGHT;

        setPreferredSize(dim);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.CENTER;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(1, 0, 0, 0);

        add(new JLabel(" "), gbc);

        for (int i = 1; i < View.scoringCategories.length+1; i++) {
            switch (View.scoringCategories[i-1]) {
                case ACES: {
                    labels[i] = new JLabel("ACES");
                    labels[i].setVisible(true);
                    labels[i].setForeground(Color.WHITE);
                    gbc.gridy++;
                    add(labels[i], gbc);
                    break;
                }
                case TWOS: {
                    labels[i] = new JLabel("TWOS");
                    labels[i].setVisible(true);
                    labels[i].setForeground(Color.WHITE);
                    gbc.gridy++;
                    add(labels[i], gbc);
                    break;
                }
                case THREES: {
                    labels[i] = new JLabel("THREES");
                    labels[i].setVisible(true);
                    labels[i].setForeground(Color.WHITE);
                    gbc.gridy++;
                    add(labels[i], gbc);
                    break;
                }
                case FOURS: {
                    labels[i] = new JLabel("FOURS");
                    labels[i].setVisible(true);
                    labels[i].setForeground(Color.WHITE);
                    gbc.gridy++;
                    add(labels[i], gbc);
                    break;
                }
                case FIVES: {
                    labels[i] = new JLabel("FIVES");
                    labels[i].setVisible(true);
                    labels[i].setForeground(Color.WHITE);
                    gbc.gridy++;
                    add(labels[i], gbc);
                    break;
                }
                case SIXES: {
                    labels[i] = new JLabel("SIXES");
                    labels[i].setVisible(true);
                    labels[i].setForeground(Color.WHITE);
                    gbc.gridy++;
                    add(labels[i], gbc);
                    break;
                }
                case UPPER_TOTAL: {
                    labels[i] = new JLabel("TOTAL");
                    labels[i].setVisible(true);
                    labels[i].setForeground(Color.WHITE);
                    gbc.gridy++;
                    add(labels[i], gbc);
                    break;
                }
                case UPPER_BONUS: {
                    labels[i] = new JLabel("BONUS");
                    labels[i].setVisible(true);
                    labels[i].setForeground(Color.WHITE);
                    gbc.gridy++;
                    add(labels[i], gbc);
                    break;
                }
                case UPPER_TOTAL_W_BONUS: {
                    labels[i] = new JLabel("TOTAL \nWITH BONUS");
                    labels[i].setVisible(true);
                    labels[i].setForeground(Color.WHITE);
                    gbc.gridy++;
                    add(labels[i], gbc);
                    break;
                }
                case THREE_OF_A_KIND: {
                    labels[i] = new JLabel("3 OF A KIND");
                    labels[i].setVisible(true);
                    labels[i].setForeground(Color.WHITE);
                    gbc.gridy++;
                    add(labels[i], gbc);
                    break;
                }
                case FOUR_OF_A_KIND: {
                    labels[i] = new JLabel("4 OF A KIND");
                    labels[i].setVisible(true);
                    labels[i].setForeground(Color.WHITE);
                    gbc.gridy++;
                    add(labels[i], gbc);
                    break;
                }
                case FULL_HOUSE: {
                    labels[i] = new JLabel("FULL HOUSE");
                    labels[i].setVisible(true);
                    labels[i].setForeground(Color.WHITE);
                    gbc.gridy++;
                    add(labels[i], gbc);
                    break;
                }
                case SM_STRAIGHT: {
                    labels[i] = new JLabel("SM. STRAIGHT");
                    labels[i].setVisible(true);
                    labels[i].setForeground(Color.WHITE);
                    gbc.gridy++;
                    add(labels[i], gbc);
                    break;
                }
                case LG_STRAIGHT: {
                    labels[i] = new JLabel("LG. STRAIGHT");
                    labels[i].setVisible(true);
                    labels[i].setForeground(Color.WHITE);
                    gbc.gridy++;
                    add(labels[i], gbc);
                    break;
                }
                case YAHTZEE: {
                    labels[i] = new JLabel("YAHTZEE!");
                    labels[i].setVisible(true);
                    labels[i].setForeground(Color.WHITE);
                    gbc.gridy++;
                    add(labels[i], gbc);
                    break;
                }
                case CHANCE: {
                    labels[i] = new JLabel("CHANCE");
                    labels[i].setVisible(true);
                    labels[i].setForeground(Color.WHITE);
                    gbc.gridy++;
                    add(labels[i], gbc);
                    break;
                }
                case BONUS_YAHTZEE_ONE: {
                    labels[i] = new JLabel("BONUS\nYAHTZEE ONE");
                    labels[i].setVisible(true);
                    labels[i].setForeground(Color.WHITE);
                    gbc.gridy++;
                    add(labels[i], gbc);
                    break;
                }
                case BONUS_YAHTZEE_TWO: {
                    labels[i] = new JLabel("BONUS\nYAHTZEE TWO");
                    labels[i].setVisible(true);
                    labels[i].setForeground(Color.WHITE);
                    gbc.gridy++;
                    add(labels[i], gbc);
                    break;
                }
                case BONUS_YAHTZEE_THREE: {
                    labels[i] = new JLabel("BONUS\nYAHTZEE THREE");
                    labels[i].setVisible(true);
                    labels[i].setForeground(Color.WHITE);
                    gbc.gridy++;
                    add(labels[i], gbc);
                    break;
                }
                case LOWER_TOTAL: {
                    labels[i] = new JLabel("LOWER TOTAL");
                    labels[i].setVisible(true);
                    labels[i].setForeground(Color.WHITE);
                    gbc.gridy++;
                    add(labels[i], gbc);
                    break;
                }
                case GRAND_TOTAL: {
                    labels[i] = new JLabel("GRAND TOTAL");
                    labels[i].setVisible(true);
                    labels[i].setForeground(Color.WHITE);
                    gbc.gridy++;
                    add(labels[i], gbc);
                    break;
                }
                default: {
                    break;
                }
            }
        }
    }

}
