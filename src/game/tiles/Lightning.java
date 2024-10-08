package game.tiles;

import game.Position;

import java.awt.*;

public class Lightning extends Tile {
    protected Color paintColour = new Color(255, 255, 100);

    public Lightning(Position position) {
        super(position);
    }

    @Override
    public Tile getNext() {
        return new Fire(pos);
    }

    @Override
    public Color getColour() {
        return paintColour;
    }

    public String toString() {
        return "L";
    };
}
