package org.example.bambam.obstacle;

import org.example.bambam.LightVector;

public class Empty implements Obstacle {

    static char symbol = '.';

    @Override
    public LightVector[] encounter(LightVector incomingLightVector) {
        return new LightVector[]{
                new LightVector(incomingLightVector.yDirection, incomingLightVector.xDirection)
        };
    }


    @Override
    public String toString() {
        return symbol + "";
    }
}
