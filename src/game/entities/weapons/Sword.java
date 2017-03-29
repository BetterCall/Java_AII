package game.entities.weapons;

import game.utils.GameHandler;
import graphic.Assets;
import javafx.animation.RotateTransition;
import javafx.util.Duration;

import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * Created by Franck on 21/03/2017.
 */
public class Sword extends Weapon {

    public static int SWORD_WIDTH = 20, SWORD_HEIGHT = 48;
    private int rotation ;

    public Sword(GameHandler handler, float x, float y) {
        super(handler, x, y, SWORD_WIDTH, SWORD_HEIGHT);
        this.power = 4.0f ;
    }

    public void die() {

    }

    @Override
    public void tick() {

    }

    public void render (Graphics g) {

    }

    @Override
    public void render(Graphics g , int direction ) {

    }
}
