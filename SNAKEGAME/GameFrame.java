package SnakeGame;

import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * The main application window (JFrame).
 * Uses CardLayout to switch between Menu, Game, and Game Over screens.
 */
public class GameFrame extends JFrame {

    private final CardLayout cardLayout;
    private final JPanel container;

    private final MenuPanel menuPanel;
    private final GamePanel gamePanel;
    private final GameOverPanel gameOverPanel;

    public static final String MENU_CARD = "MENU";
    public static final String GAME_CARD = "GAME";
    public static final String GAME_OVER_CARD = "GAME_OVER";

    public GameFrame() {
        setTitle("🐍 Snake Game - Classic Edition");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        cardLayout = new CardLayout();
        container = new JPanel(cardLayout);

        menuPanel = new MenuPanel(this);
        gamePanel = new GamePanel(this);
        gameOverPanel = new GameOverPanel(this);

        container.add(menuPanel, MENU_CARD);
        container.add(gamePanel, GAME_CARD);
        container.add(gameOverPanel, GAME_OVER_CARD);

        add(container);
        pack();
        setLocationRelativeTo(null); // center on screen

        showMenu();
    }

    /** Switch to Main Menu */
    public void showMenu() {
        cardLayout.show(container, MENU_CARD);
    }

    /** Start a new game */
    public void startGame() {
        gamePanel.startGame();
        cardLayout.show(container, GAME_CARD);
        gamePanel.requestFocusInWindow();
    }

    /** Show Game Over screen with final score */
    public void showGameOver(int score) {
        gameOverPanel.setScore(score);
        cardLayout.show(container, GAME_OVER_CARD);
    }

    public int getGameWidth() {
        return GameConstants.SCREEN_WIDTH;
    }

    public int getGameHeight() {
        return GameConstants.SCREEN_HEIGHT;
    }
}