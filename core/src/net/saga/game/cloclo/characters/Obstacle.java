package net.saga.game.cloclo.characters;

public interface Obstacle {

    public static Obstacle EMPTY = new Obstacle() {
        @Override
        public boolean touch(float x, float y, Direction direction) {
            return true;
        }

        @Override
        public boolean checkBounds(float x, float y) {
            return false;
        }
    };

    /**
     *
     * A touch from x, y in direction direction has occured.
     * Queue any actions that need to be performed (such as moving, killing the player, etc) and return
     * if the object allows the player to move in that direction.
     *
     * @param x the x source of the touch
     * @param y the y source of the touch
     * @param direction A direction vector of the touch.  UP = 0,1, LEFT = -1,0, etc.
     * @return if a player can move into space (x ,y) + direction
     */
    boolean touch(float x, float y, Direction direction);

    /**
     * Return true if x,y within Obstacle
     * @param x x point
     * @param y y point
     * @return true if x,y within obstacle (inclusive)
     */
    boolean checkBounds(float x, float y);
}
