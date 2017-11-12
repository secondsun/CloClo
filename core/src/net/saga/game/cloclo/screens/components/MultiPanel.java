package net.saga.game.cloclo.screens.components;


import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * A panel that hold multiple panels
 */
public class MultiPanel extends CloCloPanel {
    protected final TreeSet<CloCloPanel> children;

    public MultiPanel() {
        children = new TreeSet<>(Comparator.comparingInt(child -> child.zIndex));
    }


    @Override
    public void draw(Batch batch) {
        children.forEach(child -> child.draw(batch));
    }

    @Override
    public void layout() {
        x = left;
        y = bottom;
        children.forEach(child -> {
            child.x = x+getMargin();
            child.y = y+getMargin();
            child.layout();
        });
    }

    public MultiPanel addChild(CloCloPanel child) {
        children.add(child);
        return this;
    }

}
