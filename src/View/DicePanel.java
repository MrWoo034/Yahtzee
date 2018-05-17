package View;

import Model.Dice;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public class DicePanel extends JPanel {

    private ArrayList<Dice> diceArrayList;

    protected Die die1;
    protected Die die2;
    protected Die die3;
    protected Die die4;
    protected Die die5;

    private Border raisedbevel = BorderFactory.createRaisedSoftBevelBorder();

    /**
     * Requirement 3.9.2
     *
     * Allows five die to be displayed on the
     * GUI to the end user.
     *
     */

    public DicePanel() {
        //create Panel with FlowLayout via super constructor
        super(new FlowLayout());
        this.setBackground(View.WINDOW_COLOR);
        this.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.setAlignmentY(Component.CENTER_ALIGNMENT);


        /*
        Added as exmple by Leif on 03/18/18

        Adding in an array of dice for the first time.
         */

        diceArrayList = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            diceArrayList.add(new Dice());
        }

                /*
        Requirement #.#.#

        Listens for keeper status to be selected.
         */
        Die.keeperListener = new KeeperListener() {
            @Override
            public void keeperStatus(int dieId) {
                if (dieId == 1) {
                    toggleKeepDie(die1);
                } else if (dieId == 2) {
                    toggleKeepDie(die2);
                } else if (dieId == 3) {
                    toggleKeepDie(die3);
                } else if (dieId == 4) {
                    toggleKeepDie(die4);
                } else if (dieId == 5) {
                    toggleKeepDie(die5);
                }
            }
        };



        die1 = new Die(diceArrayList.get(0).getCurrentValue() - 1, 1);
        die2 = new Die(diceArrayList.get(1).getCurrentValue() - 1, 2);
        die3 = new Die(diceArrayList.get(2).getCurrentValue() - 1, 3);
        die4 = new Die(diceArrayList.get(3).getCurrentValue() - 1, 4);
        die5 = new Die(diceArrayList.get(4).getCurrentValue() - 1, 5);

        add(die1);
        add(die2);
        add(die3);
        add(die4);
        add(die5);

        setVisible(false);
    }

    /**
     * Requirement 3.9.1
     *
     * Requirement 3.9.3
     *
     * Sets the face icon to match the value rolled
     * for the die.
     *
     * @param index which die to update
     * @param die   Current die used to update
     *              die at the index position.
     */

    public void setDice(int index, Dice die) {
        switch (index) {
            case 0:
                die1.setIcon(die.getCurrentValue() - 1);
                break;
            case 1:
                die2.setIcon(die.getCurrentValue() - 1);
                break;
            case 2:
                die3.setIcon(die.getCurrentValue() - 1);
                break;
            case 3:
                die4.setIcon(die.getCurrentValue() - 1);
                break;
            case 4:
                die5.setIcon(die.getCurrentValue() - 1);
                break;
        }
    }

    public ArrayList<Dice> getDiceArrayList() {
        return diceArrayList;
    }

    public void toggleKeepDie (Die theDie){
        diceArrayList.get(theDie.getId()-1).setKeeper(!diceArrayList.get(theDie.getId()-1).getKeeper());
        if(diceArrayList.get(theDie.getId()-1).getKeeper()) {
            System.out.println("I set die " + theDie.getId() + " as a Keeper");
        } else {
            System.out.println("I set die " + theDie.getId() + " as a Goner");
        }

        if(diceArrayList.get(theDie.getId()-1).getKeeper()){
            theDie.setBorder(raisedbevel);
        }
        else {
            theDie.setBorder(null);
        }
        theDie.revalidate();
        theDie.repaint();
    }

    public String toString() {
        String diceString = new String();
        for (int i = 0; i < diceArrayList.size(); i++) {
            diceString += diceArrayList.get(i).getCurrentValue() + " ";
        }
        return diceString;
    }

    protected  void loadGame(ArrayList<Dice> dice) {
        diceArrayList = dice;

        for (int i = 0; i < dice.size(); i++) {
            setDice(i, diceArrayList.get(i));
        }
    }
}
