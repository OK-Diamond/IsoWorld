package game.tiles;

import game.Position;
import main.Consts;

import java.awt.*;

public class Sapling extends Tree {
    protected Color paintColour = new Color(0, 100, 0);
    protected double burnProb = 1- Consts.Game.Tile.saplingResistProb;

    public Sapling(Position position) {
        super(position);
    }

    @Override
    public Tile getNext() {
        Tile currTile = super.getNext();
        // Growth
        if (currTile instanceof Sapling && Consts.getRand() <= Consts.Game.Tile.growthProb) {
            return new Tree(pos);
        }
        return currTile;
    }

    @Override
    public Color getColour() {
        return paintColour;
    }

    public String toString() {
        return ".";
    };
}
