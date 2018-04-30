package net.saga.game.cloclo.characters.obstacle.editor;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import net.saga.game.cloclo.characters.obstacle.Characteristic;
import net.saga.game.cloclo.characters.obstacle.Obstacle;
import net.saga.game.cloclo.control.CloCloInputEvent;
import net.saga.game.cloclo.screens.PuzzleMapScreen;

import static net.saga.game.cloclo.characters.obstacle.Characteristic.PASSABLE;

public class ExitPlacement extends Obstacle {

    private boolean open = false;

    private final TextureRegion closedTexture;
    private final PuzzleMapScreen puzzleMapScreen;

    public ExitPlacement(Texture spritesheet, int x, int y, PuzzleMapScreen screen) {
        this.puzzleMapScreen = screen;
        setX(x);
        setY(y);
        setWidth(16);
        setHeight(16);
        closedTexture = new TextureRegion(spritesheet, 144, 217,16,16);

    }

    @Override
    public boolean touch(float x, float y, CloCloInputEvent direction) {
        return open;
    }


    @Override
    public boolean hasCharacteristic(Characteristic characteristic) {
        return PASSABLE.equals(characteristic) && open;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(closedTexture, getX(), getY());
    }

    public void open() {
        open = true;
    }
}
