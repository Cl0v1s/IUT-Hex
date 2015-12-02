/**
 * HexGame.java
 * Hugo Thiessard
 * Clovis Portron
 */

package Hex;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class HexGame extends JFrame
{
    public final static int Side = 7;

    //Paramètre les deux couleurs qui correspondent à la couleur du joueur horizontal et vertical
    public final static Color VColor = Color.white;
    public final static Color HColor = Color.BLACK;


    private View _screen;
    private Grid _grid;
    private ArrayList<Player> _players;
    private Player _currentPlayer;
    private int _currentPlayerId;
    private boolean _done;


    public HexGame()
    {
        super("S3- Thiessard Portron");
        setBounds(0, 0, 400, 800);
        setVisible(true);
        //création de la vue
        this._screen = new View(this);
        add(this._screen, "Center");
        //cration du controller
        Click click = new Click(this);
        this._screen.addMouseListener(click);
        //création de la grille
        this._grid = new Grid(400/2-HexGame.Side*Cell.rad,800/2-HexGame.Side*Cell.rad, HexGame.Side, HexGame.Side);
        //génération des joueurs
        this._players = new ArrayList<>();
        this._players.add(new Player(HexGame.VColor));
        this._players.add(new Player(HexGame.HColor));
        this._currentPlayer = this._players.get(0);
        this._currentPlayerId = 0;
        this._done = false;
    }

    /*
     getGrid
     Renvoie la grille du jeu, composée de cellules
     */
    public Grid getGrid()
    {
        return this._grid;
    }

    /*
     restart
     Permet de relancer une partie à zéro
     */
    public void restart()
    {
        this._done = false;
        this._grid.empty();
    }

    /*
    isDone
    indique si le jeu est terminé
     */
    public boolean isDone(){return this._done;}

    /*
    getView
    Retourne le manager de vue
     */
    public View getView(){return this._screen;}

    /*
    getCurrentPlayer
    Retourne le joueur dont c'est le tour de jouer actuellement
     */
    public Player getCurrentPlayer()
    {
        return this._currentPlayer;
    }

    /*
    turn
    permet au prochain joueur de jouer, une fois le test de fin de jeu réalisé
     */
    public void turn()
    {
        if(!this._done) {
            if (!this._grid.isWinner(this._currentPlayer)) {
                this._currentPlayerId += 1;
                if (this._currentPlayerId >= this._players.size()) {
                    this._currentPlayerId = 0;
                }
                this._currentPlayer = this._players.get(this._currentPlayerId);
            } else {
                this._done = true;
            }
        }
    }

    /*
    main
    Point d'entré dans le jeu
     */
    public static void main(String args[])
    {
        HexGame game = new HexGame();
    }
}
