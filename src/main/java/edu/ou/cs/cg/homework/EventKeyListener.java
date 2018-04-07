package edu.ou.cs.cg.homework;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

class EventKeyListener implements KeyListener {

    PolygonCollection polygons;
    PolygonCollection bouncers;

    public EventKeyListener(PolygonCollection collection, PolygonCollection bouncers)
    {
        this.polygons = collection;
        this.bouncers = bouncers;
    }

    public void keyReleased(KeyEvent e)
    {
        Polygon old;
        switch(e.getKeyCode())
        {
            case KeyEvent.VK_RIGHT:
                this.bouncers.getFocusedPolygon().center.alterVelocity(1.1f);
                break;
            case KeyEvent.VK_LEFT:
                this.bouncers.getFocusedPolygon().center.alterVelocity(0.9f);
                break;
            case KeyEvent.VK_UP:
                this.bouncers.getFocusedPolygon().increaseArea(1.1f);
                break;
            case KeyEvent.VK_DOWN:
                this.bouncers.getFocusedPolygon().increaseArea(0.9f);
                break;
            case KeyEvent.VK_1:
                this.polygons.setFocused(0);
                bouncers.getFocusedPolygon().center.reset(this.polygons.getFocusedPolygon().center);
                break;
            case KeyEvent.VK_2:
                this.polygons.setFocused(1);
                bouncers.getFocusedPolygon().center.reset(this.polygons.getFocusedPolygon().center);
                break;
            case KeyEvent.VK_3:
                this.polygons.setFocused(2);
                bouncers.getFocusedPolygon().center.reset(this.polygons.getFocusedPolygon().center);
                break;
            case KeyEvent.VK_4:
                this.polygons.setFocused(3);
                bouncers.getFocusedPolygon().center.reset(this.polygons.getFocusedPolygon().center);
                break;
            case KeyEvent.VK_6:
                old = this.bouncers.getFocusedPolygon();
                this.bouncers.setFocused(0);
                this.bouncers.getFocusedPolygon().copyState(old);
                break;
            case KeyEvent.VK_7:
                old = this.bouncers.getFocusedPolygon();
                this.bouncers.setFocused(1);
                this.bouncers.getFocusedPolygon().copyState(old);
                break;
            case KeyEvent.VK_8:
                old = this.bouncers.getFocusedPolygon();
                this.bouncers.setFocused(2);
                this.bouncers.getFocusedPolygon().copyState(old);
                break;
            case KeyEvent.VK_9:
                old = this.bouncers.getFocusedPolygon();
                this.bouncers.setFocused(3);
                this.bouncers.getFocusedPolygon().copyState(old);
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