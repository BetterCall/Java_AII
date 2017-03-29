package game.entities;

import game.entities.creatures.Player;
import game.entities.creatures.heroes.Hero;
import game.entities.creatures.monsters.Monster;
import game.utils.GameHandler;

import java.awt.*;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Franck on 22/03/2017.
 */
public class EntityManager implements Serializable{

    transient private GameHandler handler ;

    private Player player ;
    private ArrayList<Entity> entities ;

    private ArrayList<Monster> monsters ;
    private ArrayList<Hero> heros ;

    public EntityManager(GameHandler handler ) { //}, Player player ) {
        this.handler = handler ;
        //this.player = player ;
        entities = new ArrayList<Entity>() ;

        monsters = new ArrayList<Monster>() ;
        heros = new ArrayList<Hero>() ;
    }

    public void tick() {

        Iterator<Entity> it = entities.iterator() ;

        while (it.hasNext()) {
            Entity entity = it.next() ;
            entity.tick() ;
            if(!entity.isAlive) {
                it.remove() ;
            }
        }

        player.tick();

    }

    public void render(Graphics g) {
        for(Entity entity : entities) {
            entity.render(g) ;
        }
        player.render(g);

    }

    public void addEntity(Entity entity) {
        entities.add(entity) ;
    }
    public Entity getEntity(int id) {
        return entities.get(id) ;
    }

    // Getters And Setters
    public GameHandler getHandler() { return handler; }
    public void setHandler(GameHandler handler) { this.handler = handler; }

    public ArrayList<Entity> getEntities() { return entities; }
    public void setEntities(ArrayList<Entity> entities) {this.entities = entities; }

    public Player getPlayer() { return player ;}
    public void setPlayer(Player player) { this.player = player ;}

}
