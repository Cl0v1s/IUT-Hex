package Hex;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

/**
 * Created by hthiessard on 02/12/15.
 */
public class CellTest {

    private Grid _grid;
    private Cell _cell;

    @Before
    public void setUp() {
        _grid = new Grid(400/2-HexGame.Side*Cell.rad,800/2-HexGame.Side*Cell.rad, HexGame.Side, HexGame.Side);
        _cell = _grid.getCellAt(0, 0);
    }

    @Test (expected = IllegalArgumentException.class)
    public void setColorShouldThrowIllegalArgumentException() throws Exception {
        _cell.setColor(null);
    }

    @Test
    public void testContains() throws Exception {
        assertEquals(true, _cell.contains(74, 284));
        assertEquals(false, _cell.contains(0, 0));
    }

    @Test
    public void testOnClick() throws Exception {
        _cell.onClick(new Player(Color.pink));
        assertEquals(_cell.getColor(), Color.pink);
        assertEquals(_cell.isOwned(), true);
    }

    @Test
    public void testSetGroup() throws Exception {
        // la cellule n'appartient pas à un groupe
        Group group = new Group();
        _cell.setGroup(group);
        assertEquals(_cell.getGroup(), group);

        // La cellule appratient à un groupe
        Group group2 = new Group();
        _cell.setGroup(group2);
        assertEquals(_cell.getGroup(), group2);
        assertEquals(group.contains(_cell), false);
    }
}