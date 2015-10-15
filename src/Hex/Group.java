package Hex;

import java.util.ArrayList;

/**
 * Created by cportron on 12/10/15.
 */
public class Group {
    private ArrayList<Cell> _cells;

    public Group()
    {
        this._cells = new ArrayList<>();
    }

    public void add(Cell cell)
    {
        if(cell  != null)
            this._cells.add(cell);
    }

    public void empty()
    {
        for(int i = 0; i!= this._cells.size();){
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
}
