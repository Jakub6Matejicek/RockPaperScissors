package graphics;

import objects.GameObject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Panel extends JPanel implements Runnable{
//////////////////////////////////////////////////////////////////////////////////////////
    private ArrayList<GameObject> objects = new ArrayList<>();
    private Thread gameThread;
    private int x;
    private int y;
    private int FPS = 30;
    GameObject test;

//////////////////////////////////////////////////////////////////////////////////////////
    public Panel(int x, int y, int numberOfObjects, int speed){
        this.setPreferredSize(new Dimension(x, y));
        this.setDoubleBuffered(true);
        this.x = x;
        this.y = y;
        test = new GameObject(x,y,speed);
        for(int i = 0; i < numberOfObjects; i++){
            objects.add(new GameObject(x, y, speed));
        }
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {

        double drawInterval = 1000000000/FPS;                       //GameLoop - nesahat
        double nextDrawTime = System.nanoTime() + drawInterval;     ////////////////////

        while(gameThread != null){
            for(GameObject o: objects){
                update(o);
            }
            //System.out.println(objects.toString());
            repaint();
//////////////////////////////GameLoop/////////////////////////////////////nesahat
            try {
                double remainingTime = (nextDrawTime - System.nanoTime())/1000000;
                if(remainingTime < 0){
                    remainingTime = 0;
                }
                gameThread.sleep((long) remainingTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            nextDrawTime += drawInterval;
//////////////////////////////////////////////////////////////////////////
        }

    }

    public void update(GameObject o){
        o.movement(x, y);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for(GameObject o : objects){
            o.draw(g);
        }
    }
}
