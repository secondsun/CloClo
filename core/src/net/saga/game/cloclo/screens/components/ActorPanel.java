package net.saga.game.cloclo.screens.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.Collection;
import java.util.Comparator;
import java.util.TreeSet;

/**
 * Contains a single actor
 */
public class ActorPanel extends CloCloPanel {

    protected final Actor actor;
    private boolean selected = true;
    private Color selectedColor = Color.BLUE;

    public ActorPanel(Actor actor) {
        this.actor = actor;
    }

    @Override
    public void draw(Batch batch) {
        Color old = batch.getColor();
        if (selected ) {
            batch.setColor(Color.BLUE);
        }
        actor.draw(batch, 1f);
        batch.setColor(old);
    }

    @Override
    public void layout() {
        actor.setX(super.left + this.margin);
        actor.setY(super.bottom + this.margin);
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
