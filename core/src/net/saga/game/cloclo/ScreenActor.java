package net.saga.game.cloclo;

import com.badlogic.gdx.scenes.scene2d.Actor;
import net.saga.game.cloclo.control.KeyboardControlEventSource;

public abstract class ScreenActor extends Actor{
    public KeyboardControlEventSource source;

    public abstract void addControlSource(KeyboardControlEventSource source);

    public abstract void removeControlSource();

    public abstract void dispose();
}
