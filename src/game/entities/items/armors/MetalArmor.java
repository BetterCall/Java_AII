package game.entities.items.armors;

import game.utils.GameHandler;
import graphic.Assets;

import java.awt.image.BufferedImage;

/**
 * Created by Franck on 24/03/2017.
 */
public class MetalArmor extends Armor{

    public static final int WIDTH = 20 , HEIGHT = 0 ;
    public static final int ATTACK = 3 , DEFENSE = 0 , ASPEED = 0 ;

    public MetalArmor(GameHandler handler, float x, float y) {
        super(handler, x, y ) ;

        this.texture = Assets.metalSword ;
        this.name = "Metal Sword" ;
        this.id = 1 ;
        this.width = WIDTH  ;
        this.height = HEIGHT ;
        this.attack = ATTACK ;
        this.defense = DEFENSE ;
        this.aSpeed = ASPEED ;
    }
}
