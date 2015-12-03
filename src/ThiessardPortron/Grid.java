/**
 * Grid.java
 * Hugo Thiessard
 * Clovis Portron
 */

package ThiessardPortron;

import java.awt.*;
import java.util.ArrayList;

public class Grid {
    private ArrayList<Cell> _cells;
    private ArrayList<Group> _groups;

    public Grid(final int xpos, final int ypos, final int lines, final int rows)
    {
        int ytmp;
        this._cells = new ArrayList<>();
        this._groups = new ArrayList<>();
        for(int i = 0; i != lines; i++)
        {
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

    /*
    empty
    Vide la grille et la fait tournée à son état de départ
     */
    public void empty()
    {
        this._groups = new ArrayList<>();
        for(Cell c : this._cells)
        {
            c.reset(Color.lightGray);
        }
    }

    /*
    getCellAt
    Retourne la cellule au point physique xy passé en paramètre
     */
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

    /*
    getNeightbours
    Retourne les voisins de la cellule passée en paramètre
     */
    public ArrayList<Cell> getNeighbours(final Cell origin) throws IllegalArgumentException
    {
        if(origin == null)
            throw new IllegalArgumentException("Origin ne peux être nulle.");
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

    /*
    putCell
    Gère les actions à réaliser lorsque la cellule cell passée en paramètre est "posée" sur le plateau
     */
    public void putCell(Cell cell) throws IllegalArgumentException
    {
        if(cell == null) {
            throw new IllegalArgumentException("Cell ne peux être nulle.");
        }
        ArrayList<Cell> open = new ArrayList<>();
        //on fait en sorte que toutes les cellules adjacentes de même couleur soit du même groupe
        //on parcours tout les voisins de la cellule a placer et on les ajoutent à l'open liste
        for(Cell other : this.getNeighbours(cell))
        {
            if(other.getColor() == cell.getColor()) //même si les vérifications de couleurs sont faites dans le groupe, on veut ici en priorité les cellules de même couleur que celle qui vient d'etre posé
            {
                open.add(other);
            }
        }

        //création d'un nouveau groupe pour tous, pas d'amalgame
        Group g = new Group();
        //on propage ce group eà travers les cellules de l'openList tant que celle-ci n'est pas vide
        while(!open.isEmpty())
        {
            Cell first = open.get(0);
            //Si le groupe ne contient pas déjà la première cellule de l'openList (pour éviter de faire la même opération deux fois)
            if(!g.contains(first)) {
                //on ajoute first au groupe
                first.setGroup(g);

                //puis on ajoute à l'open liste les voisins de ce  voisins
                ArrayList<Cell> others = this.getNeighbours(first);
                for (Cell other : others) {
                    //on check que ces noeuds ne sont pas deja dans le groupe
                    if (!g.contains(other) && other.getColor() == cell.getColor()) {
                        open.add(other);
                    }
                }
            }
            //on supprime maintenant le noeud actuel
            open.remove(first);
        }
        this._groups.add(g);
    }

    /*
    isWinner
    retourne si le joueur passé en paramètre a gagné la partie
     */
    public boolean isWinner(final Player player) throws IllegalArgumentException
    {
        if(player == null)
            throw new IllegalArgumentException("player ne peux être nul.");
        //on créer une liste des groupes à supprimer à la fin des opération
        //On supprime un groupe si celui-ci est vide
        ArrayList<Group> toDelete = new ArrayList<>();
        //variable de début et de fin
        boolean start, end;
        start = false; end = false;

        if(player.getColor() == HexGame.VColor)
        {
            for(Group g : this._groups)
            {
                //si le groupe est vide on l'ajoute a la liste de groupe à supprimer
                if(g.isEmpty()) {
                    toDelete.add(g);
                    continue;
                }
                if(g.getColor() == HexGame.VColor)
                {
                    //on parcours les cellules si l'une est au début d'une colonne, start passe a vrai, en fin, end passe a vrai
                    for(Cell c : g.getCells())
                    {
                        if(c.getLogicalX() == 0)
                            start = true;
                        else if(c.getLogicalX() == HexGame.Side -1)
                            end = true;
                    }
                    //Si start et end sont à vrai, alors, le groupe complète une colonne est le joueur blanc à gagné
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
            toDelete.forEach(this._groups::remove);
            return false;
        }
        else
        {
            for(Group g : this._groups)
            {
                //si le groupe est vide on l'ajoute a la liste de groupe à supprimer
                if(g.isEmpty()) {
                    toDelete.add(g);
                    continue;
                }
                if(g.getColor() == HexGame.HColor)
                {
                    //on parcours les cellules si l'une est au début d'une ligne, start passe a vrai, en fin, end passe a vrai
                    for(Cell c : g.getCells())
                    {
                        if(c.getLogicalY() == 0)
                            start = true;
                        else if(c.getLogicalY() == HexGame.Side -1)
                            end = true;
                    }
                    //Si start et end sont à vrai, alors, le groupe complète une colonne est le joueur blanc à gagné
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
            toDelete.forEach(this._groups::remove);
            return false;
        }
    }

    /*
    getCells
    Retourne la listes des cellules présentent dans la drogue
     */
    public ArrayList<Cell> getCells()
    {
        return this._cells;
    }

}
