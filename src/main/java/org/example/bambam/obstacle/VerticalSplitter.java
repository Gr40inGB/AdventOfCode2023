package org.example.bambam.obstacle;

import org.example.bambam.LightVector;

public class VerticalSplitter implements Obstacle {
    @Override
    public String toString() {
        return symbol + "";
    }

    static char symbol = '|';

    @Override
    public LightVector[] encounter(LightVector incomingLightVector) {
        if (incomingLightVector.yDirection == 0) {
            return new LightVector[]{
                    new LightVector(1, 0),
                    new LightVector(-1, 0)
            };
            // else xD ==0
        } else return new LightVector[]{
                new LightVector(incomingLightVector.yDirection, incomingLightVector.xDirection)
        };
    }


}
