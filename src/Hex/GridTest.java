package Hex;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by hthiessard on 02/12/15.
 */
public class GridTest {

    private Grid _grid;

    @Before
    public void setUp() {
        _grid = new Grid(400/2-HexGame.Side*Cell.rad,800/2-HexGame.Side*Cell.rad, HexGame.Side, HexGame.Side);
    }

    @Test
    public void testEmpty() throws Exception {
        // Si il n'y a pas de groupe
        _grid.empty();
        assertEquals(_grid.getGroups().isEmpty(), true);

        Cell cell = _grid.getCellAt(0, 0);
        _grid.putCell(cell);
        assertEquals(_grid.getGroups().size(), 1);
        for(Cell c : _grid.getCells())
        {
            assertEquals(c.getColor(), Color.lightGray);
        }
    }

    @Test
    public void testGetCellAt() throws Exception {
        // Si aucune cellule n'a ces positions
        assertEquals(_grid.getCellAt(-1, -1), null);

        // Si une cellule est à cette position
        assertEquals(_grid.getCellAt(0, 0), _grid.getCells().get(0));
    }

    @Test (expected = IllegalArgumentException.class)
    public void getNeighboursShouldThrowIllegalArgumentException() throws Exception {
        _grid.getNeighbours(null);
    }

    @Test
    public void testGetNeighbours() throws Exception {
        Cell cell = _grid.getCellAt(2, 2);
        ArrayList<Cell> list = _grid.getNeighbours(cell);
        assertEquals(list.contains(_grid.getCellAt(cell.getLogicalX()-1, cell.getLogicalY()-1)), true);
        assertEquals(list.contains(_grid.getCellAt(cell.getLogicalX(), cell.getLogicalY()-1)), true);
        assertEquals(list.contains(_grid.getCellAt(cell.getLogicalX()+1, cell.getLogicalY())), true);
        assertEquals(list.contains(_grid.getCellAt(cell.getLogicalX()+1, cell.getLogicalY()+1)), true);
        assertEquals(list.contains(_grid.getCellAt(cell.getLogicalX(), cell.getLogicalY()+1)), true);
        assertEquals(list.contains(_grid.getCellAt(cell.getLogicalX()-1, cell.getLogicalY())), true);

        cell = _grid.getCellAt(0, 0);
        list = _grid.getNeighbours(cell);
        assertEquals(list.contains(_grid.getCellAt(cell.getLogicalX()-1, cell.getLogicalY()-1)), false);
        assertEquals(list.contains(_grid.getCellAt(cell.getLogicalX(), cell.getLogicalY()-1)), false);
        assertEquals(list.contains(_grid.getCellAt(cell.getLogicalX()+1, cell.getLogicalY())), true);
        assertEquals(list.contains(_grid.getCellAt(cell.getLogicalX()+1, cell.getLogicalY()+1)), true);
        assertEquals(list.contains(_grid.getCellAt(cell.getLogicalX(), cell.getLogicalY()+1)), true);
        assertEquals(list.contains(_grid.getCellAt(cell.getLogicalX()-1, cell.getLogicalY())), false);
    }

    @Test (expected = IllegalArgumentException.class)
    public void putCellShouldThrowIllegalArgumentException() throws Exception {
        _grid.putCell(null);
    }

    @Test
    public void testPutCell() throws Exception {
        _grid.putCell(_grid.getCellAt(0, 0));
        assertEquals(1, _grid.getGroups().size());
        _grid.putCell(_grid.getCellAt(0, 1));
        assertEquals(2, _grid.getGroups().size());
        _grid.putCell(_grid.getCellAt(3, 3));
        assertEquals(3, _grid.getGroups().size());
    }

    @Test (expected = IllegalArgumentException.class)
    public void isWinnerShouldThrowIllegalArgumentException() throws Exception {
        _grid.isWinner(null);
    }

    @Test
    public void testIsWinner() throws Exception {
        Player player = new Player(Color.white);
        for (int i = 0; i < 7; i++) {
            Cell cell = _grid.getCellAt(i,0);
            cell.onClick(player);
            _grid.putCell(cell);
        }
        assertEquals(_grid.isWinner(player), true);

        _grid.empty();
        player = new Player(Color.white);
        for (int i = 0; i < 6; i++) {
            Cell cell = _grid.getCellAt(i,0);
            cell.onClick(player);
            _grid.putCell(cell);
        }
        assertEquals(_grid.isWinner(player), false);

        _grid.empty();
        player = new Player(Color.white);
        for (int i = 1; i < 7; i++) {
            Cell cell = _grid.getCellAt(i,0);
            cell.onClick(player);
            _grid.putCell(cell);
        }
        assertEquals(_grid.isWinner(player), false);




        _grid.empty();
        player = new Player(Color.black);
        for (int i = 0; i < 7; i++) {
            Cell cell = _grid.getCellAt(0,i);
            cell.onClick(player);
            _grid.putCell(cell);
        }
        assertEquals(_grid.isWinner(player), true);

        _grid.empty();
        player = new Player(Color.black);
        for (int i = 0; i < 6; i++) {
            Cell cell = _grid.getCellAt(0,i);
            cell.onClick(player);
            _grid.putCell(cell);
        }
        assertEquals(_grid.isWinner(player), false);

        _grid.empty();
        player = new Player(Color.black);
        for (int i = 1; i < 7; i++) {
            Cell cell = _grid.getCellAt(0,i);
            cell.onClick(player);
            _grid.putCell(cell);
        }
        assertEquals(_grid.isWinner(player), false);
    }
}