package game.entities.items.weapons.swords;

import game.entities.items.Item;
import game.utils.GameHandler;
import graphic.Assets;

import java.awt.image.BufferedImage;

/**
 * Created by Franck on 23/03/2017.
 */
public class MetalSword extends Sword {

    public static final int WIDTH = 20 , HEIGHT = 48 ;


    public MetalSword(GameHandler handler , int x , int y) {
        super(handler , x, y , 20 , 48 ,Assets.metalSword  , "Metal Sword" , 0 ) ;
    }
}
