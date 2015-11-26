package Hex;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by cportron on 12/10/15.
 */
public class Group {
    private ArrayList<Cell> _cells;
    private Color _color;

    public Group()
    {
        this._cells = new ArrayList<>();
    }

    public void add(Cell cell)
    {
        if (_cells.isEmpty()) {
            _color = cell.getColor();
        }
        else if(cell.getColor() != _color)
            return;
        if(cell != null && !this._cells.contains(cell))
            this._cells.add(cell);
    }

    public void empty()
    {
        for(int i = 0; i != this._cells.size();){
            this._cells.remove(i);
        }
    }

    public void remove(Cell cell)
    {
        this._cells.remove(cell);
    }

    public Boolean contains(Cell cell)
    {
        return this._cells.contains(cell);
    }

    public boolean isEmpty()
    {
        return this._cells.isEmpty();
    }

    public int getSize()
    {
        return this._cells.size();
    }

    public Cell getCell(int index)
    {
        return this._cells.get(index);
    }

    public ArrayList<Cell> getCells()
    {
        return this._cells;
    }

    public Color getColor() {
        return _color;
    }
}
