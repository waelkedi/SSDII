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
    Point2D.Float pointOfView = new Point2D.Float(0.f,0.f);
    float distance = 50.f;
    float angle;

    public PaintPanel(BSPTree tree){

        this.tree = tree;
    }

    public void paintComponent (Graphics g) {

        System.out.println("-");

        Graphics2D g2 = (Graphics2D) g;

        float ratio = Math.min((float)(getWidth()) / (2.f * (float)tree.getX()),(float)(getHeight()) / (2.f * (float)tree.getY()));

        g2.translate((getWidth()/2 - tree.getX()*ratio),getHeight()/2 - tree.getY()*ratio);
        g2.scale(ratio,ratio);
        g2.translate(tree.getX(),tree.getY());
        g2.drawRect(-tree.getX() -5,-tree.getY() - 5,2*tree.getX() +10,2*tree.getY() +10);
        g2.fillRect(0, 0, -100, 5);

        g2.drawLine(-tree.getX(),(int)distance,tree.getX(),(int)distance);
        g2.drawLine(-10,0,10,0);
        g2.drawLine(0,-10,0,10);

        paint2DTree(g2,tree.getRoot());
        paint1DTree(g2,tree.getRoot());
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

        System.out.println("0");

        if (node.getElements() != null){

            for (Segment segment : node.getElements()) {

                if (segment.getY1() > distance && segment.getY2() > distance) {

                    System.out.println("2");

                    g2.setColor(segment.getColor());
                    Point2D.Float point = new Point2D.Float(segment.getX1(), segment.getY1());
                    float x1 = projection2DTo1D(point, distance);
                    point = new Point2D.Float(segment.getX2(), segment.getY2());
                    float x2 = projection2DTo1D(point, distance);
                    g2.fillRect((int) x1,(int) distance - 2, (int) (x2 - x1), 3);
                    g2.fillRect((int) (x2), (int) distance - 2, (int) (x1 -x2), 3);

                    System.out.println(x1 + " " + x2 + " " + segment.getColor());

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
