/**
 * Author: Austin Graham
 */
package edu.ou.cs.cg.homework;

/**
 * Calculates a distorted polygon
 */
class DistortedContainerPolygon extends Polygon
{
    /**
     * Creates a polygon,
     * with overloaded generate points 
     * to alter the sides
     */
    public DistortedContainerPolygon(int numPoints, Point center, float radius, float startAngle)
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
        float offsetX = 0.0f;
		for(float i = startAngle; i < FULL_CIRC + startAngle; i+= skipDegree)
		{
			double x =  center.getFloatX() + offsetX + Math.cos(Math.toRadians(i))*RADIUS;
			double y = center.getFloatY() + Math.sin(Math.toRadians(i))*RADIUS;
			Point p = new Point((float)x, (float)y);
			points[count] = p;
            if(count == 6)
            {
                offsetX = 0.3f;
            }
            else if(count == 7)
            {
                points[count] = new Point(p.getFloatX() - radius * 0.2f, p.getFloatY() - radius * 0.2f);
            }
            else if(count == 8)
            {
                points[count] = new Point(p.getFloatX() - radius * 0.2f, p.getFloatY() - radius * 0.6f);
            }
            else if(count == 9)
            {
                points[count] = new Point(p.getFloatX() - radius * 0.3f, p.getFloatY() - radius * 0.2f);
            }
			count ++;
		}
        return points;
    }
}