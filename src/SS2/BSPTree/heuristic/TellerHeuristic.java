package SS2.BSPTree.heuristic;

import java.util.List;
import java.awt.*;

import SS2.geometry.Segment;

public class TellerHeuristic implements Heuristic {

	@Override
	public Segment selectSegment(List<Segment> list) {
		
		/* a maximiser */
		double od = 0;
		Segment odsegment = null;
		
		/* a minimiser */
		int fd = list.size();
		Segment fdsegment = null;
		
		/* valeur de tau conseillee */
		double t = 0.5;
		
		for(Segment u : list)
		{
			int fd_temp = 0;
			
			/* calcul du nombre d'intersection avec la droite contenant u */
			for(Segment v : list)
			{
				Point.Float temp = v.intersect(u);
				if(temp != null) fd_temp += 1;
			}
			
			double od_temp = (double) fd_temp / list.size();
			
			/* on tente de maximiser od si on le peut */
			if(od_temp >= t)
			{
				if(od_temp > od)
				{
					od = od_temp;
					odsegment = u;
				}
			}
			/* sinon on minimise fd */
			else
			{
				if(fd_temp < fd)
				{
					fd = fd_temp;
					fdsegment = u;
				}
			}
		}
		
		if(odsegment != null) return odsegment;
		else return fdsegment;
	}
	
	public String toString(){
		return "TellerHeuristic";
	}

}
