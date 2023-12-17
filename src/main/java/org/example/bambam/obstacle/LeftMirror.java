package org.example.bambam.obstacle;

import org.example.bambam.LightVector;

public class LeftMirror implements Obstacle {
    static char symbol = '\\';

    @Override
    public LightVector[] encounter(LightVector incomingLightVector) {
        return new LightVector[]{
                new LightVector(incomingLightVector.xDirection, incomingLightVector.yDirection)
        };
    }


    @Override
    public String toString() {
        return symbol + "";
    }
}
