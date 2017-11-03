package net.saga.game.cloclo.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import net.saga.game.cloclo.control.CloCloInputEvent;
import net.saga.game.cloclo.control.ControlEventHandler;

import java.util.Map;

import static net.saga.game.cloclo.control.CloCloInputEvent.*;

public abstract class Player extends Actor implements ControlEventHandler {

    protected final Texture spriteSheet;
    private net.saga.game.cloclo.control.CloCloInputEvent direction = DOWN;
    private net.saga.game.cloclo.control.CloCloInputEvent displayDirection = DOWN;
    private boolean walking = false;
    private float stateTime = 0;
    private Controller controller;

    private final Animation<TextureRegion> victoryAnimation;
    private final Map<net.saga.game.cloclo.control.CloCloInputEvent, Animation<TextureRegion>> directionAnimationMap;
    private boolean victory = false;


    Player(Texture spriteSheet) {
        this.spriteSheet = spriteSheet;
        directionAnimationMap = buildeWalkingMap();
        victoryAnimation = buildVictoryAnimation();
    }

    protected abstract Animation<TextureRegion> buildVictoryAnimation();


    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (walking || victory ) {
            stateTime += Gdx.graphics.getDeltaTime(); // Accumulate elapsed animation time
        }
        // Get current frame of animation for the current stateTime
        if (!victory) {
            TextureRegion currentFrame = directionAnimationMap.get(displayDirection).getKeyFrame(stateTime, true);
            batch.draw(currentFrame, super.getX(), getY());
        } else {
            TextureRegion currentFrame = victoryAnimation.getKeyFrame(stateTime, true);
            batch.draw(currentFrame, super.getX(), getY());
        }
    }

    @Override
    public boolean onEvent(net.saga.game.cloclo.control.CloCloInputEvent value) {

            switch (value) {
                case CENTER:
                    resetWalk();
                    break;
                case UP:
                    walking = true;
                    direction = UP;
                    break;
                case DOWN:
                    walking = true;
                    direction = DOWN;
                    break;
                case RIGHT:
                    walking = true;
                    direction = RIGHT;
                    break;
                case LEFT:

                    walking = true;
                    direction = LEFT;
                    break;
                default:
                    return false;
            }
        return true;
    }



    public void resetWalk() {
        stateTime = 0;
        walking = false;
    }

    public net.saga.game.cloclo.control.CloCloInputEvent getDisplayDirection() {
        return displayDirection;
    }

    public void setDisplayDirection(net.saga.game.cloclo.control.CloCloInputEvent displayDirection) {
        this.displayDirection = displayDirection;
    }

    protected abstract Map<net.saga.game.cloclo.control.CloCloInputEvent, Animation<TextureRegion>> buildeWalkingMap();

    public boolean isWalking() {
        return walking;
    }

    public net.saga.game.cloclo.control.CloCloInputEvent getDirection() {
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
