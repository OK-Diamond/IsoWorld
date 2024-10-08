package game.tiles;

import game.Position;
import main.Consts;

import java.awt.*;

public class Ash extends Tile {
    protected Color paintColour = new Color(60, 65, 60);

    public Ash(Position position) {
        super(position);
    }

    @Override
    public Tile getNext() {
        if (Consts.getRand() <= Consts.Game.Tile.decayProb) {
            // Ash decays
            return new Empty(pos);
        }
        return this;
    }

    @Override
    public Color getColour() {
        return paintColour;
    }

    public String toString() {
        return "_";
    };
}
