package display;

import game.World;
import main.Consts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WorldPanel extends JPanel {
    private int width = Consts.Display.Instance.WINDOW_WIDTH;
    private int height = Consts.Display.Instance.WINDOW_HEIGHT;
    private World world;

    public WorldPanel() {
        // Create the world
        world = new World();
        this.setPreferredSize(new Dimension(width, height));


        // Timer to control the frame rate
        Timer timer = new Timer(
                Consts.Display.Instance.FRAME_TIME,
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Do something every frame
                        repaint();
                    }
                }
        );
        timer.start();
    }
}
