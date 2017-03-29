package game.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Created by Franck on 22/03/2017.
 */
public class MouseManager implements MouseListener, MouseMotionListener {

    private boolean leftPressed , rightPressed ;
    public boolean attack ;

    private int mouseX , mouseY ;

    public MouseManager () {

    }

    // Implements methode


    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) { // left Button
            leftPressed = true ;
        } else if (e.getButton() == MouseEvent.BUTTON3) { // Right Button
            rightPressed = true ;
            attack = true ;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) { // left Button
            leftPressed = false ;
        } else if (e.getButton() == MouseEvent.BUTTON3) { // Right Button
            rightPressed = false ;
            attack = false ;
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX  = e.getX();
        mouseY = e.getY() ;
    }

    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    @Override
    public void mouseDragged(MouseEvent e) {}
    @Override
    public void mouseClicked(MouseEvent e) {}


    // Getter And Setter
    public boolean isLeftPressed() { return leftPressed ;}
    public boolean isRightPressed() { return rightPressed ;}
    public int getMouseX() { return mouseX; }
    public int getMouseY() { return mouseY; }

}
