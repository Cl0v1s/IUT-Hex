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
    Ajoute la cellule pass�e en param�tre au groupe
     */
    public void add(final Cell cell) throws IllegalArgumentException
    {
        if(cell == null)
            throw new IllegalArgumentException("cell ne peut �tre nul.");
        if (_cells.isEmpty()) {
            _color = cell.getColor();
        }
        else if(cell.getColor() != _color)
            return;
        if(!this._cells.contains(cell))
            this._cells.add(cell);
    }

    /*
    remove
    Supprime la cellule pass�e en param�tre du groupe
     */
    public void remove(final Cell cell) throws IllegalArgumentException
    {
        if(cell == null)
            throw new IllegalArgumentException();
        this._cells.remove(cell);
    }

    /*
    contains
    retourne vrai si la cellule pass�e en param�tre est contenu dans le groupe
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
    getCells
    Retourne la liste des cellules contenuent par le groupe
     */
    public ArrayList<Cell> getCells()
    {
        return this._cells;
    }

    /*
    Retourne le couleur associ�e aux cellules du groupe
     */
    public Color getColor() {
        return _color;
    }
}
