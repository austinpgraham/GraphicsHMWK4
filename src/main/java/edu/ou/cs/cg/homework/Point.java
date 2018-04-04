/**
 * Author: Austin Graham
 * 
 * Offers simple extention of Point2D 
 * class to avoid having to cast the 
 * coordinates all the time
 */
package edu.ou.cs.cg.homework;

import java.util.Random;

import java.awt.geom.Point2D;
import javax.media.opengl.*;

public class Point extends Point2D.Float
{
    private Random rand = new Random();
    private Vector velocity;
    private final float TARGET_MAG = 0.01667f;

    public Point(float x, float y)
    {
        super(x, y);
        float xVel = (float)(rand.nextDouble()*2.0 - 1.0) * this.TARGET_MAG;
        float yVel = (float)Math.sqrt(Math.pow(this.TARGET_MAG, 2) - Math.pow(xVel, 2));
        yVel = (this.rand.nextInt(2) == 0) ? -yVel : yVel;
        this.velocity = new Vector(xVel, yVel);
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

    public static Vector subtract(Point one, Point two)
    {
        float x = two.getFloatX() - one.getFloatX();
        float y = two.getFloatY() - one.getFloatY();
        return new Vector(x, y);
    }
}