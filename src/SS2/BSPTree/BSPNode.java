package SS2.BSPTree;

import SS2.geometry.Segment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dimitri on 24/01/16.
 */

public class BSPNode {

    List<Segment> elements;
    protected BSPNode left,right;

    public BSPNode(ArrayList<Segment> elements, BSPNode right, BSPNode left) {

        this.elements =elements;
        this.right = right;
        this.left = left;
    }

    public List<Segment> getElements() {
        return  elements;
    }

    public BSPNode getLeft(){
        return left;
    }

    public BSPNode getRight(){
        return right;
    }

    public int getNbrSegements() {

        int nbr = 0;

        if (right != null)  nbr += right.getNbrSegements();

        if (left != null)   nbr += left.getNbrSegements();

        return nbr + elements.size();
    }

    public int getDeep() {

        int deep = 0;

        if (left != null) deep = left.getDeep();

        if (right != null && deep < right.getDeep()) deep = left.getDeep();

        return deep + 1;
    }
}
