package net.saga.game.cloclo.levelloader;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.google.gson.Gson;

import java.io.FileReader;

public class LevelLoader {
    public static MapData getDemoMapData() {
        Gson gson = new Gson();
        MapData data = gson.fromJson(Gdx.files.internal("levels/demo.json").reader(), MapData.class);
        return data;
    }
}
