package net.saga.game.cloclo.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import net.saga.game.cloclo.ScreenActor;
import net.saga.game.cloclo.characters.obstacle.*;
import net.saga.game.cloclo.control.CloCloInputEvent;
import net.saga.game.cloclo.control.ControlEventHandler;
import net.saga.game.cloclo.control.KeyboardControlEventSource;
import net.saga.game.cloclo.screens.components.ActorPanel;
import net.saga.game.cloclo.screens.components.ActorPanelComposite;
import net.saga.game.cloclo.screens.components.MultiPanel;

public class EditorScreen extends ScreenActor implements ControlEventHandler {

    private ActorPanelComposite toolsComposite, editorComposite;
    private MultiPanel toolBarPanel;

    private int toolIndex = 0;
    private int cursorX = 0,cursorY = 0;

    public EditorScreen(Texture spritesheet) {
        toolBarPanel = new MultiPanel();
        ActorPanel toolItemPanel = new ActorPanel(new Boulder(spritesheet, 0, 0));
        toolItemPanel.setLeft(0);
        toolBarPanel.addChild(toolItemPanel);

        toolItemPanel = new ActorPanel(new Tree(spritesheet,0,0));
        toolItemPanel.setLeft(16);
        toolBarPanel.addChild(toolItemPanel);

        toolItemPanel = new ActorPanel(new Snakey(spritesheet,0,0, null));
        toolItemPanel.setLeft(32);
        toolBarPanel.addChild(toolItemPanel);

        toolItemPanel = new ActorPanel(new HeartFrame(spritesheet,0,0, null));
        toolItemPanel.setLeft(48);
        toolBarPanel.addChild(toolItemPanel);

        toolItemPanel = new ActorPanel(new EmeraldBlock(spritesheet,0,0, null));
        toolItemPanel.setLeft(64);
        toolBarPanel.addChild(toolItemPanel);

        toolBarPanel.setLeft(0);
        toolBarPanel.setBottom(16);
        toolBarPanel.layout();
    }

    @Override
    public void addControlSource(KeyboardControlEventSource source) {
        this.source = source;
        this.source.addHandler(this);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        toolBarPanel.draw(batch);
    }

    @Override
    public void removeControlSource() {
        this.source.removeHandler(this);
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
