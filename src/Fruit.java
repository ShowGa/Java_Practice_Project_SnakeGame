import java.awt.*;

public class Fruit {
    private int x;
    private int y;

    // random fruit appear
    public Fruit() {
        this.x = (int) Math.floor(Math.random() * SnakeGame.col) * SnakeGame.cellsize;
        this.y = (int) Math.floor(Math.random() * SnakeGame.row) * SnakeGame.cellsize;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    // Graphics g => using in the painComponent in SnakeGame class
    // TODO Overlapping the snake => for loop to regenerate the fruit
    public void drawFruit(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(this.x, this.y, SnakeGame.cellsize, SnakeGame.cellsize);
    }
}
