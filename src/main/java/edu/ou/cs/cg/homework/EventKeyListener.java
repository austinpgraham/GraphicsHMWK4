package edu.ou.cs.cg.homework;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

class EventKeyListener implements KeyListener {

    Point ctrlPoint;

    public EventKeyListener(Point p)
    {
        this.ctrlPoint = p;
    }

    public void keyReleased(KeyEvent e)
    {
        switch(e.getKeyCode())
        {
            case KeyEvent.VK_UP:
                this.ctrlPoint.alterVelocity(1.1f);
                break;
            case KeyEvent.VK_DOWN:
                this.ctrlPoint.alterVelocity(0.9f);
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