package main;

import objects.Character;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

public class GameLoop extends Thread{

    //Constants
    int fps = 60;           //Frames per second
    int cps = 50;          //Cycles per second
    double G = 300;        //Gravity

    //Initial variables
    boolean running = false;
    Window window;
    BufferStrategy bs = null;
    Graphics g;
    Graphics2D g2;

    Character character;

    public static void main(String[] args){
        GameLoop gameloop = new GameLoop();
        gameloop.start();
    }

    public void start(){
        if(running) return;
        running = true;
        Thread main_thread = new Thread(this);
        main_thread.start();
    }


    public void run(){
        double delta_time_render = 0, delta_time_framecount = 0, lasttime, now = System.nanoTime();
        int framecount = 0;

        lasttime = now;
        window = new Window();
        character = new Character(G, window.getWindow());
        render();                                                   //Eerste render


        while(running){                                             //Main loop
            now = System.nanoTime();                                //Time right now
            delta_time_render += (now - lasttime)/1000000000;       //Seconds since last render
            delta_time_framecount += (now - lasttime)/1000000000;

            if(delta_time_render * fps > 1){                        //Make the loop only render at the speed of the set fps
                framecount ++;
                character.update(delta_time_render);
                render();
                delta_time_render = 0;
            }

            if(delta_time_framecount > 1){                          //Output of framerate
                System.out.println(framecount);
                delta_time_framecount = 0;
                framecount = 0;
            }


            lasttime = now;                             //Set now-time to last time
        }
    }


    void render(){

        if (bs == null){
            bs = window.getCanvas().getBufferStrategy();
            if (bs == null){
                window.getCanvas().createBufferStrategy(2);
                return;
            }
        }
        g = bs.getDrawGraphics();
        g2 = (Graphics2D) g;
        g.clearRect(0,0,window.getWidth(),window.getHeight());

        draw();
        bs.show();
        g2.dispose();
    }


    void draw(){
        character.draw(g2);
    }

}
