/**
 * Player.java
 * Hugo Thiessard
 * Clovis Portron
 */
package ThiessardPortron;

import java.awt.*;

public class Player {

    private Color _color;

    public Player(final Color color)
    {
        this._color = color;
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
