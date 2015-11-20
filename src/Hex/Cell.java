package Hex;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by cportron on 08/10/15.
 */
public class Cell {

    public final static int rad = 20;

    private int _xLog;
    private int _yLog;
    private Color _color;
    private int _xGra;
    private int _yGra;
    private Polygon _polygon;
    private boolean _owned;
    private Group _group;

    public Cell(final int xl,final int yl,final int xg,final int yg,final Color color)
    {
        this._owned = false;
        this._xLog = xl;
        this._yLog = yl;
        this._xGra = xg;
        this._yGra = yg;
        this._color = color;
        //définition du polygon
        this._polygon = new Polygon();
        double arc = Math.PI*2/ 6;
        for(int i = 0; i != 6 ;i++) {
            this._polygon.addPoint((int)Math.round(this._xGra + rad * Math.cos(arc * i))+rad, (int)Math.round(this._yGra + rad * Math.sin(arc * i))+rad);
        }

    }

    public void setColor(Color color)
    {
        this._color = color;
    }

    public void reset(Color color)
    {
        this._owned = false;
        this._color = color;
    }


    public int getLogicalX()
    {
        return this._xLog;
    }

    public int getLogicalY()
    {
        return this._yLog;
    }

    public boolean contains(int x, int y)
    {
        int distance = Math.abs(this._xGra+rad-x)+Math.abs(this._yGra+rad-y);
        if(distance <= rad) {
            return !this._owned;
        }
        return false;
    }

    public void onClick(final Player player)
    {
        this._color = player.getColor();
        this._owned = true;
    }

    public Color getColor()
    {
        return this._color;
    }

    public Polygon getPolygon()
    {
        return this._polygon;
    }

    public Group getGroup()
    {
        return _group;
    }

    public void setGroup(Group group)
    {
        this._group = group;
        group.add(this);

        String color;
        if (this.getColor() == HexGame.HColor) {
            color = "Noir";
        } else {
            color = "Blanc";
        }
    }
}
