package net.saga.game.cloclo.screens.components;

import com.badlogic.gdx.graphics.g2d.Batch;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class CloCloPanel {

    protected int zIndex = 0;
    protected int bottom, left, width, height, margin;
    /**
     * X and Y act as accumulators for the layout operatons and should not be used by external operations
     */
    protected int x,y;

    /**
     * Draws the panel to the batch.  Draws from negative to positive
     * @param batch
     */
    public abstract void draw(Batch batch);

    /**
     *
     *
     *
     * Lays out the values of children of this panel
     *
     * The order is :
     *  set x to x + getLeft();
     *  set y to y+getBottom();
     *  if you have children then set X and Y to the sum of the parent's x and y with margin
     *  then call layout of the child
     */
    public abstract void layout();

    public int getzIndex() {
        return zIndex;
    }

    public void setzIndex(int zIndex) {
        this.zIndex = zIndex;
    }

    public int getBottom() {
        return bottom;
    }

    public void setBottom(int bottom) {
        this.bottom = bottom;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getMargin() {
        return margin;
    }

    public void setMargin(int margin) {
        this.margin = margin;
    }

}
