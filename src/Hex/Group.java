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
        if(cell != null)
            this._cells.add(cell);
    }

    public void empty()
    {
        for(int i = 0; i != this._cells.size();){
            this._cells.remove(i);
        }
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
