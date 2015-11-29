/**
 * Player.java
 * Hugo Thiessard
 * Clovis Portron
 */
package Hex;

import java.awt.*;

public class Player {
    public static int ID;

    private Color _color;
    private int _id;

    public Player(final Color color)
    {
        this._color = color;
        _id = ID;
        ID++;
    }

    /*
    getColor
    retourne la couleur du joueur
     */
    public Color getColor()
    {
        return this._color;
    }


}
