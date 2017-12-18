package net.saga.game.cloclo.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import net.saga.game.cloclo.ScreenActor;
import net.saga.game.cloclo.characters.obstacle.*;
import net.saga.game.cloclo.characters.obstacle.Tree;
import net.saga.game.cloclo.control.CloCloInputEvent;
import net.saga.game.cloclo.control.ControlEventHandler;
import net.saga.game.cloclo.control.KeyboardControlEventSource;
import net.saga.game.cloclo.screens.components.ActorPanel;
import net.saga.game.cloclo.screens.components.ActorPanelComposite;
import net.saga.game.cloclo.screens.components.MultiPanel;

public class EditorScreen extends Table implements ControlEventHandler, ScreenActor  {

    private ActorPanelComposite toolsComposite, editorComposite;
    private ScrollPane toolBarScrollPane;
    private HorizontalGroup toolBarPanel;

    private int toolIndex = 0;
    private int cursorX = 0,cursorY = 0;
    private KeyboardControlEventSource source;

    public EditorScreen(Texture spritesheet, Skin skin) {
        this.setFillParent(true);
        this.align(Align.center);
        toolBarPanel = new HorizontalGroup();

        toolBarPanel.addActor(new Boulder(spritesheet, 0, 0));
        toolBarPanel.addActor(new Tree(spritesheet,0,0));
        toolBarPanel.addActor(new Snakey(spritesheet,0,0, null));
        toolBarPanel.addActor(new HeartFrame(spritesheet,0,0, null));
        toolBarPanel.addActor(new EmeraldBlock(spritesheet,0,0, null));
        toolBarPanel.expand(true);
        toolBarPanel.validate();

        toolBarScrollPane = new ScrollPane(toolBarPanel, skin);
        toolBarScrollPane.setWidth(200f);
        toolBarScrollPane.setX(16f);
        toolBarScrollPane.setY(216f);
        toolBarScrollPane.validate();

        this.add(toolBarScrollPane).size(200f);

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
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        toolBarScrollPane.draw(batch, parentAlpha);
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
