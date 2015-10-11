package Hex;

import java.awt.*;

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
}
