package display;

import lombok.Setter;

import javax.swing.*;
import java.awt.*;


public class Window {
    JFrame frame;
    @Setter
    WorldPanel worldPanel;
    public Window() {
        frame = new JFrame("IsoWorld"); // Create the window

        // Get screen size
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = gd.getDisplayMode().getWidth();
        int height = gd.getDisplayMode().getHeight();

        frame.setLayout(null);//using no layout managers
        frame.setVisible(true);//making the frame visible
        // Set up frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(worldPanel);
        frame.pack(); // Set the frame size to match the panel
        frame.setResizable(true);
        frame.setVisible(true);
        //f.setExtendedState(JFrame.MAXIMIZED_BOTH); // Set fullscreen
    }

    public void setWorldPanel(WorldPanel worldPanel) {
        this.worldPanel = worldPanel;
        frame.add(worldPanel);
        frame.pack();
    }
}
