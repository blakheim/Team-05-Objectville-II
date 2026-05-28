package model;

public abstract class Cell {

    protected final int row;
    protected final int col;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public abstract char getSymbol();
    public abstract boolean isPassable();

    public void resetTick() {}
    public void update() {}

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}