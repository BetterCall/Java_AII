package states;

import game.Game;
import game.utils.GameHandler;

import java.awt.*;

/**
 * Created by Franck on 18/03/2017.
 */
public abstract class State {

    private static State currentState = null  ;
    public static void setCurrentState(State stage) {
        currentState = stage  ;
    }

    public static State getCurrentState() {
        return currentState ;
    }

    protected GameHandler handler ;
    public State(GameHandler handler) {
        this.handler = handler ;
    }

    public abstract void tick() ;
    public abstract void render(Graphics g) ;

}
