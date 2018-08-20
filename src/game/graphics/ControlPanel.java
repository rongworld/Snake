package game.graphics;

import game.GameControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlPanel extends JPanel {
    private GameControl gameControl;

    private JButton statusButton, restartButton, quitGameButton;

    public ControlPanel(GameControl gameControl) {
        this.gameControl = gameControl;
        this.setLayout(new FlowLayout());
        init();
    }

    private void init() {

        Listener listener = new Listener();
        statusButton = new JButton("开始");
        statusButton.setSize(30,40);
        statusButton.addActionListener(listener);
        restartButton = new JButton("重新开始");
        restartButton.addActionListener(listener);
        quitGameButton = new JButton("退出");
        quitGameButton.addActionListener(listener);
        add(statusButton);
        add(restartButton);
        add(quitGameButton);
    }


    public JButton getStatusButton() {
        return statusButton;
    }

    private class Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "开始":
                    gameControl.startGame();
                    statusButton.setText("暂停");
                    break;
                case "暂停":
                    gameControl.pauseGame();
                    statusButton.setText("恢复");
                    break;
                case "恢复":
                    gameControl.reviewGame();
                    statusButton.setText("暂停");
                    break;
                case "重新开始":
                    gameControl.restartGame();
                    statusButton.setText("开始");
                    break;
                case "退出":
                    gameControl.quitGame();
                    break;
            }
        }
    }


}
