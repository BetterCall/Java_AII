package graphic;

import game.Game;
import game.entities.Entity;
import game.tiles.Tile;
import game.utils.GameHandler;

/**
 * Created by Franck on 19/03/2017.
 */
public class Camera {

    private GameHandler handler ;
    private float xOffset , yOffset ;

    public Camera(GameHandler handler , float xOffset , float yOffset) {

        this.handler = handler ;
        this.xOffset = xOffset ;
        this.yOffset = yOffset ;

    }


    public void move(float xMvt , float yMvt) {
        xOffset += xMvt ;
        yOffset += yMvt ;

        checkBlankSpace();

    }

    public void centerOnEntity(Entity entity) {
        xOffset = entity.getX() - handler.getWidth() / 2 + entity.getWidth() / 2;
        yOffset = entity.getY() - handler.getHeight() / 2 + entity.getHeight() / 2;
        checkBlankSpace();

    }
    public void checkBlankSpace() {

        if(xOffset < 0){
            xOffset = 0;
        }else if(xOffset > handler.getMap().getWidth() * Tile.TILE_WIDTH - handler.getWidth()){
            xOffset = handler.getMap().getWidth() * Tile.TILE_WIDTH - handler.getWidth();
        }

        if(yOffset < 0){
            yOffset = 0;
        }else if(yOffset >  handler.getMap().getHeight() * Tile.TILE_HEIGHT - handler.getHeight() )                     {
                //(handler.getCamera().getyOffset() + handler.getHeight()) / Tile.TILE_HEIGHT + 1) {
            //yOffset = handler.getMap().getHeight() * Tile.TILE_HEIGHT - handler.getHeight();
            //yOffset = (handler.getCamera().getyOffset() + handler.getHeight()) / Tile.TILE_HEIGHT + 1 ;

            yOffset = handler.getMap().getHeight() * Tile.TILE_HEIGHT - handler.getHeight() ;

        }
    }





    // Getter Setter
    public float getxOffset() {
        return xOffset;
    }

    public void setxOffset(float xOffset) {
        this.xOffset = xOffset;
    }

    public float getyOffset() {
        return yOffset;
    }

    public void setyOffset(float yOffset) {
        this.yOffset = yOffset;
    }

}
