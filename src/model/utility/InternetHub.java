package model.utility;

public class InternetHub extends UtilityProvider {
    public InternetHub(int row, int col) {
        super(row, col);
    }
    @Override
    public String getUtilityType() {
        return "internet";
    }
    @Override
    public char getSymbol() {
        return 'T';
    }
}