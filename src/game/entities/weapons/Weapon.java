package game.entities.weapons;

import game.entities.Entity;
import game.utils.GameHandler;
import graphic.Assets;

import java.awt.*;

/**
 * Created by Franck on 21/03/2017.
 */
public abstract class Weapon extends Entity{

    public static final float DEFAULT_WEAPON_POWER = 3.0f ;
    protected float power ;

    public Weapon(GameHandler handler, float x, float y , int width , int height) {
        super(handler, x, y, width, height);
        this.power = DEFAULT_WEAPON_POWER ;
    }

    @Override
    public void tick() {
        for( Entity e : handler.getMap().getEntityManager().getEntities()){
            if (collideWithCreature(e)) {
                e.setX(e.getX() + 10);
                System.out.println("Colide");
            }
        }
    }



    public void render(Graphics g , int direction ) {

        if (direction == 0) {

        } else if (direction == 1) {

        } else if (direction == 2) {

        } else {

        }
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




    public int getPower() { return (int)power ;}

}
