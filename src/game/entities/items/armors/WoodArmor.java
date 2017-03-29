package game.entities.items.armors;

import game.utils.GameHandler;
import graphic.Assets;

/**
 * Created by Franck on 24/03/2017.
 */
public class WoodArmor extends Armor {

    public static final int WIDTH = 20 , HEIGHT = 0 ;
    public static final int ATTACK = 1 , DEFENSE = 0 , ASPEED = 0 ;

    public WoodArmor(GameHandler handler, float x, float y) {
        super(handler, x, y ) ;

        this.texture = Assets.woodSword ;
        this.name = "Wood Sword" ;
        this.id = 2 ;
        this.width = WIDTH  ;
        this.height = HEIGHT ;
        this.attack = ATTACK ;
        this.defense = DEFENSE ;
        this.aSpeed = ASPEED ;
    }
}
