package View;

import java.util.EventObject;

public class NewGameEvent extends EventObject {

    String[] names;
    int numPlayers;

    public NewGameEvent(Object source, String[] names, int numPlayers) {
        super (source);
        this.names = names;
        this.numPlayers = numPlayers;
    }

    public String[] getNames () {
        return this.names;
    }

    public int getNumPlayers() {
        return this.numPlayers;
    }

}
