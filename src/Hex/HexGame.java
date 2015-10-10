package Hex;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by cportron on 08/10/15.
 */
public class HexGame extends JFrame
{
    private View _screen;
    private Click _click;
    private Grid _grid;
    private ArrayList<Player> _players;
    private Player _currentPlayer;
    private int _currentPlayerId;

    public HexGame()
    {
        super("S3- Thiessard Portron");
        setBounds(0, 0, 400, 800);
        setVisible(true);
        //création de la vue
        this._screen = new View(this);
        add(this._screen, "Center");
        //cration du controller
        _click = new Click(this);
        this._screen.addMouseListener(_click);
        //création de la grille
        this._grid = new Grid(400/2-7*Cell.rad,800/2-7*Cell.rad, 7, 7);
        //génération des joueurs
        this._players = new ArrayList<Player>();
        this._players.add(new Player(Color.black));
        this._players.add(new Player(Color.white));
        this._currentPlayer = this._players.get(0);
        this._currentPlayerId = 0;


    }

    public Grid getGrid()
    {
        return this._grid;
    }
    public View getView(){return this._screen;}

    public Player getCurrentPlayer()
    {
        return this._currentPlayer;
    }

    public ArrayList<Player> getPlayers()
    {
        return this._players;
    }

    public void turn()
    {
        this._currentPlayerId += 1;
        if(this._currentPlayerId >= this._players.size())
            this._currentPlayerId = 0;
        this._currentPlayer = this._players.get(this._currentPlayerId);
    }

    public static void main(String args[])
    {
        HexGame game = new HexGame();
    }
}
