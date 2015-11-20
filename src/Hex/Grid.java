package Hex;

import java.awt.*;
import java.util.ArrayList;

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
        this._cells = new ArrayList<>();
        this._groups = new ArrayList<>();
        for(int i = 0; i != lines; i++)
        {
            ytmp = 0;
            for(int u = 0; u != rows; u++)
            {
                ytmp = u*Cell.rad*2;
                int xg, yg;
                xg = xpos+(u * (Cell.rad*2));
                yg = ypos+u*Cell.rad - ytmp + (i*(Cell.rad*2));
                //d�termination de la position visuelle de la cellule dans la grille
                Cell cell = new Cell(i,u,xg,yg, Color.lightGray);
                this._cells.add(cell);

            }
        }
    }

    public void empty()
    {
        this._groups = new ArrayList<>();
        for(Cell c : this._cells)
        {
            c.reset(Color.lightGray);
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
        ArrayList<Cell> l = new ArrayList<>();
        Cell tmp;
        //top-left
        tmp = getCellAt(origin.getLogicalX()-1, origin.getLogicalY()-1);
        if(tmp != null)
            l.add(tmp);
        //top
        tmp = getCellAt(origin.getLogicalX(), origin.getLogicalY()-1);
        if(tmp != null)
            l.add(tmp);
        //top-right
        tmp = getCellAt(origin.getLogicalX()+1, origin.getLogicalY());
        if(tmp != null)
            l.add(tmp);
        //bot-right
        tmp = getCellAt(origin.getLogicalX()+1, origin.getLogicalY()+1);
        if(tmp != null)
            l.add(tmp);
        //bot
        tmp = getCellAt(origin.getLogicalX(), origin.getLogicalY()+1);
        if(tmp != null)
            l.add(tmp);
        //bot-left
        tmp = getCellAt(origin.getLogicalX()-1, origin.getLogicalY());
        if(tmp != null)
            l.add(tmp);
        return l;
    }

    public void putCell(Cell c)
    {
        //on fait en sorte sue toutes les cellules adjacentes de même couleur soit du même groupe
        ArrayList<Cell> neigh = this.getNeighbours(c);
        int i = 0;
        while (neigh.get(i).getColor() != c.getColor()) {
            i++;
            if (i == neigh.size()) {
                return;
            }
        }
        Group g = neigh.get(i).getGroup();
        //on vide le groupe afin d'éviter les doublons
        if(g == null) {
            g = new Group();
            this._groups.add(g);
        }
        for(Cell n : neigh)
        {
            if(n.getColor() == c.getColor() && n.getGroup() != g) {
                if (n.getGroup() != null) {
                    for (Cell o : n.getGroup().getCells()) {
                        o.setGroup(g);
                    }
                    _groups.remove(g);
                }
                n.setGroup(g);
            }
        }
        //on ajoute au groupe la cellule qu'on vient d'ajouter
        c.setGroup(g);
    }

    public boolean isWinner(Player player)
    {
        Boolean start = false;
        Boolean end = false;
        if(player.getColor() == HexGame.HColor)
        {
            for (Group group : this._groups) {
                if (group.getColor() != player.getColor()) {
                    continue;
                }
                for (int j = 0; j < group.getSize(); j++) {
                    if (group.getCell(j).getLogicalX() == 0) {
                        start = true;
                    }
                    if (group.getCell(j).getLogicalX() == HexGame.Side-1) {
                        end = true;
                    }
                    if (start && end) {
                        for(Cell c : group.getCells())
                            c.setColor(Color.GREEN);
                        return true;
                    }
                }
            }
        } else {
            for (Group group : this._groups) {
                if (group.getColor() != player.getColor()) {
                    continue;
                }
                for (int j = 0; j < group.getSize(); j++) {
                    if (group.getCell(j).getLogicalY() == 0) {
                        start = true;
                    }
                    if (group.getCell(j).getLogicalY() == HexGame.Side-1) {
                        end = true;
                    }
                    if (start && end) {
                        for(Cell c : group.getCells())
                            c.setColor(Color.GREEN);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public ArrayList<Cell> getCells()
    {
        return this._cells;
    }

}
