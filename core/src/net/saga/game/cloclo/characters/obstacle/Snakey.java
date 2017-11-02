package net.saga.game.cloclo.characters.obstacle;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import net.saga.game.cloclo.control.CloCloInputEvent;
import net.saga.game.cloclo.levelloader.MapData;
import net.saga.game.cloclo.screens.PuzzleMapScreen;

public class Snakey extends EnemyActor {

    private final TextureRegion left, right, straight;
    private final PuzzleMapScreen puzzleMapScreen;

    public Snakey(Texture spritesheet, int x, int y, PuzzleMapScreen screen) {
        this.puzzleMapScreen = screen;
        setX(x);
        setY(y);
        straight = new TextureRegion(spritesheet, 0, 105,16,16);
        left = new TextureRegion(spritesheet, 16, 105,16,16);
        right = new TextureRegion(spritesheet, 32, 105,16,16);
    }


    @Override
    public void act(float delta) {
        super.act(delta);

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        MapData.Point playerPosition = puzzleMapScreen.getPlayerPosition();
        int delta = (int) (getX() - playerPosition.x);
        if (delta > 32) {
            batch.draw(left, getX(), getY());
        } else if (delta < -48) {
            batch.draw(right, getX(), getY());
        } else {
            batch.draw(straight, getX(), getY());
        }
    }


    @Override
    public boolean touch(float x, float y, CloCloInputEvent direction) {
        return false;
    }

    @Override
    public boolean hasCharacteristic(Characteristic characteristic) {
        return false;
    }
}
