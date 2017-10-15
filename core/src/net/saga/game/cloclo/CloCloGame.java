package net.saga.game.cloclo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.mappings.Xbox;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import net.saga.game.cloclo.characters.Boy;
import net.saga.game.cloclo.characters.PuzzleMapScreen;
import net.saga.game.cloclo.control.KeyboardControlEventSource;
import net.saga.game.farfar.util.abstraction.OnButtonDown;
import net.saga.game.farfar.util.controller.LoggingControllerListenerProxyUtil;
import net.saga.game.farfar.util.player.PlayerController;

public class CloCloGame extends ApplicationAdapter {
	private PlayerController playerController;
    private Viewport viewport;

    public static final int WORLD_WIDTH = 320;
    public static final int WORLD_HEIGHT = 240;

    private Stage stage;
    private Texture globalSheet;
    private PuzzleMapScreen mapScreen;

    @Override
	public void create () {
        globalSheet = new Texture(Gdx.files.internal("spritesheet.png"));
        this.mapScreen = new PuzzleMapScreen(globalSheet);
        viewport = new FitViewport(WORLD_WIDTH,WORLD_HEIGHT);
        stage = new Stage(viewport);
        stage.addActor(mapScreen);


//        if (controller == null) {
//            controller = Controllers.getControllers().get(0);
//            if (controller == null) {
//                Gdx.app.error("TODO:", "Gracefull handle no controllers");
//                throw new RuntimeException("No Controller");
//            }
//            Gdx.app.debug("Controller", controller.getName());
//            mapScreen.addControllerListeners(controller);
//        }
//

        KeyboardControlEventSource source = new KeyboardControlEventSource();
        Gdx.input.setInputProcessor(source);
        mapScreen.addControlSource(source);
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



}
