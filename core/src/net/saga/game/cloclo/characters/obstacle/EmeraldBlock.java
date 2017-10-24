package net.saga.game.cloclo.characters.obstacle;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import net.saga.game.cloclo.control.CloCloInputEvent;
import net.saga.game.cloclo.screens.PuzzleMapScreen;

import static net.saga.game.cloclo.characters.obstacle.Characteristic.PUSHABLE;

/**
 * EmeraldBlocks are opaque blocks that the Player can push.
 */
public class EmeraldBlock extends Obstacle {

    private final TextureRegion treeTexture;
    private final PuzzleMapScreen puzzleMapScreen;

    public EmeraldBlock(Texture spriteSheet, int i, int j, PuzzleMapScreen puzzleMapScreen) {
        super();
        this.treeTexture = new TextureRegion(spriteSheet, 176, 233, 16, 16);
        setX(i);
        setY(j);
        setWidth(16);
        setHeight(16);
        this.puzzleMapScreen = puzzleMapScreen;
    }



    @Override
    public boolean touch(float playerX, float playerY, CloCloInputEvent direction) {
        switch (direction) {
            case UP:
                if (puzzleMapScreen.getObstacleAt(getX(), getY() + 1, this) == Obstacle.EMPTY) {
                    if (getActions().size == 0 && playerY < 160) {
                        EmeraldBlockMoveBy action = new EmeraldBlockMoveBy(puzzleMapScreen);
                        action.setAmount(0, 8);
                        addAction(action);
                    }
                    return true;
                } else {
                    return false;
                }

            case DOWN:
                if (puzzleMapScreen.getObstacleAt(getX(), getY() - 1, this) == Obstacle.EMPTY) {
                    if (getActions().size == 0 && playerY > 32) {
                        EmeraldBlockMoveBy action = new EmeraldBlockMoveBy(puzzleMapScreen);
                        action.setAmount(0, -8);
                        addAction(action);
                    }
                    return true;
                } else {
                    return false;
                }


            case LEFT:
                if (puzzleMapScreen.getObstacleAt(getX() - 1, getY(), this) == Obstacle.EMPTY) {
                    if (getActions().size == 0 && playerX > 32) {
                        EmeraldBlockMoveBy action = new EmeraldBlockMoveBy(puzzleMapScreen);
                        action.setAmount(-8, 0);
                        addAction(action);
                    }
                    return true;
                } else {
                    return false;
                }

            case RIGHT:
                if (puzzleMapScreen.getObstacleAt(getX() + 1, getY(), this) == Obstacle.EMPTY) {
                    if (getActions().size == 0 && playerX < 160) {
                        EmeraldBlockMoveBy action = new EmeraldBlockMoveBy(puzzleMapScreen);
                        action.setAmount(8, 0);
                        addAction(action);
                    }
                    return true;
                } else {
                    return false;
                }

        }
        return false;
    }

    @Override
    public boolean hasCharacteristic(Characteristic characteristic) {
        return PUSHABLE.equals(characteristic);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(treeTexture, getX(), getY());
    }
}
