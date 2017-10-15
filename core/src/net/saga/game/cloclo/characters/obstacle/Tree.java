package net.saga.game.cloclo.characters.obstacle;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import net.saga.game.cloclo.characters.CloCloInputEvent;

/**
 * A tree is an non interactable object that does not move, prevents the player from moving through it,
 * but does not obstruct sight.
 */
public class Tree extends Obstacle {

    private final TextureRegion treeTexture;

    public Tree(Texture spriteSheet, int i, int j) {
        this.treeTexture = new TextureRegion(spriteSheet, 128,233,16,16);
        setX(i);
        setY(j);
        setWidth(16);
        setHeight(16);
    }

    @Override
    public boolean touch(float x, float y, CloCloInputEvent direction) {
        return false;
    }


    @Override
    public boolean hasCharacteristic(Characteristic characteristic) {
        return false;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(treeTexture, getX(), getY());
    }
}
