package Package;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Fruit {
    private int x;
    private int y;
    private ImageIcon img;

    // random fruit appear
    public Fruit() {
        img = new ImageIcon("fruit.png");
        this.x = (int) Math.floor(Math.random() * SnakeGame.col) * SnakeGame.cellsize;
        this.y = (int) Math.floor(Math.random() * SnakeGame.row) * SnakeGame.cellsize;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    // Graphics g => using in the painComponent in Package.SnakeGame class
    // TODO Overlapping the snake => for loop to regenerate the fruit
    public void drawFruit(Graphics g) {

        img.paintIcon(null, g, this.x, this.y);
    }

    public void setNewLocation(Snake s) {
        int new_x;
        int new_y;
        boolean overlapping;

        do {
            new_x = (int) Math.floor(Math.random() * SnakeGame.col) * SnakeGame.cellsize;
            new_y = (int) Math.floor(Math.random() * SnakeGame.row) * SnakeGame.cellsize;
            overlapping = check_overlap(new_x, new_y, s);
        } while (overlapping);

        this.x = new_x;
        this.y = new_y;
    }

    // make sure the new fruit is not overlapping on the snake
    public boolean check_overlap(int new_x, int new_y, Snake s) {
        ArrayList<Node> snake_body = s.getSnakeBody();

        for (int j = 0; j < snake_body.size(); j++) {
            if (new_x == snake_body.get(j).x && new_y == snake_body.get(j).y) {
                return true;
            }
        }

        return false;
    }
};
