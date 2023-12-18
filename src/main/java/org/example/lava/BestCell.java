package org.example.lava;

public class BestCell {
    public int y;
    public int x;
    public LavaVector incomingVector;
    public int directionSteps;
    public int bestValue;

    public BestCell(int y, int x, LavaVector incomingVector, int directionSteps, int bestValue) {
        this.y = y;
        this.x = x;
        this.directionSteps = directionSteps;
        this.incomingVector = incomingVector;
        this.bestValue = bestValue;
    }
}
