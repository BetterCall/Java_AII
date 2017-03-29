package game.entities.items.boots;


import game.utils.GameHandler;
import graphic.Assets;

import java.awt.image.BufferedImage;

/**
 * Created by Franck on 24/03/2017.
 */
public class ArmorBoot extends Boot{
    public static final int WIDTH = 20 , HEIGHT = 0 ;
    public static final int ATTACK = 0 , DEFENSE = 3 , ASPEED = 0 ;

    public ArmorBoot(GameHandler handler, float x, float y) {
        super(handler, x, y ) ;

        this.texture = Assets.metalSword ;
        this.name = "Armor Boot" ;
        this.id = 1 ;
        this.width = WIDTH  ;
        this.height = HEIGHT ;
        this.attack = ATTACK ;
        this.defense = DEFENSE ;
        this.aSpeed = ASPEED ;
    }
}
