package model.service;

public class PoliceStation extends ServiceBuilding {
    public PoliceStation(int row, int col) {
        super(row, col, 5, "security");
    }

    @Override
    public char getSymbol() { return 'F'; }
}
