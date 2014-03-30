package org.wopple.gjk.point;

public class Point2D implements IPoint2D {
    public final double x;
    public final double y;

    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public double x() {
        return x;
    }

    @Override
    public double y() {
        return y;
    }

    @Override
    public IPoint2D add(IPoint2D b) {
        return new Point2D(x + b.x(), y + b.y());
    }

    @Override
    public IPoint2D sub(IPoint2D b) {
        return new Point2D(x - b.x(), y - b.y());
    }

    @Override
    public IPoint2D neg() {
        return new Point2D(-x, -y);
    }

    @Override
    public double dot(IPoint2D b) {
        return x * b.x() + y * b.y();
    }

    @Override
    public IPoint2D normal(IPoint2D b) {
        double ax_by = x * b.y();
        double ay_bx = y * b.x();

        return new Point2D(
                y * (ay_bx - ax_by),
                x * (ax_by - ay_bx));
    }
}
