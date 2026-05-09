package model;

public class RoadCell extends Cell {
    public RoadCell(int row, int col) {
        super(row, col);
    }

    @Override
    public char getSymbol() {
        return 'R';
    }

    @Override
    public boolean isPassable() {
        return true;
    }
}