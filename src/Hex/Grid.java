package Hex;

import javafx.geometry.Point2D;

import java.awt.*;
import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Created by Clovis on 10/10/2015.
 */
public class Grid {
    private ArrayList<Cell> _cells;
    private ArrayList<Group> _groups;
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
                //d�termination de la position visuelle de la cellule dans la grille
                Cell cell = new Cell(i,u,xg,yg, Color.lightGray);
                this._cells.add(cell);

            }
        }
    }

    public Cell getCellAt(final int x, final int y)
    {
        int i = 0;
        boolean done = false;
        while(!done && i != this._cells.size())
        {
            if(this._cells.get(i).getLogicalX() == x && this._cells.get(i).getLogicalY() == y)
                done = true;
            else
                i++;
        }
        if(done)
            return this._cells.get(i);
        else
            return null;
    }

    public ArrayList<Cell> getNeighbours(Cell origin)
    {
        //TODO: ce truc est une horreur faudrait voir � l'am�liorer
        ArrayList<Cell> l = new ArrayList<Cell>();
        //top-left
        l.add(getCellAt(origin.getLogicalX()-1, origin.getLogicalY()-1));
        //top
        l.add(getCellAt(origin.getLogicalX(), origin.getLogicalY()-1));
        //top-right
        l.add(getCellAt(origin.getLogicalX()+1, origin.getLogicalX()-1));
        //right
        l.add(getCellAt(origin.getLogicalX()+1, origin.getLogicalY()));
        //bot-right
        l.add(getCellAt(origin.getLogicalX()+1, origin.getLogicalY()+1));
        //bot
        l.add(getCellAt(origin.getLogicalX(), origin.getLogicalY()+1));
        //bot-left
        l.add(getCellAt(origin.getLogicalX()-1, origin.getLogicalY()+1));
        //left
        l.add(getCellAt(origin.getLogicalX()-1, origin.getLogicalY()));

        return l;
    }

    public void putCell(Cell c)
    {
        //on fait en sorte sue toutes les cellules adjacentes de même couleur soit du même groupe
        ArrayList<Cell> neigh = this.getNeighbours(c);
        Group g = neigh.get(0).getGroup();
        for(Cell v : neigh)
        {
            if(v.getColor() == c.getColor())
                v.setGroup(g);
        }
        //on vérifie que certains groups ne sont pas inutiles
        for(int i = 0; i!=this._groups.size();)
        {
            if(this._groups.get(i).getSize() == 0)
                this._groups.remove(i);
            else
                i++;
        }
    }

    public boolean isWinner(Player player)
    {
        return false;
    }

    public ArrayList<Cell> getCells()
    {
        return this._cells;
    }

}
