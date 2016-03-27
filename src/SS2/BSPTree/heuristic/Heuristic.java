package SS2.BSPTree.heuristic;

import SS2.geometry.Segment;

import java.util.List;

/**
 * Created by dimitri on 3/26/16.
 */
public interface Heuristic {

    public Segment selectSegment(List<Segment> list);

}
