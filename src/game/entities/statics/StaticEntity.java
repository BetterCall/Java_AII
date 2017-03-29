package game.entities.statics;

import game.entities.Entity;
import game.utils.GameHandler;

/**
 * Created by Franck on 21/03/2017.
 */
public abstract class StaticEntity extends Entity{

    public StaticEntity(GameHandler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
    }


    public void die() {

    }
}
