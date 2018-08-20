package game.data;


import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import static game.data.GameSize.*;


public class Snake {


    private List<Node> nodes;
    private final Flag flag = new Flag();


    public Snake(Node header) {
        this.nodes = new LinkedList<>();
        nodes.add(header);
    }


    public void changeDir(Dir dir) {
        switch (dir) {
            case UP:
                if (flag.isDown || flag.isUp) {
                    break;
                }

                flag.isUp = true;
                flag.isDown = false;
                flag.isLeft = false;
                flag.isRight = false;
                break;
            case DOWN:
                if (flag.isUp || flag.isDown) {
                    break;
                }

                flag.isUp = false;
                flag.isDown = true;
                flag.isLeft = false;
                flag.isRight = false;
                break;
            case LEFT:
                if (flag.isRight || flag.isLeft) {
                    break;
                }

                flag.isUp = false;
                flag.isDown = false;
                flag.isLeft = true;
                flag.isRight = false;
                break;
            case RIGHT:

                if (flag.isLeft || flag.isRight) {
                    break;
                }

                flag.isUp = false;
                flag.isDown = false;
                flag.isLeft = false;
                flag.isRight = true;
                break;
        }
    }


    public void move(FoodNode foodNode) {
        Node header = nodes.get(0);
        Node newNode;
        if (flag.isUp) {
            newNode = new Node(header.getX(), header.getY() - SNAKE_SIZE);
        } else if (flag.isDown) {
            newNode = new Node(header.getX(), header.getY() + SNAKE_SIZE);
        } else if (flag.isLeft) {
            newNode = new Node(header.getX() - SNAKE_SIZE, header.getY());
        } else if (flag.isRight) {
            newNode = new Node(header.getX() + SNAKE_SIZE, header.getY());
        } else {
            throw new RuntimeException("Game Error");
        }

        nodes.add(0, newNode);

        if (!isEat(foodNode)) {
            nodes.remove(nodes.size() - 1);
        } else {
            setEat(true);
        }
    }


    private boolean isEat(FoodNode foodNode) {
        Node header = nodes.get(0);
        return Objects.equals(foodNode.getX(), header.getX())
                && Objects.equals(foodNode.getY(), header.getY());
    }

    public boolean isGameOver() {
        Node header = nodes.get(0);
        return header.getX() < 0 || header.getY() < 0 || header.getX() > (X_GRID_NUM - 1) * SNAKE_SIZE || header.getY() > (Y_GRID_NUM - 1) * SNAKE_SIZE;
    }


    public List<Node> getNodes() {
        return nodes;
    }


    public void setUp(boolean up) {
        flag.isUp = up;
    }

    public void setDown(boolean down) {
        flag.isDown = down;
    }

    public void setRight(boolean right) {
        flag.isRight = right;
    }

    public void setLeft(boolean left) {
        flag.isLeft = left;
    }

    public boolean isEat() {
        return flag.isEat;
    }

    public void setEat(boolean eat) {
        flag.isEat = eat;
    }

    public class Flag {

        private volatile boolean isUp = false;

        private volatile boolean isDown = false;

        private volatile boolean isRight = false;

        private volatile boolean isLeft = false;

        private volatile boolean isEat = false;


        @Override
        public String toString() {
            return String.format("isUp:%s,isDown:%s,isRight:%s,isLeft:%s,isEat:%s",
                    isUp, isDown, isRight, isLeft, isEat
            );

        }
    }

}
