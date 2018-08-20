package game.data;

import java.util.Objects;
import java.util.Random;

public class Node {

    //左上角的坐标
    private Integer x, y;

    public Node() {
        Random random = new Random();
        x = random.nextInt(1000) % GameSize.X_GRID_NUM * 15;
        y = random.nextInt(1000) % GameSize.Y_GRID_NUM * 15;
    }

    Node(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }


    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Node)) {
            return false;
        }
        Node node = (Node) obj;
        return Objects.equals(this.getX(), node.getX()) && Objects.equals(this.getY(), node.getY());
    }
}
