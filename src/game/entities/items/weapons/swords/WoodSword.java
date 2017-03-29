package game.entities.items.weapons.swords;

import game.utils.GameHandler;
import graphic.Assets;

/**
 * Created by Franck on 23/03/2017.
 */
public class WoodSword extends Sword {

    public static final int WIDTH = 16 , HEIGHT = 61 ;

    public WoodSword(GameHandler handler , int x , int y) {
        super(handler , x, y , WIDTH , HEIGHT ,Assets.woodSword  , "Wood Sword" , 1 ) ;
    }

}
