package Model;

public class FourBoxTest {

    public boolean calcPotential(int[] totals, int assertValue) {
        FoursBox testBox = new FoursBox();

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