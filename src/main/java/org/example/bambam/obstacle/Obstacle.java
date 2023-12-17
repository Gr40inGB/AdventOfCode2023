package org.example.bambam.obstacle;

import org.example.bambam.LightVector;

public interface Obstacle {
    public LightVector[] encounter(LightVector incomingLightVector);
}
