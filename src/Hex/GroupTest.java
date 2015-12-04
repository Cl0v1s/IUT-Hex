package Hex;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by hthiessard on 02/12/15.
 */
public class GroupTest {

    Grid _grid;
    Group _group;

    @Before
    public void setUp() {
        _grid = new Grid(400/2-HexGame.Side*Cell.rad,800/2-HexGame.Side*Cell.rad, HexGame.Side, HexGame.Side);
        _group = new Group();
    }

    @Test (expected = IllegalArgumentException.class)
    public void addShouldThrowIllegalArgumentException() throws Exception {
        _group.add(null);
    }

    @Test
    public void testAdd() throws Exception {
        _group.add(_grid.getCellAt(0, 0));
        assertEquals(_group.getColor(), Color.lightGray);
        ArrayList<Cell> l = new ArrayList<>();
        l.add(_grid.getCellAt(0, 0));
        assertEquals(_group.getCells(), l);

        Cell cell = _grid.getCellAt(1, 0);
        cell.setColor(Color.black);
        _group.add(cell);
        assertEquals(_group.getColor(), Color.lightGray);
        assertEquals(_group.getCells(), l);

        _group.add(_grid.getCellAt(0, 0));
        assertEquals(_group.getCells(), l);

        _group.add(_grid.getCellAt(1,1));
        l.add(_grid.getCellAt(1,1));
        assertEquals(_group.getCells(), l);
    }

    @Test (expected = IllegalArgumentException.class)
    public void removeShouldThrowIllegalArgumentException() throws Exception {
        _group.remove(null);
    }

    @Test
    public void testRemove() throws Exception {
        _group.remove(_grid.getCellAt(0, 0));
        assertEquals(_group.getCells(), new ArrayList<Cell>());

        _group.add(_grid.getCellAt(0, 0));
        _group.remove(_grid.getCellAt(0, 0));
        assertEquals(_group.getCells(), new ArrayList<Cell>());
    }

    @Test (expected = IllegalArgumentException.class)
    public void containsShouldThrowIllegalArgumentException() throws Exception {
        _group.contains(null);
    }

    @Test
    public void testContains() throws Exception {
        assertEquals(_group.contains(_grid.getCellAt(0,0)), false);
        _group.add(_grid.getCellAt(0,0));
        assertEquals(_group.contains(_grid.getCellAt(0,0)), true);
    }
}