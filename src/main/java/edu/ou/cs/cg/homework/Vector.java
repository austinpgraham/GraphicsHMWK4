package edu.ou.cs.cg.homework;


class Vector
{
    public float x;
    public float y;

    private float magnitude;
    private Point start;
    private Point end;

    private float calcMagnitude()
    {
        return (float)(Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2)));
    }

    public Vector(float x, float y)
    {
        this.x = x;
        this.y = y;
        this.magnitude = this.calcMagnitude();
        this.start = null;
        this.end = null;
    }

    public Vector(Point start, Point end)
    {
        this.start = start;
        this.end = end;
        this.x = end.getFloatX() - start.getFloatX();
        this.y = end.getFloatY() - start.getFloatY();
        this.magnitude = this.calcMagnitude();
    }

    public float getMagnitude()
    {
        return this.magnitude;
    }

    public void setX(float x)
    {
        this.x = x;
        this.magnitude = this.calcMagnitude();
    }

    public void setY(float y)
    {
        this.y = y;
        this.magnitude = this.calcMagnitude();
    }

    public void increaseMagnitude(float amount)
    {
        this.x *= amount;
        this.y *= amount;
        this.magnitude = this.calcMagnitude();
    }

    public Point getStartPoint()
    {
        return this.start;
    }

    public Point getEndPoint()
    {
        return this.end;
    }

    public Vector reflected(Vector normal)
    {
        float _dot = Vector.dot(this, normal);
        Vector scaledNormal = Vector.scale(normal, _dot*2);
        return Vector.subtract(this, scaledNormal);
    }

    public Vector getNormal()
    {
        return new Vector(-this.y / this.magnitude, this.x / this.magnitude);
    }

    public static float dot(Vector v1, Vector v2)
    {
        return (v1.x*v2.x) + (v1.y*v2.y);
    }
    
    public static Vector subtract(Vector v1, Vector v2)
    {
        float x = v1.x - v2.x;
        float y = v1.y - v2.y;
        return new Vector(x, y);
    }

    public static Vector scale(Vector v, float amount)
    {
        return new Vector(v.x*amount, v.y*amount);
    }
}