package game.map;

import game.Game;
import game.entities.Entity;
import game.entities.EntityManager;
import game.entities.creatures.Player;
import game.entities.creatures.monsters.Monster;
import game.entities.items.Item;
import game.entities.items.ItemManager;
import game.tiles.Tile;
import game.utils.GameHandler;
import game.utils.Utils;
import graphic.Assets;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Created by Franck on 18/03/2017.
 */
public class Map implements Serializable {

    private GameHandler handler ;
    public static final int MAX_MAP_X = 80 , MAX_MAP_Y = 80 ;
    public static final int TILE_NUMBER_X = 10, TILE_NUMBER_Y = 33 ;

    private int width, height = 0 ;
    private int spawnX, spawnY;

    public static int[][] tiles1;
    public static int[][] tiles2;
    public static int[][] tiles3;
    public static int[][] tiles4;

    private Player player ;

    // Entities manager
    private EntityManager entityManager ;
    private ItemManager itemManager ;

    // Monsters
    private Monster monsters[] ;
    private int monsterNumber ;
    public static int MONSTER_MAX = 50 ;


    //Special Tiles
    public static final int TILE_MONSTRE_START = 20 , TILE_MONSTRE_END = 39 ;

    public Map(GameHandler handler , String path){
        this.handler = handler ;

        entityManager = new EntityManager(handler) ;
        itemManager = new ItemManager(handler) ;
        loadMap(path);
        //entityManager.setPlayer(new Player(handler , spawnX , spawnX));

    }

    public void tick(){
        //entityManager.tick();
        //itemManager.tick();

    }

    public void render(int[][] tiles ,  Graphics g){

        // rendering efficiency the map
        int xStart = (int) Math.max(0, handler.getCamera().getxOffset() / Tile.TILE_WIDTH);
        int xEnd = (int) Math.min(width, (handler.getCamera().getxOffset() + handler.getWidth()) / Tile.TILE_WIDTH + 1);
        int yStart = (int) Math.max(0, handler.getCamera().getyOffset() / Tile.TILE_HEIGHT);
        int yEnd = (int) Math.min(height, (handler.getCamera().getyOffset() + handler.getHeight()) / Tile.TILE_HEIGHT + 1);

        for(int y = yStart;y < yEnd;y++){
            for(int x = xStart;x < xEnd;x++){

                g.drawImage(
                        Assets.map[ tiles[ y ][ x ]] ,
                        (int) (x * Tile.TILE_WIDTH - handler.getCamera().getxOffset() ) -1 ,
                        (int ) (y * Tile.TILE_WIDTH - handler.getCamera().getyOffset() ) - 1,
                        Tile.TILE_WIDTH , Tile.TILE_HEIGHT,
                        null
                ) ;

            }
        }

    }

    public int getTile(int[][] tiles, int x, int y){

        // check parameters is in the boundering of the map
        if (x < 0 || y < 0 || x >= width || y >= height ) {
            return 0;
        }

        Tile t ;

        try {
            t = Tile.tiles[tiles[y][x]];
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

        if(t == null)
            return 0;
        return 0;
    }

    private void loadMap(String path) {



        ArrayList<String[]> tokens = new ArrayList<String[]>() ;

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                // process the line.
                tokens.add(line.split("\\s+"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        spawnX = Utils.parseInt(tokens.get(0)[0]) ;
        spawnY = Utils.parseInt(tokens.get(0)[1]) ;

        int tilesetDisplayed  = Utils.parseInt(tokens.get(0)[2]) ;

        System.out.println(spawnX);

        // Initialize map's layer
        tiles1 = new int[MAX_MAP_X][MAX_MAP_Y];
        tiles2 = new int[MAX_MAP_X][MAX_MAP_Y];
        tiles3 = new int[MAX_MAP_X][MAX_MAP_Y];
        tiles4 = new int[MAX_MAP_X][MAX_MAP_Y];

        monsters = new Monster[MONSTER_MAX] ;

        int x , y = 0;

        // 1st line of the first layer
        for (x = 3; x < MAX_MAP_X + 3; x++) {
            int tests = Utils.parseInt(tokens.get(y)[x]);
        }

        // 1st layer
        for (y = 1; y < MAX_MAP_Y; y++) {
            for (x = 0; x < MAX_MAP_X; x++) {
                tiles1[y][x] = Utils.parseInt(tokens.get(y)[x]);

            }
        }

        // 2nd layer
        for (y = 0; y < MAX_MAP_Y; y++) {
            for (x = 0; x < MAX_MAP_X; x++) {
                tiles2[y][x] =  Utils.parseInt(tokens.get(y + MAX_MAP_Y)[x]);
            }
        }

        for (y = 0; y < MAX_MAP_Y; y++) {
            for (x = 0; x < MAX_MAP_X; x++) {
                tiles3[y][x] = Utils.parseInt(tokens.get(y + MAX_MAP_Y * 2 )[x]);
            }
        }

        for (y = 0; y < MAX_MAP_Y; y++) {
            for (x = 0; x < MAX_MAP_X; x++) {
                tiles4[y][x] = Utils.parseInt(tokens.get(y + MAX_MAP_Y * 3 )[x]);

                if (tiles4[y][x] >= TILE_MONSTRE_START && tiles4[y][x] <= TILE_MONSTRE_END ) {

                    if (monsterNumber < MONSTER_MAX) {
                        entityManager.addEntity(
                                new Monster(
                                        handler,
                                        (int) (x * Tile.TILE_HEIGHT),
                                        (int) (y * Tile.TILE_WIDTH),
                                        Monster.MONSTER_WIDTH, Monster.MONSTER_HEIGHT ,
                                        monsterNumber
                                )
                        );
                        monsterNumber++ ;
                    }
                } else if (tiles1[y][x] > 0) {
                    if (x > width) {
                        width = x;
                    }

                    if (y > height) {
                        height = x;
                    }
                }
            }
        }

        y = MAX_MAP_Y * 4;

        width++ ; // = ( width + 1 ) * Tile.TILE_WIDTH ;
        height++  ;// = ( height + 1 ) * Tile.TILE_HEIGHT ;

        System.out.println(" width  => " +width);
        System.out.println("height => " +height);

        entityManager.setPlayer(new Player( spawnX , spawnY , 1) );

    }


/*
    private void loadWorld(String path){

        // Initialize map's layer
        tiles1 = new int[MAX_MAP_X][MAX_MAP_Y];
        tiles2 = new int[MAX_MAP_X][MAX_MAP_Y];
        tiles3 = new int[MAX_MAP_X][MAX_MAP_Y];
        tiles4 = new int[MAX_MAP_X][MAX_MAP_Y];

        monsters = new Monster[MONSTER_MAX] ;

        int x , y = 0 ;

        File f = new File(path);

        //File f = new File(getCacheDirectory() + "/resources/map/map1.txt");
        Scanner scanner = null;
        try {
            scanner = new Scanner(f);

            spawnX = scanner.nextInt() ;
            spawnY = scanner.nextInt() ;

            int tilesetDisplayed  = scanner.nextInt() ;


            // Map size
            width = 0 ; height = 0 ;

            // Couche 1
            for (y = 0; y < MAX_MAP_Y; y++) {
                for (x = 0; x < MAX_MAP_X; x++) {

			        //On lit le numero de la tile et on le copie dans notre tableau
                    tiles1[y][x] = scanner.nextInt() ;
                    // Permet de determiner la taille de la map (voir plus bas)
                    if (tiles1[y][x] > 0) {
                        if (x > width) {
                            width = x;
                        }
                        if (y > height){
                            height = y;
                        }
                    }
                }
            }

            System.out.println(" width => "+width);
            System.out.println(" height => "+height);

            // Couche 2
            for (y = 0; y < MAX_MAP_Y; y++) {
                for (x = 0; x < MAX_MAP_X; x++) {
			        //On lit le num�ro de la tile et on le copie dans notre tableau
                     tiles2[y][x] =  scanner.nextInt() ;
                }
            }

            System.out.println(tiles2.length);

            //Troisi�me couche de tiles
            for (y = 0; y < MAX_MAP_Y; y++) {
                for (x = 0; x < MAX_MAP_X; x++) {

			        // On lit le num�ro de la tile et on le copie dans notre tableau
                    tiles3[y][x] = scanner.nextInt() ;
                }
            }
            //Quatrieme couche de tiles
            for (y = 0; y < MAX_MAP_Y; y++) {
                for (x = 0; x < MAX_MAP_X; x++) {
			        //On lit le num�ro de la tile et on le copie dans notre tableau
                    //tiles4[y][x] =  scanner.nextInt() ;

                    //if (tiles4[y][x] >= TILE_MONSTRE_START && tiles4[y][x] <= TILE_MONSTRE_END ) {


                        if (monsterNumber < MONSTER_MAX ) {

                            entityManager.addEntity(
                                    new Monster (
                                        handler ,
                                        (int) ( y * Tile.TILE_HEIGHT) ,
                                        (int) ( x * Tile.TILE_WIDTH) ,
                                        Monster.MONSTER_WIDTH , Monster.MONSTER_HEIGHT ,
                                        monsterNumber
                                    )
                            );

                            //monsters[monsterNumber] = new Monster(
                            //        handler ,
                            //        (int) ( y * Tile.TILE_HEIGHT) ,
                            //        (int) ( x * Tile.TILE_WIDTH) ,
                            //        Monster.MONSTER_WIDTH , Monster.MONSTER_HEIGHT ,
                            //        monsterNumber
                            //) ;

                            monsterNumber++ ;
                        }
                        tiles4[y][x] = 0 ;
                    }
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        width = (width + 1) * Tile.TILE_WIDTH;
        height = (height + 1) * Tile.TILE_HEIGHT;

        //String file = Utils.loadFileAsString(path);
        //String[] tokens = file.split("\\s+");

        //width = 80 ; //Utils.parseInt(tokens[0]);
        //height = 80 ;// Utils.parseInt(tokens[1]);

    }

*/


    public void detectSpecialTiles( int mapX , int mapY , Monster monster[] ) {
        if (tiles4[mapY][mapX] != 0) {

            //
            //if (tiles4[mapY][mapX] >= TILE_MONSTRE_START && tiles4[mapY][mapX] <= TILE_MONSTRE_END ) {

               // if (monsterNumber < MONSTER_MAX ) {
                   // monsters[monsterNumber] = new Monster(handler , mapX , mapY , Monster.MONSTER_WIDTH , Monster.MONSTER_HEIGHT) ;

                   // monsterNumber++ ;
                //}
                //tiles4[mapY][mapX] = 0 ;
           // }
        }
    }


    public int getWidth() { return width; }
    public void setWidth(int width) { this.width = width; }

    public int getHeight() { return height; }
    public void setHeight(int height) { this.height = height; }

    public int getSpawnX() { return spawnX; }
    public void setSpawnX(int spawnX) { this.spawnX = spawnX ;}

    public int getSpawnY() { return spawnY; }
    public void setSpawnY(int spawnY) { this.spawnY = spawnY ; }

    public int getMonsterNumber() { return monsterNumber ; }
    public void setMonsterNumber(int monsterNumber) { this.monsterNumber = monsterNumber; }

    public Monster getMonster(int i) { return monsters[i] ; }

    public EntityManager getEntityManager() { return entityManager ; }
    public ItemManager getItemManager() { return itemManager ; }

}
