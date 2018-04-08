/**
 * Author: Austin Graham
 */
package edu.ou.cs.cg.homework;

/**
 * Draws a distorted pointer polygon
 */
class DistortedPointerPolygon extends Polygon
{
    /**
     * Constructs a polygon
     */
    public DistortedPointerPolygon(int numPoints, Point center, float radius, float startAngle)
    {
        super(numPoints, center, radius, startAngle);
    }

    /**
     * Generate points altering them in special cases
     */
    @Override
    protected Point[] generatePoints(int numPoints, Point center, float radius, float startAngle)
    {
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
            if(count == 2)
            {
                points[count] = new Point(points[count - 1].getFloatX(), p.getFloatY() - radius*0.2f);
            }
            else if(count == 3)
            {
                points[count] = new Point(p.getFloatX() + radius * 1.0f, p.getFloatY() + radius*0.2f);
            }
			count ++;
		}
        return points;
    }
}