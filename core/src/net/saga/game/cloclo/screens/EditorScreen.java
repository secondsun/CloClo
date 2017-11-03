package net.saga.game.cloclo.screens;

import net.saga.game.cloclo.ScreenActor;
import net.saga.game.cloclo.control.CloCloInputEvent;
import net.saga.game.cloclo.control.ControlEventHandler;
import net.saga.game.cloclo.control.KeyboardControlEventSource;

public class EditorScreen extends ScreenActor implements ControlEventHandler {


    @Override
    public void addControlSource(KeyboardControlEventSource source) {
        this.source = source;
        this.source.addHandler(this);
    }

    @Override
    public void removeControlSource() {
        this.source.removeHandler(this);
    }

    @Override
    public boolean onEvent(CloCloInputEvent direction) {
        switch (direction) {
            case UP:
                break;
            case DOWN:
                break;
            case LEFT:
                break;
            case RIGHT:
                break;
            case ACTION:
                break;
            case CENTER:
                break;
            case BACK:
                break;
        }
        return true;
    }

    @Override
    public void dispose() {

    }

}
