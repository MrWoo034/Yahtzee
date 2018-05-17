import Controller.Controller;
import Model.Game;
import View.View;

import javax.jws.WebParam;
import javax.swing.SwingUtilities;

public class App {

    public static void main(String[] args) {

        //View theView = new View();

        // Game theModel = new Game(0);
        //TODO might need to encapsulate Game into an abstract Model object

        //Controller theController = new Controller(theModel, theView);

        //theView.setVisible(true);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new View();
            }
        });

    }
}

