package main;

import display.Window;
import display.WorldPanel;
import game.World;

public class Main {
    public static void main(String[] args) {
        Window window = new Window();
        World world = new World();
        window.setWorldPanel(new WorldPanel(world));

    }
}
