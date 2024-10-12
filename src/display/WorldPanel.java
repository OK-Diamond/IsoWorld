package display;

import game.World;
import lombok.Setter;

import static main.Consts.Display.DISPLAY_CONSTS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WorldPanel extends JPanel {
    private int width =  DISPLAY_CONSTS.WINDOW_WIDTH;
    private int height = DISPLAY_CONSTS.WINDOW_HEIGHT;
    @Setter
    private World world;

    public void setWidth(int width) {
        this.width = width;
        this.setPreferredSize(new Dimension(this.width, this.height));
    }
    public void setHeight(int height) {
        this.height = height;
        this.setPreferredSize(new Dimension(this.width, this.height));
    }

    public WorldPanel(World world) {
        // Create the world
        this.setPreferredSize(new Dimension(width, height));


        // Timer to control the frame rate
        Timer timer = new Timer(
                DISPLAY_CONSTS.FRAME_TIME,
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
