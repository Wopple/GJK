package org.wopple.gjk;

import org.wopple.gjk.point.IPoint2D;

public class Simplex2D {
    private final IPoint2D[] points = new IPoint2D[3];
    private int size = 0;

    public IPoint2D get(int index) {
        return points[index];
    }

    public int size() {
        return size;
    }

    public void push(IPoint2D point) {
        points[size++] = point;
    }

    public void remove(int index) {
        while (index < size - 1) {
            points[index] = points[index + 1];
            index++;
        }

        size--;
    }
}
