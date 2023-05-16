package graphics;

import objects.GameObject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Panel extends JPanel implements Runnable{

    private ArrayList<GameObject> objects = new ArrayList<>();
    private ArrayList<Graphics> graphics = new ArrayList<>();
    private int numberOfObjects = 1;
    private Thread gameThread;
    private int x;
    private int y;
    private int FPS = 30;
    public Panel(int x, int y){
        this.setPreferredSize(new Dimension(x, y));
        this.setDoubleBuffered(true);
        this.x = x;
        this.y = y;
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {
        for(int i = 0; i < numberOfObjects; i++){
            objects.add(new GameObject(x, y, 6));
        }

        double drawInterval = 1000000000/FPS;                       ////////////////
        double nextDrawTime = System.nanoTime() + drawInterval;     //GameLoop
        int direction = new Random().nextInt(4);             ////////////////

        while(gameThread != null){

            update(direction);
            for(GameObject o : objects){
                repaint(o);
            }


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

    public void update(int temp){
        for(GameObject o: objects){
            o.move(x, y, temp);
        }
    }

    public void paintComponent(Graphics g, GameObject o){
        super.paintComponent(g);
            o.draw(g);
    }
}
