package Model;

public class FullHouseBoxTest {

    public boolean calcPotential(int[] totals, int assertValue) {
        FullHouse testBox = new FullHouse();

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