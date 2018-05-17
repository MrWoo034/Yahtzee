package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Die extends JLabel{

    /*
        Requirement 3.9.1

        These static images are the virtual / GUI rendered
        representations of the dice to be displayed to the user

        Each icon corresponds to one side of the die.
     */
    private static ImageIcon oneIcon = new ImageIcon(new ImageIcon("res/diceIcons/default/diceOne.png").getImage().getScaledInstance(104, 104, Image.SCALE_SMOOTH));
    private static ImageIcon twoIcon = new ImageIcon(new ImageIcon("res/diceIcons/default/diceTwo.png").getImage().getScaledInstance(104, 104, Image.SCALE_SMOOTH));
    private static ImageIcon threeIcon = new ImageIcon(new ImageIcon("res/diceIcons/default/diceThree.png").getImage().getScaledInstance(104, 104, Image.SCALE_SMOOTH));
    private static ImageIcon fourIcon = new ImageIcon(new ImageIcon("res/diceIcons/default/diceFour.png").getImage().getScaledInstance(104, 104, Image.SCALE_SMOOTH));
    private static ImageIcon fiveIcon = new ImageIcon(new ImageIcon("res/diceIcons/default/diceFive.png").getImage().getScaledInstance(104, 104, Image.SCALE_SMOOTH));
    private static ImageIcon sixIcon = new ImageIcon(new ImageIcon("res/diceIcons/default/diceSix.png").getImage().getScaledInstance(104, 104, Image.SCALE_SMOOTH));

    private static ImageIcon[] dieSideIcons;

    protected static KeeperListener keeperListener;

    private int id;

    public Die(int value, int id){
        this.setBackground(View.WINDOW_COLOR);
        dieSideIcons = new ImageIcon[]{oneIcon, twoIcon, threeIcon, fourIcon, fiveIcon, sixIcon};
        this.id = id;
        this.setIcon(dieSideIcons[value]);

        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e){ }

            @Override
            public void mousePressed(MouseEvent e) {
                keeperListener.keeperStatus(id);
            }

            @Override
            public void mouseReleased(MouseEvent e) { }

            @Override
            public void mouseEntered(MouseEvent e) { }

            @Override
            public void mouseExited(MouseEvent e) { }
        });
    }


    /**
     * Requirement 3.9.3
     *
     * Allows the icon to be set to discern
     * the value of the dice rolled to the
     * user playing the game.
     *
     * @param value Value to set the die too
     */
    public void setIcon (int value) {
        this.setIcon(dieSideIcons[value]);
    }

    public int getId() { return this.id; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Die die = (Die) o;
        return id == die.id;
    }

    /**
     * Requirement 3.9.3
     * <p>
     * Allows hte icon to be set to discern
     * the value of the dice rolled to the
     * user playing the game.
     *
     * @param color the color scheme to set the dice to (default, green, orange, pink, twilight, violet, watermelon, white)
     */

    public static void setColorScheme(String color) {
        oneIcon = new ImageIcon(new ImageIcon("res/diceIcons/" + color + "/diceOne.png").getImage().getScaledInstance(104, 104, Image.SCALE_SMOOTH));
        twoIcon = new ImageIcon(new ImageIcon("res/diceIcons/" + color + "/diceTwo.png").getImage().getScaledInstance(104, 104, Image.SCALE_SMOOTH));
        threeIcon = new ImageIcon(new ImageIcon("res/diceIcons/" + color + "/diceThree.png").getImage().getScaledInstance(104, 104, Image.SCALE_SMOOTH));
        fourIcon = new ImageIcon(new ImageIcon("res/diceIcons/" + color + "/diceFour.png").getImage().getScaledInstance(104, 104, Image.SCALE_SMOOTH));
        fiveIcon = new ImageIcon(new ImageIcon("res/diceIcons/" + color + "/diceFive.png").getImage().getScaledInstance(104, 104, Image.SCALE_SMOOTH));
        sixIcon = new ImageIcon(new ImageIcon("res/diceIcons/" + color + "/diceSix.png").getImage().getScaledInstance(104, 104, Image.SCALE_SMOOTH));
        dieSideIcons = new ImageIcon[]{oneIcon, twoIcon, threeIcon, fourIcon, fiveIcon, sixIcon};

    }
}
