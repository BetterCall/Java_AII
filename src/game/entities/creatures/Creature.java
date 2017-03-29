package game.entities.creatures;

import game.Game;
import game.entities.Entity;
import game.entities.items.Item;
import game.entities.items.armors.Armor;
import game.entities.items.boots.Boot;
import game.entities.statics.StaticEntity;
import game.entities.weapons.Sword;
import game.map.Map;
import game.tiles.Tile;
import game.utils.GameHandler;
import game.entities.items.weapons.Weapon;


import java.awt.*;

/**
 * Created by Franck on 18/03/2017.
 */
public abstract  class Creature extends Entity {


    // state
    transient public static final int IDLE = 0 ;
    transient public static final int WALK = 1 ;
    transient public static final int DEATH = 4 ;

    //Direction
    transient public static final int DOWN = 0 ;
    transient public static final int UP = 1 ;
    transient public static final int LEFT = 2;
    transient public static final int RIGHT = 3 ;


    transient public static final float DEFAULT_SPEED = 3.0f ;
    // SIZE OF CREATURE
    transient public static final int DEFAULT_CREATE_WIDTH = 64 ;
    transient public static final int DEFAULT_CREATE_HEIGHT = 64 ;

    transient protected float speed ;
    // MOVEMENT
    transient protected float xMove , yMove ;
    // animation variables
    transient protected int state , direction ;
    protected int invincibleTimer  ;
    protected int deathTimer ;

    protected int isRunning ;
    protected int isAttacking ;

    protected int isHurt ;

    protected Item stuff[] = new Item[3] ;


    //Sword
    protected int swordX , swordY ;
    protected int swordTimer ;
    protected int swordRotation ;

    // Magie
    protected int  magicNumber ;

    // Attack timer
    private long lastAttackTimer , attackCooldown = 800 , attackTimer = attackCooldown;



    // Constructor
    public Creature(GameHandler handler, float x , float y , int width , int heigth ) {
        super(handler,x,y , width , heigth) ;

        speed = DEFAULT_SPEED ;
        xMove = 0 ;
        yMove = 0 ;


        stuff[Item.WEAPON] = new Weapon(handler , x , y)  ;
        stuff[Item.ARMOR] = new Armor(handler , x , y)  ;
        stuff[Item.BOOT] = new Boot(handler , x , y)  ;



    }

    public void move(){
        if( x + xMove > 0 && y + yMove > 0 ){

            if ( x + xMove < handler.getMap().getWidth() * Tile.TILE_WIDTH && y + yMove < handler.getMap().getHeight() * Tile.TILE_HEIGHT) {
                moveX();
                moveY();
            }
        }


    }

    public void moveX(){

        if(xMove > 0){//Moving right
            int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILE_WIDTH;

            if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILE_HEIGHT) &&
                    !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILE_HEIGHT)){
                x += xMove;
            } else { // collision
                //x = tx * Tile.TILE_WIDTH - bounds.x - bounds.width - 1;
            }
        }else if(xMove < 0){//Moving left
            int tx = (int) (x + xMove + bounds.x) / Tile.TILE_WIDTH;

            if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILE_HEIGHT) &&
                    !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILE_HEIGHT)){
                x += xMove;
            } else { // collision
                //x = tx * Tile.TILE_WIDTH + Tile.TILE_WIDTH- bounds.x ;
            }
        }
    }

    public void moveY(){
        if(yMove < 0){//Up
            int ty = (int) (y + yMove + bounds.y) / Tile.TILE_HEIGHT;

            if(!collisionWithTile((int) (x + bounds.x) / Tile.TILE_WIDTH, ty) &&
                    !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILE_WIDTH, ty)){
                y += yMove;
            } else { // collision
                //y = ty * Tile.TILE_HEIGHT + Tile.TILE_HEIGHT - bounds.y ;
            }

        }else if(yMove > 0){//Down
            int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILE_HEIGHT;

            if(!collisionWithTile((int) (x + bounds.x) / Tile.TILE_WIDTH, ty) &&
                    !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILE_WIDTH, ty)){
                y += yMove;
            } else { // collision
                //y = ty * Tile.TILE_HEIGHT - bounds.y - bounds.height -1 ;
            }
        }
    }


    public void render(Graphics g) {
    }

    protected boolean collisionWithTile(int x , int y) {

        if( Map.tiles4[y][x] == 0) { //    handler.getMap().getTile(Map.tiles4 , x ,y ).getId() == 0 ) {
            return false ;
        } else {
            return true ;
        }
    }

    public void attack() {

        attackTimer += System.currentTimeMillis() - lastAttackTimer ; // The time between 2 method callq
        lastAttackTimer = System.currentTimeMillis() ;

        if( attackTimer < attackCooldown ) {
            //return ;
        }

        isAttacking = 1 ;




        switch (direction) {

            case DOWN :
                stuff[Item.WEAPON].setPosition(
                        (int) x + width / 2 - stuff[Item.WEAPON].getWidth() / 2 ,
                        (int)y + height
                );
                break;
            case UP :
                stuff[Item.WEAPON].setPosition(
                        (int) x + width / 2 - stuff[Item.WEAPON].getWidth() / 2,
                        (int) y - stuff[Item.WEAPON].getHeight()
                );
                break;

            case RIGHT :
                stuff[Item.WEAPON].setPosition(
                        (int) x + width ,
                        (int) y + height / 2 - stuff[Item.WEAPON].getHeight() / 2
                );
                break ;

            case LEFT :
                stuff[Item.WEAPON].setPosition(
                        (int) x - width ,
                        (int) y + height / 2 -  stuff[Item.WEAPON].getHeight()/ 2
                );
                break ;

        }

        Entity e = stuff[Item.WEAPON].collide(this) ;
        if( e != null ) {
            e.hurt(2);
        }
        //reset the cooldown
        attackTimer = 0 ;

    }

    protected boolean collideWithStaticEntity(StaticEntity staticEntity) {
        return true;
    }

    // Getter and Setter

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
    public float getxMove() {
        return xMove;
    }

    public void setxMove(float xMove) {
        this.xMove = xMove;
    }

    public float getyMove() {
        return yMove;
    }

    public void setyMove(float yMove) {
        this.yMove = yMove;
    }

    public int getDirection() { return direction ;}
    // END Getter and Setter

}
