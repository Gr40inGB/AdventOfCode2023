package org.example.lava;

import java.util.Objects;

public class LavaVector {
    public int yDirection;
    public int xDirection;

    public LavaVector(int yDirection, int xDirection) {
        this.yDirection = yDirection;
        this.xDirection = xDirection;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LavaVector that = (LavaVector) o;
        return yDirection == that.yDirection && xDirection == that.xDirection;
    }

    @Override
    public int hashCode() {
        return Objects.hash(yDirection, xDirection);
    }
}
