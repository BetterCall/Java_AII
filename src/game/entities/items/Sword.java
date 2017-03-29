package game.entities.items;

import game.utils.GameHandler;
import graphic.Assets;

import java.awt.image.BufferedImage;

/**
 * Created by Franck on 23/03/2017.
 */
public class Sword extends Item {
    public Sword(GameHandler handler , int x , int y) {
        super(handler , x, y , 20 , 48 ,Assets.woodSword , "sword" , 0 ) ;
    }
}
