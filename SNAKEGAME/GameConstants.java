package SnakeGame;


import java.awt.Color;
import java.awt.Font;

/**
 * Holds all constant values used across the Snake Game.
 * Centralizing constants makes the game easy to tweak/balance.
 */
public class GameConstants {

    // ---------- Grid / Screen Settings ----------
    public static final int TILE_SIZE = 25;
    public static final int GRID_WIDTH = 30;
    public static final int GRID_HEIGHT = 20;

    public static final int SCREEN_WIDTH = TILE_SIZE * GRID_WIDTH;
    public static final int SCREEN_HEIGHT = TILE_SIZE * GRID_HEIGHT;

    // ---------- Game Speed ----------
    public static final int INITIAL_DELAY = 130; // milliseconds
    public static final int MIN_DELAY = 60;
    public static final int SPEED_STEP = 2; // speed increase per food eaten

    // ---------- Colors (Classic Retro Theme) ----------
    public static final Color BACKGROUND_COLOR = new Color(15, 15, 25);
    public static final Color GRID_COLOR = new Color(28, 28, 40);
    public static final Color SNAKE_HEAD_COLOR = new Color(0, 230, 118);
    public static final Color SNAKE_BODY_COLOR = new Color(0, 170, 90);
    public static final Color FOOD_COLOR = new Color(255, 61, 61);
    public static final Color TEXT_COLOR = new Color(240, 240, 240);
    public static final Color ACCENT_COLOR = new Color(0, 230, 118);
    public static final Color BUTTON_BG = new Color(35, 35, 50);
    public static final Color BUTTON_HOVER = new Color(0, 230, 118);

    // ---------- Fonts ----------
    public static final Font TITLE_FONT = new Font("Consolas", Font.BOLD, 52);
    public static final Font SUBTITLE_FONT = new Font("Consolas", Font.PLAIN, 16);
    public static final Font BUTTON_FONT = new Font("Consolas", Font.BOLD, 20);
    public static final Font SCORE_FONT = new Font("Consolas", Font.BOLD, 20);
    public static final Font GAME_OVER_FONT = new Font("Consolas", Font.BOLD, 46);

    private GameConstants() {
        // prevent instantiation
    }
}