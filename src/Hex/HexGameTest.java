package Hex;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by hthiessard on 02/12/15.
 */
public class HexGameTest {

    HexGame _game;

    @Before
    public void setUp() {
        _game = new HexGame();
    }

    @Test
    public void testRestart() throws Exception {
        _game.restart();
        assertEquals(_game.isDone(), false);
        assertEquals(_game.getGrid().getGroups(), new ArrayList<Group>());
    }
}