package Hex;

import Hex.Cell;

import java.util.ArrayList;

/**
 * Created by cportron on 12/10/15.
 */
public class Group {
    private ArrayList<Cell> _cells;

    public Group()
    {
        this._cells = new ArrayList<Cell>();
    }

    public void add(Cell cell)
    {
        if(cell  != null)
            this._cells.add(cell);
    }

    public int getSize()
    {
        return this._cells.size();
    }
}