package net.saga.game.cloclo.characters.obstacle;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import net.saga.game.cloclo.characters.CloCloInputEvent;
import net.saga.game.cloclo.characters.PuzzleMapScreen;

import static net.saga.game.cloclo.characters.obstacle.Characteristic.PASSABLE;

/**
 * EmeraldBlocks are opaque blocks that the Player can push.
 */
public class HeartFrame extends Obstacle {

    private final TextureRegion treeTexture;
    private final PuzzleMapScreen puzzleMapScreen;

    public HeartFrame(Texture spriteSheet, int i, int j, PuzzleMapScreen puzzleMapScreen) {
        super();
        this.treeTexture = new TextureRegion(spriteSheet, 192, 233, 16, 16);
        setX(i);
        setY(j);
        setWidth(16);
        setHeight(16);
        this.puzzleMapScreen = puzzleMapScreen;
    }




    @Override
    public boolean touch(float playerX, float playerY, CloCloInputEvent direction) {

        return true;
    }



    @Override
    public boolean hasCharacteristic(Characteristic characteristic) {
        return PASSABLE.equals(characteristic);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(treeTexture, getX(), getY());
    }
}
