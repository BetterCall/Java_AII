package game.entities.statics;

import game.tiles.Tile;
import game.utils.GameHandler;
import graphic.Assets;

import java.awt.*;

/**
 * Created by Franck on 22/03/2017.
 */
public class Tree extends StaticEntity {

    public Tree(GameHandler handler, float x, float y, int width, int height) {
        super(handler, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(
                Assets.map[1] ,
                (int) (x - handler.getCamera().getxOffset()),
                (int) (y - handler.getCamera().getyOffset()),
                width , height ,
                null
        ) ;
    }
}
