package net.saga.game.cloclo.characters.obstacle;

import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import net.saga.game.cloclo.screens.PuzzleMapScreen;

public class EmeraldBlockMoveBy  extends MoveByAction {

    private final PuzzleMapScreen screen;


    public EmeraldBlockMoveBy(PuzzleMapScreen screen) {
        this.screen = screen;
        setDuration( 1 / 8f);

    }

    protected void updateRelative (float percentDelta) {
        int newX = (int) Math.floor(target.getX() + getAmountX()* percentDelta +.0001);
        int newY = (int) Math.floor(target.getY() + getAmountY()* percentDelta +.0001);
        Obstacle obstacle = screen.getObstacleAt(newX, newY, (Obstacle) target);

        if (obstacle == Obstacle.EMPTY ) {
            target.moveBy(getAmountX() * percentDelta, getAmountY() * percentDelta);
        } else {
            finish();
        }

    }

    @Override
    protected void end() {
        target.setX(screen.round(target.getX(), 8));
        target.setY(screen.round(target.getY(), 8));
    }

}

