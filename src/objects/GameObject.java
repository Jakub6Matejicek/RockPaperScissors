package objects;

import java.awt.*;
import java.util.Random;

public class GameObject extends Rectangle {

    private int xPosition;
    private int yPosition;
    private int speed;
    private int direction;
    final int size = 32;
    private String imagePath;

    public GameObject(int x, int y, int speed){
        setyPosition(new Random().nextInt(y-size));
        setxPosition(new Random().nextInt(x-size));
        setDirection(2);
        this.speed = speed;
    }

    @Override
    public String toString() {
        return "GameObject{" +
                "xPosition=" + xPosition +
                ", yPosition=" + yPosition +
                ", speed=" + speed +
                ", direction=" + direction +
                '}';
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
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
        g.setColor(Color.red);
        g.dispose();
    }

    public void movement(int x, int y){
        if(xPosition >= (x-size) && direction == 0){
            direction = 2;
        }else if(xPosition >= (x-size) && direction == 1){
            direction = 3;
        }else if(xPosition <= 0 && direction == 2){
            direction = 0;
        }else if(xPosition <= 0 && direction == 3){
            direction = 1;
        }else if(yPosition >= (y-size) && direction == 0){
            direction = 1;
        }else if(yPosition >= (y-size) && direction == 2){
            direction = 3;
        }else if(yPosition <= 0 && direction == 1){
            direction = 0;
        }else if(yPosition <= 0 && direction == 3){
            direction = 2;
        }

        if(this.intersects(this)){
            setDirection(new Random().nextInt(4));
        }
        directions();
    }

    public void directions(){
        if(direction == 0){
            rightTop();
        }else if(direction == 1){
            rightBottom();
        }else if(direction == 2){
            leftTop();
        }else if(direction == 3){
            leftBottom();
        }else{
            rightTop();
            direction =0;
        }

    }

    public void rightTop(){
        xPosition+=speed;
        yPosition+=speed;
    }
    public void rightBottom(){
        xPosition+=speed;
        yPosition-=speed;
    }
    public void leftTop(){
        xPosition-=speed;
        yPosition+=speed;
    }
    public void leftBottom(){
        xPosition-=speed;
        yPosition-=speed;
    }



}
