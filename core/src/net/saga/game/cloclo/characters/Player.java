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
import net.saga.game.cloclo.control.ControlEventHandler;

import java.util.Map;

import static net.saga.game.cloclo.characters.CloCloInputEvent.*;

public abstract class Player extends Actor implements ControlEventHandler {

    protected final Texture spriteSheet;
    private CloCloInputEvent direction = DOWN;
    private boolean walking = false;
    private float stateTime = 0;
    private Controller controller;

    private final Animation<TextureRegion> victoryAnimation;
    private final Map<CloCloInputEvent, Animation<TextureRegion>> directionAnimationMap;
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
    public boolean onEvent(CloCloInputEvent value) {

            switch (value) {
                case CENTER:
                    resetWalk();
                    break;
                case UP:
                    resetWalk();
                    walking = true;
                    direction = UP;
                    break;
                case DOWN:
                    resetWalk();
                    walking = true;
                    direction = DOWN;
                    break;
                case RIGHT:
                    resetWalk();
                    walking = true;
                    direction = RIGHT;
                    break;
                case LEFT:
                    resetWalk();
                    walking = true;
                    direction = LEFT;
                    break;

            }
        return true;
    }



    private void resetWalk() {
        stateTime = 0;
        walking = false;
    }

    protected abstract Map<CloCloInputEvent, Animation<TextureRegion>> buildeWalkingMap();

    public boolean isWalking() {
        return walking;
    }

    public CloCloInputEvent getDirection() {
        return direction;
    }

    public void setDirection(CloCloInputEvent direction) {
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

}
