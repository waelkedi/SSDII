package SS2.BSPTree.heuristic;

import SS2.geometry.Segment;

import java.util.List;
import java.util.Random;

/**
 * Created by dimitri on 3/26/16.
 */
public class Ramdom implements Heuristic{

    @Override
    public Segment selectSegment(List<Segment> list) {
        return list.get(new Random().nextInt(list.size()));
    }

    public String toString(){
        return "Ramdom";
    }
}
