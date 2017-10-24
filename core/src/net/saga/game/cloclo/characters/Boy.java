package net.saga.game.cloclo.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import net.saga.game.cloclo.control.CloCloInputEvent;

import java.util.HashMap;
import java.util.Map;

import static net.saga.game.cloclo.control.CloCloInputEvent.*;

/**
 * Class for the boy PC
 */
public class Boy extends Player {

    private static int FRAME_COLS = 128;
    private static int FRAME_ROWS = 128;


    public Boy(Texture spriteSheet) {
        super(spriteSheet);

        setX(0);
        setY(0);

    }

    @Override
    protected Animation<TextureRegion> buildVictoryAnimation() {
        TextureRegion[][] tmp = TextureRegion.split(spriteSheet,
                spriteSheet.getWidth() / FRAME_COLS,
                spriteSheet.getHeight() / FRAME_ROWS);

        Animation<TextureRegion> animation; // Must declare frame type (TextureRegion)
        TextureRegion[] frames = new TextureRegion[2];

        int index = 0;
        for (int j = 0; j < 2; j++) {
            frames[index++] = tmp[4][j];
        }
        animation = new Animation<TextureRegion>(0.150f, frames);

        return animation;
    }

    @Override
    protected Map<net.saga.game.cloclo.control.CloCloInputEvent, Animation<TextureRegion>> buildeWalkingMap() {
        // Use the split utility method to create a 2D array of TextureRegions. This is
        // possible because this sprite sheet contains frames of equal size and they are
        // all aligned.
        TextureRegion[][] tmp = TextureRegion.split(spriteSheet,
                spriteSheet.getWidth() / FRAME_COLS,
                spriteSheet.getHeight() / FRAME_ROWS);

        // Place the regions into a 1D array in the correct order, starting from the top
        // left, going across first. The Animation constructor requires a 1D array.
        TextureRegion[] walkFrames = new TextureRegion[8];
        int index;
        Map<CloCloInputEvent, Animation<TextureRegion>> directionAnimationMap = new HashMap<>(4);
        Animation<TextureRegion> walkAnimation; // Must declare frame type (TextureRegion)

        index = 0;
        for (int j = 0; j < 8; j++) {
            walkFrames[index++] = tmp[0][j];
        }
        walkAnimation = new Animation<TextureRegion>(0.050f, walkFrames);
        directionAnimationMap.put(DOWN, walkAnimation);

        walkFrames = new TextureRegion[8];
        index = 0;
        for (int j = 0; j < 8; j++) {
            walkFrames[index++] = tmp[1][j];
        }
        walkAnimation = new Animation<TextureRegion>(0.050f, walkFrames);
        directionAnimationMap.put(RIGHT, walkAnimation);

        walkFrames = new TextureRegion[8];
        index = 0;
        for (int j = 0; j < 8; j++) {
            walkFrames[index++] = tmp[2][j];
        }
        walkAnimation = new Animation<TextureRegion>(0.050f, walkFrames);
        directionAnimationMap.put(LEFT, walkAnimation);

        walkFrames = new TextureRegion[8];
        index = 0;
        for (int j = 0; j < 8; j++) {
            walkFrames[index++] = tmp[3][j];
        }
        walkAnimation = new Animation<TextureRegion>(0.050f, walkFrames);
        directionAnimationMap.put(UP, walkAnimation);

        // Initialize the Animation with the frame interval and array of frames
        return directionAnimationMap;
    }


    public void dispose() {
        spriteSheet.dispose();
    }

}
