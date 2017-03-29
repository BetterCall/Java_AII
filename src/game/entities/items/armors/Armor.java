package game.entities.items.armors;

import game.entities.items.Item;
import game.utils.GameHandler;
import graphic.Assets;


/**
 * Created by Franck on 24/03/2017.
 */
public class Armor extends Item {

    public static final int  WIDTH = 20 , HEIGHT = 20 ;

    public Armor(GameHandler handler, float x, float y) {
        super(handler, x, y, WIDTH, HEIGHT, Assets.metalSword, "empty" , 101 ) ;

        this.category = ARMOR ;
    }
}
