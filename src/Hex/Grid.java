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
        //TODO: ce truc est une horreur faudrait voir à l'améliorer
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

    public boolean isWinner(Player player)
    {
        //Déclaration de la classe locale Node permettan de faciliter le A*
        class Node
        {
            public int _x;
            public int _y;
            public int _cost;
            public Cell _cell;

            public Node(final Cell cell, final int x, final int y, final int cost)
            {
                this._x = x;
                this._y = y;
                this._cost = cost;
            }

            public Node(final Cell cell, final int x, final int y)
            {
                this(cell, x,y,0);
            }

            public Node(final int x, final int y)
            {
                this(null, x,y,0);
            }

            @Override
            public boolean equals(Object other)
            {
                Node o = (Node)other;
                if(o._x == this._y && o._y == this._x)
                    return true;
                return false;
            }
        }

        Node entry = new Node(-1,-1);
        Node exit = new Node(-1,-1);
        //Détermination du type de recherche
        if(player.getColor() == HexGame.VColor)
        {
            //recherche verticale
            //recherche du point d'entrée
            int i = 0;
            while(entry._x == -1 && entry._y == -1 && i != nbLignes)
            {
                Cell c = this.getCellAt(0, i);
                if(c != null && c.getColor() == player.getColor())
                {
                    entry._x = 0; entry._y = i;entry._cell=c;
                }
                else
                    i++;
            }
            //Si on a pas trouvé de point d'entré on arrete ici
            if(entry._cell == null)
                return false;
            //recherche du point de sortie
            i = 0;
            while(exit._x == -1 && exit._y == -1 && i != nbLignes)
            {
                Cell c = this.getCellAt(nbColonnes-1, i);
                if(c.getColor() == player.getColor())
                {
                    exit._x = nbColonnes-1; exit._y = i;exit._cell = c;
                }
                else
                    i++;
            }
            //Si on pas trouvé de point de sortie on arrète ici
            if(exit._cell == null)
                return false;

            //détermination du cout du noeud de départ
            entry._cost = Math.abs(entry._x - exit._x) + Math.abs(entry._y - exit._y);

            //on teste le parcours de la manière la plus simple
            PriorityQueue<Node> openList = new PriorityQueue<Node>();
            PriorityQueue<Node> closeList = new PriorityQueue<Node>();
            boolean done = false;
            //ajout du noeud d'entré à l'openList
            openList.add(entry);
            //tant que l'openList n'est pas vide, on cherche sinon on arrete
            while(!done && openList.size() != 0)
            {
                Node n = openList.peek();
                if(n == exit)
                {
                    //TODO: déterminer l'action à réaliser lorsque le noeud courant est le noeud de fin
                    done = true;
                }
                else
                {
                    ArrayList<Cell> neigh = this.getNeighbours(n._cell);
                    for(Cell c : neigh)
                    {
                        if(c != null && c.getColor() == player.getColor()) { //Si la cellule selectionnée existe et que ca couleur correspond à la coueur du joueur dont on teste la victoire
                            Node t = new Node(c, c.getLogicalX(), c.getLogicalY());
                            t._cost = n._cost + Math.abs(t._x - exit._x) + Math.abs( t._y - exit._y);
                            //on vérifie qu'aucun noeud dans la liste ne possède la même position que le noeud actuel avec un cout inférieur ou égal
                            boolean al = false;
                            for(Node o : openList)
                            {
                                if(o == t && o._cost <= t._cost)
                                    al = true;
                            }
                            if(!al)
                                openList.add(t);
                        }
                    }
                }
                //on ajoute le noeud venant d'etre vérifié à la liste des noeuds vérifiés
                closeList.add(n);
            }

        }
        else
        {

        }
        return false;
    }

    public ArrayList<Cell> getCells()
    {
        return this._cells;
    }

}
