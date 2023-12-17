package org.example.bambam;

import java.util.Objects;

public class LightVector {
    public int yDirection;
    public int xDirection;

    public LightVector(int yDirection, int xDirection) {
        this.yDirection = yDirection;
        this.xDirection = xDirection;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LightVector that = (LightVector) o;
        return yDirection == that.yDirection && xDirection == that.xDirection;
    }

    @Override
    public int hashCode() {
        return Objects.hash(yDirection, xDirection);
    }
}
