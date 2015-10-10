package Hex;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by cportron on 08/10/15.
 */
public class View extends Canvas {

    private HexGame _game;

    public View(HexGame game)
    {
        this._game = game;
        this.setBackground(Color.GRAY);
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        ArrayList<Cell> cells = this._game.getGrid().getCells();
        for(int i = 0; i!= cells.size(); i++)
        {
            g.setColor(cells.get(i).getColor());
            g.fillPolygon(cells.get(i).getPolygon());
        }
    }
}
