/**
 * Cell.java
 * Hugo Thiessard
 * Clovis Portron
 */

package ThiessardPortron;

import java.awt.*;

public class Cell {

    //diamètre d'une cellule hexagonale
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

    /*
    setColor
    définit la couleur de la cellule
     */
    public void setColor(final Color color) throws IllegalArgumentException
    {
        if(color == null)
            throw new IllegalArgumentException("Color ne peut pas être nul.");
        this._color = color;
    }

    /*
    reset
    rend de nouveau la cellule "neutre"
     */
    public void reset(final Color color)
    {
        this._owned = false;
        this._color = color;
    }

    /*
    getLogicalX
    retourne la position logique X de la cellule
     */
    public int getLogicalX()
    {
        return this._xLog;
    }

    /*
    getLogicalY
    retourne la position logique Y de la cellule
     */
    public int getLogicalY()
    {
        return this._yLog;
    }

    /*
    contains
    Retourne si la cellule contient le point au coordonnées x y transmises
     */
    public boolean contains(final int x,final int y) {
        int distance = Math.abs(this._xGra + rad - x) + Math.abs(this._yGra + rad - y);
        return distance <= rad && !this._owned;
    }

    /*
    onClick
    Définit l'action de la cellule en l'associant au joueur passé en paramètre
     */
    public void onClick(final Player player)
    {
        this._color = player.getColor();
        this._owned = true;
    }

    /*
    getColor
    retourne la couleur actuelle de la cellule
     */
    public Color getColor()
    {
        return this._color;
    }

    /*
    getPolygon
    retourne la forme logique de la celluel
     */
    public Polygon getPolygon()
    {
        return this._polygon;
    }

    /*
    setGroup
    définie le groupe de la cellule, et l'associe à ce dernier
     */
    public void setGroup(Group group)
    {
        //Si cette cellule avait deja un groupe, on la supprime de ce groupe
        if(this._group != null)
            this._group.remove(this);
        this._group = group;
        group.add(this);
    }
}
