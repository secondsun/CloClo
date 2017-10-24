package net.saga.game.cloclo.characters.obstacle;

import net.saga.game.cloclo.control.CloCloInputEvent;
import net.saga.game.cloclo.screens.PuzzleMapScreen;

import java.util.List;
import java.util.stream.Collectors;

/**
 * When a player is between obstacles then this helper class combines them together to perform
 * movement checking and rules.
 */
public class ObstacleTuple extends Obstacle {
    private final List<Obstacle> obstacles;
    private final PuzzleMapScreen screen;
    public ObstacleTuple(List<Obstacle> obstacles, PuzzleMapScreen screen) {
        this.obstacles = obstacles;
        this.screen = screen;
    }


    @Override
    public boolean touch(float x, float y, CloCloInputEvent direction) {
        List<Obstacle> impassables = obstacles.stream()
                .filter(obstacle -> !obstacle.hasCharacteristic(Characteristic.PASSABLE))
                .collect(Collectors.toList());

        if (impassables.isEmpty()) {
            return true;
        }

        List<Obstacle> pushables = obstacles.stream()
                .filter(obstacle -> obstacle.hasCharacteristic(Characteristic.PUSHABLE))
                .collect(Collectors.toList());

        if (pushables.size() == impassables.size()) {
            //all impassible objects are pushable
            if (pushables.size() == 1) {
                return pushables.get(0).touch(x,y,direction);
            }
        }
        return false;

    }

    @Override
    public boolean checkBounds(float x, float y) {
        return obstacles.stream().reduce(false, (ignore, obstacle) -> obstacle.checkBounds(x, y),
                (left, right) -> left | right);
    }

    @Override
    /**
     * A tuple has no characteristics
     */
    public boolean hasCharacteristic(Characteristic characteristic) {
        return false;
    }
}
