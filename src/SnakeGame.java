import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

public class SnakeGame extends JPanel implements KeyListener {

    // Game configuration
    public static final int cellsize = 20; // Size of the cell
    public static int width = 400; // Panel
    public static int height = 400; // Panel
    public static int row = height / cellsize;
    public static int col = width / cellsize;
    private Snake snake;
    private Fruit fruit;
    private Timer t;
    private int speed = 100;
    private static String direction;
    private Boolean canChange = true;

    public SnakeGame() {
        snake = new Snake();
        fruit = new Fruit();

        // something like setInterval in JavaScript
        t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                repaint();
            }
        }, 0, speed);

        direction = "Right";

        // add KeyListener
        addKeyListener(this);
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

        // remove snake tail then put in front of the head
        int snakeX = snake.getSnakeBody().getFirst().x;
        int snakeY = snake.getSnakeBody().getFirst().y;
        if (direction.equals("Left")) {
            snakeX -= cellsize;
        } else if (direction.equals("Right")) {
            snakeX += cellsize;
        } else if (direction.equals("Up")) {
            snakeY -= cellsize;
        } else if (direction.equals("Down")) {
            snakeY += cellsize;
        }
        Node newHead = new Node(snakeX, snakeY);
        snake.getSnakeBody().removeLast();
        snake.getSnakeBody().add(0, newHead);

        // make canChange => true
        this.canChange = true;

        requestFocusInWindow();
    }

    // set the panel
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }

    /*-----------From KeyListener------------*/
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int k = e.getKeyCode();

        // check if canChange
        if (canChange) {
            if (k == KeyEvent.VK_A && !direction.equals("Right")) {
                direction = "Left";

            } else if (k == KeyEvent.VK_D && !direction.equals("Left")) {
                direction = "Right";

            } else if (k == KeyEvent.VK_W && !direction.equals("Down")) {
                direction = "Up";

            } else if (k == KeyEvent.VK_S && !direction.equals("Up")) {
                direction = "Down";
            }
        }
        canChange = false;
    }

    @Override
    public void keyReleased(KeyEvent e) {

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