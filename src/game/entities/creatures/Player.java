package game.entities.creatures;

import game.Game;
import game.entities.Entity;
import game.entities.items.Item;
import game.entities.weapons.Sword;
import game.entities.weapons.Weapon;
import game.inventory.Inventory;
import game.utils.GameHandler;
import graphic.Assets;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.io.Serializable;

/**
 * Created by Franck on 18/03/2017.
 */
public class Player extends Creature implements Serializable{

    transient public static int PLAYER_WIDTH = 40 ;
    transient public static int PLAYER_HEIGHT = 48 ;
    transient public static int MAX_FRAME = 8 ;

    transient public static final int STATE_NUMBER = 12;
    transient public static final int FRAME_NUMBER = 8;

    transient public static int TIME_BETWEEN_2_FRAMES_PLAYER = 3 ;
    transient public static float TIME_BETWEEN_2_FRAMES_SWORD = 1 ;

    public static int id ;

    private Inventory inventory ;

    // Constructor
    public Player( float x, float y  , int id ) {

        super(Game.handler , x, y  , PLAYER_WIDTH ,PLAYER_HEIGHT);

        direction = RIGHT ;
        state = IDLE ;

        frameNumber = 0 ;
        frameMax = MAX_FRAME ;
        frameTimer = TIME_BETWEEN_2_FRAMES_PLAYER ;

        isAttacking = 0 ;
        deathTimer = 0 ;

        this.id = id ;

        inventory = new Inventory(handler) ;

    }


    public void die() {

    }

    @Override
    public void tick() {

        inventory.tick();

        if(inventory.isActive())
            if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER)) {
                Item i = inventory.getCurrentItem() ;
                setStuff( i.getCategory() , i );
            }

        getInput();

        handler.getCamera().centerOnEntity(this);

        if( isAttacking == 1 ) {
            attack();
        }

        if(stuff[Item.WEAPON] != null)
            stuff[Item.WEAPON].tick() ;

        move() ;
    }

    private void getInput() {
        xMove = 0 ;
        yMove = 0 ;

        int TIME_BETWEEN_2_FRAMES_PLAYER =0;
        state = IDLE  ;

        // IS RUNNING
        if(handler.getKeyManager().run) {
            isRunning = 1 ;
        } else {
            isRunning = 0 ;
        }


        // IS RUNNING
        if(handler.getKeyManager().attack || handler.getMouseManager().attack) {
            isAttacking = 1 ;
        } else {
            isAttacking = 0 ;
        }


        if( handler.getKeyManager().left){
            xMove = -speed - isRunning;
            direction = LEFT ;

            if(state != WALK) {
                state = WALK ;
            }

        }

        if( handler.getKeyManager().right){
            xMove = speed + isRunning;
            direction = RIGHT ;

            if(state != WALK) {
                state = WALK ;
            }

        }

        if( handler.getKeyManager().up) {
            yMove = -speed -isRunning  ;
            direction = UP ;

            if(state != WALK) {
                state = WALK ;

            }

        }
        if ( handler.getKeyManager().down){
            yMove = speed + isRunning  ;
            direction = DOWN ;

            if(state != WALK) {
                state = WALK ;

            }
        }
    }

    @Override
    public void render(Graphics g) {

        inventory.render(g);

        if (frameTimer <= 0) {
            //On le réinitialise
            frameTimer = TIME_BETWEEN_2_FRAMES_PLAYER;

            //Et on incrémente notre variable qui compte les frames de 1 pour passer à la suivante
            frameNumber++;

            //Mais si on dépasse la frame max, il faut revenir à la première :
            if (frameNumber >= frameMax)
                frameNumber = 0;

        }
        //Sinon, on décrémente notre timer

        else {
            if (state != IDLE)
                frameTimer -= 1 + (isRunning * 2);
            else
                frameTimer--;
        }

        // si on a ete toucher et qu'on est invisible
        if(invincibleTimer > 0 ) {

            if( frameNumber % 2 == 0 ) {
                if ( direction == RIGHT ) { // Flip the sheet

                     AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
                     tx.translate(- Assets.player[ (LEFT + 3)* state][frameNumber].getWidth(null), 0);
                     AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);

                    g.drawImage(
                            op.filter(Assets.player[ (LEFT  + 3)* state][frameNumber], null) ,
                            (int) ( x - handler.getCamera().getxOffset() ) ,
                            (int) ( y - handler.getCamera().getyOffset() ) ,
                            width , height,
                            null
                    ) ;
                } else {
                    g.drawImage(
                            Assets.player[ direction  * state][frameNumber] ,
                            (int) ( x - handler.getCamera().getxOffset() ) ,
                            (int) ( y - handler.getCamera().getyOffset() ) ,
                            width , height,
                            null
                    ) ;

                }
            } else {} // render nothing

        } else {

            if ( direction == RIGHT ) {

                AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
                tx.translate(- Assets.player[ (LEFT + 3)* state][frameNumber].getWidth(null), 0);
                AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);


                g.drawImage(

                        op.filter(Assets.player[ (LEFT + 3)* state][frameNumber], null) ,
                        (int) ( x - handler.getCamera().getxOffset() ) ,
                        (int) ( y - handler.getCamera().getyOffset() ) ,
                        width , height,
                        null

                ) ;

            } else {

                g.drawImage(
                        Assets.player[ (direction + 3 ) * state ][frameNumber] ,
                        (int) ( x - handler.getCamera().getxOffset() ) ,
                        (int) ( y - handler.getCamera().getyOffset() ) ,
                        width , height,
                        null
                ) ;

            }

        }

        if( isAttacking == 1) {
            stuff[Item.WEAPON].render(g,(int) stuff[Item.WEAPON].getX() , (int) stuff[Item.WEAPON] .getY() );
        }


    }

    public int getId() { return id ; }
    public Inventory getInventory() { return inventory ;}

    public Item getStuff(int id) { return stuff[id] ; }
    public void setStuff(int id , Item i) { stuff[id] = i ;}

}
