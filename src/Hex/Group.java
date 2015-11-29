/**
 * Group.java
 * Hugo Thiessard
 * Clovis Portron
 */

package Hex;

import java.awt.*;
import java.util.ArrayList;

public class Group {
    private ArrayList<Cell> _cells;
    private Color _color;

    public Group()
    {
        this._cells = new ArrayList<>();
    }

    /*
    add
    Ajoute la cellule passée en paramètre au groupe
     */
    public void add(final Cell cell) throws IllegalArgumentException
    {
        if(cell == null)
            throw new IllegalArgumentException("cell ne peut être nul.");
        if (_cells.isEmpty()) {
            _color = cell.getColor();
        }
        else if(cell.getColor() != _color)
            return;
        if(cell != null && !this._cells.contains(cell))
            this._cells.add(cell);
    }

    /*
    empty
    Vide le groupe en le remettant à son etat de départ
     */
    public void empty()
    {
        for(int i = 0; i != this._cells.size();){
            this._cells.remove(i);
        }
        this._color = null;
    }

    /*
    remove
    Supprime la cellule passée en paramètre du groupe
     */
    public void remove(final Cell cell) throws IllegalArgumentException
    {
        if(cell == null)
            throw new IllegalArgumentException();
        this._cells.remove(cell);
    }

    /*
    contains
    retourne vrai si la cellule passée en paramètre est contenu dans le groupe
     */
    public Boolean contains(final Cell cell) throws IllegalArgumentException
    {
        if(cell == null)
            throw new IllegalArgumentException();
        return this._cells.contains(cell);
    }

    /*
    isEmpty
    Retourne si le groupe est vide
     */
    public boolean isEmpty()
    {
        return this._cells.isEmpty();
    }

    /*
    getSize
    Retourne le nombre de cellules présentent dans le groupe
     */
    public int getSize()
    {
        return this._cells.size();
    }

    /*
    getCell
    Retourne la cellule située dans la liste à l'index passé en paramètre
     */
    public Cell getCell(final int index) throws IllegalArgumentException
    {
        if(index < 0)
            throw new IllegalArgumentException();
        return this._cells.get(index);
    }

    /*
    getCells
    Retourne la liste des cellules contenuent par le groupe
     */
    public ArrayList<Cell> getCells()
    {
        return this._cells;
    }

    /*
    Retourne le couleur associée aux cellules du groupe
     */
    public Color getColor() {
        return _color;
    }
}
