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

    // drawSnake function
    public void drawSnake(Graphics g) {
        g.setColor(Color.GREEN);
        for (Node n : snakeBody) {
            g.fillOval(n.x, n.y, SnakeGame.cellsize, SnakeGame.cellsize);
        }
    }
}


