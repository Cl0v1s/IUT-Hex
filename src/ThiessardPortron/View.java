/**
 * View.java
 * Hugo Thiessard
 * Clovis Portron
 */

package ThiessardPortron;

import java.awt.*;
import java.util.ArrayList;

public class View extends Canvas {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HexGame _game;

    public View(HexGame game)
    {
        this._game = game;
        this.setBackground(Color.GRAY);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.WHITE);
        g.drawString("Regles:", 0,10);
        g.drawString("Le joueur blanc doit remplir la grille verticalement.", 0,20);
        g.drawString("Le joueur noir doit remplir la grille horizontalement.", 0,30);

        ArrayList<Cell> cells = this._game.getGrid().getCells();
        for(int i = 0; i!= cells.size(); i++)
        {
            g.setColor(cells.get(i).getColor());
            g.fillPolygon(cells.get(i).getPolygon());
        }
        if(this._game.isDone())
        {
            String color = "Blanc";
            if(this._game.getCurrentPlayer().getColor() != Color.white)
                color= "Noir";
            g.setColor(Color.GREEN);
            g.drawString("Le joueur "+color+" a gagne.", 0,50);
            g.setColor(Color.WHITE);
            g.drawString("Cliquez sur une cellule pour relancer une partie", 0,70);
        }
    }


}
