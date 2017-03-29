package game;

import display.Display;
import game.input.KeyManager;
import game.input.MouseManager;
import game.utils.GameHandler;
import graphic.Assets;
import graphic.Camera;
import graphic.ImageLoader;
import graphic.SpriteSheet;
import states.GameState;
import states.MenuState;
import states.State;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 * Created by Franck on 18/03/2017.
 */
public class Game implements Runnable , Serializable{

    transient private Display display ;

    int x = 0 ;
    private String title ;
    private int width , height ;

    private boolean running = false ;

    private Thread thread ;

    private BufferStrategy bs ;
    transient private Graphics g ;

    //States
    transient public State gameState ;
    transient public State menuState ;

    //Input
    transient private KeyManager keyManager;
    transient private MouseManager mouseManager;

    // Camera
    transient private Camera camera ;

    // Handler
    transient public static GameHandler handler ;

    public static int id ;

    public Game(String title , int width , int height ) {
        this.title = title ;
        this.width = width ;
        this.height = height ;

        keyManager = new KeyManager() ;
        mouseManager = new MouseManager() ;

        handler = new GameHandler(this) ;


    }

    //update
    public void tick() {
        keyManager.tick();

        if(State.getCurrentState() != null) {
            State.getCurrentState().tick() ;
        }
    }

    private void render() {
        bs = display.getCanvas().getBufferStrategy() ;
        if(bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics() ;

        // clear Screen
        g.clearRect(0 , 0 , width , height);
        // Draw here
        if(State.getCurrentState() != null) {
            State.getCurrentState().render(g) ;
        }
        //g.drawImage(Assets.grass , x , 0 , null) ;
        //End Drawing
        bs.show();
        g.dispose();

    }

    // initialize all the graphics of the game
    public void init(){
        display = new Display(title , width , height) ;

        // AddKeyListener need the instance of class called on event
        display.getFrame().addKeyListener(keyManager);
        // AddMouseListener
        display.getFrame().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);


        // Init the sheet
        Assets.init() ;



        camera = new Camera(handler , 0 , 0) ;

        gameState = new GameState(handler) ;
        menuState = new MenuState(handler) ;
        State.setCurrentState(gameState);
    }

    public void run() {

        init() ;

        int fps = 60 ;
        // 1 000 000 000 nano secondes = 1 seconde
        double timePerTick = 1000000000 / fps  ;
        double delta = 0 ;
        long now ;
        long lastTime = System.nanoTime() ; // return the current time of the computer in nanosec
        long timer = 0 ;
        int ticks = 0 ;

        while(running) {

            now = System.nanoTime() ;
            delta +=  (now - lastTime) / timePerTick ;
            timer += now - lastTime ;
            lastTime = now ;

            if (delta >= 1 ) {
                tick() ;
                render();
                ticks++;
                delta-- ;
            }
            if ( timer >= 1000000000) {
                System.out.println("ticks and frame " + ticks);
                ticks = 0  ;
                timer = 0 ;
            }
        }
        stop() ;
    }


    // use synchronized when you work with thread directly
    public synchronized void start() {

        if(running)
            return ;
        running = true ;
        thread = new Thread(this) ; // run a Game instance
        thread.start() ; // call the run method
    }

    public synchronized void stop() {

        if(!running)
            return;
        running = false ;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Getter Setter
    public KeyManager getKeyManager() { return keyManager ; }
    public MouseManager getMouseManager() { return mouseManager ; }
    public Camera getCamera() { return camera ; }

    public int getWidth() { return width ; }
    public void setWidth(int width) { this.width = width ; }

    public int getHeight() { return height ; }
    public void setHeight(int height) { this.height = height ; }


    public GameHandler getHandler() { return handler ;}



}
