package game.entities.items;

import game.utils.GameHandler;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Franck on 23/03/2017.
 */
public class ItemManager implements Serializable {

    private GameHandler handler ;
    private ArrayList<Item> items ;

    public ItemManager(GameHandler handler) {
        this.handler = handler ;
        items = new ArrayList<Item>() ;

    }

    public void tick() {
        Iterator<Item> it = items.iterator() ;

        while(it.hasNext()) {
            Item item = it.next() ;
            item.tick();
            // that means that the player picked up to the map
            // we need to remove it
            if(item.isPickedUp()) {
                it.remove();
            }
        }
    }

    public void render(Graphics g) {
        for(Item item : items) {
            item.render(g);
        }

    }

    public void addItem(Item item) {
        item.setHandler(handler);

        for( Item i : items ) {
            if ( item.getId() == i.getId() ) {
                i.count++ ;
                return ;
            }
        }

        items.add(item);
    }


    // Getter Setter
    public GameHandler getHandler() { return handler ;}
    public void setHandler(GameHandler handler) { this.handler = handler ;}
}


