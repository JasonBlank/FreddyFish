package objects;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.IOException;

public class Character {
    private double Gforce;
    private double Xvelocity = 100, Yvelocity = 0.01;
    private double xdouble = 10.0, ydouble = 10.0;
    private double Xacc = 0;
    private int x = 10, y = 10;
    private Window window;
    private Graphics2D g2;
    private boolean bufferswitch = false;
    private double bufferx = 0.0, buffery = 0.0;
    private Image im = main.Window.loadImage("plane.png");

    public Character(double Gforce, Window window){
        this.Gforce = Gforce;
        this.window = window;
        g2 = (Graphics2D) window.getGraphics();
    }

    public void update(double deltaT){
        if(Yvelocity <= 400 && Yvelocity != 0){                       //Max vertical speed set at 400
            Yvelocity += deltaT * Gforce;
        }
        if(Xvelocity <= 200){//Max horizontal speed set at 200
            if(Yvelocity == 0) Xvelocity = 0;
            if(Xvelocity > 0) {                    //Bounce to the other wall when bounced upon one
                Xvelocity += deltaT * Xacc;
            }
            if(Xvelocity < 0){                      //Same ^
                Xvelocity = deltaT * Xacc;
            }
        }
        bufferx += deltaT;
        buffery += deltaT;
        if(buffery > .1) {
            if (y <= window.getHeight() - 100) ;
            else Yvelocity = 0;
            buffery = 0;
        }
        if(bufferx > .01) {
            if (x <= window.getWidth() - 200 & x >= 0) ;
            else Xvelocity = -Xvelocity;
            bufferx = 0;
        }

        ydouble += deltaT * Yvelocity;
        xdouble += deltaT * Xvelocity;
        y = (int) ydouble;
        x = (int) xdouble;

    }

    public void draw(Graphics2D g2){
        Rectangle r = new Rectangle(x, y, 10,10);
        g2.draw(r);
        g2.drawImage(im, x,y,x+50,y + 50,0,0,400,400,null);
        AffineTransform aff = new AffineTransform();
        aff.rotate(Math.PI*.5);
    }

    public static BufferedImage loadImage(String path){
        try {
            return ImageIO.read(Character.class.getResource(path));
        } catch (IOException e) {
            System.out.println("error 404");
        }
        return null;
    }
}
