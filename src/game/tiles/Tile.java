package game.tiles;
import game.Position;
import lombok.*;

import java.awt.*;

// Abstract - unimplemented. Only subclasses of this should be used
@RequiredArgsConstructor
public abstract class Tile {
    @NonNull
    protected Position pos;
    protected Color paintColour = Color.WHITE;

    /**
     * Returns a new Tile object to replace with one with.
     * @return Tile object reference - could be 'this'
     */
    public abstract Tile getNext();

    /** Colour object to use when displaying this tile */
    public abstract Color getColour();

    /**
     * Searches nearby tiles using {@link game.Chunk#getNeighbours(Position, int)}
     * @param neighbours List of neighbours to search
     * @param tileType The subclass of Tile to look for
     * @return true if the tileType is present, else false
     */
    public boolean hasNeighbour(Tile[][] neighbours, Class<? extends Tile> tileType) {
        for (Tile[] neighbour : neighbours) {
            for (Tile tile : neighbour) {
                if (tileType.isInstance(tile)) {
                    // If the tile matches the type, return true
                    return true;
                }
            }
        }
        // No matching game.tiles found
        return false;
    }

    public String toString() {
        return "!";
    };
}
