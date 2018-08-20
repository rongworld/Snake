package game.data;

import java.awt.*;

public class GameSize {
    public final static Integer SNAKE_SIZE = 15;
    public final static Integer FRAME_WIDTH = 600;
    public final static Integer FRAME_HEIGHT = 500;

    public final static Point FRAME_CENTER = new Point(
            (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() - FRAME_WIDTH) / 2,
            (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() - FRAME_HEIGHT) / 2
    );

    public final static Integer GAME_PANEL_WIDTH = (FRAME_WIDTH / SNAKE_SIZE - 2) * SNAKE_SIZE;
    public final static Integer GAME_PANEL_HEIGHT = (FRAME_HEIGHT / SNAKE_SIZE - 8) * SNAKE_SIZE;
    public final static Integer X_GRID_NUM = GAME_PANEL_WIDTH / SNAKE_SIZE;
    public final static Integer Y_GRID_NUM = GAME_PANEL_HEIGHT / SNAKE_SIZE;

    public final static Integer CONTROL_HEIGHT = (int) (FRAME_HEIGHT - GAME_PANEL_HEIGHT * 1.2);

}
