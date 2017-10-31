package net.saga.game.cloclo.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import net.saga.game.cloclo.ActorScreen;
import net.saga.game.cloclo.characters.Boy;
import net.saga.game.cloclo.characters.Player;
import net.saga.game.cloclo.characters.PlayerMoveByAction;
import net.saga.game.cloclo.characters.obstacle.*;
import net.saga.game.cloclo.characters.obstacle.Obstacle;
import net.saga.game.cloclo.control.KeyboardControlEventSource;
import net.saga.game.cloclo.levelloader.MapData;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static net.saga.game.cloclo.control.CloCloInputEvent.DOWN;
import static net.saga.game.cloclo.control.CloCloInputEvent.UP;

/**
 * This is a normal gameplay level.
 * <p>
 * Each level has a tileset, map, player, enemies, treasure, heart frames, blocks, and an exit.
 * <p>
 * A screen also process all logic to a level such as if a player pushes a block to block moves,
 * checking if enemies can affect the player, etc
 */
public class PuzzleMapScreen extends ActorScreen {


    private final TextureRegion frame;
    private final TextureRegion floorTile;
    private final Player player;
    private final List<net.saga.game.cloclo.characters.obstacle.Obstacle> obstacles = new ArrayList<>(100);
    private final Door door;
    private boolean ended = false;
    public PuzzleMapScreen(Texture spritesheet, MapData mapData) {
        frame = new TextureRegion(spritesheet, 128, 0, 208, 216);
        floorTile = new TextureRegion(spritesheet, 128, 217, 16, 16);
        this.player = new Boy(spritesheet);
        this.player.setX(mapData.metadata.start.x);
        this.player.setY(mapData.metadata.start.y);
        this.door = new Door(spritesheet, mapData.metadata.door.x, mapData.metadata.door.y, this);
        loadObstacles(spritesheet, mapData);

    }

    private void loadObstacles(Texture spritesheet, MapData mapData) {
         for (MapData.Point blockData : mapData.obstacles.emeraldBlock) {
            obstacles.add(new EmeraldBlock(spritesheet, blockData.x, blockData.y,this));
         }

        for (MapData.Point blockData : mapData.obstacles.heartFrame) {
            obstacles.add(new HeartFrame(spritesheet, blockData.x, blockData.y,this));
        }

        for (MapData.Point blockData : mapData.obstacles.tree) {
            obstacles.add(new Tree(spritesheet, blockData.x, blockData.y));
        }

        obstacles.add(door);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        drawFloor(batch);
        drawFrame(batch);
        drawObstacles(batch);
        drawPlayer(batch);
    }

    private void drawObstacles(Batch batch) {
        obstacles.stream().map(obj -> (Actor) obj).forEach(obj -> {
            obj.draw(batch, 0);
        });
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
        if (!ended) {
            if (player.isWalking() && player.getActions().size == 0) {
                PlayerMoveByAction action;
                Obstacle obstacle;
                switch (player.getDirection()) {
                    case UP:
                        obstacle = getObstacleAt(player.getX(), player.getY() + 8);
                        if (touchAndCanMoveTo(obstacle)) {
                            if (obstacle != door) {
                                action = new PlayerMoveByAction(this, player);
                                action.setAmount(0, 8);
                                player.addAction(action);
                            } else {
                                player.setDisplayDirection(UP);
                                player.addAction(Actions.sequence(Actions.moveTo(door.getX(), door.getY(), 0.25f), new Action() {
                                    @Override
                                    public boolean act(float delta) {
                                        endLevel();
                                        return true;
                                    }
                                }));

                            }
                        } else {
                            player.setDisplayDirection(player.getDirection());
                        }
                        break;
                    case DOWN:
                        if (touchAndCanMoveTo(getObstacleAt(player.getX(), player.getY() - 8))) {
                            action = new PlayerMoveByAction(this, player);
                            action.setAmount(0, -8);
                            player.addAction(action);
                        } else {
                            player.setDisplayDirection(player.getDirection());
                        }
                        break;
                    case LEFT:
                        if (touchAndCanMoveTo(getObstacleAt(player.getX() - 8, player.getY()))) {
                            action = new PlayerMoveByAction(this, player);
                            action.setAmount(-8, 0);
                            player.addAction(action);
                        } else {
                            player.setDisplayDirection(player.getDirection());
                        }
                        break;
                    case RIGHT:
                        if (touchAndCanMoveTo(getObstacleAt(player.getX() + 8, player.getY()))) {
                            action = new PlayerMoveByAction(this, player);
                            action.setAmount(8, 0);
                            player.addAction(action);

                        } else {
                            player.setDisplayDirection(player.getDirection());
                        }
                        break;

                }
            }
            player.act(delta);
            obstacles.forEach(obstacle -> {
                if (obstacle instanceof Actor) {
                    ((Actor) obstacle).act(delta);
                }
            });
        }
    }

    private void endLevel() {
        ended = true;
        player.setDirection(DOWN);
        player.animateVictory();
        addAction(Actions.sequence(Actions.fadeOut(5), new Action() {
            @Override
            public boolean act(float delta) {
                Gdx.app.exit();
                return true;
            }
        }));
    }


    public float round(float x, int i) {
        return i * (Math.round(x / i));
    }

    /**
     * Touches the space that obstacle is in and returns if the obstacle does not resist
     * the player moving into that space.
     *
     * @param obstacle an obstacle
     * @return
     */
    public boolean touchAndCanMoveTo(net.saga.game.cloclo.characters.obstacle.Obstacle obstacle) {
        boolean canMoveThrough = obstacle.touch(player.getX(), player.getY(), player.getDirection());
        return canMoveThrough;
    }

    public net.saga.game.cloclo.characters.obstacle.Obstacle getObstacleAt(float x, float y) {
        return getObstacleAt(x, y, null);
    }

    /**
     * TODO : When you are pushing an object into objects that can be pushed sometimes you can pass through
     * them because the player is aligned with multiple objects in a line and only one has its bounds checked.
     *
     * @param x      x position
     * @param y      y position
     * @param ignore an object to ignore.  Used when obstacles are getting obstacles for themselves.
     * @return an obstacle or EMPTY
     */
    public net.saga.game.cloclo.characters.obstacle.Obstacle getObstacleAt(float x, float y, net.saga.game.cloclo.characters.obstacle.Obstacle ignore) {

        if (x < 16 || x > 176 || y < 16 || y > 176) {
            if (!door.checkBounds(x,y) || door.getX() != player.getX()) {
                return net.saga.game.cloclo.characters.obstacle.Obstacle.BOUNDARY;
            }
        }

        List<net.saga.game.cloclo.characters.obstacle.Obstacle> obstacleList = obstacles.stream().filter(obj -> {
            return obj.checkBounds(x, y) && obj != ignore;
        }).collect(Collectors.toList());

        switch (obstacleList.size()) {
            case 0:
                return net.saga.game.cloclo.characters.obstacle.Obstacle.EMPTY;
            case 1:
                return obstacleList.get(0);
            default:
                return new ObstacleTuple(obstacleList, this);

        }


    }
//
//
//    public void addControllerListeners(Controller controller) {
//        controller.addListener(player);
//        player.setController(controller);
//        controller.addListener((OnButtonDown) (Controller controller1, int buttonCode) -> {
//            if (buttonCode == Xbox.BACK) {
//                Gdx.app.exit();
//            }
//            return false;
//        });
//    }

    public void collectItem(HeartFrame heartFrame) {
        if (Math.abs(player.getX() - heartFrame.getX()) < 4 && Math.abs(player.getY() - heartFrame.getY()) < 4) {
            obstacles.remove(heartFrame);
            if (allHeartsCollected()) {
                triggerGateOpen();
            }
        }
    }

    private void triggerGateOpen() {
        door.open();
    }

    private boolean allHeartsCollected() {
        return obstacles.stream().noneMatch(obj -> obj instanceof HeartFrame);
    }

    @Override
    public void addControlSource(KeyboardControlEventSource source) {
        this.source = source;
        source.addHandler(player);
    }

    @Override
    public void removeControlSource() {
        source.removeHandler(player);
    }

    @Override
    public void dispose() {

    }
}
