
package edu.ou.cs.cg.homework;

import java.util.ArrayList;
import javax.media.opengl.*;

class Polygon
{
    private ArrayList<Vector> sides;

    public Polygon(Point... points)
    {
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
}
