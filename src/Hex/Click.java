/**
 * Click.java
 * Hugo Thiessard
 * Clovis Portron
 */

package Hex;

import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Click extends MouseInputAdapter {
    private HexGame _game;

    public Click(HexGame game)
    {
        super();
        this._game = game;
    }

    public HexGame getGame() {
        return _game;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        clicked(e.getX(), e.getY());
    }

    public void clicked(int x, int y) {
        if(this._game.isDone()) {
            this._game.restart();
            return;
        }
        ArrayList<Cell> cells = this._game.getGrid().getCells();
        boolean done = false;
        int i = 0;
        while(!done && i != cells.size())
        {
            if(cells.get(i).contains(x, y))
            {
                cells.get(i).onClick(this._game.getCurrentPlayer());
                this._game.getGrid().putCell(cells.get(i));
                done = true;
            }
            i++;
        }
        if(done) {
            this._game.turn();
            this._game.getView().repaint();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
