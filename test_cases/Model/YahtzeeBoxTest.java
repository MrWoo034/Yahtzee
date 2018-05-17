package Model;

public class YahtzeeBoxTest {

    public boolean calcPotential(int[] totals, int assertValue) {
        YahtzeeBox testBox = new YahtzeeBox();

        try {
            testBox.calcPotential(totals);
        } catch (InvalidTotalException e) {
            e.printStackTrace();
        }

        if (assertValue == testBox.getPotentialScore().intValue()){
            return true;
        } else {
            return false;
        }
    }
}