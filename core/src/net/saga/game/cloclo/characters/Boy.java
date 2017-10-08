package net.saga.game.cloclo.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.controllers.mappings.Xbox;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.HashMap;
import java.util.Map;

import static net.saga.game.cloclo.characters.Direction.*;

/**
 * Class for the boy PC
 */
public class Boy extends Actor implements ControllerListener {

    private static int FRAME_COLS = 128;
    private static int FRAME_ROWS = 128;
    private final Map<Direction, Animation<TextureRegion>> directionAnimationMap;


    private float stateTime = 0;
    private Texture walkSheet;

    private Direction direction = DOWN;
    private boolean walking = false;


    public Boy() {
        setX(0);
        setY(0);
        walkSheet = new Texture(Gdx.files.internal("spritesheet.png"));
        // Use the split utility method to create a 2D array of TextureRegions. This is
        // possible because this sprite sheet contains frames of equal size and they are
        // all aligned.
        TextureRegion[][] tmp = TextureRegion.split(walkSheet,
                walkSheet.getWidth() / FRAME_COLS,
                walkSheet.getHeight() / FRAME_ROWS);

        // Place the regions into a 1D array in the correct order, starting from the top
        // left, going across first. The Animation constructor requires a 1D array.
        TextureRegion[] walkFrames = new TextureRegion[8];
        int index;
        directionAnimationMap = new HashMap<>(4);
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
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (walking) {
            stateTime += Gdx.graphics.getDeltaTime(); // Accumulate elapsed animation time
        }
        // Get current frame of animation for the current stateTime
        TextureRegion currentFrame = directionAnimationMap.get(direction).getKeyFrame(stateTime, true);
        batch.draw(currentFrame, super.getX(), getY());
    }

    public void dispose() {
        walkSheet.dispose();
    }

    @Override
    public void connected(Controller controller) {

    }

    @Override
    public void disconnected(Controller controller) {

    }

    @Override
    public boolean buttonDown(Controller controller, int buttonCode) {

        return false;
    }

    @Override
    public boolean buttonUp(Controller controller, int buttonCode) {
       return false;
    }

    private void resetWalk() {
        stateTime = 0;
        walking = false;

    }

    @Override
    public boolean axisMoved(Controller controller, int axisCode, float value) {
        return false;
    }

    @Override
    public boolean povMoved(Controller controller, int povCode, PovDirection value) {
        switch (value) {

            case center:
                resetWalk();
                break;
            case north:
                resetWalk();
                walking = true;
                direction = UP;
                break;
            case south:
                resetWalk();
                walking = true;
                direction = DOWN;
                break;
            case east:
                resetWalk();
                walking = true;
                direction = RIGHT;
                break;
            case west:
                resetWalk();
                walking = true;
                direction = LEFT;
                break;
            case northEast:
                break;
            case southEast:
                break;
            case northWest:
                break;
            case southWest:
                break;
        }
        return false;
    }

    @Override
    public boolean xSliderMoved(Controller controller, int sliderCode, boolean value) {
        return false;
    }

    @Override
    public boolean ySliderMoved(Controller controller, int sliderCode, boolean value) {
        return false;
    }

    @Override
    public boolean accelerometerMoved(Controller controller, int accelerometerCode, Vector3 value) {
        return false;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (walking) {
            switch (direction) {
                case UP:
                    setY(getY()+1);
                    break;
                case DOWN:
                    setY(getY()-1);
                    break;
                case LEFT:
                    setX(getX()-1);
                    break;
                case RIGHT:
                    setX(getX()+1);
                    break;
            }
        }
    }
}
