package io.github.plenglin.flipper.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

import io.github.plenglin.flipper.game.arena.Arena;
import io.github.plenglin.flipper.game.arena.ArenaRenderer;
import io.github.plenglin.flipper.game.player.LocalPlayer;

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
        arena.getPlayers().get(0).attachController(controller);
    }

    @Override
    public void show() {
        int width = Gdx.graphics.getWidth(), height = Gdx.graphics.getHeight();
        camera.setToOrtho(true, 17.6f, 9.9f);
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
