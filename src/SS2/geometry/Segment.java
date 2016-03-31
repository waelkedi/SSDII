package SS2.geometry;

import java.awt.*;

/**
 * Created by dimitri on 26/01/16.
 */
public class Segment {

    private float x1, x2, y1, y2;
    Color color;

    public Segment(float x1, float y1, float x2, float y2, Color color){

        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;

        if(color != null)
            this.color = color;
        else
            this.color = Color.black;
    }

    public float getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public float getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public float getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public float getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }


    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * @param s a segment
     * @return the intersection point if the line segment s intersects Segement, false otherwise
     */
    public Point.Float intersect(Segment s){

        float x3 = s.x1;
        float y3 = s.y1;
        float x4 = s.x2;
        float y4 = s.y2;

        float d = (x1-x2)*(y3-y4) - (y1-y2)*(x3-x4);
        if (d == 0) return null;

        float xi = ((x3-x4)*(x1*y2-y1*x2)-(x1-x2)*(x3*y4-y3*x4))/d;
        float yi = ((y3-y4)*(x1*y2-y1*x2)-(y1-y2)*(x3*y4-y3*x4))/d;

        Point.Float p = new Point.Float(xi,yi);
        if (xi < Math.min(x1,x2) || xi > Math.max(x1,x2)) return null;
        if (xi < Math.min(x3,x4) || xi > Math.max(x3,x4)) return null;
        if (yi < Math.min(y1,y2) || yi > Math.max(y1,y2)) return null;
        if (yi < Math.min(y3,y4) || yi > Math.max(y3,y4)) return null;

        return p;
    }


    /**
     * @param s a segment
     * @return the intersection point if the line segment intersects the extension of Segement, false otherwise
     */
    public Point.Float intersectLine(Segment s){

        float x3 = s.x1;
        float y3 = s.y1;
        float x4 = s.x2;
        float y4 = s.y2;

        float d = (x1-x2)*(y3-y4) - (y1-y2)*(x3-x4);
        if (d == 0) return null;

        float xi = ((x3-x4)*(x1*y2-y1*x2)-(x1-x2)*(x3*y4-y3*x4))/d;
        float yi = ((y3-y4)*(x1*y2-y1*x2)-(y1-y2)*(x3*y4-y3*x4))/d;

        Point.Float p = new Point.Float(xi,yi);
        if (xi < Math.min(x3,x4) || xi > Math.max(x3,x4)) return null;
        return p;
    }

    public boolean isRightOfLine(float x, float y){

        return ((x2-x1) * y - x * (y2-y1) > 0);
    }

    public boolean isOnLine(float x, float y){

        return ((Math.abs((x2-x1) * (y - y1) - (x - x1) * (y2-y1))) < 1);
    }
}
