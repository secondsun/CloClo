package net.saga.game.cloclo.control;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.math.Vector3;

import static net.saga.game.cloclo.control.CloCloInputEvent.*;

public class XboxControllerEventSource extends ControlEventSource implements ControllerListener{

    @Override
    public void connected(Controller controller) {

    }

    @Override
    public void disconnected(Controller controller) {

    }

    @Override
    public boolean buttonDown(Controller controller, int buttonCode) {
        super.onEvent(ACTION);
        return false;
    }

    @Override
    public boolean buttonUp(Controller controller, int buttonCode) {
        return false;
    }

    @Override
    public boolean axisMoved(Controller controller, int axisCode, float value) {
        return false;
    }

    @Override
    public boolean povMoved(Controller controller, int povCode, PovDirection value) {
        CloCloInputEvent direction = null;
        switch (value) {
            case center:
                direction = CENTER;
                break;
            case north:
                direction = UP;
                break;
            case south:
                direction = DOWN;
                break;
            case east:
                direction = RIGHT;
                break;
            case west:
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

        if (direction != null) {
            super.onEvent(direction);
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
}
