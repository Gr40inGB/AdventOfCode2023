package org.example.lava;

public class BestCell {
    public int y;
    public int x;
    public LavaVector incomingVector;
    public int bestValue;

    public BestCell(int y, int x, LavaVector incomingVector, int bestValue) {
        this.y = y;
        this.x = x;
        this.incomingVector = incomingVector;
        this.bestValue = bestValue;
    }
}
