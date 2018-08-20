package game.graphics;

import game.data.Dir;
import game.data.FoodNode;
import game.data.GameSize;
import game.data.Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static game.data.GameSize.*;

public class GamePanel extends JPanel {

    private Snake snake;
    private FoodNode foodNode;

    public GamePanel(Snake snake, FoodNode foodNode) {
        this.snake = snake;
        this.foodNode = foodNode;
        setFocusable(true);
        addKeyListener(new Listener());
    }


    @Override
    protected void paintChildren(Graphics g) {
        drawBorder(g);
        drawFood(g);
        drawSnake(g);
        drawGrid(g);
        super.paintChildren(g);
    }

    private void drawSnake(Graphics g) {
        g.setColor(Color.BLACK);
        snake.getNodes()
                .forEach(
                        e -> g.fillRect(e.getX(), e.getY(), SNAKE_SIZE, SNAKE_SIZE)
                );
    }

    private void drawBorder(Graphics g) {
        g.drawLine(0, 0, GAME_PANEL_WIDTH - 1, 0);
        g.drawLine(0, 0, 0, GAME_PANEL_HEIGHT - 1);
        g.drawLine(0, GAME_PANEL_HEIGHT - 1, GAME_PANEL_WIDTH - 1, GAME_PANEL_HEIGHT - 1);
        g.drawLine(GAME_PANEL_WIDTH - 1, 0, GAME_PANEL_WIDTH - 1, GAME_PANEL_HEIGHT - 1);
        super.paintChildren(g);
    }


    private void drawGrid(Graphics g) {
        g.setColor(Color.BLACK);
        for (int i = 1; i < GameSize.X_GRID_NUM; i++) {
            g.drawLine(i * SNAKE_SIZE, 0, i * SNAKE_SIZE, GAME_PANEL_HEIGHT);
        }
        for (int i = 1; i < GameSize.Y_GRID_NUM; i++) {
            g.drawLine(0, i * SNAKE_SIZE, GAME_PANEL_WIDTH, i * SNAKE_SIZE);
        }
    }

    private void drawFood(Graphics g) {
        g.setColor(FoodNode.FOOD_COLOR);
        g.fillOval(foodNode.getX(), foodNode.getY(), SNAKE_SIZE, SNAKE_SIZE);

    }

    public void setFoodNode(FoodNode foodNode) {
        this.foodNode = foodNode;
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    public class Listener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            System.out.println(e.getExtendedKeyCode());
            switch (e.getExtendedKeyCode()) {
                case KeyEvent.VK_UP:
                case KeyEvent.VK_W:
                    snake.changeDir(Dir.UP);
                    break;
                case KeyEvent.VK_DOWN:
                case KeyEvent.VK_S:
                    snake.changeDir(Dir.DOWN);
                    break;
                case KeyEvent.VK_LEFT:
                case KeyEvent.VK_A:
                    snake.changeDir(Dir.LEFT);
                    break;
                case KeyEvent.VK_RIGHT:
                case KeyEvent.VK_D:
                    snake.changeDir(Dir.RIGHT);
                    break;
            }
        }
    }

}
