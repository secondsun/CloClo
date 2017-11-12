package net.saga.game.cloclo.screens.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.utils.Array;

public class ActorPanelComposite extends Actor {
    private final ActorPanel panel;
    private final Actor actor;
    public ActorPanelComposite(ActorPanel panel) {
        this.panel = panel;
        this.actor = panel.actor;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        panel.draw(batch);
    }

    @Override
    public void act(float delta) {
        actor.act(delta);
    }

    @Override
    public boolean fire(Event event) {
        return actor.fire(event);
    }

    @Override
    public boolean notify(Event event, boolean capture) {
        return actor.notify(event, capture);
    }

    @Override
    public Actor hit(float x, float y, boolean touchable) {
        return actor.hit(x, y, touchable);
    }

    @Override
    public boolean remove() {
        return actor.remove();
    }

    @Override
    public boolean addListener(EventListener listener) {
        return actor.addListener(listener);
    }

    @Override
    public boolean removeListener(EventListener listener) {
        return actor.removeListener(listener);
    }

    @Override
    public Array<EventListener> getListeners() {
        return actor.getListeners();
    }

    @Override
    public boolean addCaptureListener(EventListener listener) {
        return actor.addCaptureListener(listener);
    }

    @Override
    public boolean removeCaptureListener(EventListener listener) {
        return actor.removeCaptureListener(listener);
    }

    @Override
    public Array<EventListener> getCaptureListeners() {
        return actor.getCaptureListeners();
    }

    @Override
    public void addAction(Action action) {
        actor.addAction(action);
    }

    @Override
    public void removeAction(Action action) {
        actor.removeAction(action);
    }

    @Override
    public Array<Action> getActions() {
        return actor.getActions();
    }

    @Override
    public boolean hasActions() {
        return actor.hasActions();
    }

    @Override
    public void clearActions() {
        actor.clearActions();
    }

    @Override
    public void clearListeners() {
        actor.clearListeners();
    }

    @Override
    public void clear() {
        actor.clear();
    }

    @Override
    public Stage getStage() {
        return actor.getStage();
    }

    @Override
    public boolean isDescendantOf(Actor actor) {
        return this.actor.isDescendantOf(actor);
    }

    @Override
    public boolean isAscendantOf(Actor actor) {
        return this.actor.isAscendantOf(actor);
    }

    @Override
    public <T extends Actor> T firstAscendant(Class<T> type) {
        return actor.firstAscendant(type);
    }

    @Override
    public boolean hasParent() {
        return actor.hasParent();
    }

    @Override
    public Group getParent() {
        return actor.getParent();
    }


    @Override
    public boolean isTouchable() {
        return actor.isTouchable();
    }

    @Override
    public Touchable getTouchable() {
        return actor.getTouchable();
    }

    @Override
    public void setTouchable(Touchable touchable) {
        actor.setTouchable(touchable);
    }

    @Override
    public boolean isVisible() {
        return actor.isVisible();
    }

    @Override
    public void setVisible(boolean visible) {
        actor.setVisible(visible);
    }

    @Override
    public Object getUserObject() {
        return actor.getUserObject();
    }

    @Override
    public void setUserObject(Object userObject) {
        actor.setUserObject(userObject);
    }

    @Override
    public float getX() {
        return actor.getX();
    }

    @Override
    public float getX(int alignment) {
        return actor.getX(alignment);
    }

    @Override
    public void setX(float x) {
        actor.setX(x);
    }

    @Override
    public float getY() {
        return actor.getY();
    }

    @Override
    public void setY(float y) {
        actor.setY(y);
    }

    @Override
    public float getY(int alignment) {
        return actor.getY(alignment);
    }

    @Override
    public void setPosition(float x, float y) {
        actor.setPosition(x, y);
    }

    @Override
    public void setPosition(float x, float y, int alignment) {
        actor.setPosition(x, y, alignment);
    }

    @Override
    public void moveBy(float x, float y) {
        actor.moveBy(x, y);
    }

    @Override
    public float getWidth() {
        return actor.getWidth();
    }

    @Override
    public void setWidth(float width) {
        actor.setWidth(width);
    }

    @Override
    public float getHeight() {
        return actor.getHeight();
    }

    @Override
    public void setHeight(float height) {
        actor.setHeight(height);
    }

    @Override
    public float getTop() {
        return actor.getTop();
    }

    @Override
    public float getRight() {
        return actor.getRight();
    }



    @Override
    public void setSize(float width, float height) {
        actor.setSize(width, height);
    }

    @Override
    public void sizeBy(float size) {
        actor.sizeBy(size);
    }

    @Override
    public void sizeBy(float width, float height) {
        actor.sizeBy(width, height);
    }

    @Override
    public void setBounds(float x, float y, float width, float height) {
        actor.setBounds(x, y, width, height);
    }

    @Override
    public float getOriginX() {
        return actor.getOriginX();
    }

    @Override
    public void setOriginX(float originX) {
        actor.setOriginX(originX);
    }

    @Override
    public float getOriginY() {
        return actor.getOriginY();
    }

    @Override
    public void setOriginY(float originY) {
        actor.setOriginY(originY);
    }

    @Override
    public void setOrigin(float originX, float originY) {
        actor.setOrigin(originX, originY);
    }

    @Override
    public void setOrigin(int alignment) {
        actor.setOrigin(alignment);
    }

    @Override
    public float getScaleX() {
        return actor.getScaleX();
    }

    @Override
    public void setScaleX(float scaleX) {
        actor.setScaleX(scaleX);
    }

    @Override
    public float getScaleY() {
        return actor.getScaleY();
    }

    @Override
    public void setScaleY(float scaleY) {
        actor.setScaleY(scaleY);
    }

    @Override
    public void setScale(float scaleXY) {
        actor.setScale(scaleXY);
    }

    @Override
    public void setScale(float scaleX, float scaleY) {
        actor.setScale(scaleX, scaleY);
    }

    @Override
    public void scaleBy(float scale) {
        actor.scaleBy(scale);
    }

    @Override
    public void scaleBy(float scaleX, float scaleY) {
        actor.scaleBy(scaleX, scaleY);
    }

    @Override
    public float getRotation() {
        return actor.getRotation();
    }

    @Override
    public void setRotation(float degrees) {
        actor.setRotation(degrees);
    }

    @Override
    public void rotateBy(float amountInDegrees) {
        actor.rotateBy(amountInDegrees);
    }

    @Override
    public void setColor(Color color) {
        actor.setColor(color);
    }

    @Override
    public void setColor(float r, float g, float b, float a) {
        actor.setColor(r, g, b, a);
    }

    @Override
    public Color getColor() {
        return actor.getColor();
    }

    @Override
    public String getName() {
        return actor.getName();
    }

    @Override
    public void setName(String name) {
        actor.setName(name);
    }

    @Override
    public void toFront() {
        actor.toFront();
    }

    @Override
    public void toBack() {
        actor.toBack();
    }

    @Override
    public void setZIndex(int index) {
        actor.setZIndex(index);
    }

    @Override
    public int getZIndex() {
        return actor.getZIndex();
    }

    @Override
    public boolean clipBegin() {
        return actor.clipBegin();
    }

    @Override
    public boolean clipBegin(float x, float y, float width, float height) {
        return actor.clipBegin(x, y, width, height);
    }

    @Override
    public void clipEnd() {
        actor.clipEnd();
    }

    @Override
    public Vector2 screenToLocalCoordinates(Vector2 screenCoords) {
        return actor.screenToLocalCoordinates(screenCoords);
    }

    @Override
    public Vector2 stageToLocalCoordinates(Vector2 stageCoords) {
        return actor.stageToLocalCoordinates(stageCoords);
    }

    @Override
    public Vector2 localToStageCoordinates(Vector2 localCoords) {
        return actor.localToStageCoordinates(localCoords);
    }

    @Override
    public Vector2 localToParentCoordinates(Vector2 localCoords) {
        return actor.localToParentCoordinates(localCoords);
    }

    @Override
    public Vector2 localToAscendantCoordinates(Actor ascendant, Vector2 localCoords) {
        return actor.localToAscendantCoordinates(ascendant, localCoords);
    }

    @Override
    public Vector2 parentToLocalCoordinates(Vector2 parentCoords) {
        return actor.parentToLocalCoordinates(parentCoords);
    }

    @Override
    public void drawDebug(ShapeRenderer shapes) {
        actor.drawDebug(shapes);
    }


    @Override
    public void setDebug(boolean enabled) {
        actor.setDebug(enabled);
    }

    @Override
    public boolean getDebug() {
        return actor.getDebug();
    }

    @Override
    public Actor debug() {
        return actor.debug();
    }

    @Override
    public String toString() {
        return actor.toString();
    }
}
