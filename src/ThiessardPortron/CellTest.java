package ThiessardPortron;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

/**
 * Created by hthiessard on 02/12/15.
 */
public class CellTest {

    private Grid _grid;

    @Before
    public void init() {
        _grid = new Grid(400/2-HexGame.Side*Cell.rad,800/2-HexGame.Side*Cell.rad, HexGame.Side, HexGame.Side);
    }

    @Test (expected = IllegalArgumentException.class)
    public void setColorShouldThrowIllegalArgumentException() throws Exception {
        _grid.getCellAt(0, 0).setColor(null);
    }

    @Test
    public void setColorShouldChangeColor() {
        _grid.getCellAt(0, 0).setColor(Color.red);
        assertEquals(_grid.getCellAt(0, 0).getColor(), Color.red);
    }

    @Test
    public void containsShouldReturnTrue() {
        System.out.println(_grid.getCellAt(0, 0).contains(60, 280));
        System.out.println(_grid.getCellAt(0, 0).contains(60, 280));
    }
}