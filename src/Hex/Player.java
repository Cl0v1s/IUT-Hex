package Hex;

import java.awt.*;

/**
 * Created by Clovis on 10/10/2015.
 */
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

    public Color getColor()
    {
        return this._color;
    }


}
