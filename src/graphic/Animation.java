package graphic;

import java.awt.image.BufferedImage;

/**
 * Created by Franck on 20/03/2017.
 */
public class Animation {

    private int speed, index ;
    private long lastTime , timer ;
    private BufferedImage[] frames ;

    public Animation(int speed , BufferedImage[] frames ){

        this.speed = speed ;
        this.frames = frames ;

        index = 0 ;
        timer = 0 ;
        lastTime = System.currentTimeMillis() ; // current time in millisecond
    }


    public void tick() {
        // the time between now and the last time
        timer += System.currentTimeMillis() - lastTime ;
        lastTime = System.currentTimeMillis() ;

        if( timer > speed ) {
            index++ ;
            timer = 0 ;

            if(index >= frames.length)
                index= 0 ;
        }

    }

    public BufferedImage getCurrentFrame() { return frames[index]; }

}
