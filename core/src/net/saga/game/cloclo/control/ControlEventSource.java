package net.saga.game.cloclo.control;

import net.saga.game.cloclo.characters.CloCloInputEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * A source of events for the player.  This can be a Controller (abstracted) or a keyboard, or maybe something else.
 */
public abstract class ControlEventSource {

    List<ControlEventHandler> handlers = new ArrayList<>();

    public void addHandler(ControlEventHandler handler) {
        handlers.add(handler);
    }

    public void removeHandler(ControlEventHandler handler) {
        handlers.remove(handler);
    }

    public List<ControlEventHandler> getHandlers() {
        return handlers;
    }

    public void onEvent(CloCloInputEvent direction) {
        for (ControlEventHandler handler : handlers) {
            if (handler.onEvent(direction)) {
                break;
            }
        }
    }


}
