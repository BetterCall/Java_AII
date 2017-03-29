package game.inventory;

import game.entities.items.Item;
import game.utils.GameHandler;
import graphic.Assets;
import graphic.Text;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Franck on 23/03/2017.
 */
public class Inventory implements Serializable{

    public static final int INVENTORY_WIDTH = 512 , INVENTORY_HEIGHT = 384 ;

    private GameHandler handler ;
    private boolean active = false ;

    private ArrayList<Item> inventoryItems ;

    private int x = 64, y = 48 ;
    private int invListCenterX = x + 171 ;
    private int invListCenterY = y + INVENTORY_HEIGHT / 2  + 5;
    private int invListSpacing = 30 ;


    private int invImageX = 452, invImageY = 82,
            invImageWidth = 64, invImageHeight = 64;

    private int invCountX = 484, invCountY = 172;

    private int selectedItem = 0;

    public Inventory( GameHandler handler) {
        this.handler = handler;
        inventoryItems = new ArrayList<Item>() ;

    }

    public void tick() {

        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_E) ) {
            active = !active;
        }

        if(!active)
            return ;

        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_UP)) {
            selectedItem-- ;
        }
        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_DOWN)) {
            selectedItem++ ;
        }

        if(selectedItem<0)
            selectedItem = inventoryItems.size()-1 ;
        else if ( selectedItem >= inventoryItems.size())
            selectedItem = 0;

    }

    public void render(Graphics g) {

        if (!active)
            return;

        g.drawImage(Assets.inventoryScreen , x , y , INVENTORY_WIDTH , INVENTORY_HEIGHT , null ) ;

        int len = inventoryItems.size() ;
        if (len == 0 )
            return  ;

        for (int i = -5 ; i < 6 ; i ++) {
            if( selectedItem + i < 0 || selectedItem + i >= len )
                continue;

            if(i == 0){
                Text.drawString(
                        g,
                        "> " + inventoryItems.get(selectedItem + i).getName() + " <",
                        invListCenterX, invListCenterY + i * invListSpacing,
                        true,
                        Color.YELLOW, Assets.font28);
            }else{
                Text.drawString(
                        g,
                        inventoryItems.get(selectedItem + i).getName(),
                        invListCenterX, invListCenterY + i * invListSpacing,
                        true,
                        Color.WHITE, Assets.font28);

            }
        }

        Item item = inventoryItems.get(selectedItem);
        g.drawImage(item.getTexture() , invImageX , invImageY , invImageWidth , invImageHeight ,null) ;

        Text.drawString(g , Integer.toString(item.getCount()) , invCountX , invCountY , true , Color.WHITE , Assets.font28) ;
    }

       public void addItem(Item item) {
        for( Item i : inventoryItems) {
            if( i.getId() == item.getId() ) {
                i.setCount(i.getCount() + item.getCount());
                return;
            }
        }

        inventoryItems.add(item);
    }


    // Getter Setter
    public GameHandler getHandler() { return handler; }
    public void setHandler(GameHandler handler ) { this.handler = handler ;}

    public int getX() { return x; }
    public void setX(int x) { this.x = x ; }

    public int getY() { return y; }
    public void setY(int y) { this.y = y ; }


    public boolean isActive() {return active ;}
    public Item getCurrentItem() {return inventoryItems.get(selectedItem) ;}
    public Item getInventoryItem(int id) { return inventoryItems.get(id) ;}

}
