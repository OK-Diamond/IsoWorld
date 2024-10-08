package game;


import lombok.*;

/** Stores a position, used for both Tiles (with Chunk field) and for Chunks (with World field) */
@Data @Getter @Setter @RequiredArgsConstructor
public class Position {
    @NonNull
    private int x;
    @NonNull
    private int y;
    private Chunk chunk;
    private World world;

    // Constructor that takes Chunk
    public Position(int x, int y, Chunk chunk) {
        this.x = x;
        this.y = y;
        this.chunk = chunk;
        this.world = null; // Ensure world is null if chunk is provided
    }

    // Constructor that takes World
    public Position(int x, int y, World world) {
        this.x = x;
        this.y = y;
        this.chunk = null; // Ensure chunk is null if world is provided
        this.world = world;
    }
}
