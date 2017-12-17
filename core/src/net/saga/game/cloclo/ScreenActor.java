package net.saga.game.cloclo;

import com.badlogic.gdx.scenes.scene2d.Actor;
import net.saga.game.cloclo.control.KeyboardControlEventSource;

public interface ScreenActor {
    KeyboardControlEventSource getSource();
    void setSource(KeyboardControlEventSource  source);

    void addControlSource(KeyboardControlEventSource source);

    void removeControlSource();

    void dispose();
}
