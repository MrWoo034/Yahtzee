package Model;

public class InvalidTotalException extends Exception{

    public InvalidTotalException(String message, int[] totals) {
        super(message);
        System.out.println("\n The totals passed were: ");
        for (int i = 0; i < totals.length; i++) {
            System.out.println(totals[i] + " ");
        }
        System.out.println("\n");
    }
}
