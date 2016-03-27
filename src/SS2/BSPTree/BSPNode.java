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
}
