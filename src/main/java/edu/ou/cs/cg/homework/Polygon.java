
package edu.ou.cs.cg.homework;

import java.awt.geom.Line2D;
import java.util.ArrayList;
import javax.media.opengl.*;

class Polygon
{
    private ArrayList<Vector> sides;
    private Point[] points;
    private float radius;
    private float startAngle;
    public Point center;

    public Polygon(int numPoints, Point center, float radius, float startAngle)
    {
        this.center = center;
        this.radius = radius;
        this.startAngle = startAngle;
        this.points = this.generatePoints(numPoints, this.center, radius, startAngle);
        this.sides = this.updateVectors();
        for(Point p: this.points)
        {
            p.setVelocity(this.center.getVelocity());
        }
    }

    public Polygon(Point center, float startRadius)
    {
        this.center = center;
        this.radius = startRadius;
        this.points = new Point[]{ this.center };
        this.sides = this.updateVectors();
    }

    private ArrayList<Vector> updateVectors()
    {
        ArrayList<Vector> _s = new ArrayList<Vector>();
        for(int i = 0; i < points.length - 1; i++)
        {
            Vector v = new Vector(points[i], points[i+1]);
            _s.add(v);
        }
        _s.add(new Vector(points[points.length - 1], points[0]));
        return _s;
    }

    public void draw(GL2 gl)
    {
        float[] color = {1.0f, 1.0f, 1.0f};
        if(this.sides.size() <= 1)
        {
            this.center.draw(gl);
        }
        else 
        {
            for(Vector v: this.sides)
            {
                Utils.drawLine(gl, v.getStartPoint(), v.getEndPoint(), color);
            }
        }
    }

    public void setVelocity(Vector c)
    {
        this.center.setVelocity(c);
        for(Point p: this.points)
        {
            p.setVelocity(c);
        }
    }

    public void setCenter(Point center)
    {
        this.center = center;
    }

    public void increaseArea(float amount)
    {
        this.radius = this.radius * amount * amount;
        this.points = this.generatePoints(this.points.length, this.center, this.radius, this.startAngle);
        for(Point p: this.points)
        {
            p.setVelocity(this.center.getVelocity());
        }
    }

    public void copyState(Polygon p)
    {
        this.center = p.center;
        this.radius = p.getRadius();
        this.points = this.generatePoints(this.points.length, this.center, this.radius, this.startAngle);
        this.setVelocity(p.center.getVelocity());
    }

    public float getRadius()
    {
        return this.radius;
    }

    public void update()
    {
        this.center.update();
        if(this.points.length > 1)
        {
            for(Point p: this.points)
            {
                p.update();
            }
        }
        this.sides = this.updateVectors();
    }

    public Vector collision(Vector v)
    {
        for(Vector in_v: this.sides)
        {
            if(Line2D.linesIntersect(in_v.getStartPoint().getX(), 
                                     in_v.getStartPoint().getY(), 
                                     in_v.getEndPoint().getX(), 
                                     in_v.getEndPoint().getY(), 
                                     v.getStartPoint().getX(), 
                                     v.getStartPoint().getY(), 
                                     v.getEndPoint().getX(), 
                                     v.getEndPoint().getY()))
                                     {
                                         return in_v;
                                     }
        }
        return null;
    }

    public ArrayList<Vector> getSides()
    {
        return this.sides;
    }

    public Vector collision(Polygon p)
    {
        if(p.getSides().size() <= 1)
        {
            return this.collision(p.center.getPointVector());
        }
        for(Vector in_v: this.sides)
        {
            for(Vector v: p.getSides())
            {
                if(Line2D.linesIntersect(in_v.getStartPoint().getX(), 
                                        in_v.getStartPoint().getY(), 
                                        in_v.getEndPoint().getX(), 
                                        in_v.getEndPoint().getY(), 
                                        v.getStartPoint().getX(), 
                                        v.getStartPoint().getY(), 
                                        v.getEndPoint().getX(), 
                                        v.getEndPoint().getY()))
                                        {
                                            return in_v;
                                        }
            }
        }
        return null;
    }

    protected Point[] generatePoints(int numPoints, Point center, float radius, float startAngle)
    {
        if(numPoints == 1)
        {
            return new Point[]{ center };
        }
        final float FULL_CIRC = 360f;
		final float RADIUS = radius;
		float skipDegree = FULL_CIRC / numPoints;
		Point[] points = new Point[numPoints];
		int count = 0;
		for(float i = startAngle; i < FULL_CIRC + startAngle; i+= skipDegree)
		{
			double x =  center.getFloatX() + Math.cos(Math.toRadians(i))*RADIUS;
			double y = center.getFloatY() + Math.sin(Math.toRadians(i))*RADIUS;
			Point p = new Point((float)x, (float)y);
			points[count] = p;
			count ++;
		}
        return points;
    }
}
