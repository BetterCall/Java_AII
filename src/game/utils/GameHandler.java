package game.utils;

import game.Game;
import game.input.KeyManager;
import game.input.MouseManager;
import game.map.Map;
import graphic.Camera;
import states.GameState;

import java.io.Serializable;

/**
 * Created by Franck on 20/03/2017.
 */
public class GameHandler  implements Serializable {

    transient private Game game ;
    private Map map ;

    public GameHandler(Game game) {
        this.game = game ;
        map = GameState.map ;
    }

    // Getter Setter
    public KeyManager getKeyManager() { return game.getKeyManager(); }
    public MouseManager getMouseManager() { return game.getMouseManager(); }
    public Camera getCamera() { return game.getCamera(); }

    public int getWidth() { return game.getWidth() ;}
    public int getHeight() { return game.getHeight() ;}

    public Game getGame() { return game; }
    public void setGame(Game game) { this.game = game; }

    public Map getMap() { return map; }
    public void setMap(Map map) { this.map = map;}

}
