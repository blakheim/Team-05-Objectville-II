package model.service;

public class School extends ServiceBuilding {
    public School(int row, int col) {
        super(row, col, 4, "education");
    }

    @Override
    public char getSymbol() { return 'S'; }
}
