package Package;

import java.awt.*;
import java.util.ArrayList;

// snake body
public class Snake {

    private ArrayList<Node> snakeBody;

    // initialize the snake
    public Snake() {
        snakeBody = new ArrayList<>();
        snakeBody.add(new Node(80, 0));
        snakeBody.add(new Node(60, 0));
        snakeBody.add(new Node(40, 0));
        snakeBody.add(new Node(20, 0));
    }

    public ArrayList<Node> getSnakeBody() {
        return snakeBody;
    }

    // drawSnake function
    public void drawSnake(Graphics g) {
        // make the head be Orange
        for (int i = 0; i < snakeBody.size(); i++) {
            if (i == 0) {
                g.setColor(Color.ORANGE);
            } else {
                g.setColor(Color.GREEN);
            }
            Node n = snakeBody.get(i);
            g.fillOval(n.x, n.y, SnakeGame.cellsize, SnakeGame.cellsize);
        }
    }
}


