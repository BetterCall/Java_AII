package display;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Franck on 18/03/2017.
 */
public class Display {

    private JFrame frame ;
    private Canvas canvas ;

    private String title ;
    private int width , height ;

    public Display(String title , int width , int height ){
        this.title = title ;
        this.width = width ;
        this.height = height ;

        createDisplay() ;
    }

    private void createDisplay() {

        frame = new JFrame(title) ;
        frame.setSize(width , height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        canvas = new Canvas() ;
        canvas.setPreferredSize(new Dimension(width , height));
        canvas.setMaximumSize(new Dimension(width , height));
        canvas.setMinimumSize(new Dimension(width , height));
        canvas.setFocusable(false);

        frame.add(canvas) ;
        // to be able to see all the canvas
        frame.pack();
    }

    public Canvas getCanvas() {
        return this.canvas ;
    }

    public JFrame getFrame() {return frame ;}




}

