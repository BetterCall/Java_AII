package game.entities;

import game.Game;
import game.utils.GameHandler;

import java.awt.*;
import java.io.Serializable;

/**
 * Created by Franck on 18/03/2017.

 */
public abstract class Entity implements Serializable {

    public static final int DEFAULT_HEALTH = 10 ;

    transient protected GameHandler handler ;
    protected float x , y ;
    protected int width , height ;
    //Animation variables
    protected int frameNumber , frameTimer , frameMax ;

    protected int health ;
    protected boolean isAlive = true;

    transient protected Rectangle bounds ;

    public Entity(GameHandler handler , float x ,float y , int width , int height) {
        this.handler = handler ;

        this.x = x  ;
        this.y = y  ;

        this.width = width ;
        this.height = height ;

        // bounds rectangle by default
        bounds = new Rectangle(0,0 ,width , height) ;

        health = DEFAULT_HEALTH ;
    }
    protected boolean collideWithCreature(Entity entity) {

        if ((entity.getX() >= x + width) // collide
                || (entity.getX() + entity.getWidth() <= x)
                || (entity.getY() >= y + height)
                || (entity.getY() + entity.getHeight() <= y)
                )
            return false;
        else
            return true;


    }


    public void hurt(int damage) {
        health -= damage;

        if (health <= 0 ) {
            isAlive = false ;
            die() ;
        }

    }


    public abstract void die();


    public abstract void tick() ;
    public abstract void render(Graphics g );

    // Getter and Setter

    public float getX() {
        return x;
    }
    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }
    public void setY(float y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }
    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }

    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
    }

    // END GETTER AND SETTER
}
