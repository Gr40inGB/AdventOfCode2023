package org.example.bambam.obstacle;

import org.example.bambam.LightVector;

public class HorizontalSplitter implements Obstacle {
    static char symbol = '-';

    @Override
    public LightVector[] encounter(LightVector incomingLightVector) {
        if (incomingLightVector.xDirection == 0) {
            return new LightVector[]{
                    new LightVector(0, -1),
                    new LightVector(0, 1)
            };
            // else yD ==0
        } else return new LightVector[]{
                new LightVector(incomingLightVector.yDirection, incomingLightVector.xDirection)
        };
    }

    @Override
    public String toString() {
        return symbol + "";
    }
}
