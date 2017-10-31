package net.saga.game.cloclo.levelloader;

public class MapData {
    public MapMetadata metadata;
    public Obstacles obstacles;

    public static class MapMetadata {
        public int frame, tileset;
        public Point door, start;
    }

    public static class Obstacles {
        public Point[] emeraldBlock,heartFrame,tree;
    }

    public static class Point {
        public int x,  y;
    }
}
