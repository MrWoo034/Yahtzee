package View;

import Model.Dice;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DiceRollPanel extends JPanel{
    DicePanel dicePanel = new DicePanel();
    JButton rollStartButton = new JButton("Start Game");
    private RollListener rollListener;

    public DiceRollPanel(){
        super();
        this.setBackground(View.WINDOW_COLOR);
        Border raisedbevel = BorderFactory.createRaisedSoftBevelBorder();
        this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
        JPanel dicePanelWrapper = new JPanel();
//        dicePanelWrapper.setBorder(raisedbevel);
        dicePanelWrapper.setAlignmentX(Component.CENTER_ALIGNMENT);
        dicePanelWrapper.setAlignmentY(Component.CENTER_ALIGNMENT);
        dicePanelWrapper.setBackground(View.WINDOW_COLOR);
        dicePanelWrapper.setLayout(new GridLayout(1, 1));
        dicePanelWrapper.setMaximumSize(new Dimension(600, 120));
        dicePanelWrapper.setMinimumSize(new Dimension(600, 120));
        dicePanelWrapper.setPreferredSize(new Dimension(600, 120));
        dicePanelWrapper.add(dicePanel);
        this.add(dicePanelWrapper);

        rollStartButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        rollStartButton.setAlignmentY(BOTTOM_ALIGNMENT);
        rollStartButton.setPreferredSize(new Dimension(120, 50));
        rollStartButton.setMinimumSize(new Dimension(120, 50));
        rollStartButton.setMaximumSize(new Dimension(120, 50));
        rollStartButton.setFont(new Font("Arial", Font.BOLD,16));


        JPanel rollButtonWrapperPanel = new JPanel();
        rollButtonWrapperPanel.setLayout(new BoxLayout(rollButtonWrapperPanel, BoxLayout.Y_AXIS));
        rollButtonWrapperPanel.setBackground(View.WINDOW_COLOR);
        rollButtonWrapperPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        rollButtonWrapperPanel.setAlignmentY(BOTTOM_ALIGNMENT);
        rollButtonWrapperPanel.setMaximumSize(new Dimension(600, 50));
        rollButtonWrapperPanel.setMinimumSize(new Dimension(600, 50));
        rollButtonWrapperPanel.setPreferredSize(new Dimension(600, 50));
        rollButtonWrapperPanel.add(rollStartButton);
        this.add(rollButtonWrapperPanel);
        /**
         * Requirements 3.2
         *
         * Action listener that catches a roll event
         *
         */

        this.rollStartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                View.gameStarted = true;
                rollStartButton.setActionCommand("Roll");
                rollStartButton.setText("Roll");
                rollListener.diceRolled(new RollEvent(e, dicePanel.getDiceArrayList()));
                System.out.println("Dice Roll Results: " + dicePanel.toString());
                dicePanel.setVisible(true);
            }
        });

        setVisible(true);
    }

    public void setRollListener(RollListener rollListener) {
        this.rollListener = rollListener;
    }


    /**
     * Sets the roll button to invisible
     * so the user cannot roll the dice
     * again.
     */
    protected void hideRollButton() {
        this.rollStartButton.setVisible(false);
    }

    /**
     * Sets the roll button to visible
     * enabling dice rolls to take place
     * normally.
     */

    protected void showRollButton() {
        this.rollStartButton.setVisible(true);
    }

    /**
     * Requiremet 3.1.2.3
     *
     * Loads a game from a saved file.
     * Passes the dice down so they can be
     * set to the state of the last roll
     * when the game was saved.
     *
     * @param dice
     */

    protected void loadGame(ArrayList<Dice> dice) {
        dicePanel.loadGame(dice);
        showRollButton();
        View.gameStarted = true;
        rollStartButton.setActionCommand("Roll");
        rollStartButton.setText("Roll");
        dicePanel.setVisible(true);
        dicePanel.repaint();
        setVisible(true);
        this.repaint();

    }



}
