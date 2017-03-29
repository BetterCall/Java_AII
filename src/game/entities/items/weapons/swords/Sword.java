package game.entities.items.weapons.swords;

import game.entities.items.Item;
import game.utils.GameHandler;

import java.awt.image.BufferedImage;

/**
 * Created by Franck on 24/03/2017.
 */
public class Sword extends Item {
    public Sword(GameHandler handler, float x, float y, int width, int heigth, BufferedImage texture, String name, int id) {
        super(handler, x, y, width, heigth, texture, name, id);
    }
}
