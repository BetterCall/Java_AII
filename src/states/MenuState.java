package states;

import game.Game;
import game.utils.GameHandler;

import java.awt.*;

/**
 * Created by Franck on 18/03/2017.
 */
public class MenuState extends State{


    public MenuState(GameHandler handler) {
        super(handler) ;
    }


    @Override
    public void tick() {
        if( handler.getMouseManager().isRightPressed())
            State.setCurrentState(handler.getGame().gameState );
    }

    @Override
    public void render(Graphics g) {

    }



}
