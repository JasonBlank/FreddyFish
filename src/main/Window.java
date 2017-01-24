package main;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Window extends JFrame{
    JFrame jframe;
    Canvas canvas;
    int width = 1000, height = 600;

    public Window(){
        jframe = new JFrame("Coole shit");
        jframe.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jframe.setSize(width,height);
        jframe.setVisible(true);
        //jframe.setIconImage(loadImage("penis.png"));
        canvas = new Canvas();
        jframe.add(canvas);
    }

    public int getHeight() {
        return height;
    }

    public int getWidth(){
        return width;
    }

    public Canvas getCanvas(){
        return canvas;
    }

    public Window getWindow(){return this;};

    public static BufferedImage loadImage(String path){
        try {
            return ImageIO.read(Window.class.getResource(path));
        } catch (IOException e) {
            System.out.println("error 404");
        }
        return null;
    }

}
