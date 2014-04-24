package org.wopple.gjk.point;

public class MutablePoint2D implements IPoint2D {
    public double x;
    public double y;

    public MutablePoint2D() {
    }

    public MutablePoint2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public MutablePoint2D(MutablePoint2D point) {
        x = point.x;
        y = point.y;
    }

    public void set(IPoint2D point) {
        x = point.x();
        y = point.y();
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
        x += b.x();
        y += b.y();
        return this;
    }

    @Override
    public IPoint2D sub(IPoint2D b) {
        x -= b.x();
        y -= b.y();
        return this;
    }

    @Override
    public IPoint2D neg() {
        x = -x;
        y = -y;
        return this;
    }

    @Override
    public double dot(IPoint2D b) {
        return x * b.x() + y * b.y();
    }

    @Override
    public IPoint2D normal(IPoint2D b) {
        double ax_by = x * b.y();
        double ay_bx = y * b.x();
        double temp = y * (ay_bx - ax_by);
        y = x * (ax_by - ay_bx);
        x = temp;
        return this;
    }
}
