package model.utility;

public class PowerPlant extends UtilityProvider {
    public PowerPlant(int row, int col) {
        super(row, col);
    }
    @Override
    public String getUtilityType() {
        return "electricity";
    }
    @Override
    public char getSymbol() {
        return 'P';
    }
}