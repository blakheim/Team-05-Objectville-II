package model;

import model.Cell;

public class Grid {
    private Cell[][] cells;
    private int rows;
    private int cols;

    public Grid(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.cells = new Cell[rows][cols];
    }

    public Cell getCell(int row, int col) {
        if (isInside(row, col)) {
            return cells[row][col];
        }
        return null;
    }

    public void setCell(int row, int col, Cell cell) {
        if (isInside(row, col)) {
            cells[row][col] = cell;
        }
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public boolean isInside(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }
}