package net.saga.game.cloclo.characters.obstacle;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import net.saga.game.cloclo.characters.Direction;
import net.saga.game.cloclo.characters.Obstacle;
import net.saga.game.cloclo.characters.PuzzleMapScreen;

/**
 * EmeraldBlocks are opaque blocks that the Player can push.
 */
public class EmeraldBlock extends Actor implements Obstacle {

    private final TextureRegion treeTexture;
    private final PuzzleMapScreen puzzleMapScreen;

    public EmeraldBlock(Texture spriteSheet, int i, int j, PuzzleMapScreen puzzleMapScreen) {
        this.treeTexture = new TextureRegion(spriteSheet, 176, 233, 16, 16);
        setX(i);
        setY(j);
        setWidth(16);
        setHeight(16);
        this.puzzleMapScreen = puzzleMapScreen;
    }

    @Override
    public boolean touch(float playerX, float playerY, Direction direction) {
        switch (direction) {
            case UP:
                if (playerY < 160) {
                    if (puzzleMapScreen.getObstacleAt(getX(), getY() + 1, this) == Obstacle.EMPTY) {
                        setY(getY() + 1);
                        return true;
                    } else {
                        return false;
                    }
                }
                break;
            case DOWN:
                if (playerY > 32) {
                    if (puzzleMapScreen.getObstacleAt(getX(), getY() - 1, this) == Obstacle.EMPTY) {
                        setY(getY() - 1);
                        return true;
                    } else {
                        return false;
                    }

                }

                break;
            case LEFT:
                if (playerX > 32) {
                    if (puzzleMapScreen.getObstacleAt(getX() - 1, getY(), this) == Obstacle.EMPTY) {
                        setX(getX() - 1);
                        return true;
                    } else {
                        return false;
                    }
                }
                break;
            case RIGHT:
                if (playerX < 160) {
                    if (puzzleMapScreen.getObstacleAt(getX() + 1, getY(), this) == Obstacle.EMPTY) {
                        setX(getX() + 1);
                        return true;
                    } else {
                        return false;
                    }
                }
                break;
        }
        return false;
    }

    @Override
    public boolean checkBounds(float x, float y) {
        Rectangle treeRect = new Rectangle(getX(), getY(), 16, 16);
        Rectangle playerRectangle = new Rectangle(x, y, 16, 16);
        return playerRectangle.overlaps(treeRect);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(treeTexture, getX(), getY());
    }
}
