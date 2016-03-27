package SS2.geometry;

import java.awt.geom.Point2D;

/**
 * Created by dimitri on 3/27/16.
 */
public class Projection {

    public static float projection2DTo1D(Point2D.Float point,float d){

        return (float) (point.getX() *
                d/point.getY());

    }
}
