package net.saga.game.cloclo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import net.saga.game.cloclo.levelloader.LevelLoader;
import net.saga.game.cloclo.screens.EditorScreen;
import net.saga.game.cloclo.screens.HomeScreen;
import net.saga.game.cloclo.control.KeyboardControlEventSource;
import net.saga.game.cloclo.screens.PuzzleMapScreen;
import net.saga.game.cloclo.screens.components.ActorPanel;
import net.saga.game.cloclo.screens.components.ActorPanelComposite;
import net.saga.game.farfar.util.player.PlayerController;

public class CloCloGame extends ApplicationAdapter {
	private PlayerController playerController;
    private Viewport viewport;

    public static final int WORLD_WIDTH = 320;
    public static final int WORLD_HEIGHT = 240;

    private Stage stage;
    private Texture globalSheet;
    private Table mapScreen;

    @Override
	public void create () {
        globalSheet = new Texture(Gdx.files.internal("spritesheet.png"));
        //this.mapScreen = new PuzzleMapScreen(globalSheet);
        this.mapScreen = new HomeScreen(this);
        viewport = new FitViewport(WORLD_WIDTH,WORLD_HEIGHT);
        stage = new Stage(viewport);
        stage.addActor(new ActorPanelComposite(new ActorPanel(mapScreen)));


        KeyboardControlEventSource source = new KeyboardControlEventSource();
        Gdx.input.setInputProcessor(source);
        ((ScreenActor)mapScreen).addControlSource(source);
	}


    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Clear screen
        stage.act();
        stage.draw();
    }



    public void resize (int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void dispose() {
        globalSheet.dispose();
    }


    public void switchToPuzzleMapScreen() {
        stage.getActors().removeIndex(0);
        ((ScreenActor)mapScreen).removeControlSource();
        this.mapScreen = new PuzzleMapScreen(globalSheet, LevelLoader.getDemoMapData());
        KeyboardControlEventSource source = new KeyboardControlEventSource();
        Gdx.input.setInputProcessor(source);
        ((ScreenActor)mapScreen).addControlSource(source);
        stage.addActor(new ActorPanelComposite(new ActorPanel(((PuzzleMapScreen)mapScreen))));
    }

    public void switchToEditorScreen() {
        stage.getActors().removeIndex(0);
        ((ScreenActor)mapScreen).removeControlSource();
        this.mapScreen = new EditorScreen(globalSheet);
        KeyboardControlEventSource source = new KeyboardControlEventSource();
        Gdx.input.setInputProcessor(source);
        ((ScreenActor)mapScreen).addControlSource(source);
        stage.addActor(mapScreen);

    }
}
