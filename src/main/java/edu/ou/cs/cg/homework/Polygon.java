
package edu.ou.cs.cg.homework;

import java.awt.geom.Line2D;
import java.util.ArrayList;
import javax.media.opengl.*;

class Polygon
{
    private ArrayList<Vector> sides;
    public Point center;

    public Polygon(Point center, Point... points)
    {
        this.center = center;
        this.sides = new ArrayList<Vector>();
        for(int i = 0; i < points.length - 1; i++)
        {
            Vector v = new Vector(points[i], points[i+1]);
            this.sides.add(v);
        }
        this.sides.add(new Vector(points[points.length - 1], points[0]));
    }

    public void draw(GL2 gl)
    {
        float[] color = {1.0f, 1.0f, 1.0f};
        for(Vector v: this.sides)
        {
            Utils.drawLine(gl, v.getStartPoint(), v.getEndPoint(), color);
        }
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
}
