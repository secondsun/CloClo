package net.saga.game.cloclo.control;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import net.saga.game.cloclo.characters.CloCloInputEvent;

import java.util.EnumSet;
import java.util.HashMap;

public class KeyboardControlEventSource  extends ControlEventSource implements InputProcessor{

    private final HashMap<Integer, CloCloInputEvent> buttonMappings = new HashMap<>();
    private final EnumSet<CloCloInputEvent> downSet = EnumSet.noneOf(CloCloInputEvent.class);

    public KeyboardControlEventSource() {
        buttonMappings.put(Input.Keys.DPAD_DOWN, CloCloInputEvent.DOWN);
        buttonMappings.put(Input.Keys.DPAD_UP, CloCloInputEvent.UP);
        buttonMappings.put(Input.Keys.DPAD_LEFT, CloCloInputEvent.LEFT);
        buttonMappings.put(Input.Keys.DPAD_RIGHT, CloCloInputEvent.RIGHT);
        buttonMappings.put(Input.Keys.ENTER, CloCloInputEvent.ACTION);


    }

    private boolean directionKeysAreDown() {
        return downSet.contains(CloCloInputEvent.DOWN) || downSet.contains(CloCloInputEvent.LEFT) ||
                downSet.contains(CloCloInputEvent.RIGHT) || downSet.contains(CloCloInputEvent.UP);
    }

    /**
     * @param event event to emit, may be null.  On null do nothing
     */
    private void emit(CloCloInputEvent event) {
        if (event != null) {
            downSet.add(event);
            super.onEvent(event);
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        emit(buttonMappings.get(keycode));
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        CloCloInputEvent mapping = buttonMappings.get(keycode);
        if (mapping != null) {
            downSet.remove(mapping);
            if (!directionKeysAreDown()) {
                emit(CloCloInputEvent.CENTER);
            }
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
