package io.github.plenglin.flipper.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;

import io.github.plenglin.flipper.game.arena.Arena;


public class DebugScreen implements Screen {

    Arena arena;
    OrthographicCamera camera;
    Box2DDebugRenderer debugRenderer;
    SpriteBatch batch;
    Texture img;

    public DebugScreen(Arena arena) {
        this.arena = arena;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        debugRenderer = new Box2DDebugRenderer();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 32, 18);
        Vector2 p = arena.getPlayers().get(0).getBody().getPosition();
        camera.position.set(0, 0, 0);
        camera.update();
    }

    @Override
    public void render(float delta) {
        arena.update(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        debugRenderer.render(arena.getWorld(), camera.combined);

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
