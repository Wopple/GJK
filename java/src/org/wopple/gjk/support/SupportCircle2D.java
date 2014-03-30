package org.wopple.gjk.support;

import org.wopple.gjk.point.IPoint2D;
import org.wopple.gjk.point.Point2D;
import org.wopple.gjk.shape.Circle;

public class SupportCircle2D implements Support2D<Circle> {
    @Override
    public Point2D support(Circle circle, IPoint2D direction) {
        double magnitude = Math.sqrt(direction.dot(direction));
        double scale = circle.radius / magnitude;

        return new Point2D(
                circle.center.x + scale * direction.x(),
                circle.center.y + scale * direction.y());
    }
}
