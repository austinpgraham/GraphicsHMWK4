/**
 * Author: Austin Graham
 */
package edu.ou.cs.cg.homework;

import java.util.ArrayList;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

/**
 * Implements a mouse listener to spawn new polygons
 */
class EventMouseListener implements MouseListener
{
    // Polygon collections of pointers
    private ArrayList<PolygonCollection> cols;

    /**
     * Create listener
     *
     * @param c: The list of pointers
     */
    public EventMouseListener(ArrayList<PolygonCollection> c)
    {
        this.cols = c;
    }

    /**
     * Translate x,y point to GL coordinates
     * @param x: X coordinate
     * @param y: Y coordinage
     */
    private Point translateToGL(float x, float y)
    {
        float glX = 2*x / 550f - 1f;
        float glY = 2*y / 550f - 1f;
        return new Point(glX, -glY);
    }

    /**
     * If the mouse button is released
     */
    public void mouseReleased(MouseEvent e) 
    {
        Polygon ex = this.cols.get(0).getFocusedPolygon();
        Point glPoint = this.translateToGL(e.getX(), e.getY());
        float radius = ex.getRadius();
        PolygonCollection pc = new PolygonCollection();
        pc.addPolygon(new Polygon(glPoint, radius));
		pc.addPolygon(new Polygon(4, glPoint, radius, 45f));
		pc.addPolygon(new Polygon(8, glPoint, radius, 0f));
        pc.addPolygon(new DistortedPointerPolygon(5, glPoint, radius, 90f));
        pc.setFocused(this.cols.get(0).getFocused());
        this.cols.add(pc);
    }

    /**
     * If mouse button is pressed
     */
    public void mousePressed(MouseEvent e) 
    {
        // Not implemented
    }

    /**
     * If the mouse is clicked
     */
    public void mouseClicked(MouseEvent e) 
    {
        // Not implemented
    }

    public void mouseExited(MouseEvent e) 
    {
        // Not implemented
    }

    public void mouseEntered(MouseEvent e) 
    {
        // Not implemented
    }
}