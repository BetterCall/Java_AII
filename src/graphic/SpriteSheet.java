package graphic;

import java.awt.image.BufferedImage;

/**
 * Created by Franck on 18/03/2017.
 */
public class SpriteSheet {
    private BufferedImage sheet ;

    public SpriteSheet(BufferedImage sheet) {
        this.sheet = sheet ;

    }

    /**
     * @param x
     * @param y
     * @param width
     * @param height
     * @return
     */
    public BufferedImage crop(int x , int y , int width , int height) {
        return this.sheet.getSubimage(x , y , width , height);
    }

}
