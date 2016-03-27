package SS2.GUI;

import SS2.BSPTree.*;
import SS2.geometry.Segment;

import javax.swing.JPanel;
import java.awt.*;

/**
 * Created by dimitri on 26/01/16.
 */
public class PaintPanel extends JPanel{

    BSPTree tree;

    public PaintPanel(BSPTree tree){

        this.tree = tree;
    }

    public void paintComponent (Graphics g) {

        System.out.println(getWidth() +  " " + getHeight());
        System.out.println(tree.getX() + " " + tree.getY());

        Graphics2D g2 = (Graphics2D) g;
        float ratio = Math.max(2.f * (float)tree.getX() /(float)(getWidth()),2.f * (float)tree.getY()  / (float)(getHeight()));
        
        g2.scale(1/ratio,1/ratio);
        g2.translate(tree.getX(),tree.getY());
        g2.drawRect(-tree.getX() -5,-tree.getY() - 5,2*tree.getX() +10,2*tree.getY() +10);
        BSPNode root = tree.getRoot();
        paintComponent(g2,root);
    }

    public void paintComponent (Graphics2D g2, BSPNode node) {

        for (Segment segment : node.getElements()){
            g2.drawLine((int)segment.getX1(),(int)segment.getY1(),(int)segment.getX2(),(int)segment.getY2());
        }

        if (node.getLeft() != null)
            paintComponent(g2,node.getLeft());

        if (node.getRight() != null)
            paintComponent(g2,node.getRight());
    }
}
