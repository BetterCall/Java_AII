package game.entities.items.boots;

import game.entities.items.Item;
import game.utils.GameHandler;
import graphic.Assets;

import java.awt.image.BufferedImage;

/**
 * Created by Franck on 24/03/2017.
 */
public class Boot extends Item {

    public static final int WIDTH = 20 , HEIGHT = 20 ;

    public Boot(GameHandler handler, float x, float y ) {
        super(handler, x, y, WIDTH,HEIGHT , Assets.map[0]  , " empty ", 103 );

        this.category = BOOT ;
    }
}
