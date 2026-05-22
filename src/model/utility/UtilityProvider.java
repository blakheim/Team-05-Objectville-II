package model.utility;
import model.Cell;

public abstract class UtilityProvider extends Cell {
    protected int capacity;
    public UtilityProvider(int row, int col) {
        super(row, col);
        this.capacity = 100;
    }

    public abstract String getUtilityType();
    @Override
    public boolean isPassable() {
        return false;
    }

    public int getCapacity() {
        return capacity;
    }
}