package net.saga.game.cloclo.control;

public interface ControlEventHandler {
    /**
     *
     * @param direction the digital direction from a control
     * @return true if handling should stop, false to pass on.
     */
    boolean onEvent(CloCloInputEvent direction);

}
