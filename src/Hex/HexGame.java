package Hex;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by cportron on 08/10/15.
 */
public class HexGame extends JFrame
{
    public final static int Side = 9;
    public final static Color VColor = Color.white;
    public final static Color HColor = Color.BLACK;


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
        this._grid = new Grid(400/2-HexGame.Side*Cell.rad,800/2-HexGame.Side*Cell.rad, HexGame.Side, HexGame.Side);
        //génération des joueurs
        this._players = new ArrayList<Player>();
        this._players.add(new Player(HexGame.VColor));
        this._players.add(new Player(HexGame.HColor));
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
        if(!this._grid.isWinner(this._currentPlayer)) {
            this._currentPlayerId += 1;
            if (this._currentPlayerId >= this._players.size()) {
                this._currentPlayerId = 0;
            }
            this._currentPlayer = this._players.get(this._currentPlayerId);
        } else {
            System.out.println("Le joueur " + this._currentPlayer + "a gagné");
        }
    }

    public static void main(String args[])
    {
        HexGame game = new HexGame();
    }
}
