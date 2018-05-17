package Controller;

import Model.Dice;
import Model.Game;
import Model.TurnInfo;

import java.io.*;
import java.util.ArrayList;

public class Controller {

    private Game theModel;

    public Controller() {

    }

    /**
     * Requirement 3.7
     * <p>
     * Returns the pertinent information from the model
     * regarding the current turn.  Information extracted
     * should be displayed upon the GUI.
     *
     * @param player     Current Player
     * @param diceToRoll The dice that are keepers for
     *                   the current roll.
     * @param turnInfo   The turnInf
     * @return The TurnInfo object with the resulting
     * information from the current turn taken.
     */

    public TurnInfo takeTurn(int player, ArrayList<Dice> diceToRoll, TurnInfo turnInfo) {
        return theModel.takeTurn(player, diceToRoll);
    }


    /**
     * Requirements 3.5.1 (Model)
     * <p>
     * Setups the model to incorporate a new
     * game based upon the number of players
     * as required by the user.
     */
    public void newGame(int numPlayers, String[] names) {
        this.theModel = new Game(numPlayers, names);
    }

    /**
     * Requirements 3.8.2.2
     * <p>
     * Allows the user to record a score to the model.
     *
     * @param scoreCat Scoring Category the user is
     *                 attempt to commit to memory.
     * @param playerId Player ID committing a score
     *                 to memory.
     * @return New TurnInfo object with updated
     * game state.
     */

    public TurnInfo setScore(int scoreCat, int playerId) {
        return theModel.setScore(scoreCat, playerId);
    }

    public void setUpperTotals(int playerId) {
        theModel.setUpperTotalScore(playerId);
    }

    public void setLowerTotals(int playerId) {
        theModel.setLowerTotalScore(playerId);
    }

    public void setGrandTotalScore(int playerId) {
        theModel.setGrandTotalScore(playerId);
    }

    /**
     * Requirements 3.1.2.2
     * <p>
     * Saves the current model state to a new game.
     *
     * @param file
     * @throws IOException
     */

    public void saveToFile(File file) throws IOException {
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        Game game = theModel;

        oos.writeObject(game);

        oos.close();
    }

    /**
     * Requirements 3.1.2.3
     * <p>
     * Loads a game from a file into memory.
     *
     * @param file
     * @throws IOException
     */

    public TurnInfo[] loadFromFile(File file) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);


        try {

            Game game = (Game) ois.readObject();

            theModel = game;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        ois.close();

        return theModel.loadGame();
    }

    /**
     * Requirement 3.1.2.3
     *
     * Used to load the names saved from
     * the previous game.
     *
     * @return
     */

    public String[] getNames() {
        return theModel.getNames();
    }

    /**
     *  Used to set the score of a bonus
     *  yahtzee box, in the event it is
     *  triggered.
     *
     * @param bonusIndex
     */

    public void setBonusScore (int bonusIndex) {
        theModel.setBonusScore(bonusIndex);
    }

    /**
     * Requirement 3.x.x.x
     *
     * Sets the name of the player who won the game.
     *
     * In the even of a tie, more than one name will
     * be in the array.  Therefor, the GUI should check
     * size of the array returned for determining a tie.
     *
     * @return
     */

    public String[] getWinner() {
        return theModel.getWinner();
    }

}