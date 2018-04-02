/**
 * Author: Austin Graham
 * 
 * Offers simple extention of Point2D 
 * class to avoid having to cast the 
 * coordinates all the time
 */
package edu.ou.cs.cg.homework;

import java.awt.geom.Point2D;
import javax.media.opengl.*;

public class Point extends Point2D.Float
{
    Vector velocity = new Vector(0.001f, 0.001f);

    public Point(float x, float y)
    {
        super(x, y);
    }

    public float getFloatY()
    {
        return (float)this.getY();
    }

    public float getFloatX()
    {
        return (float)this.getX();
    }

    public void setFloatX(float x)
    {
        this.x = x;
    }

    public void setFloatY(float y)
    {
        this.y = y;
    }

    public void alterVelocity(float amount)
    {
        this.velocity.increaseMagnitude(amount);
    }

    public void draw(GL2 gl)
    {
        Utils.drawPoint(gl, this);
    }

    public void update()
    {
        this.setFloatX(this.x + this.velocity.x);
        this.setFloatY(this.y + this.velocity.y);
    }
}