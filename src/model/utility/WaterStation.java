package model.utility;

public class WaterStation extends UtilityProvider {
    public WaterStation(int row, int col) {
        super(row, col);
    }
    @Override
    public String getUtilityType() {
        return "water";
    }
    @Override
    public char getSymbol() {
        return 'W';
    }
}