package Model;

public class TwoBoxTest {

    public boolean calcPotential(int[] totals, int assertValue) {
        TwosBox twoBox = new TwosBox();

        try {
            twoBox.calcPotential(totals);
        } catch (InvalidTotalException e) {
            e.printStackTrace();
        }

        if (assertValue == twoBox.getPotentialScore().intValue()){
            return true;
        } else {
            return false;
        }
    }
}