package SnakeGame;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

/**
 * Displayed when the player's snake dies.
 * Shows the final score with options to restart or return to menu.
 */
public class GameOverPanel extends JPanel {

    private final JLabel scoreLabel;

    public GameOverPanel(GameFrame frame) {
        setPreferredSize(new Dimension(GameConstants.SCREEN_WIDTH, GameConstants.SCREEN_HEIGHT));
        setBackground(GameConstants.BACKGROUND_COLOR);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.insets = new Insets(10, 0, 10, 0);

        JLabel gameOverLabel = new JLabel("GAME OVER");
        gameOverLabel.setFont(GameConstants.GAME_OVER_FONT);
        gameOverLabel.setForeground(GameConstants.FOOD_COLOR);
        gbc.gridy = 0;
        add(gameOverLabel, gbc);

        scoreLabel = new JLabel("Final Score: 0");
        scoreLabel.setFont(GameConstants.SCORE_FONT);
        scoreLabel.setForeground(GameConstants.TEXT_COLOR);
        gbc.gridy = 1;
        gbc.insets = new Insets(5, 0, 30, 0);
        add(scoreLabel, gbc);

        JButton restartButton = createStyledButton("🔄  RESTART");
        restartButton.addActionListener(e -> frame.startGame());
        gbc.gridy = 2;
        gbc.insets = new Insets(10, 0, 10, 0);
        add(restartButton, gbc);

        JButton menuButton = createStyledButton("🏠  MAIN MENU");
        menuButton.addActionListener(e -> frame.showMenu());
        gbc.gridy = 3;
        add(menuButton, gbc);
    }

    /** Update the displayed score */
    public void setScore(int score) {
        scoreLabel.setText("Final Score: " + score);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(GameConstants.BUTTON_FONT);
        button.setForeground(GameConstants.TEXT_COLOR);
        button.setBackground(GameConstants.BUTTON_BG);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(240, 50));
        button.setBorder(BorderFactory.createLineBorder(GameConstants.ACCENT_COLOR, 2));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(GameConstants.BUTTON_HOVER);
                button.setForeground(Color.BLACK);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(GameConstants.BUTTON_BG);
                button.setForeground(GameConstants.TEXT_COLOR);
            }
        });

        return button;
    }
}
