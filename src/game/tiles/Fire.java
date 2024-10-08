package game.tiles;

import game.Position;
import main.Consts;

import java.awt.*;

public class Fire extends Tile {
    protected Color paintColour = new Color(200, 50, 50);

    public Fire(Position position) {
        super(position);
    }

    @Override
    public Tile getNext() {

        if (Consts.Game.Tile.fireBurnoutProb < Consts.getRand()) {
            // Fire keeps burning
            return this;
        }

        // Fire burns out
        if (Consts.getRand() <= Consts.Game.Tile.treeSurviveProb) {
            // Sapling is left behind
            return new Sapling(pos);
        }
        if (Consts.getRand() <= Consts.Game.Tile.ashProb) {
            // Ash is left behind
            return new Ash(pos);
        }
        return new Empty(pos);
    }

    @Override
    public Color getColour() {
        return paintColour;
    }

    public String toString() {
        return "X";
    };
}
