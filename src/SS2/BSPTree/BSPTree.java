package SS2.BSPTree;

/**
 * Created by dimitri on 24/01/16.
 */


public class BSPTree {

    private BSPNode root;
    private int x,y;


    public BSPTree(BSPNode node, int xBound, int yBound) {

        this.x = xBound;
        this.y = yBound;
        this.root = node;
    }

    public BSPNode getRoot() {
        return root;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getNbrSegemets(){
        return root.getNbrSegements();
    }

    public int getDeep(){
        return root.getDeep();
    }
}
