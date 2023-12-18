package org.example.lagoone;

import java.util.Objects;

public class DiggerVector {
    public int yDirection;
    public int xDirection;

    public DiggerVector(int yDirection, int xDirection) {
        this.yDirection = yDirection;
        this.xDirection = xDirection;
    }

    @Override
    public String toString() {
        return "DiggerVector{" +
                "yDirection=" + yDirection +
                ", xDirection=" + xDirection +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiggerVector that = (DiggerVector) o;
        return yDirection == that.yDirection && xDirection == that.xDirection;
    }

    @Override
    public int hashCode() {
        return Objects.hash(yDirection, xDirection);
    }
}
