package Model;

public class SmStraightBoxTest {

    public boolean calcPotential(int[] totals, int assertValue) {
        SmStrghtBox testBox = new SmStrghtBox();

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