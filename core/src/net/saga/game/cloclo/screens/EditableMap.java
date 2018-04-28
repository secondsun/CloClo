package net.saga.game.cloclo.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class EditableMap extends Actor {
    private final TextureRegion frame;
    private final TextureRegion floorTile;

    public EditableMap(TextureRegion frame, TextureRegion floorTile) {
        this.frame = frame;
        this.floorTile = floorTile;
        setWidth(200);
        setHeight(200);
        setX(0);
        setY(0);

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        drawFloor(batch);
        drawFrame(batch);

    }

    private void drawFloor(Batch batch) {
        for (int x = 0; x < 200; x++) {
            for (int y = 0; y < 200; y++) {
                batch.draw(floorTile, x, y);
                y += 15;
            }
            x += 15;
        }
    }

    private void drawFrame(Batch batch) {
        batch.draw(frame, 0, 0);
    }
}
