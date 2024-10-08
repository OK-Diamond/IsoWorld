package game.tiles;

import game.Chunk;
import game.Position;
import main.Consts;

import java.awt.*;
import java.util.Random;

public class Empty extends Tile {
    protected Color paintColour = new Color(9, 5, 5);
    protected Chunk chunk;



    public Empty(Position position) {
        super(position);
    }

    @Override
    public Tile getNext() {
        Random rand = new Random();
        if (rand.nextDouble() <= Consts.Game.Tile.growthProb) {
            // If there is a tree within range 3
            Tile[][] n = chunk.getNeighbours(pos, Consts.Game.Tile.growRange);
            if (hasNeighbour(n, Tree.class)) {
                // New sapling grows
                return new Sapling(pos);
            }
            //for (int iy = 0; iy < n.length; iy++) {
            //    for (int ix = 0; ix < n[iy].length; ix++) {
            //        if (n[iy][ix] instanceof Tree) {
            //            // New sapling grows
            //            return new Sapling(x,y);
            //        }
            //    }
            //}
        }
        return this;
    }

    @Override
    public Color getColour() {
        return paintColour;
    }

    public String toString() {
        return " ";
    };
}
