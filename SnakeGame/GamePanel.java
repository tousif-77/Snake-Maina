package SnakeGame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.*;

/**
 * The core game panel: handles snake movement, food spawning,
 * collision detection, scoring, and rendering.
 */
public class GamePanel extends JPanel implements ActionListener {

    private final int GRID_WIDTH = GameConstants.GRID_WIDTH;
    private final int GRID_HEIGHT = GameConstants.GRID_HEIGHT;
    private final int TILE_SIZE = GameConstants.TILE_SIZE;
    private final int MAX_PARTS = GRID_WIDTH * GRID_HEIGHT;

    private final int[] snakeX = new int[MAX_PARTS];
    private final int[] snakeY = new int[MAX_PARTS];
    private int bodyParts;

    private int foodX, foodY;
    private int score;

    // direction: 'U', 'D', 'L', 'R'
    private char direction;
    private boolean directionChangedThisTick;

    private boolean running;
    private Timer timer;
    private int currentDelay;

    private final Random random;
    private final GameFrame gameFrame;

    public GamePanel(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
        this.random = new Random();

        setPreferredSize(new Dimension(GameConstants.SCREEN_WIDTH, GameConstants.SCREEN_HEIGHT));
        setBackground(GameConstants.BACKGROUND_COLOR);
        setFocusable(true);
        addKeyListener(new SnakeKeyAdapter());
    }

    /** Resets and starts a fresh game */
    public void startGame() {
        bodyParts = 3;
        score = 0;
        direction = 'R';
        directionChangedThisTick = false;
        currentDelay = GameConstants.INITIAL_DELAY;

        // Initialize snake in the middle of the grid, moving right
        int startX = GRID_WIDTH / 4;
        int startY = GRID_HEIGHT / 2;
        for (int i = 0; i < bodyParts; i++) {
            snakeX[i] = startX - i;
            snakeY[i] = startY;
        }

        spawnFood();
        running = true;

        if (timer != null) {
            timer.stop();
        }
        timer = new Timer(currentDelay, this);
        timer.start();

        repaint();
    }

    /** Places food at a random empty grid cell */
    private void spawnFood() {
        boolean validPosition;
        do {
            validPosition = true;
            foodX = random.nextInt(GRID_WIDTH);
            foodY = random.nextInt(GRID_HEIGHT);

            // Ensure food doesn't spawn on the snake's body
            for (int i = 0; i < bodyParts; i++) {
                if (snakeX[i] == foodX && snakeY[i] == foodY) {
                    validPosition = false;
                    break;
                }
            }
        } while (!validPosition);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw((Graphics2D) g);
    }

    private void draw(Graphics2D g2) {
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        drawGrid(g2);

        if (running) {
            drawFood(g2);
            drawSnake(g2);
            drawScore(g2);
        }
    }

    private void drawGrid(Graphics2D g2) {
        g2.setColor(GameConstants.GRID_COLOR);
        for (int i = 0; i <= GRID_WIDTH; i++) {
            g2.drawLine(i * TILE_SIZE, 0, i * TILE_SIZE, GameConstants.SCREEN_HEIGHT);
        }
        for (int i = 0; i <= GRID_HEIGHT; i++) {
            g2.drawLine(0, i * TILE_SIZE, GameConstants.SCREEN_WIDTH, i * TILE_SIZE);
        }
    }

    private void drawFood(Graphics2D g2) {
        g2.setColor(GameConstants.FOOD_COLOR);
        int pad = 3;
        g2.fillOval(foodX * TILE_SIZE + pad, foodY * TILE_SIZE + pad,
                TILE_SIZE - pad * 2, TILE_SIZE - pad * 2);
    }

    private void drawSnake(Graphics2D g2) {
        for (int i = 0; i < bodyParts; i++) {
            if (i == 0) {
                g2.setColor(GameConstants.SNAKE_HEAD_COLOR);
            } else {
                g2.setColor(GameConstants.SNAKE_BODY_COLOR);
            }
            g2.fillRoundRect(snakeX[i] * TILE_SIZE + 1, snakeY[i] * TILE_SIZE + 1,
                    TILE_SIZE - 2, TILE_SIZE - 2, 8, 8);
        }
    }

    private void drawScore(Graphics2D g2) {
        g2.setColor(GameConstants.TEXT_COLOR);
        g2.setFont(GameConstants.SCORE_FONT);
        g2.drawString("Score: " + score, 10, 22);
    }

    /** Moves the snake one step in the current direction */
    private void move() {
        // Shift body segments
        for (int i = bodyParts; i > 0; i--) {
            snakeX[i] = snakeX[i - 1];
            snakeY[i] = snakeY[i - 1];
        }

        switch (direction) {
            case 'U' -> snakeY[0]--;
            case 'D' -> snakeY[0]++;
            case 'L' -> snakeX[0]--;
            case 'R' -> snakeX[0]++;
        }

        directionChangedThisTick = false;
    }

    private void checkFoodCollision() {
        if (snakeX[0] == foodX && snakeY[0] == foodY) {
            bodyParts++;
            score += 10;
            spawnFood();
            increaseSpeed();
        }
    }

    private void increaseSpeed() {
        if (currentDelay > GameConstants.MIN_DELAY) {
            currentDelay -= GameConstants.SPEED_STEP;
            timer.setDelay(currentDelay);
        }
    }

    private void checkCollisions() {
        // Check collision with self
        for (int i = bodyParts; i > 0; i--) {
            if (snakeX[0] == snakeX[i] && snakeY[0] == snakeY[i]) {
                running = false;
                break;
            }
        }

        // Check collision with walls
        if (snakeX[0] < 0 || snakeX[0] >= GRID_WIDTH ||
            snakeY[0] < 0 || snakeY[0] >= GRID_HEIGHT) {
            running = false;
        }

        if (!running) {
            timer.stop();
            gameFrame.showGameOver(score);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
            checkFoodCollision();
            checkCollisions();
        }
        repaint();
    }

    /** Handles keyboard input for snake direction */
    private class SnakeKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (directionChangedThisTick) return;

            int key = e.getKeyCode();

            if ((key == KeyEvent.VK_W || key == KeyEvent.VK_UP) && direction != 'D') {
                direction = 'U';
                directionChangedThisTick = true;
            } else if ((key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) && direction != 'U') {
                direction = 'D';
                directionChangedThisTick = true;
            } else if ((key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) && direction != 'R') {
                direction = 'L';
                directionChangedThisTick = true;
            } else if ((key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) && direction != 'L') {
                direction = 'R';
                directionChangedThisTick = true;
            }
        }
    }
}
