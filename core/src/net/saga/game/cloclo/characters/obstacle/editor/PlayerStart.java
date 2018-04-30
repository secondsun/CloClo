package net.saga.game.cloclo.characters.obstacle.editor;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import net.saga.game.cloclo.characters.obstacle.Characteristic;
import net.saga.game.cloclo.characters.obstacle.Obstacle;
import net.saga.game.cloclo.control.CloCloInputEvent;
import net.saga.game.cloclo.screens.PuzzleMapScreen;

import static net.saga.game.cloclo.characters.obstacle.Characteristic.PUSHABLE;

public class PlayerStart extends Obstacle {

    private final TextureRegion boyTexture;
    private final PuzzleMapScreen puzzleMapScreen;

    public PlayerStart(Texture spriteSheet, int i, int j, PuzzleMapScreen puzzleMapScreen) {
        super();
        this.boyTexture = new TextureRegion(spriteSheet, 0, 0, 16, 16);
        setX(i);
        setY(j);
        setWidth(16);
        setHeight(16);
        this.puzzleMapScreen = puzzleMapScreen;
    }



    @Override
    public boolean touch(float playerX, float playerY, CloCloInputEvent direction) {
        return false;
    }

    @Override
    public boolean hasCharacteristic(Characteristic characteristic) {
        return PUSHABLE.equals(characteristic);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(boyTexture, getX(), getY());
    }
}
