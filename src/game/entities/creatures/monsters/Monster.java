package game.entities.creatures.monsters;

import game.entities.Entity;
import game.entities.creatures.Creature;
import game.entities.items.weapons.swords.MetalSword;
import game.entities.items.weapons.swords.WoodSword;
import game.utils.GameHandler;
import graphic.Assets;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.util.Random;

/**
 * Created by Franck on 21/03/2017.
 */
public class Monster extends Creature {

    public static final int TIME_BETWEEN_2_FRAMES_MONSTER = 3;

    public static final int STATE_NUMBER = 3;
    public static final int FRAME_NUMBER = 8;

    public static final int IATime = 50;
    public static final int MONSTER_WIDTH = 42 , MONSTER_HEIGHT = 42;


    private int monsterNumber ;
    private float saveX, saveY;
    private int IATimer;
    private int hurtDirection ;

    Random r = new Random();


    public Monster(GameHandler handler, float x, float y, int width, int heigth , int monsterNumber) {
        super(handler, x, y, width, heigth);
        this.monsterNumber = monsterNumber ;
        System.out.println("monsteer init ");
    }

    public void die () {

        int mini = 0 , maxi = 3 ;
        int n = mini +(int)( Math.random() * (maxi +1 ))  ;
        System.out.println(n);

        if( n == 1 || n == 3  ) {
            handler
                    .getMap()
                    .getItemManager()
                    .addItem(
                            new MetalSword(handler ,(int)(x) ,
                                    (int)(y))
                                    .createNew(1)
                    );
        } else if (n == 2 ) {
            handler
                    .getMap()
                    .getItemManager()
                    .addItem(
                            new WoodSword( handler , (int)(x) , (int)(y)) .createNew(1)
                    );
        }
    }

    @Override
    public void tick() {
        ia();
        move();
    }

    private void ia() {

        if (deathTimer == 0) {
            //On diminue le timer d'invincibilité et de recul
            if (invincibleTimer > 0)
                invincibleTimer--;

            if (isHurt > 0)
                isHurt--;

            xMove = 0;
            yMove = 0;

            if (x == saveX && isHurt == 0) {
                if (direction == LEFT) {
                    direction = RIGHT;

                    //Sécurité : système d'éjection du monstre pour
                    //l'évacuer s'il reste coincé dans un obstacle
                    if (y == saveY && direction == UP)
                        y += 1;
                    else if (y == saveY && direction == DOWN)
                        y -= 1;
                } else if (direction == RIGHT) {
                    direction = LEFT;

                    //Sécurité : système d'éjection du monstre pour
                    //l'évacuer s'il reste coincé dans un obstacle*
                    if (y == saveY && direction == UP)
                        y += 1;
                    else if (y == saveY && direction == DOWN)
                        y -= 1;
                }

            } else if (y == saveY) {
                if (direction == UP) {
                    direction = DOWN;

                    //Sécurité : système d'éjection du monstre pour
                    //l'évacuer s'il reste coincé dans un obstacle
                    if (x == saveX && direction == LEFT)
                        x += 1;
                    else if (x == saveX && direction == RIGHT)
                        x -= 1;
                } else if (direction == DOWN) {
                    direction = UP;

                    //Sécurité : système d'éjection du monstre pour
                    //l'évacuer s'il reste coincé dans un obstacle
                    if (x == saveX && direction == LEFT)
                        x += 1;
                    else if (x == saveX && direction == RIGHT)
                        x -= 1;
                }

            }

            //Si le timer de l'IA descend en-dessous de 0, il est temps
            //de changer de direction au hasard.
            if (IATimer <= 0 && isHurt == 0) {
                direction = 0 + r.nextInt(4 - 0) ;
                IATimer = IATime;
            } else
                IATimer--;

                //Déplacement du monstre selon la direction
                //s'il est touché
            if (isHurt > 0) {
                if (hurtDirection == LEFT)
                    xMove -= 1;
                else if (hurtDirection == RIGHT)
                    xMove += 1;
                else if (hurtDirection == UP)
                    yMove -= 1;
                else if (hurtDirection == DOWN)
                    yMove += 1;
            } else {
                if (direction == LEFT)
                    xMove -= 1;
                else if (direction == RIGHT)
                    xMove += 1;
                else if (direction == UP)
                    yMove -= 1;
                else if (direction == DOWN)
                    yMove += 1;
            }

            //On sauvegarde les coordonnées du monstre pour gérer le demi-tour
            //avant que mapCollision() ne les modifie.
            saveX = x;
            saveY = y;

        }

        //On détecte les collisions avec les autres monstres

        for(Entity e : handler.getMap().getEntityManager().getEntities() ) {
            if (e != this) {

                if (collideWithCreature(e) ) {
                    if (direction == UP) {
                        direction = DOWN;
                        y += 3 ; //e.getY() + e.getHeight() + 1;

                    } else if (direction == DOWN) {

                        direction = UP;
                        y -=3 ; // e.getY() - height - 1;

                    } else if (direction == RIGHT) {

                        direction = LEFT;
                        x -=3 ;// e.getX() - width - 1;
                    } else if (direction == LEFT) {

                        direction = RIGHT;
                        x += 3 ;// e.getX() + e.getWidth() + 1;
                    }

                }
            }
        }
    }



    @Override
    public void render(Graphics g) {

        /* Gestion du timer */
        // Si notre timer (un compte à rebours en fait) arrive à zéro
        if (frameTimer <= 0) {
            //On le réinitialise
            frameTimer = TIME_BETWEEN_2_FRAMES_MONSTER ;

            //Et on incrémente notre variable qui compte les frames de 1 pour passer à la suivante
            frameNumber++;

            //Mais si on dépasse la frame max, il faut revenir à la première :
            if (frameNumber >= frameMax)
                frameNumber = 0;

        }else
            frameTimer--;



        // si on a ete toucher et qu'on est invisible
        if(invincibleTimer > 0 ) {

            if( frameNumber % 2 == 0 ) {
                if ( direction == RIGHT ) { // Flip the sheet

                    AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
                    tx.translate(- Assets.monsterBlue[ (LEFT + 3)* state][frameNumber].getWidth(null), 0);
                    AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);

                    g.drawImage(
                            op.filter(Assets.monsterBlue[ (LEFT  + 3)* state][frameNumber], null) ,
                            (int) ( x - handler.getCamera().getxOffset() ) ,
                            (int) ( y - handler.getCamera().getyOffset() ) ,
                            width , height,
                            null
                    ) ;
                } else {
                    g.drawImage(
                            Assets.monsterBlue[ direction  * state][frameNumber] ,
                            (int) ( x - handler.getCamera().getxOffset() ) ,
                            (int) ( y - handler.getCamera().getyOffset() ) ,
                            width , height,
                            null
                    ) ;

                }
            } else {} // render nothing

        } else {

            if ( direction == RIGHT ) { // Flip the sheet

                AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
                tx.translate(- Assets.monsterBlue[ (LEFT + 3)* state][frameNumber].getWidth(null), 0);
                AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);

                g.drawImage(
                        op.filter(Assets.monsterBlue[ (LEFT  + 3)* state][frameNumber], null) ,
                        (int) ( x - handler.getCamera().getxOffset() ) ,
                        (int) ( y - handler.getCamera().getyOffset() ) ,
                        width , height,
                        null
                ) ;
            } else {
                g.drawImage(
                        Assets.monsterBlue[ direction  * state][frameNumber] ,
                        (int) ( x - handler.getCamera().getxOffset() ) ,
                        (int) ( y - handler.getCamera().getyOffset() ) ,
                        width , height,
                        null
                ) ;

            }

        }
    }
}
