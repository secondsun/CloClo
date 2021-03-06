package net.saga.game.cloclo.screens;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import net.saga.game.cloclo.ScreenActor;
import net.saga.game.cloclo.CloCloGame;
import net.saga.game.cloclo.control.CloCloInputEvent;
import net.saga.game.cloclo.control.ControlEventHandler;
import net.saga.game.cloclo.control.KeyboardControlEventSource;

public class HomeScreen extends Table implements ControlEventHandler, ScreenActor {

    private static String[] MENU = {"SETTINGS", "EDITOR", "GAME"};
    private final CloCloGame game;
    private int selectionIndex = 0;
    private BitmapFont font = new BitmapFont();
    private KeyboardControlEventSource source;

    public HomeScreen(CloCloGame game) {
        this.game = game;

    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        font.draw(batch, ">", 85, calculateCursorForSelection());
        font.draw(batch, "GAME", 100, 150);
        font.draw(batch, "EDITOR", 100, 100);
        font.draw(batch, "SETTINGS", 100, 50);

    }

    private float calculateCursorForSelection() {
        return selectionIndex % 3 * 50 + 50;
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
    public void addControlSource(KeyboardControlEventSource source) {
        this.setSource( source) ;
        this.getSource().addHandler(this);
    }

    @Override
    public void removeControlSource() {
        this.getSource().removeHandler(this);
    }

    @Override
    public boolean onEvent(CloCloInputEvent direction) {
        switch (direction) {
            case UP:
                selectionIndex++;
                break;
            case DOWN:
                selectionIndex--;
                if (selectionIndex < 0) {
                    selectionIndex = 2;
                }
                break;
            case LEFT:
                break;
            case RIGHT:
                break;
            case ACTION:
                doSelection(MENU[selectionIndex % 3]);
                break;
            case CENTER:
                break;
        }
        return true;
    }

    private void doSelection(String menu) {
        switch (menu) {
            case "GAME":
                game.switchToPuzzleMapScreen();
                break;
            case "SETTINGS":
                break;
            case "EDITOR":
                game.switchToEditorScreen();
                break;
        }
    }


    @Override
    public void dispose() {
        this.font.dispose();
    }

}
