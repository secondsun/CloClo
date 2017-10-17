package net.saga.game.cloclo.characters;

import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import net.saga.game.cloclo.characters.obstacle.HeartFrame;
import net.saga.game.cloclo.characters.obstacle.Obstacle;

public class PlayerMoveByAction extends MoveByAction {

    private final PuzzleMapScreen screen;


    public PlayerMoveByAction(PuzzleMapScreen screen, Player player) {
        this.screen = screen;
        setDuration( 1 / 8f);
        player.setDisplayDirection(player.getDirection());
    }

    protected void updateRelative (float percentDelta) {
        int newX = (int) Math.floor(target.getX() + getAmountX()* percentDelta +.0001);
        int newY = (int) Math.floor(target.getY() + getAmountY()* percentDelta +.0001);

        if ((newX <= 176 && newX >= 16 && newY >= 16 && newY <= 176) ) {
            target.moveBy(getAmountX() * percentDelta, getAmountY() * percentDelta);
        } else {
            finish();
        }

    }

    @Override
    protected void end() {

        target.setX(screen.round(target.getX(), 8));
        target.setY(screen.round(target.getY(), 8));
        Obstacle obstacle = screen.getObstacleAt(target.getX(), target.getY());
        if (obstacle instanceof HeartFrame) {
            screen.collectItem((HeartFrame) obstacle);
        }
    }
}
