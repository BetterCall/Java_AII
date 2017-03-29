package states;

import game.Game;
import game.entities.creatures.Player;
import game.map.Map;
import game.tiles.Tile;
import game.utils.GameHandler;
import graphic.Assets;

import java.awt.*;

/**
 * Created by Franck on 18/03/2017.
 */
public class GameState extends State{

    private Player player ;
    public static Map map ;

    public GameState(GameHandler handler) {

        super(handler) ;
        map  = new Map(handler , "res/map/map1.txt") ;
        handler.setMap(map);
        //player = new Player(handler ,200 , 10);

        //game.getCamera().move(0, 0);
    }

    @Override
    public void tick() {
        map.tick();

        map.getItemManager().tick() ;
        map.getEntityManager().tick() ;


        //for ( int i = 0 ; i < handler.getMap().getMonsterNumber() ; i++) {
        //map.getMonster(i).tick();
        //}
    }

    @Override
    public void render(Graphics g) {

        map.render(Map.tiles2 ,g);
        map.render(Map.tiles1 ,g);
        map.getEntityManager().render(g) ;
        map.getItemManager().render(g);

        map.render(Map.tiles3 ,g);
        map.getEntityManager().getPlayer().getInventory().render(g);

        //map.getEntityManager().getPlayer(0).getInventory().render(g) ;

    }
}

