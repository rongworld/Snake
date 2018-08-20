package game;

import game.data.*;
import game.graphics.ControlPanel;
import game.graphics.GameFrame;
import game.graphics.GamePanel;

import javax.swing.*;
import java.util.Random;

public class GameControl {

    private  Snake snake;

    private FoodNode foodNode;

    private GamePanel gamePanel;

    private Thread snakeThread;

    private volatile boolean isPause = false;

    private GameFrame gameFrame;

    private ControlPanel controlPanel;
    public GameControl() {
        foodNode = new FoodNode();
        Node header = new Node();
        snake = new Snake(header);
        gamePanel = new GamePanel(snake, foodNode);
        controlPanel = new ControlPanel(this);
        controlPanel.setBounds(0, 0, 100, 20);
        gameFrame = new GameFrame(gamePanel, controlPanel);
    }

    private void initGame() {
        foodNode = new FoodNode();
        Node header = new Node();
        snake = new Snake(header);
        gamePanel.setFoodNode(foodNode);
        gamePanel.setSnake(snake);

    }


    public void startGame() {

        gamePanel.requestFocus();
        Random random = new Random();
        switch (random.nextInt(3)) {
            case 0:
                snake.setUp(true);
                break;
            case 1:
                snake.setDown(true);
                break;
            case 2:
                snake.setLeft(true);
                break;
            case 3:
                snake.setRight(true);
                break;
            default:
                snake.setUp(true);
        }


        snakeThread = new Thread(() -> {
            int count = 0;
            while (true) {
                if (!isPause) {
                    snake.move(foodNode);
                    if (snake.isEat()) {
                        count++;
                        foodNode = new FoodNode();
                        gamePanel.setFoodNode(foodNode);
                        snake.setEat(false);
                    }
                    if (snake.isGameOver()) {

                        int s = JOptionPane.showConfirmDialog(gameFrame,String.format("得分:%s",count),"游戏结束"
                                , JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE);
                        if (s == 0){
                            restartGame();
                            controlPanel.getStatusButton().setText("开始");
                        }

                        break;
                    }
                    gamePanel.repaint();
                    try {
                        Thread.sleep(Setting.DIFFICULT);
                    } catch (InterruptedException ignored) {
                        break;
                    }
                }
            }
        });

        snakeThread.start();
    }


    public void pauseGame() {
        isPause = true;
    }


    public void restartGame() {
        isPause = false;
        if (snakeThread != null && snakeThread.isAlive()) {
            snakeThread.interrupt();
        }

        initGame();
        gamePanel.repaint();
    }

    public void quitGame() {
        System.exit(0);
    }

    public void reviewGame() {
        isPause = false;
        gamePanel.requestFocus();
    }

}
