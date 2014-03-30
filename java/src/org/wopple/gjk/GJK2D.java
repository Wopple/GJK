package org.wopple.gjk;

import org.wopple.gjk.point.IPoint2D;
import org.wopple.gjk.point.MutablePoint2D;
import org.wopple.gjk.point.Point2D;
import org.wopple.gjk.shape.Circle;
import org.wopple.gjk.support.Support2D;
import org.wopple.gjk.support.SupportCircle2D;
import org.wopple.gjk.support.SupportPoly2D;

public class GJK2D {
    public boolean collide(IPoint2D[] poly1, IPoint2D[] poly2) {
        return collide(poly1, poly2, new SupportPoly2D(), new SupportPoly2D());
    }

    public boolean collide(IPoint2D[] poly, Circle circle) {
        return collide(poly, circle, new SupportPoly2D(), new SupportCircle2D());
    }

    private <A, B> boolean collide(A shape1, B shape2, Support2D<A> support1, Support2D<B> support2) {
        Simplex2D simplex = new Simplex2D();
        IPoint2D a = support(shape1, shape2, support1, support2, new Point2D(-1, -1));
        simplex.push(a);
        MutablePoint2D direction = new MutablePoint2D(a.x(), a.y());

        for (int i = 0; i < 100; i++) {
            a = support(shape1, shape2, support1, support2, direction);

            if (a.dot(direction) < 0) {
                return false;
            }

            if (updateSimplex(simplex, direction)) {
                return true;
            }
        }

        throw new RuntimeException("possible infinite loop in GJK algorithm");
    }

    private <A, B> IPoint2D support(A shape1, B shape2, Support2D<A> support1, Support2D<B> support2, IPoint2D direction) {
        return support1.support(shape1, direction).sub(support2.support(shape2, direction.neg()));
    }

    private boolean updateSimplex(Simplex2D simplex2D, MutablePoint2D direction) {
        if (simplex2D.size() == 2) {
            return updateSimplex2(simplex2D, direction); 
        } else {
            return updateSimplex3(simplex2D, direction); 
        }
    }

    private boolean updateSimplex2(Simplex2D simplex, MutablePoint2D direction) {
        IPoint2D b = simplex.get(0);
        IPoint2D a = simplex.get(1);
        IPoint2D a0 = a.neg();
        IPoint2D ab = b.sub(a);

        if (ab.dot(a0) >= 0) {
            IPoint2D normal = ab.normal(a0);
            direction.set(normal);
        } else {
            simplex.remove(0);
            direction.set(a0);
        }

        return false;
    }

    private boolean updateSimplex3(Simplex2D simplex, MutablePoint2D direction) {
        IPoint2D c = simplex.get(0);
        IPoint2D b = simplex.get(1);
        IPoint2D a = simplex.get(2);
        IPoint2D a0 = a.neg();
        IPoint2D ab = b.sub(a);
        IPoint2D ac = c.sub(a);
        IPoint2D normal;

        if (ab.dot(a0) >= 0) {
            normal = ab.normal(a0);

            if (ac.dot(normal) >= 0) {
                normal = ac.normal(a0);

                if (ab.dot(normal) >= 0) {
                    return true;
                } else {
                    simplex.remove(1);
                    direction.set(normal);
                }
            } else {
                simplex.remove(0);
                direction.set(normal);
            }
        } else {
           if (ac.dot(a0) >= 0) {
               normal = ac.normal(a0);

               if (ab.dot(normal) >= 0) {
                   return true;
               } else {
                   simplex.remove(1);
                   direction.set(normal);
               }
           } else {
               simplex.remove(1);
               simplex.remove(0);
               direction.set(a0);
           }
        }

        return false;
    }
}
