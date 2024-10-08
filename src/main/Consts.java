package main;

import java.util.Random;

public enum Consts {
    CONSTS; // Create an instance of the enum

    // Random
    private final Random random = new Random();
    public double getRand() {
        return random.nextDouble();
    }

    public enum Display {
        DISPLAY_CONSTS;

        public final String APP_TITLE = "IsoWorld";
        public final int WINDOW_WIDTH = 800;
        public final int WINDOW_HEIGHT = 600;
        public final int FPS = 60;
        public final int FRAME_TIME = 1000/FPS;
    }
    public enum Game {
        GAME_CONSTS;

        /** Chunk size in tiles */
        public final int CHUNK_SIZE = 8;
        /** World size in chunks */
        public final int WORLD_SIZE = 10;

        /** Tile constants */
        public enum Tile {
            TILE_CONSTS;

            /** Chance of a new tree growing */
            public final double growthProb = 0.001;
            /** Chance of lightning striking a tree */
            public final double lightningProb = 0.000001;
            /** Chance of fire spreading to non-diagonals */
            public final double spreadProb = 1;
            /** Chance of fire spreading diagonally */
            public final double diagSpreadProb = spreadProb*0.573;
            /** Chance for trees to survive fire - they will be set to saplings */
            public final double treeSurviveProb = 0.02;
            /** Chance for trees to resist fire */
            public final double treeResistProb = 0.09;
            /** Chance for saplings to resist fire */
            public final double saplingResistProb = treeResistProb *10;
            /** Chance for fire to burn out */
            public final double fireBurnoutProb = 0.1;
            /** Chance for fire to leave ash when it burns out */
            public final double ashProb = 0.85;
            /** Chance for ash to decay, leaving an empty tile */
            public final double decayProb = 0.1;

            /** Distance from an existing tree that a new tree can grow */
            public final int growRange = 10;
            /** Distance that fire can spread */
            public final int fireRange = 2;
        }
        public enum Generation {
            GENERATION_CONSTS;

            /** Chance of a tile being a tree in each tile of a generated chunk */
            public final double TREE_CHANCE = 0.01;
        }
    }



    // Private constructor to prevent instantiation
    private Consts() {}
}
