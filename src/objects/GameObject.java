package objects;

import java.awt.*;
import java.util.Random;

public class GameObject extends Rectangle {

    private int xPosition;
    private int yPosition;
    private int speed;
    final int size = 32;
    private String imagePath;

    public GameObject(int x, int y, int speed){
        setyPosition(new Random().nextInt(y-size));
        setxPosition(new Random().nextInt(x-size));
        this.speed = speed;
    }

    public int getxPosition() {
        return xPosition;
    }

    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    public void draw(Graphics g){
        g.fillRect(xPosition, yPosition, size, size);
        g.setColor(Color.BLUE);
        g.dispose();
    }

    public void move(int x, int y, int temp){
        if(temp == 0 && xPosition < x - speed){
            xPosition-=speed;
        } else if(temp == 1 && xPosition > speed+size){
            xPosition+=speed;
        }else if(temp == 2 && yPosition < y - speed){
            yPosition+=speed;
        }else if(temp == 3 && yPosition > speed+size){
            yPosition-=speed;
        }
    }



}
