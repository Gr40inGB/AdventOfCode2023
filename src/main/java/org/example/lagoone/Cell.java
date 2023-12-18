package org.example.lagoone;

public class Cell {
    public int y;
    public int x;

    public Cell(int y, int x) {
        this.y = y;
        this.x = x;
    }

    public Cell[] getCellNeighbors() {
        return new Cell[]{
                new Cell(y - 1, x),
                new Cell(y + 1, x),
                new Cell(y, x - 1),
                new Cell(y, x + 1),
        };
    }
}
