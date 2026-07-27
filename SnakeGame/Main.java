package SnakeGame;



import javax.swing.SwingUtilities;

/**
 * Entry point of the Snake Game application.
 */
public class Main {
    public static void main(String[] args) {
        // Run the GUI on the Event Dispatch Thread (Swing best practice)
        SwingUtilities.invokeLater(() -> {
            GameFrame gameFrame = new GameFrame();
            gameFrame.setVisible(true);
        });
    }
}
