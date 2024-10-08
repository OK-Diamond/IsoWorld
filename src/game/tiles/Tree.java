package game.tiles;
import game.Position;
import main.Consts;

import java.awt.*;

public class Tree extends Tile {
    protected Color paintColour = new Color(0, 200, 0);
    protected double burnProb = 1- Consts.Game.Tile.treeResistProb;

    public Tree(Position position) {
        super(position);
    }

    /**
     * Determines whether a tree catches alight from a neighbouring fire
     * @param neighbour The neighbouring tile
     * @param isDiagonal Whether the tile is diagonal to its neighbour
     * @return Whether the tile should be replaced with fire
     */
    protected boolean getFireSpread(Tile neighbour, boolean isDiagonal) {
        if (neighbour instanceof Fire || neighbour instanceof Lightning) {
            if (Consts.getRand() <= burnProb) {
                // Reduced chance for diagonal spread
                if (isDiagonal) {
                    return Consts.getRand() <= Consts.Game.Tile.diagSpreadProb;
                } else {
                    return Consts.getRand() <= Consts.Game.Tile.spreadProb;
                }
            }
        }
        return false;
    }

    @Override
    public Tile getNext() {
        // Lightning strike
        if (Consts.getRand() <= Consts.Game.Tile.lightningProb && Consts.getRand() <= burnProb) {
            return new Lightning(pos);
        }
        // Fire spreading
        Tile[][] neighbours = pos.getChunk().getNeighbours(pos, Consts.Game.Tile.fireRange);
        for (int i = 0; i < neighbours.length; i++) {
            for (int j = 0; j < neighbours[i].length; j++) {
                if (getFireSpread(neighbours[i][j], ((i+j)%2 == 0))) {
                    return new Fire(pos);
                }
            }
        }
        return this;
    }

    @Override
    public Color getColour() {
        return paintColour;
    }

    public String toString() {
        return "|";
    };
}
