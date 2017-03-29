package graphic;

import game.entities.creatures.Player;
import game.entities.creatures.monsters.Monster;
import game.entities.items.weapons.swords.MetalSword;
import game.entities.items.weapons.swords.WoodSword;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Franck on 18/03/2017.
 */
public class Assets {
    //Tiles size
    private static final int tileMapWidth = 32 , tileMapHeight = 32 ;
    private static final int playerTileWidth = 40 , playerTileHeight = 48 ;

    public static Font font28 ;

    public static BufferedImage[] map ;
    public static BufferedImage[][] player;
    public static BufferedImage woodSword ;
    public static BufferedImage metalSword ;
    public static BufferedImage magic ;
    public static BufferedImage life ;

    public static BufferedImage[] monsterYellow ;
    public static BufferedImage[][] monsterBlue ;

    public static BufferedImage inventoryScreen ;

    public static void init() {

        font28 = FontLoader.loadFont("res/font/slkscr.ttf" , 28) ;

        // map sprite load
        SpriteSheet mapSheet = new SpriteSheet(ImageLoader.loadImage("/textures/tileset.png")) ;
        //SpriteSheet mapSheetB = new SpriteSheet(ImageLoader.loadImage("/textures/tilesetB.png")) ;

        map = new BufferedImage[400] ;

        for( int y = 0 ; y <  40  ; y++) {
            for (int x = 0; x <  10 ; x++) {
                map[ y * 10 + x ] =  mapSheet.crop(
                        x *  32 ,
                        y * 32 ,
                        32,32
                );
            }
        }
        // end map sprite init

        // Player sprite load
        SpriteSheet playerSheet = new SpriteSheet(ImageLoader.loadImage("/textures/player.png")) ;
        player = new BufferedImage[Player.STATE_NUMBER][Player.FRAME_NUMBER] ;

        for( int y = 0 ; y < Player.STATE_NUMBER ; y++) {
            for( int x = 0 ; x < Player.FRAME_NUMBER ; x++) {
                player[y][x] =  playerSheet.crop(
                                    x * 40 ,
                                    y * 48 ,
                                    Player.PLAYER_WIDTH ,
                                    Player.PLAYER_HEIGHT
                                );
            }
        }
        // End player sprite init

        // Monsters

        // MONSTER 1
        SpriteSheet monster1Sheet = new SpriteSheet(ImageLoader.loadImage("/textures/monster1.png")) ;
        monsterBlue = new BufferedImage[Monster.STATE_NUMBER][Monster.FRAME_NUMBER] ;
        for( int y = 0 ; y < Monster.STATE_NUMBER ; y++) {
            for( int x = 0 ; x < Monster.FRAME_NUMBER ; x++) {
                monsterBlue[y][x] =  monster1Sheet.crop( x * Monster.MONSTER_WIDTH , y * Monster.MONSTER_HEIGHT ,Monster.MONSTER_WIDTH , Monster.MONSTER_HEIGHT );
            }
        }


        //ENd monster



        // Sword Sprite
        SpriteSheet woodSwordSheet = new SpriteSheet(ImageLoader.loadImage("/textures/woodsword.png")) ;
        woodSword = woodSwordSheet.crop(0 , 0 , WoodSword.WIDTH , WoodSword.HEIGHT); //20 48

        SpriteSheet metalSwordSheet = new SpriteSheet(ImageLoader.loadImage("/textures/metalsword.png")) ;
        metalSword = metalSwordSheet.crop(0 , 0 , MetalSword.WIDTH , MetalSword.HEIGHT); //20 48
        // End Sword

        // magic Sprite
        SpriteSheet magicSheet = new SpriteSheet(ImageLoader.loadImage("/textures/magic.png")) ;
        magic = magicSheet.crop(0 , 0 , 16 , 16);
        // End Sword

        // magic Sprite
        SpriteSheet lifeSheet = new SpriteSheet(ImageLoader.loadImage("/textures/life.png")) ;
        life = lifeSheet.crop(0 , 0 , 32 , 32);
        // End Sword

        // Inventory screen
        SpriteSheet inventorySheet = new SpriteSheet(ImageLoader.loadImage("/textures/inventoryScreen.png")) ;
        inventoryScreen = inventorySheet.crop(0 , 0 , 512 , 384 ) ;

        // END inventory scree


    }
}
