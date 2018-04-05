package edu.ou.cs.cg.homework;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

class EventKeyListener implements KeyListener {

    Point ctrlPoint;
    PolygonCollection polygons;

    public EventKeyListener(Point p, PolygonCollection collection)
    {
        this.ctrlPoint = p;
        this.polygons = collection;
    }

    public void keyReleased(KeyEvent e)
    {
        switch(e.getKeyCode())
        {
            case KeyEvent.VK_RIGHT:
                this.ctrlPoint.alterVelocity(1.1f);
                break;
            case KeyEvent.VK_LEFT:
                this.ctrlPoint.alterVelocity(0.9f);
                break;
            case KeyEvent.VK_1:
                this.polygons.setFocused(0);
                ctrlPoint.reset(this.polygons.getFocusedPolygon().center);
                break;
            case KeyEvent.VK_2:
                this.polygons.setFocused(1);
                ctrlPoint.reset(this.polygons.getFocusedPolygon().center);
                break;
            case KeyEvent.VK_3:
                this.polygons.setFocused(2);
                ctrlPoint.reset(this.polygons.getFocusedPolygon().center);
                break;
            case KeyEvent.VK_4:
                break;
        }
    }

    public void keyTyped(KeyEvent e) 
    {
        // Not implemented
    }

    public void keyPressed(KeyEvent e) 
    {
        // Not implemented
    }
}