package model;

public abstract class Cell {

    protected int row;
    protected int col;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public abstract char getSymbol();
    public abstract boolean isPassable();

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}