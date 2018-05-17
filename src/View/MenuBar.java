package View;

import javax.swing.*;

/**
 * Requirements 3.1.2 and 3.1.3
 *
 * Create a file menu for the top bar
 *
 */

public class MenuBar extends JMenuBar{
    // Start Panel top menu bar and items
    private JMenu fileMenu = new JMenu("File");
    protected JMenuItem newGameMenuItem = new JMenuItem("New Game");
    protected JMenuItem saveGameMenuItem = new JMenuItem("Save Game");
    protected JMenuItem loadGameMenuItem = new JMenuItem("Load Game");
    protected JMenuItem exitMenuItem = new JMenuItem("Exit");

    private JMenu optionsMenu = new JMenu("Options");
        private JMenu soundMenu = new JMenu("Sound");
    private ButtonGroup soundRadioGroup = new ButtonGroup();
    protected JRadioButton soundOnRadio = new JRadioButton("On");
    protected JRadioButton soundOffRadio = new JRadioButton("Off");

        private JMenu colorMenu = new JMenu("Dice Color");
    private ButtonGroup colorRadioGroup = new ButtonGroup();
    protected JRadioButton defaultRadio = new JRadioButton("White - Top Down");
    protected JRadioButton whiteRadio = new JRadioButton("White");
    protected JRadioButton greenRadio = new JRadioButton("Green");
    protected JRadioButton orangeRadio = new JRadioButton("Orange");
    protected JRadioButton pinkRadio = new JRadioButton("Pink");
    protected JRadioButton twilightRadio = new JRadioButton("Twilight Blue");
    protected JRadioButton violetRadio = new JRadioButton("Violet");
    protected JRadioButton watermelonRadio = new JRadioButton("Watermelon");


    public MenuBar() {
        super();
        /*
        Requirements 3.1.2.1 -- 3.1.2.3
        3.1.2.1.	New Game - Should initiate a new game for the user
        3.1.2.2.	Save Game - User should be able to save the state of the game
        3.1.2.3.	Load Game - Should allow the user to automatically load the last saved game
         */

        fileMenu.add(newGameMenuItem);
        fileMenu.add(saveGameMenuItem);
        fileMenu.add(loadGameMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);

        /*
        Requirement
        3.1.3.1	User should be able to toggle sound effects between on and off
         */
        soundRadioGroup.add(soundOnRadio);
        soundMenu.add(soundOnRadio);
        soundRadioGroup.add(soundOffRadio);
        soundMenu.add(soundOffRadio);
        soundOnRadio.setSelected(true);
        /*
        Requirement
        3.1.3.2	User should be able to select a color for dice
        */
        colorRadioGroup.add(defaultRadio);
        colorMenu.add(defaultRadio);
        defaultRadio.setSelected(true);
        colorRadioGroup.add(whiteRadio);
        colorMenu.add(whiteRadio);
        colorRadioGroup.add(greenRadio);
        colorMenu.add(greenRadio);
        colorRadioGroup.add(orangeRadio);
        colorMenu.add(orangeRadio);
        colorRadioGroup.add(pinkRadio);
        colorMenu.add(pinkRadio);
        colorRadioGroup.add(twilightRadio);
        colorMenu.add(twilightRadio);
        colorRadioGroup.add(violetRadio);
        colorMenu.add(violetRadio);
        colorRadioGroup.add(watermelonRadio);
        colorMenu.add(watermelonRadio);

        optionsMenu.add(soundMenu);
        optionsMenu.add(colorMenu);

        this.add(fileMenu);
        this.add(optionsMenu);
    }
}

