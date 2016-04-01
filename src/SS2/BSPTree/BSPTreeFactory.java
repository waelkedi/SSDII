package SS2.BSPTree;

import SS2.BSPTree.heuristic.Heuristic;
import SS2.geometry.Segment;
import SS2.io.ParsedFile;

import java.awt.*;
import java.io.File;
import java.util.*;
import java.util.List;

/**
 * Created by dimitri on 3/26/16.
 */
public class BSPTreeFactory {

    public static BSPTree createTree(File file,Heuristic heuristic){

        ParsedFile parser = new ParsedFile(file);
        ArrayList list = new ArrayList<Segment>(Arrays.asList(parser.getSegments()));

        BSPNode root = creaNode(list,heuristic);

        return new BSPTree(root,parser.getXBound(),parser.getYBound());
    }


    private static BSPNode creaNode(List<Segment> list,Heuristic heuristic){

        ArrayList<Segment> elements = new ArrayList<Segment>();
        BSPNode right = null;
        BSPNode left = null;

        if (list.size() > 0) {

            Segment firstSegment = heuristic.selectSegment(list);

            ArrayList<Segment> rigthList = new ArrayList<Segment>();
            ArrayList<Segment> leftList = new ArrayList<Segment>();

            Point.Float tmp;

            for (Segment s :list) {

                if (firstSegment.isOnLine(s.getX1(),s.getY1()) && firstSegment.isOnLine(s.getX2(),s.getY2())){

                    elements.add(s);

                } else if ((tmp = firstSegment.intersect(s)) != null){

                    if (s.isRightOfLine(s.getX1(),s.getY1())) {

                        rigthList.add(new Segment(s.getX1(), s.getY1(),new Double(tmp.getX()).floatValue(), new Double(tmp.getY()).floatValue(), s.getColor()));
                        leftList.add(new Segment(s.getX2(), s.getY2(), (float) tmp.getX(), (float) tmp.getY(), s.getColor()));

                    }else{

                        leftList.add(new Segment(s.getX1(), s.getY1(), (float) tmp.getX(), (float) tmp.getY(), s.getColor()));
                        rigthList.add(new Segment(s.getX2(), s.getY2(), (float) tmp.getX(), (float) tmp.getY(), s.getColor()));
                    }


                }else if (firstSegment.isRightOfLine(s.getX1(),s.getY1())){

                    rigthList.add(s);

                }else{

                    leftList.add(s);

                }
            }

            if (!rigthList.isEmpty()){
                right =  creaNode(rigthList,heuristic);
            }
            if (!leftList.isEmpty()) {
                left = creaNode(leftList,heuristic);
            }
        }
        return new BSPNode(elements,right,left);
    }
}

