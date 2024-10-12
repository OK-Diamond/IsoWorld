package game;

import game.tiles.Empty;
import game.tiles.Tile;
import game.tiles.Tree;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import static main.Consts.CONSTS;
import static main.Consts.Game.GAME_CONSTS;
import static main.Consts.Game.Generation.GENERATION_CONSTS;

@RequiredArgsConstructor
public class Chunk {
    @Getter
    private final int SIZE = GAME_CONSTS.CHUNK_SIZE;

    /** 3d array of all blocks in the chunk */
    @NonNull @Setter
    private Tile[][] chunkData;
    @NonNull
    private Position pos;

    /**
     * Generates the {@link #chunkData} for a mew chunk
     * @return 2d array of tiles
     */
    public static Tile[][] generateChunkData() {
        Tile[][] chunkData = new Tile[GAME_CONSTS.CHUNK_SIZE][GAME_CONSTS.CHUNK_SIZE];
        for (int y = 0; y < GAME_CONSTS.CHUNK_SIZE; y++) {
            for (int x = 0; x < GAME_CONSTS.CHUNK_SIZE; x++) {
                double rand = CONSTS.getRand();
                if (rand < GENERATION_CONSTS.TREE_CHANCE) {
                    chunkData[y][x] = new Tree(new Position(x, y));
                } else {
                    chunkData[y][x] = new Empty(new Position(x, y));
                }
            }
        }
        return chunkData;
    }

    private String getDirection(int x, int y) {
        String direction = "";
        if (x < 0) {
            if (y < 0) { // -x, -y
                direction = "-x-y";
            } else if (y >= SIZE) { // -x, +y
                direction = "-x+y";
            } else { // -x
                direction = "-x";
            }
        } else if (y < 0) {
            if (x >= SIZE) { // +x, -y
                direction = "+x-y";
            } else { // -y
                direction = "-y";
            }
        } else if (x >= SIZE) {
            if (y >= SIZE) { // +x, +y
                direction = "+x+y";
            } else { // +x
                direction = "+x";
            }
        } else if (y >= SIZE) { // +y
            direction = "+y";
        }
        return direction;
    };

    /**
     * Replace the tile at a particular position
     * @param pos Position to replace
     * @param newTile New tile object
     */
    void replaceTile(Position pos, Tile newTile) {
        chunkData[pos.getY()][pos.getX()] = newTile;
    }

    /**
     * Gives the tile at a particular position
     * @param pos Position to get tile from
     * @return Tile object at the position
     */
    public Tile getTile(Position pos) {
        return chunkData[pos.getY()][pos.getX()];
    }

    /**
     * Searches nearby tiles using {@link game.Chunk#getNeighbours(Position, int)}
     * @param neighbours List of neighbours to search
     * @param tileType The subclass of Tile to look for
     * @return true if the tileType is present, else false
     */
    public boolean hasNeighbour(Tile[][] neighbours, Tile tileType) {
        for (Tile[] neighbour : neighbours) {
            for (Tile tile : neighbour) {
                if (tile == tileType) {
                    // If the tile matches the type, return true
                    return true;
                }
            }
        }
        // No matching tiles found
        return false;
    }

    /**
     * Calculates the {@link #chunkData} for the next frame.
     * @return chunkData for the next frame
     */
    public Chunk getNext() {
        Tile[][] newChunkData = new Tile[SIZE][SIZE];
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                newChunkData[y][x] = chunkData[y][x].getNext();
            }
        }
        return new Chunk(newChunkData, pos);
    }

    /**
     * Called by a Tile to get its neighbours
     * @param position Tile position
     * @param distance Distance to search
     * @return 2d array of neighbours
     */
    public Tile[][] getNeighbours(Position position, int distance) {
        Tile[][] neighbours = new Tile[2*distance+1][2*distance+1];
        Position[][] positions = new Position[2*distance+1][2*distance+1];
        // Declare vars for loop
        int dy, dx, currX, currY;
        Chunk targetChunk;
        String direction;

        for (dy = -distance; dy <= distance; dy++) {
            for (dx = -distance; dx <= distance; dx++) {
                if (dx != 0 && dy != 0) { // Ignore self
                    currX = position.getX() + dx;
                    currY = position.getY() + dy;
                    targetChunk = null;
                    // Find which chunk to retrieve from
                    direction = getDirection(currX, currY);

                    targetChunk = switch (direction) {
                        case "-x-y" -> pos.getWorld().getChunk(pos.getX() - 1, pos.getY() - 1);
                        case "-x+y" -> pos.getWorld().getChunk(pos.getX() - 1, pos.getY() + 1);
                        case "-x" -> pos.getWorld().getChunk(pos.getX() - 1, pos.getY());
                        case "+x-y" -> pos.getWorld().getChunk(pos.getX() + 1, pos.getY() - 1);
                        case "-y" -> pos.getWorld().getChunk(pos.getX(), pos.getY() - 1);
                        case "+x" -> pos.getWorld().getChunk(pos.getX() + 1, pos.getY());
                        case "+x+y" -> pos.getWorld().getChunk(pos.getX() + 1, pos.getY() + 1);
                        case "+y" -> pos.getWorld().getChunk(pos.getX(), pos.getY() + 1);
                        default ->
                            // Else, the target is in this chunk
                            this;
                    };

                    if (targetChunk != null) {
                        neighbours[dy + distance][dx + distance] = switch (direction) {
                            case "-x-y" ->  targetChunk.getTile(new Position(SIZE-1, SIZE-1));
                            case "-x+y" -> targetChunk.getTile(new Position(SIZE-1, 0));
                            case "-x" -> targetChunk.getTile(new Position(SIZE-1, currY));
                            case "+x-y" -> targetChunk.getTile(new Position(0, SIZE-1));
                            case "-y" -> targetChunk.getTile(new Position(currX, SIZE-1));
                            case "+x" -> targetChunk.getTile(new Position(0, currY));
                            case "+x+y" -> targetChunk.getTile(new Position(0, 0));
                            case "+y" -> targetChunk.getTile(new Position(currX, 0));
                            default ->
                                // Else, the target is in this chunk
                                chunkData[currX][currY];
                        };
                    }
                }
            }
        }
        return neighbours;
    }

    /** Overload of {@link #getNeighbours(Position, int)} with a default {@code distance} value of {@code 1}. */
    public Tile[][] getNeighbours(Position position) {
        return getNeighbours(position, 1);
    }
}
