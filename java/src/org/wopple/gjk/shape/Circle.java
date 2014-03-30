package org.wopple.gjk.shape;

import org.wopple.gjk.point.Point2D;

public class Circle {
    public final Point2D center;
    public final double radius;

    public Circle(Point2D center, double radius) {
        this.center = center;
        this.radius = radius;
    }
}
