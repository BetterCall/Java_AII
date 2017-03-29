package game.entities.items.weapons;

import game.entities.items.Item;
import game.utils.GameHandler;
import graphic.Assets;

import java.awt.image.BufferedImage;

/**
 * Created by Franck on 24/03/2017.
 */
public class Weapon extends Item{


    public static int WIDTH = 20 , HEIGHT = 20 ;


    public Weapon(GameHandler handler, float x, float y) {
        super(handler, x, y, WIDTH, HEIGHT, Assets.map[0]  , "empty", 100);

        this.category = Item.WEAPON ;
    }
}
