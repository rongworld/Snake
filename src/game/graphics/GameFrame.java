package game.graphics;

import game.data.GameSize;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static game.data.GameSize.*;

public class GameFrame extends JFrame {

    public GameFrame(JComponent... jComponents) {
        setLayout(null);
        setSize(GameSize.FRAME_WIDTH, GameSize.FRAME_HEIGHT);
        setLocation(GameSize.FRAME_CENTER);
        setResizable(false);
        for (JComponent jComponent : jComponents) {
            if (jComponent instanceof GamePanel) {
                jComponent.setBounds((FRAME_WIDTH-GAME_PANEL_WIDTH)/4, (int) (FRAME_HEIGHT - GAME_PANEL_HEIGHT*1.2), GAME_PANEL_WIDTH, GAME_PANEL_HEIGHT);
                jComponent.setBackground(Color.orange);
                add(jComponent);
            }
            if (jComponent instanceof ControlPanel){
                jComponent.setBounds((FRAME_WIDTH-GAME_PANEL_WIDTH)/4,5,GAME_PANEL_WIDTH,GameSize.CONTROL_HEIGHT);
                jComponent.setBackground(Color.cyan);
               add(jComponent);
            }
        }

        setVisible(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

}
