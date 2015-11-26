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
        ArrayList<Cell> open = new ArrayList<Cell>();
        //on fait en sorte sue toutes les cellules adjacentes de même couleur soit du même groupe
        ArrayList<Cell> neigh = this.getNeighbours(c);
        for(Cell d : neigh)
        {
            if(d.getColor() == c.getColor()) //même si les vérifications de couleurs sont faites dans le groupe, on veut ici en priorité les cellules de même couleur que celle qui vient d'etre posé
                open.add(d);
        }

        //création d'un nouveau groupe pour tous, pas d'amalgame
        Group g = new Group();
        //on propage ce groupeà travers les cellules voisines
        while(!open.isEmpty())
        {
            Cell o = open.get(0);
            if(!g.contains(o)) {
                g.add(o);
                o.setGroup(g);

                //puis on ajoute à l'open liste les voisins de ce  voisins
                ArrayList<Cell> n = this.getNeighbours(o);
                for (Cell on : n) {
                    //on check que ces noeuds ne sont pas deja dans le groupe
                    if (!g.contains(on) && on.getColor() == c.getColor())
                        open.add(on);
                }
            }
            //on supprime maintenant le noeud actuel
            open.remove(o);
        }

        for(Cell o : g.getCells()) {
            System.out.println(g + " contient "+ o+"("+ o.getLogicalX()+","+o.getLogicalY()+")");
        }

        System.out.println("========");
        this._groups.add(g);
    }

    public boolean isWinner(Player player)
    {
        //on créer une liste des groupes à supprimer à la fin des opération
        //On supprime un groupe si celui-ci est vide
        ArrayList<Group> toDelete = new ArrayList<Group>();
        //variable de début et de fin
        boolean start, end;
        start = false; end = false;

        if(player.getColor() == HexGame.VColor)
        {
            for(Group g : this._groups)
            {
                if(g.isEmpty()) {
                    toDelete.add(g);
                    continue;
                }
                if(g.getColor() == HexGame.VColor)
                {
                    for(Cell c : g.getCells())
                    {
                        if(c.getLogicalX() == 0)
                            start = true;
                        else if(c.getLogicalX() == HexGame.Side -1)
                            end = true;
                    }
                    if(start && end)
                    {
                        //on colorie toutes les cellules du groupe en vert
                        for(Cell c : g.getCells())
                            c.setColor(Color.green);
                        return true;
                    }
                    //sinon on remet start et end à faux et on continue
                    start = false; end = false;
                }
            }
            //on nettoie les groupes vides et on retourne faux pour signaler que le joueur Vcolor n'a pas gagné
            for(Group g : toDelete)
                this._groups.remove(g);
            return false;
        }
        else
        {
            for(Group g : this._groups)
            {
                if(g.isEmpty()) {
                    toDelete.add(g);
                    continue;
                }
                if(g.getColor() == HexGame.HColor)
                {
                    for(Cell c : g.getCells())
                    {
                        if(c.getLogicalY() == 0)
                            start = true;
                        else if(c.getLogicalY() == HexGame.Side -1)
                            end = true;
                    }
                    if(start && end)
                    {
                        //on colorie toutes les cellules du groupe en vert
                        for(Cell c : g.getCells())
                            c.setColor(Color.green);
                        return true;
                    }
                    //sinon on remet start et end à faux et on continue
                    start = false; end = false;
                }
            }
            //on nettoie les groupes vides et on retourne faux pour signaler que le joueur Vcolor n'a pas gagné
            for(Group g : toDelete)
                this._groups.remove(g);
            return false;
        }
    }

    public ArrayList<Cell> getCells()
    {
        return this._cells;
    }

}
