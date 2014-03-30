package org.wopple.gjk.support;

public interface Support<Shape, Direction, Point> {
    Point support(Shape shape, Direction direction);
}
