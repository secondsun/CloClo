package net.saga.game.cloclo.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.mappings.Xbox;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import net.saga.game.cloclo.characters.obstacle.EmeraldBlock;
import net.saga.game.cloclo.characters.obstacle.Tree;
import net.saga.game.farfar.util.abstraction.OnButtonDown;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a normal gameplay level.
 * <p>
 * Each level has a tileset, map, player, enemies, treasure, heart frames, blocks, and an exit.
 * <p>
 * A screen also process all logic to a level such as if a player pushes a block to block moves,
 * checking if enemies can affect the player, etc
 */
public class PuzzleMapScreen extends Actor {


    private final TextureRegion frame;
    private final TextureRegion floorTile;
    private final Player player;
    private final List<Obstacle> obstacles = new ArrayList<>(100);

    public PuzzleMapScreen(Texture spritesheet) {
        frame = new TextureRegion(spritesheet, 128, 0, 208, 216);
        floorTile = new TextureRegion(spritesheet, 128, 217, 16, 16);
        this.player = new Boy(spritesheet);
        this.player.setX(16);
        this.player.setY(16);

        loadObstacles(spritesheet);

    }

    private void loadObstacles(Texture spritesheet) {
        for (int i = 2; i < 8; i++) {
            for (int j = 2; j < 8; j++) {
                if (i%3 == 1 && j%3 == 1) {
                    obstacles.add(new Tree(spritesheet, i * 16, j*16));
                    obstacles.add(new EmeraldBlock(spritesheet, (1+i) * 16, j*16, this));
                }
            }
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        drawFloor(batch);
        drawFrame(batch);
        drawPlayer(batch);
        drawObstacles(batch);
    }

    private void drawObstacles(Batch batch) {
        obstacles.stream().map(obj -> (Actor)obj).forEach(obj -> {obj.draw(batch,0);});
    }

    private void drawPlayer(Batch batch) {
        this.player.draw(batch, 0);
    }

    private void drawFrame(Batch batch) {
        batch.draw(frame, 0, 0);
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

    @Override
    public void act(float delta) {
        super.act(delta);
        //check Player Collisions
        if (player.isWalking()) {
            float playerX = player.getX();
            float playerY = player.getY();
            switch (player.getDirection()) {
                case UP:
                    if (playerY < 176) {
                        Obstacle obstacle = getObstacleAt(playerX, playerY + 1);
                        if (touchAndCanMoveTo(obstacle)) {
                            player.setY(playerY + 1);
                        }
                    }
                    break;
                case DOWN:
                    if (playerY > 16) {
                        Obstacle obstacle = getObstacleAt(playerX, playerY - 1);
                        if (touchAndCanMoveTo(obstacle)) {
                            player.setY(player.getY() - 1);
                        }
                    }

                    break;
                case LEFT:
                    if (playerX > 16) {
                        Obstacle obstacle = getObstacleAt(playerX - 1, playerY);
                        if (touchAndCanMoveTo(obstacle)) {
                            player.setX(player.getX() - 1);
                        }
                    }
                    break;
                case RIGHT:
                    if (playerX < 176) {
                        Obstacle obstacle = getObstacleAt(playerX + 1, playerY);
                        if (touchAndCanMoveTo(obstacle)) {
                            player.setX(player.getX() + 1);
                        }
                    }
                    break;
            }
        }
    }

    /**
     * Touches the space that obstacle is in and returns if the obstacle does not resist
     * the player moving into that space.
     *
     * @param obstacle an obstacle
     * @return
     */
    public boolean touchAndCanMoveTo(Obstacle obstacle) {
        boolean canMoveThrough = obstacle.touch(player.getX(), player.getY(), player.getDirection());
        return canMoveThrough;
    }

    public Obstacle getObstacleAt(float x, float y) {
        return getObstacleAt(x,y,null);
    }

    public Obstacle getObstacleAt(float x, float y, Obstacle ignore) {
        return obstacles.stream().filter(obj -> {return obj.checkBounds(x,y) && obj != ignore;} ).findFirst().orElseGet(() -> Obstacle.EMPTY);
    }


    public void addControllerListeners(Controller controller) {
        controller.addListener(player);
        controller.addListener((OnButtonDown) (Controller controller1, int buttonCode) -> {
            if (buttonCode == Xbox.BACK) {
                Gdx.app.exit();
            }
            return false;
        });
    }

}
