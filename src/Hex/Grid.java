package Hex;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Clovis on 10/10/2015.
 */
public class Grid {
    private ArrayList<Cell> _cells;
    private int nbLignes;
    private int nbColonnes;

    public Grid(final int xpos, final int ypos, final int lines, final int rows)
    {
        nbLignes = lines;
        nbColonnes = rows;
        int ytmp = 0;
        this._cells = new ArrayList<Cell>();
        for(int i = 0; i != lines; i++)
        {
            ytmp = 0;
            for(int u = 0; u != rows; u++)
            {
                ytmp = u*Cell.rad*2;
                int xg, yg;
                xg = xpos+(int)(u * (Cell.rad*2));
                yg = ypos+u*Cell.rad - ytmp + (i*(Cell.rad*2));
                //détermination de la position visuelle de la cellule dans la grille
                Cell cell = new Cell(i,u,xg,yg, Color.lightGray);
                this._cells.add(cell);

            }
        }
    }

    public ArrayList<Cell> getCells()
    {
        return this._cells;
    }

}
