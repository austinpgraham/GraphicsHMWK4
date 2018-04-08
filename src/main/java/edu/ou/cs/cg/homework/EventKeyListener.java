/**
 * Author: Austin Graham
 */
package edu.ou.cs.cg.homework;

import java.util.ArrayList;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

/**
 * Implementation of the key listener
 * for the canvas object
 */
class EventKeyListener implements KeyListener {

    // List of container polygons
    PolygonCollection polygons;

    // List of collections of pointers
    ArrayList<PolygonCollection> bouncers;

    /**
     * Construct the listener
     *
     * @param collection: container collection
     * @param bouncers: collection of pointers
     */
    public EventKeyListener(PolygonCollection collection, ArrayList<PolygonCollection> bouncers)
    {
        this.polygons = collection;
        this.bouncers = bouncers;
    }

    /**
     * Fires when a key on the keyboard is released.
     * If arrow keys are pressed, adjust area/velocity
     * appropriately. 1-4 changes conatiners, and 6-9 changes
     * all pointers keeping state from original pointer
     *
     * @param e: Key event causing the method to run
     */
    public void keyReleased(KeyEvent e)
    {
        for(PolygonCollection pc: this.bouncers)
        {
            Polygon old;
            switch(e.getKeyCode())
            {
                    case KeyEvent.VK_RIGHT:
                        pc.getFocusedPolygon().center.alterVelocity(1.1f);
                        break;
                    case KeyEvent.VK_LEFT:
                        pc.getFocusedPolygon().center.alterVelocity(0.9f);
                        break;
                    case KeyEvent.VK_UP:
                        pc.getFocusedPolygon().increaseArea(1.1f);
                        break;
                    case KeyEvent.VK_DOWN:
                        pc.getFocusedPolygon().increaseArea(0.9f);
                        break;
                    case KeyEvent.VK_1:
                        this.polygons.setFocused(0);
                        pc.getFocusedPolygon().center.reset(this.polygons.getFocusedPolygon().center);
                        break;
                    case KeyEvent.VK_2:
                        this.polygons.setFocused(1);
                        pc.getFocusedPolygon().center.reset(this.polygons.getFocusedPolygon().center);
                        break;
                    case KeyEvent.VK_3:
                        this.polygons.setFocused(2);
                        pc.getFocusedPolygon().center.reset(this.polygons.getFocusedPolygon().center);
                        break;
                    case KeyEvent.VK_4:
                        this.polygons.setFocused(3);
                        pc.getFocusedPolygon().center.reset(this.polygons.getFocusedPolygon().center);
                        break;
                    case KeyEvent.VK_6:
                        old = pc.getFocusedPolygon();
                        pc.setFocused(0);
                        pc.getFocusedPolygon().copyState(old);
                        break;
                    case KeyEvent.VK_7:
                        old = pc.getFocusedPolygon();
                        pc.setFocused(1);
                        pc.getFocusedPolygon().copyState(old);
                        break;
                    case KeyEvent.VK_8:
                        old = pc.getFocusedPolygon();
                        pc.setFocused(2);
                        pc.getFocusedPolygon().copyState(old);
                        break;
                    case KeyEvent.VK_9:
                        old = pc.getFocusedPolygon();
                        pc.setFocused(3);
                        pc.getFocusedPolygon().copyState(old);
                        break;
            }
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