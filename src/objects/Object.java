package objects;

import java.awt.*;

enum definition{
            ROCK,
            PAPER,
            SCISSORS,
        }

public class Object extends Rectangle {

    private definition d;
    private int xDirection;
    private int yDirection;
    private String imagePath;

    public Object(int i){
        switch(i){
            case 1:
                d = definition.ROCK;
                imagePath = "images/rock.png";
                break;
            case 2:
                d = definition.PAPER;
                imagePath = "images/paper.png";
                break;
            case 3:
                d = definition.SCISSORS;
                imagePath = "images/scissors.png";
                break;
        }
    }

    public void draw(Graphics g){

    }



}
