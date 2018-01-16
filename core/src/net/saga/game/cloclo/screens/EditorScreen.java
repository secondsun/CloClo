package net.saga.game.cloclo.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import net.saga.game.cloclo.ScreenActor;
import net.saga.game.cloclo.characters.obstacle.*;
import net.saga.game.cloclo.control.CloCloInputEvent;
import net.saga.game.cloclo.control.ControlEventHandler;
import net.saga.game.cloclo.control.KeyboardControlEventSource;
import net.saga.game.cloclo.screens.components.ActorPanelComposite;

public class EditorScreen extends Table implements ControlEventHandler, ScreenActor  {

    private ActorPanelComposite toolsComposite, editorComposite;
    private ScrollPane toolBarScrollPane;
    private HorizontalGroup toolBarPanel;

    private int toolIndex = 0;
    private int cursorX = 0,cursorY = 0;
    private KeyboardControlEventSource source;

    public EditorScreen(Texture spritesheet, Skin skin) {
        this.setFillParent(true);
        setDebug(true);
        this.align(Align.center);
        this.setBackground(new TextureRegionDrawable(new TextureRegion(spritesheet,0,106,48,16)));
        toolBarPanel = new HorizontalGroup();

        toolBarPanel.addActor(attachLoggingListener(new Boulder(spritesheet, 0, 0)));
        toolBarPanel.addActor(attachLoggingListener(new Tree(spritesheet,0,0)));
        toolBarPanel.addActor(attachLoggingListener(new Snakey(spritesheet,0,0, null)));
        toolBarPanel.addActor(attachLoggingListener(new HeartFrame(spritesheet,0,0, null)));
        toolBarPanel.addActor(attachLoggingListener(new EmeraldBlock(spritesheet,0,0, null)));

        //toolBarPanel.setWidth(64f);
        toolBarPanel.invalidate();
        toolBarPanel.validate();

        toolBarScrollPane = new ScrollPane(toolBarPanel, skin);
        toolBarScrollPane.setX(16f);
        toolBarScrollPane.setY(216f);
        toolBarScrollPane.getStyle().hScrollKnob = null;

        toolBarScrollPane.invalidate();
        toolBarScrollPane.validate();

        row();
        add(toolBarScrollPane ).width(54f);

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
