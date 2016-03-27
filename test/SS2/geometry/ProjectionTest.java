package SS2.geometry;

import java.awt.*;
import java.awt.geom.Point2D;

import static org.junit.Assert.*;

/**
 * Created by dimitri on 3/27/16.
 */
public class ProjectionTest {

    @org.junit.Test
    public void projection2DTo1D() throws Exception {

        float result = Projection.projection2DTo1D(new Point.Float(2.f,2.f),1.f);
        assertTrue(result == 1.f);
        result = Projection.projection2DTo1D(new Point.Float(1.f,2.f),1.f);
        assertTrue(result == 0.5f);
    }
}