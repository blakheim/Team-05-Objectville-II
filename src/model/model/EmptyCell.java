package model;

public class EmptyCell extends Cell {
    public EmptyCell(int row, int col) {
        super(row, col);
    }

    @Override
    public char getSymbol() {
        return 'E';
    }

    @Override
    public boolean isPassable() {

        return false;
    }
}