package Model;

public class LgStraightBoxTest {

    public boolean calcPotential(int[] totals, int assertValue) {
        LgStrghtBox testBox = new LgStrghtBox();

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