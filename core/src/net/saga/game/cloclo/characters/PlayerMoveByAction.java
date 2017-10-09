package net.saga.game.cloclo.characters;

import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;

public class PlayerMoveByAction extends MoveByAction {

    private final PuzzleMapScreen screen;


    public PlayerMoveByAction(PuzzleMapScreen screen) {
        this.screen = screen;
        setDuration( 1 / 8f);

    }

    protected void updateRelative (float percentDelta) {
        int newX = (int) Math.floor(target.getX() + getAmountX()* percentDelta +.0001);
        int newY = (int) Math.floor(target.getY() + getAmountY()* percentDelta +.0001);
        Obstacle obstacle = screen.getObstacleAt(newX, newY);

        if (/*screen.touchAndCanMoveTo(obstacle) &&*/ (newX <= 176 && newX >= 16 && newY >= 16 && newY <= 176) ) {
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
