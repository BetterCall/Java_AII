package game.entities.items;

import game.Game;
import game.entities.Entity;
import game.entities.creatures.Creature;
import game.utils.GameHandler;
import graphic.Assets;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

/**
 * Created by Franck on 23/03/2017.
 */
public class Item extends Entity{

    // Constant
    public static final int WIDTH = 32 , HEIGHT = 32 ;
    public static final int ATTACK = 0 , DEFENSE = 0 , ASPEED = 0 ;

    public static final int WEAPON =  0 , ARMOR = 1 , BOOT = 2 ;

    transient protected BufferedImage texture ;
    protected String name ;
    protected int id ;
    protected int count ;
    protected int attack , defense , aSpeed ;
    protected boolean pickedUp = false;
    protected int category ;

    public Item(GameHandler handler, float x , float y , int width , int heigth , BufferedImage texture, String name , int id) {
        //public Item(BufferedImage texture, String name , int id) {
        super(handler,x,y , width , heigth) ;
        this.texture = texture ;
        this.name = name ;
        this.id = id ;

        count = 1 ;

        this.width = WIDTH  ;
        this.height = HEIGHT ;
        this.attack = ATTACK ;
        this.defense = DEFENSE ;
        this.aSpeed = ASPEED ;
    }

    @Override
    public void die() {

    }

    public void tick() {
        if(collideWithCreature( handler.getMap().getEntityManager().getPlayer() ) ) {
            pickedUp = true ;
            handler
                    .getMap()
                    .getEntityManager()
                    .getPlayer()
                    .getInventory()
                    .addItem(this) ;

        }
    }

    // render on the map
    public void render(Graphics g ) {
        if(handler == null) {
            return;
        }
        // call the render method with x and y of the current position of the item
        render(
                g ,
                //(int)( x - handler.getCamera().getxOffset() ) ,
                //(int)( y - handler.getCamera().getyOffset() )
                (int)x , (int) y
        ) ;
    }

    // render in the inventory
    public void render(Graphics g , int x , int y) {

        int direction = handler.getMap().getEntityManager().getPlayer().getDirection() ;


        if ( direction == Creature.RIGHT ) {

            AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
            tx.translate(- texture.getWidth(null), 0);
            AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);


            g.drawImage(

                    op.filter(texture, null) ,
                    (int) ( x - handler.getCamera().getxOffset() ) ,
                    (int) ( y - handler.getCamera().getyOffset() ) ,
                    width , height,
                    null

            ) ;

        } else if(direction == Creature.UP ){



            g.drawImage(

                    texture ,
                    (int) ( x - handler.getCamera().getxOffset() ) ,
                    (int) ( y - handler.getCamera().getyOffset() ) ,
                    width , height,
                    null

            ) ;

        } else if(direction == Creature.DOWN ){
            AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
            tx.translate(- texture.getWidth(null), 0);
            AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);


            g.drawImage(

                    op.filter(texture, null) ,
                    (int) ( x - handler.getCamera().getxOffset() ) ,
                    (int) ( y - handler.getCamera().getyOffset() ) ,
                    width , height,
                    null

            ) ;

        } else if(direction == Creature.LEFT ){
            AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
            tx.translate(- texture.getWidth(null), 0);
            AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);


            g.drawImage(

                    op.filter(texture, null) ,
                    (int) ( x - handler.getCamera().getxOffset() ) ,
                    (int) ( y - handler.getCamera().getyOffset() ) ,
                    width , height,
                    null

            ) ;

        }


    }

    public Item createNew(int count) {
        Item item = new Item(handler , x , y , width , height, texture , name , id) ;
        return item;
    }

    public void setPosition(int x , int y) {
        this.x = x ;
        this.y = y ;

        bounds.x = x ;
        bounds.y = y ;
    }


    public Entity collide( Entity entity) {
        for (Entity e : handler.getMap().getEntityManager().getEntities()) {
            if (e != entity) {
                if(collideWithCreature(e))
                    return e ;              // return the entity touched
            }
        }
        return null ;                       // no collide detected
    }

    // GETTER SETTER

    public GameHandler getHandler() { return handler; }
    public void setHandler(GameHandler handler) { this.handler = handler; }

    public BufferedImage getTexture() { return texture; }
    public void setTexture(BufferedImage texture) { this.texture = texture; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getCount() { return count; }
    public void setCount(int count) { this.count = count; }

    public boolean isPickedUp() { return pickedUp ; }
    public void setPickedUp(boolean pickedUp) { this.pickedUp = pickedUp ;}

    public int getCategory() { return category ;}

    // END GETTER SETTER
}
