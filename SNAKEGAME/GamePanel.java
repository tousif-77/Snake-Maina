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
