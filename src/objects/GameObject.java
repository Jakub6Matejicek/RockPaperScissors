package objects;

import java.awt.*;
import java.util.Random;

public class GameObject extends Rectangle {

    private int xPosition;
    private int yPosition;
    private int speed;
    private int direction;
    private Color color;
    final int size = 32;
    private Identity type;

    public GameObject(int x, int y, int speed){
        setyPosition(new Random().nextInt(y-size));
        setxPosition(new Random().nextInt(x-size));
        setDirection(new Random().nextInt(4));
        this.speed = new Random().nextInt(speed)+1;
        type = getRandomEnumValue(Identity.class);
        define();
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

    public int getFinalSize() {
        return size;
    }

    public Identity getType() {
        return type;
    }

    public void setType(Identity type) {
        this.type = type;
    }

    public Color getColor() {
        return color;
    }

    public void draw(Graphics g){
        g.fillRect(xPosition, yPosition, size, size);
        define();
        g.setColor(color);
    }

    public void movement(int x, int y){
        if(xPosition >= (x-size) && direction == 0){
            direction = 2;
            this.speed = new Random().nextInt(speed)+1;
        }else if(xPosition >= (x-size) && direction == 1){
            direction = 3;
            this.speed = new Random().nextInt(speed)+1;
        }else if(xPosition <= 0 && direction == 2){
            direction = 0;
            this.speed = new Random().nextInt(speed)+1;
        }else if(xPosition <= 0 && direction == 3){
            direction = 1;
            this.speed = new Random().nextInt(speed)+1;
        }else if(yPosition >= (y-size) && direction == 0){
            direction = 1;
            this.speed = new Random().nextInt(speed)+1;
        }else if(yPosition >= (y-size) && direction == 2){
            direction = 3;
            this.speed = new Random().nextInt(speed)+1;
        }else if(yPosition <= 0 && direction == 1){
            direction = 0;
            this.speed = new Random().nextInt(speed)+1;
        }else if(yPosition <= 0 && direction == 3){
            direction = 2;
            this.speed = new Random().nextInt(speed)+1;
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

    public void collision(GameObject o){
        int temp = this.getDirection();
        while(getDirection() == temp){
            setDirection(new Random().nextInt(4));
        }
        if(getDirection() == 0){
            o.setDirection(3);
        }else if(getDirection() == 1){
            o.setDirection(2);
        }else if(getDirection() == 2){
            o.setDirection(1);
        }else if(getDirection() == 3){
            o.setDirection(0);
        }



        if(this.getType() == Identity.PAPER && o.getType() == Identity.ROCK){
            o.setType(Identity.PAPER);
        }else if(this.getType() == Identity.SCISSORS && o.getType() == Identity.PAPER){
            o.setType(Identity.SCISSORS);
        }else if(this.getType() == Identity.ROCK && o.getType() == Identity.SCISSORS){
            o.setType(Identity.ROCK);
        }else if(this.getType() == Identity.PAPER && o.getType() == Identity.SCISSORS){
            this.setType(Identity.SCISSORS);
        }else if(this.getType() == Identity.SCISSORS && o.getType() == Identity.ROCK){
            this.setType(Identity.ROCK);
        }else if(this.getType() == Identity.ROCK && o.getType() == Identity.PAPER){
            o.setType(Identity.PAPER);
        }else if(this.getType() == Identity.PAPER && o.getType() == Identity.LIZARD){
            this.setType(Identity.LIZARD);
        }else if(this.getType() == Identity.PAPER && o.getType() == Identity.SPOCK){
            o.setType(Identity.PAPER);
        }else if(this.getType() == Identity.ROCK && o.getType() == Identity.LIZARD){
            o.setType(Identity.ROCK);
        }else if(this.getType() == Identity.ROCK && o.getType() == Identity.SPOCK){
            this.setType(Identity.SPOCK);
        }else if(this.getType() == Identity.SCISSORS && o.getType() == Identity.LIZARD){
            o.setType(Identity.SCISSORS);
        }else if(this.getType() == Identity.SCISSORS && o.getType() == Identity.SPOCK){
            this.setType(Identity.SPOCK);
        }else if(this.getType() == Identity.SPOCK && o.getType() == Identity.PAPER){
            this.setType(Identity.PAPER);
        }else if(this.getType() == Identity.SPOCK && o.getType() == Identity.LIZARD){
            this.setType(Identity.LIZARD);
        }else if(this.getType() == Identity.SPOCK && o.getType() == Identity.ROCK){
            o.setType(Identity.SPOCK);
        }else if(this.getType() == Identity.SPOCK && o.getType() == Identity.SCISSORS){
            o.setType(Identity.SPOCK);
        }else if(this.getType() == Identity.LIZARD && o.getType() == Identity.PAPER){
            o.setType(Identity.LIZARD);
        }else if(this.getType() == Identity.LIZARD && o.getType() == Identity.ROCK){
            this.setType(Identity.ROCK);
        }else if(this.getType() == Identity.LIZARD && o.getType() == Identity.SCISSORS){
            this.setType(Identity.SCISSORS);
        }else if(this.getType() == Identity.LIZARD && o.getType() == Identity.SPOCK){
            o.setType(Identity.LIZARD);
        }
    }

    public static <T extends Enum<?>> T getRandomEnumValue(Class<T> enumClass) { //ChatGPT
        Random random = new Random();
        T[] values = enumClass.getEnumConstants();
        int index = random.nextInt(values.length);
        return values[index];
    }

    public void define(){
        if(type == Identity.PAPER){
            color = new Color(248, 224, 178);
        }else if(type == Identity.ROCK){
            color = Color.DARK_GRAY;
        }else if(type == Identity.SCISSORS){
            color = Color.LIGHT_GRAY;
        }else if(type == Identity.LIZARD){
            color = Color.GREEN;
        }if(type == Identity.SPOCK){
            color = Color.BLUE;
        }
    }

}
