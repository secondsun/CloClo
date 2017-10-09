package net.saga.game.cloclo.characters.obstacle;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import net.saga.game.cloclo.characters.Direction;
import net.saga.game.cloclo.characters.PuzzleMapScreen;

import static net.saga.game.cloclo.characters.obstacle.Characteristic.PASSABLE;

public class Door extends Obstacle {

    private boolean open = false;

    private final TextureRegion closedTexture, openTexture;
    private final PuzzleMapScreen puzzleMapScreen;

    public Door(Texture spritesheet, int x, int y, PuzzleMapScreen screen) {
        this.puzzleMapScreen = screen;
        setX(x);
        setY(y);
        closedTexture = new TextureRegion(spritesheet, 128+16, 217,16,16);
        openTexture = new TextureRegion(spritesheet, 128+32, 217,16,16);
    }

    @Override
    public boolean touch(float x, float y, Direction direction) {
        return open;
    }


    @Override
    public boolean hasCharacteristic(Characteristic characteristic) {
        return PASSABLE.equals(characteristic) && open;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(open?openTexture:closedTexture, getX(), getY());
    }

    public void open() {
        open = true;
    }
}
