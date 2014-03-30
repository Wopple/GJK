package org.wopple.gjk.support;

import org.wopple.gjk.point.IPoint2D;

public class SupportPoly2D implements Support2D<IPoint2D[]> {
    @Override
    public IPoint2D support(IPoint2D[] polygon, IPoint2D direction) {
        IPoint2D bestPoint = polygon[0];
        double bestProduct = polygon[0].dot(direction);

        for (int i = 1; i < polygon.length; i++) {
            IPoint2D point = polygon[i];
            double product = point.dot(direction);

            if (product > bestProduct) {
                bestProduct = product;
                bestPoint = point;
            }
        }

        return bestPoint;
    }
}
