package game.entities.items.boots;

import game.utils.GameHandler;
import graphic.Assets;

import java.awt.image.BufferedImage;

/**
 * Created by Franck on 24/03/2017.
 */
public class AttackBoot extends  Boot {
    public static final int WIDTH = 20 , HEIGHT = 20 ;
    public static final int ATTACK = 2 , DEFENSE = 0 , ASPEED = 1 ;

    public AttackBoot(GameHandler handler, float x, float y) {
        super(handler, x, y ) ;

        this.texture = Assets.metalSword ;
        this.name = "Attack Boot" ;
        this.id = 3 ;
        this.width = WIDTH  ;
        this.height = HEIGHT ;
        this.attack = ATTACK ;
        this.defense = DEFENSE ;
        this.aSpeed = ASPEED ;
    }
}
