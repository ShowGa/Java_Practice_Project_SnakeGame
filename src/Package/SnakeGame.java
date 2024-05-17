package Package;

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
    private Boolean canChange;
    private int score;

    public SnakeGame() {
        // or start game
        reset();

        // add KeyListener
        addKeyListener(this);
    }

    // setTime method
    private void setTimer() {
        t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                repaint();
            }
        }, 0, speed);
    }

    // reset game method
    private void reset() {
        score = 0;
        if (snake != null) {
            snake.getSnakeBody().clear();
        }
        canChange = true;
        direction = "Right";
        snake = new Snake();
        fruit = new Fruit();
        setTimer();
    }

    // Draw the snake
    @Override
    public void paintComponent(Graphics g) {
        // check snake hit itself
        // check with if statement
        if (snake.checkSnakeHitItself()) {
            canChange = false;
            // dealing timer
            t.cancel();
            t.purge();
            // dealing Dialog window
            int response = JOptionPane.showOptionDialog(this, "Start again?", "Game Over", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, JOptionPane.YES_NO_OPTION);

            switch(response) {
                case JOptionPane.CLOSED_OPTION:
                    System.exit(0);
                    break;
                case JOptionPane.NO_OPTION:
                    System.exit(0);
                    break;
                case JOptionPane.YES_NO_OPTION:
                    reset();
                    return;
            }

        }

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
        // check if hit the wall => YES 1.run through 2.Game Over
        if (snakeX >= width) {
            snakeX = 0;
        } else if (snakeX < 0) {
            snakeX = width - cellsize;
        } else if (snakeY >= height) {
            snakeY = 0;
        } else if (snakeY < 0) {
            snakeY = height - cellsize;
        }

        Node newHead = new Node(snakeX, snakeY);
        // get fruit then don't remove the last Node
        if (snake.getSnakeBody().getFirst().x == fruit.getX() && snake.getSnakeBody().getFirst().y == fruit.getY()) {
            // 1. New fruit location
            fruit.setNewLocation(snake);
            // 2. Draw new fruit
            fruit.drawFruit(g);
            // 3. add score (do later)

        } else {
            snake.getSnakeBody().removeLast();
        }
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
        JFrame window = new JFrame("Package.Snake Game");

        // exit the program when close window
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // set window's content panel to new Package.SnakeGame instance (Package.SnakeGame inherit from JPanel)
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