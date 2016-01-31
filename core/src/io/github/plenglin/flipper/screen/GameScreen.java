package io.github.plenglin.flipper.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

import io.github.plenglin.flipper.game.Constants;
import io.github.plenglin.flipper.game.arena.Arena;
import io.github.plenglin.flipper.game.arena.ArenaRenderer;
import io.github.plenglin.flipper.game.player.AIPlayerController;
import io.github.plenglin.flipper.game.player.LocalPlayer;
import io.github.plenglin.flipper.game.player.PlayerController;

/**
 * Displays a game, with arena height aligned to actual height.
 */
public class GameScreen implements Screen {

    Arena arena;
    OrthographicCamera camera;
    ArenaRenderer renderer;
    LocalPlayer controller;

    public GameScreen(Arena arena) {
        this.arena = arena;
        this.camera = new OrthographicCamera();
        this.renderer = new ArenaRenderer();
        this.controller = new LocalPlayer(camera);
        PlayerController ai = new AIPlayerController();
        arena.getPlayers().get(0).attachController(controller);
        arena.getPlayers().get(1).attachController(ai);
    }

    @Override
    public void show() {
        int width = Gdx.graphics.getWidth(), height = Gdx.graphics.getHeight();
        float awidth = arena.getWidth(), aheight = arena.getHeight();
        float windowRatio = ((float) width)/height;
        float arenaRatio = arena.getWidth()/arena.getHeight();
        float scaling;
        if (windowRatio > arenaRatio) {
            scaling = aheight / height;
        } else {
            scaling = awidth / height;
        }
        switch (Gdx.app.getType()) {
            case Desktop:
                camera.setToOrtho(true, height * scaling / Constants.CAMERA_ZOOM, width * scaling / Constants.CAMERA_ZOOM);
                break;
            case Android:
                camera.setToOrtho(true, width * scaling / Constants.CAMERA_ZOOM, height * scaling / Constants.CAMERA_ZOOM);
                break;
        }
        camera.position.set(0, 0, 0);
        camera.update();
    }

    @Override
    public void render(float delta) {
        float gray = .9f;
        Gdx.gl.glClearColor(gray, gray, gray, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.render(arena, camera.combined);
        arena.update(delta);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

}
