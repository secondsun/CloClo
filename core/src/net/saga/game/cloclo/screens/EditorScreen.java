package net.saga.game.cloclo.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import net.saga.game.cloclo.ScreenActor;
import net.saga.game.cloclo.characters.obstacle.*;
import net.saga.game.cloclo.characters.obstacle.Tree;
import net.saga.game.cloclo.characters.obstacle.editor.ExitPlacement;
import net.saga.game.cloclo.characters.obstacle.editor.PlayerStart;
import net.saga.game.cloclo.control.CloCloInputEvent;
import net.saga.game.cloclo.control.ControlEventHandler;
import net.saga.game.cloclo.control.KeyboardControlEventSource;
import net.saga.game.cloclo.screens.components.ActorPanelComposite;

public class EditorScreen extends Table implements ControlEventHandler, ScreenActor  {

    private final Texture spritesheet;
    private ScrollPane toolBarScrollPane;
    private HorizontalGroup toolBarPanel;

    private int toolIndex = 0;
    private int cursorX = 0,cursorY = 0;
    private KeyboardControlEventSource source;
    private Skin skin;
    private TextureRegion frame;
    private TextureRegion floorTile;

    public EditorScreen(Texture spritesheet, Skin skin) {
        this.setFillParent(true);
        this.spritesheet = spritesheet;
        this.skin = skin;
        frame = new TextureRegion(spritesheet, 128, 0, 208, 216);
        floorTile = new TextureRegion(spritesheet, 128, 217, 16, 16);

        top().left();
        drawToolbarRow();
        row();
        drawEditorRow();



    }

    private void drawEditorRow() {

        HorizontalGroup group = new HorizontalGroup();
        group.addActor(attachLoggingListener(new EditableMap(frame, floorTile)));
        add(group).width(200).height(200).left().top();
    }

    private void drawToolbarRow() {

        toolBarPanel = new HorizontalGroup();
        toolBarPanel.align(Align.topLeft);
        toolBarPanel.addActor(attachLoggingListener(new Boulder(spritesheet, 0, 0)));
        toolBarPanel.addActor(attachLoggingListener(new Tree(spritesheet,0,0)));
        toolBarPanel.addActor(attachLoggingListener(new Snakey(spritesheet,0,0, null)));
        toolBarPanel.addActor(attachLoggingListener(new HeartFrame(spritesheet,0,0, null)));
        toolBarPanel.addActor(attachLoggingListener(new EmeraldBlock(spritesheet,0,0, null)));
        toolBarPanel.addActor(attachLoggingListener(new PlayerStart(spritesheet,0,0, null)));
        toolBarPanel.addActor(attachLoggingListener(new ExitPlacement(spritesheet,0,0, null)));

        add(toolBarPanel ).left().height(32);
    }

    private Actor attachLoggingListener(final Actor actor) {
        actor.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Clicked " + actor.getClass().getSimpleName());
            }

        });
        return actor;
    }

    @Override
    public void addControlSource(KeyboardControlEventSource source) {
        this.setSource(source);
        this.getSource().addHandler(this);
    }

    @Override
    public KeyboardControlEventSource getSource() {
        return source;
    }

    @Override
    public void setSource(KeyboardControlEventSource source) {
        this.source = source;
    }

    @Override
    public void removeControlSource() {
        this.getSource().removeHandler(this);
    }

    @Override
    public boolean onEvent(CloCloInputEvent direction) {
        switch (direction) {
            case UP:
                break;
            case DOWN:
                break;
            case LEFT:
                break;
            case RIGHT:
                break;
            case ACTION:
                break;
            case CENTER:
                break;
            case BACK:
                Gdx.app.exit();
                break;
            case TAB:
                switchPanel();
                break;
        }
        return true;
    }

    private void switchPanel() {
    }

    @Override
    public void dispose() {

    }

}
