package game;

import game.tiles.Tile;
import game.tiles.Empty;
import lombok.Setter;
import static main.Consts.Game.GAME_CONSTS;


public class World {
    private final int SIZE = GAME_CONSTS.WORLD_SIZE;
    /** 2d array of all chunks in the world */
    @Setter
    private Chunk[][] worldData;

    public World() {
        worldData = new Chunk[SIZE][SIZE];
        int chunkSize = GAME_CONSTS.CHUNK_SIZE;
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                worldData[y][x].setChunkData(new Empty[chunkSize][chunkSize]);
            }
        }
    }

    /**
     * Calculates the next frame.
     * @return Updated world data for the next frame
     */
    public Chunk[][] getNext() {
        Chunk[][] newData = new Chunk[SIZE][SIZE];
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                newData[y][x] = worldData[y][x].getNext();
            }
        }
        return newData;
    }

    public Chunk getChunk(Position pos) {
        return worldData[pos.getY()][pos.getX()];
    }

    public Chunk getChunk(int x, int y) {
        return worldData[y][x];
    }

    /**
     * Called by a cell to get its neighbours
     * @param x Cell X pos
     * @param y Cell Y pos
     * @param d Distance to get from
     * @return 2d array of neighbours
     */
    public Tile[][] getNeighbours(int x, int y, int d) {
        Tile[][] neighbours = new Tile[2*d+1][2*d+1];
        for (int dy = -d; dy <= d; dy++) {
            for (int dx = -d; dx <= d; dx++) {
                if (
                        (dx != 0 || dy != 0) // Ignore self
                                && (dx + x >= 0) && (dy + y >= 0) // Stay in range (up + left)
                                //&& (dx + x < cols) && (dy + y < rows) // Stay in range (down + right)
                ) {
                    //neighbours[dy + d][dx + d] = forest[y + dy][x + dx];
                }
            }
        }
        return neighbours;
    }

    /**
     * Overload of the {@link #getNeighbours(int, int, int)} method with a default {@code d} value of 1.
     */
    public Tile[][] getNeighbours(int x, int y) {
        return getNeighbours(x, y, 1);
    }
}
