package edu.ou.cs.cg.homework;

import java.util.ArrayList;

class PolygonCollection
{
    private ArrayList<Polygon> polygons;
    private int focused = 0;

    public PolygonCollection()
    {
        this.polygons = new ArrayList<Polygon>();
    }

    public void addPolygon(Polygon p)
    {
        this.polygons.add(p);
    }

    public void setFocused(int index)
    {
        this.focused = index;
    }

    public Polygon getFocusedPolygon()
    {
        return this.polygons.get(this.focused);
    }

    public ArrayList<Polygon> getPolygons()
    {
        return this.polygons;
    }

    public int getFocused()
    {
        return this.focused;
    }
}