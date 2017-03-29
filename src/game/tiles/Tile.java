package game.tiles;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Franck on 18/03/2017.
 */
public class Tile {


    public static Tile[] tiles = new Tile[400] ;

    // CLASS
    public static final int TILE_WIDTH = 32 ;
    public static final int TILE_HEIGHT = 32 ;

    protected BufferedImage texture ;
    protected final int id ;

    public Tile(BufferedImage texture , int id) {
        this.texture = texture ;
        this.id = id ;

        tiles[id] = this;
    }

    public void tick() {

    }

    /**
     * @param g
     * @param x the x position to render the tile
     * @param y the y position to render the tile
     */
    public void render(Graphics g , int x , int y) {
        g.drawImage(texture , x, y ,TILE_WIDTH , TILE_HEIGHT, null );
    }


    public boolean isSolid() {
        return false ;
    }


    //Getter Setter

    public int getId() {
        return id ;
    }

    // End Getter Setter
}
