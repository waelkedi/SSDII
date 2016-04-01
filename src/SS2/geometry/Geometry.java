package SS2.geometry;

import java.awt.Point;

public final class Geometry {

	/* les float[] representent des droites */
	
	
	/* calcule la perpendiculaire de d au point p */
	public static float[] perpendicular(float[] d, Point.Float p)
	{
		float[] res = new float[3];
		res[0] = - d[1];
		res[1] = d[0];
		res[2] = - res[0] * p.x - res[1] * p.y ;
		return res;
	}
	
	/* calcule la droite passant par p et q */
	public static float[] line(Point.Float p, Point.Float q)
	{
		float[] res = new float[3];
		res[0]= p.y - q.y;
		res[1] = q.x - p.x;
		res[2] = - res[0] * p.x - res[1] * p.y;
		return res;
	}
	
	/*l'angle est en radians */
	/* fait faire a p une rotation de angle degres autour d'origin */
	public static Point.Float rotation(Point.Float origin, Point.Float p, double angle)
	{
		float a = p.x - origin.x;
		float b = p.y - origin.y;
		float x = (float) (a * Math.cos(angle) - b * Math.sin(angle)) + origin.x;
		float y = (float) (a * Math.sin(angle) + b * Math.cos(angle)) + origin.y;
		return new Point.Float(x,y);
	}

	public static Point.Float rotation(Point.Float p, double angle)
	{
		float a = p.x;
		float b = p.y;
		float x = (float) (a * Math.cos(angle) - b * Math.sin(angle));
		float y = (float) (a * Math.sin(angle) + b * Math.cos(angle));
		return new Point.Float(x,y);
	}
	
	/* evalue la position du point (x,y) par rapport a d */
	public static float eval(float[] d, float x, float y)
	{
		return d[0] * x + d[1] * y + d[2];
	}

	public static float eval(float[] d,Point.Float p){
		return eval(d,p.x,p.y);
	}

	/* calcule l'intersection entre s et d */
	public static Point.Float intersection(float[] d, Segment s)
	{
		float[] t = new float[3];
		t[0] = s.getY1() - s.getY2();
		t[1] = s.getX2() - s.getX1();
		t[2] = - t[0] * s.getX1() - t[1] * s.getY1();

		Point.Float p = intersection(d,t);
		if (p.x > s.getX1() && p.x > s.getX2()) return null;
		if (p.x < s.getX1() && p.x < s.getX2()) return null;

		return p;
	}
	
	/* calcule l'intersection entre d et t */
	public static Point.Float intersection(float[] d, float[] t)
	{
		if(t[1] == 0 && d[1] == 0) return null;
		else if(t[1] == 0 && d[1] != 0)
		{
			float x = - t[2] / t[0];
			float y = - (d[2] + d[0] * x) / d[1];
			return new Point.Float(x,y);
		}
		else if(t[1] != 0 && d[1] == 0)
		{
			float x = - d[2] / d[0];
			float y = - (t[2] + t[0] * x) / t[1];
			return new Point.Float(x,y);
		}
		else if(d[1] / t[1] * t[0] - d[0] == 0) return null;
		else
		{
			float x = (d[2] - t[2] * d[1] / t[1]) / (d[1] / t[1] * t[0] - d[0]);
			float y = - (d[2] + d[0] * x) / d[1];
			return new Point.Float(x,y);
		}
	}
}
