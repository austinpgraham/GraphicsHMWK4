package edu.ou.cs.cg.homework;


class Vector
{
    float magnitude;
    public float x;
    public float y;

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
}