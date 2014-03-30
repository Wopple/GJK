package org.wopple.gjk.point;

public interface IPoint2D {
    double x();
    double y();
    IPoint2D add(IPoint2D b);
    IPoint2D sub(IPoint2D b);
    IPoint2D neg();
    double dot(IPoint2D b);
    IPoint2D normal(IPoint2D b);
}
