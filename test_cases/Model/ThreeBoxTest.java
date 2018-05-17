package Model;

public class ThreeBoxTest {

    public boolean calcPotential(int[] totals, int assertValue) {
        ThreesBox testBox = new ThreesBox();

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