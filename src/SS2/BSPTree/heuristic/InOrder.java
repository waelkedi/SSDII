package SS2.BSPTree.heuristic;

import SS2.geometry.Segment;
import com.sun.xml.internal.ws.api.ha.StickyFeature;

import java.security.PublicKey;
import java.util.List;

/**
 * Created by dimitri on 3/26/16.
 */
public class InOrder implements Heuristic{
    @Override
    public Segment selectSegment(List<Segment> list) {
        return list.get(0);
    }

    public String toString(){
        return "InOrder";
    }
}
