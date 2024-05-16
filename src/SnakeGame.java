import javax.swing.*;
import java.awt.*;

public class SnakeGame extends JPanel {

    // Game configuration
    public static final int cellsize = 20; // Size of the cell
    public static int width = 400; // Panel
    public static int height = 400; // Panel
    public static int row = height / cellsize;
    public static int col = width / cellsize;
    private Snake snake;
    private Fruit fruit;

    public SnakeGame() {
        snake = new Snake();
        fruit = new Fruit();
    }

    // Draw the snake
    @Override
    public void paintComponent(Graphics g) {
        // draw background
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);

        // draw snake
        snake.drawSnake(g);

        //draw snake
        fruit.drawFruit(g);
    }

    // set the panel
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }

    // Executing the code
    public static void main(String[] args) {
        // Create JFrame window
        JFrame window = new JFrame("Snake Game");

        // exit the program when close window
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // set window's content panel to new SnakeGame instance (SnakeGame inherit from JPanel)
        window.setContentPane(new SnakeGame());

        // adjust window size to fit the content
        window.pack();

        // put window in the middle of screen
        window.setLocationRelativeTo(null);

        // make window Visible
        window.setVisible(true);

        // Set unable to Resize the window
        window.setResizable(false);
    }
}