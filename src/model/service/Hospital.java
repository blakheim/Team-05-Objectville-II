package model.service;

public class Hospital extends ServiceBuilding {
    public Hospital(int row, int col) {
        super(row, col, 3, "health");
    }

    @Override
    public char getSymbol() { return 'D'; }
}
