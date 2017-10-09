package net.saga.game.cloclo.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.Map;

import static net.saga.game.cloclo.characters.Direction.*;

public abstract class Player extends Actor implements ControllerListener {

    protected final Texture spriteSheet;
    private Direction direction = DOWN;
    private boolean walking = false;
    private float stateTime = 0;
    private Controller controller;

    private final Animation<TextureRegion> victoryAnimation;
    private final Map<Direction, Animation<TextureRegion>> directionAnimationMap;
    private boolean victory = false;


    Player(Texture spriteSheet) {
        this.spriteSheet = spriteSheet;
        directionAnimationMap = buildeWalkingMap();
        victoryAnimation = buildVictoryAnimation();
    }

    protected abstract Animation<TextureRegion> buildVictoryAnimation();


    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (walking || victory) {
            stateTime += Gdx.graphics.getDeltaTime(); // Accumulate elapsed animation time
        }
        // Get current frame of animation for the current stateTime
        if (!victory) {
            TextureRegion currentFrame = directionAnimationMap.get(direction).getKeyFrame(stateTime, true);
            batch.draw(currentFrame, super.getX(), getY());
        } else {
            TextureRegion currentFrame = victoryAnimation.getKeyFrame(stateTime, true);
            batch.draw(currentFrame, super.getX(), getY());
        }
    }

    @Override
    public void connected(Controller controller) {
        controller.addListener(this);
    }

    @Override
    public void disconnected(Controller controller) {
        controller.removeListener(this);
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


    protected abstract Map<Direction, Animation<TextureRegion>> buildeWalkingMap();

    public boolean isWalking() {
        return walking;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void animateVictory() {
        resetWalk();
        victory = true;
    }

    public void unregisterAsControllerListener() {
        controller.removeListener(this);
    }
}
