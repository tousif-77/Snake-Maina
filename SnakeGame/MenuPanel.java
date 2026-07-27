package SnakeGame;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

/**
 * The Main Menu screen shown when the application starts.
 */
public class MenuPanel extends JPanel {

    public MenuPanel(GameFrame frame) {
        setPreferredSize(new Dimension(GameConstants.SCREEN_WIDTH, GameConstants.SCREEN_HEIGHT));
        setBackground(GameConstants.BACKGROUND_COLOR);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.insets = new Insets(12, 0, 12, 0);

        // ---------- Title ----------
        JLabel title = new JLabel("SNAKE MANIA");
        title.setFont(GameConstants.TITLE_FONT);
        title.setForeground(GameConstants.ACCENT_COLOR);
        gbc.gridy = 0;
        add(title, gbc);

        JLabel subtitle = new JLabel("Classic Edition — Java Swing");
        subtitle.setFont(GameConstants.SUBTITLE_FONT);
        subtitle.setForeground(GameConstants.TEXT_COLOR);
        gbc.gridy = 1;
        add(subtitle, gbc);

        // ---------- Start Button ----------
        JButton startButton = createStyledButton("▶  START GAME");
        startButton.addActionListener(e -> frame.startGame());
        gbc.gridy = 2;
        gbc.insets = new Insets(30, 0, 12, 0);
        add(startButton, gbc);

        // ---------- Exit Button ----------
        JButton exitButton = createStyledButton("✖  EXIT");
        exitButton.addActionListener(e -> System.exit(0));
        gbc.gridy = 3;
        gbc.insets = new Insets(12, 0, 30, 0);
        add(exitButton, gbc);

        // ---------- Controls Info Panel ----------
        JPanel controlsPanel = buildControlsPanel();
        gbc.gridy = 4;
        gbc.insets = new Insets(10, 0, 0, 0);
        add(controlsPanel, gbc);
    }

    private JPanel buildControlsPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(GameConstants.BUTTON_BG);
        panel.setLayout(new GridLayout(4, 1, 0, 4));
        panel.setBorder(BorderFactory.createLineBorder(GameConstants.ACCENT_COLOR, 1));
        panel.setPreferredSize(new Dimension(260, 130));

        JLabel header = new JLabel("HOW TO PLAY", SwingConstants.CENTER);
        header.setFont(new Font("Consolas", Font.BOLD, 16));
        header.setForeground(GameConstants.ACCENT_COLOR);

        JLabel move = new JLabel("W A S D  or  Arrow Keys", SwingConstants.CENTER);
        move.setFont(GameConstants.SUBTITLE_FONT);
        move.setForeground(GameConstants.TEXT_COLOR);

        JLabel goal = new JLabel("Eat food to grow & score points", SwingConstants.CENTER);
        goal.setFont(GameConstants.SUBTITLE_FONT);
        goal.setForeground(GameConstants.TEXT_COLOR);

        JLabel warn = new JLabel("Avoid walls & yourself!", SwingConstants.CENTER);
        warn.setFont(GameConstants.SUBTITLE_FONT);
        warn.setForeground(GameConstants.TEXT_COLOR);

        panel.add(header);
        panel.add(move);
        panel.add(goal);
        panel.add(warn);

        return panel;
    }

    /** Creates a styled retro-looking button with hover effect */
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
