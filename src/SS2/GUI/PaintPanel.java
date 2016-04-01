package SS2.GUI;

import SS2.BSPTree.*;
import SS2.geometry.Geometry;
import SS2.geometry.Segment;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;

import static SS2.geometry.Projection.projection2DTo1D;

/**
 * Created by dimitri on 26/01/16.
 */
public class PaintPanel extends JPanel implements MouseListener {

    BSPTree tree;
    Point2D.Float pointOfView = new Point2D.Float(0.f,0.f);
    float distance = 50.f;
    float angle;

    public PaintPanel(BSPTree tree){

        this.tree = tree;
        addMouseListener(this);
    }

    public void paintComponent (Graphics g) {
        super.paintComponent(g);

        float ratio = Math.min((float)(getWidth()) / (2.f * (float)tree.getX()),(float)(getHeight() - 25.f) / (2.f * (float)tree.getY()));

        Graphics2D g2 = (Graphics2D) g;

        g2.translate(getWidth()/2,0);
        g2.scale(ratio,1);
        paint1DTree(g2,tree.getRoot());
        g2.scale(1/ratio,1);
        g2.translate(-1*getWidth()/2,0);

        g2.drawRect(0,0,5,5);

        g2.translate((getWidth()/2 - tree.getX()*ratio),(getHeight())/2 - tree.getY()*ratio + 10);
        g2.scale(ratio,ratio);
        g2.translate(tree.getX(),tree.getY());

        g2.setColor(Color.gray);
        g2.drawRect(-tree.getX() - 5,-tree.getY() - 5,2*tree.getX() + 5,2*tree.getY() + 5);

        g2.drawLine((int) pointOfView.getX() - 5, (int) pointOfView.getY(),(int) pointOfView.getX() + 5,(int) pointOfView.getY());
        g2.drawLine((int) pointOfView.getX(),(int) pointOfView.getY() - 5, (int)pointOfView.getX(),(int ) pointOfView.getY() + 5);

        g2.drawLine((int) pointOfView.getX(),(int) pointOfView.getY(),(int) (pointOfView.getX() + distance * Math.cos(angle + Math.PI/2)),(int) (pointOfView.getY() + distance * Math.sin(angle + Math.PI/2)));

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

                    Point2D.Float point1 = new Point2D.Float(segment.getX1() - (float) pointOfView.getX(), segment.getY1() - (float) pointOfView.getY());
                    point1 = Geometry.rotation(new Point2D.Float(0.f,0.f),point1,-angle);

                    Point2D.Float point2 = new Point2D.Float(segment.getX2() - (float) pointOfView.getX(), segment.getY2() - (float) pointOfView.getY());
                    point2 = Geometry.rotation(new Point2D.Float(0.f,0.f),point2,-angle);

                if (point1.getY() > distance + pointOfView.getY() && point2.getY() > distance + pointOfView.getY()) {

                    float x1 = projection2DTo1D(point1, distance);
                    float x2 = projection2DTo1D(point2, distance);

                    g2.setColor(segment.getColor());
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

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

        float ratio = Math.min((float)(getWidth()) / (2.f * (float)tree.getX()),(float)(getHeight() - 25.f) / (2.f * (float)tree.getY()));

        float x = mouseEvent.getX() - (getWidth()/2 - tree.getX()*ratio);
        x = (x / ratio - (float) tree.getX());

        float y = mouseEvent.getY() - ((getHeight())/2 - tree.getY()*ratio + 10);
        y = y / ratio - (float) tree.getY();

        pointOfView = new Point.Float(x,y);

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

        float ratio = Math.min((float)(getWidth()) / (2.f * (float)tree.getX()),(float)(getHeight() - 25.f) / (2.f * (float)tree.getY()));

        float x = mouseEvent.getX() - (getWidth()/2 - tree.getX()*ratio);
        x = (x / ratio - (float) tree.getX());

        float y = mouseEvent.getY() - ((getHeight())/2 - tree.getY()*ratio + 10);
        y = y / ratio - (float) tree.getY();

        angle = - (float) (Math.atan2( -(y - pointOfView.getY()),(x - pointOfView.getX())) + Math.PI/2);

        distance = (float) Math.sqrt((pointOfView.getX() - x) * (pointOfView.getX() - x) + (pointOfView.getY() - y) * (pointOfView.getY() - y));

        repaint();
    }


    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
