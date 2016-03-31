package SS2.GUI;

import SS2.BSPTree.*;
import SS2.geometry.Projection;
import SS2.geometry.Segment;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.geom.Point2D;

import static SS2.geometry.Projection.projection2DTo1D;

/**
 * Created by dimitri on 26/01/16.
 */
public class PaintPanel extends JPanel{

    BSPTree tree;
    Point2D.Float pointOfView = new Point2D.Float(0.f,-51);
    float distance = 50.f;
    float angle;

    public PaintPanel(BSPTree tree){

        this.tree = tree;
    }

    public void paintComponent (Graphics g) {

        float ratio = Math.min((float)(getWidth()) / (2.f * (float)tree.getX()),(float)(getHeight()) / (2.f * (float)tree.getY() + 25));

        Graphics2D g2 = (Graphics2D) g;
        g2.translate(getWidth()/2,0);
        g2.scale(ratio,1);
        paint1DTree(g2,tree.getRoot());
        g2.scale(1/ratio,1);
        g2.translate(-1*getWidth()/2,0);


        g2.translate((getWidth()/2 - tree.getX()*ratio),(getHeight())/2 - tree.getY()*ratio);
        g2.scale(ratio,ratio);
        g2.translate(tree.getX(),tree.getY());
        g2.setColor(Color.gray);
        g2.drawRect(-tree.getX() - 5,-tree.getY() - 5,2*tree.getX() + 5,2*tree.getY() + 5);
        g2.drawLine((int) pointOfView.getX() - 5, (int) pointOfView.getY(),(int) pointOfView.getX() + 5,(int) pointOfView.getY());
        g2.drawLine((int) pointOfView.getX(),(int) pointOfView.getY() - 5, (int)pointOfView.getX(),(int ) pointOfView.getY() + 5);
        g2.drawLine(-100,(int) (distance + pointOfView.getY()), 100,(int) (distance + pointOfView.getY()));
        paint2DTree(g2,tree.getRoot());
    }

    // Affichage 2D

    public void paint2DTree (Graphics2D g2, BSPNode node) {

        for (Segment segment : node.getElements()){
            g2.setColor(segment.getColor());
            g2.drawLine((int)segment.getX1(),(int)segment.getY1(),(int)segment.getX2(),(int)segment.getY2());
        }

        if (node.getLeft() != null)
            paint2DTree(g2,node.getLeft());

        if (node.getRight() != null)
            paint2DTree(g2,node.getRight());
    }

    public void paintSegments(Graphics2D g2, BSPNode node){

        if (node.getElements() != null){

            for (Segment segment : node.getElements()) {

                if (segment.getY1() > distance + pointOfView.getY() && segment.getY2() > distance + pointOfView.getY()) {

                    g2.setColor(segment.getColor());
                    Point2D.Float point = new Point2D.Float(segment.getX1() - (float) pointOfView.getX(), segment.getY1() - (float) pointOfView.getY());
                    float x1 = projection2DTo1D(point, distance);
                    point = new Point2D.Float(segment.getX2() - (float) pointOfView.getX(), segment.getY2() - (float) pointOfView.getY());
                    float x2 = projection2DTo1D(point, distance);
                    g2.fillRect((int) x1,0, (int) (x2 - x1), 3);
                    g2.fillRect((int) x2,0, (int) (x1 -x2), 3);

                }
            }
        }
    }

    public void paint1DTree(Graphics2D g2,BSPNode node){

        if (node.getElements().get(0).isRightOfLine(pointOfView.x,pointOfView.y)){
            if (node.getLeft() != null)
                paint1DTree(g2,node.getLeft());

            paintSegments(g2,node);

            if (node.getRight() != null)
                paint1DTree(g2,node.getRight());

        } else {

            if (node.getRight() != null)
                paint1DTree(g2,node.getRight());

            paintSegments(g2, node);

            if (node.getLeft() != null)
                paint1DTree(g2,node.getLeft());
        }
    }
}
