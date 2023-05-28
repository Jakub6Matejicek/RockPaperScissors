package graphics;

import objects.GameObject;
import objects.Identity;

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
    private boolean winner;
    private Identity winnerType;

//////////////////////////////////////////////////////////////////////////////////////////
    public Panel(int x, int y, int numberOfObjects, int speed){
        this.setPreferredSize(new Dimension(x, y));
        this.setDoubleBuffered(true);
        this.x = x;
        this.y = y;
        for(int i = 0; i < numberOfObjects; i++){
            objects.add(new GameObject(x, y, speed));
        }
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    public boolean isWinner() {
        return winner;
    }

    @Override
    public void run() {

        double drawInterval = 1000000000/FPS;                       //GameLoop - nesahat
        double nextDrawTime = System.nanoTime() + drawInterval;     ////////////////////

        while(gameThread != null && !winner){
            for(GameObject o: objects){
                update(o);
            }
            checkWinner();
            System.out.println(objects.toString());
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
        System.out.println(winnerType);
        this.setVisible(false);
    }

    public void update(GameObject o){
        o.movement(x, y);
        for(GameObject o2 : objects){
            if(o != o2 && checkCollision(o,o2)){
                o.collision(o2);
                System.out.println("COLLISION DETECTED");
            }
        }
    }
    private boolean checkCollision(GameObject o1, GameObject o2) { //prevzato z chatGPT(klasicky intersects nefungoval)
        Rectangle r1 = new Rectangle(o1.getxPosition(), o1.getyPosition(), o1.getFinalSize(), o1.getFinalSize());
        Rectangle r2 = new Rectangle(o2.getxPosition(), o2.getyPosition(), o2.getFinalSize(), o2.getFinalSize());
        return r1.intersects(r2);
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for(GameObject o : objects){
            g.setColor(o.getColor());
            o.draw(g);
        }
    }

    public void checkWinner(){
        boolean temp = true;
        for(int i = 0; i < objects.size(); i++){
            if(i != 0 && objects.get(i).getType() != objects.get(i-1).getType()){
                temp = false;
            }
        }
        winner = temp;
        if(winner){
            winnerType = objects.get(0).getType();
        }
    }
}
