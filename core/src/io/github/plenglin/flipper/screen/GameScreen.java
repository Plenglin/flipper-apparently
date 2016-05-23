package io.github.plenglin.flipper.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import io.github.plenglin.flipper.Flipper;
import io.github.plenglin.flipper.game.Constants;
import io.github.plenglin.flipper.game.arena.Arena;
import io.github.plenglin.flipper.game.arena.ArenaRenderer;
import io.github.plenglin.flipper.game.player.LocalPlayer;
import io.github.plenglin.flipper.game.player.Player;

/**
 * Displays a game, with arena height aligned to actual height.
 */
public class GameScreen implements Screen {

    Arena arena;
    OrthographicCamera camera, fontCamera;
    ArenaRenderer renderer;
    LocalPlayer controller;
    Game game;
    BitmapFont font;
    SpriteBatch batch;

    float timePassed;

    public GameScreen(Game game, Arena arena) {

        this.game = game;
        this.arena = arena;
        this.camera = new OrthographicCamera();
        this.fontCamera = new OrthographicCamera();
        this.renderer = new ArenaRenderer();
        this.controller = new LocalPlayer(camera);

        Gdx.input.setInputProcessor(null);

        font = Flipper.font;

        batch = Flipper.batch;

    }

    @Override
    public void show() {
        timePassed = 0;

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
                camera.setToOrtho(true, width * scaling / Constants.CAMERA_ZOOM, height * scaling / Constants.CAMERA_ZOOM);
                break;
            case Android:
                camera.setToOrtho(true, width * scaling / Constants.CAMERA_ZOOM, height * scaling / Constants.CAMERA_ZOOM);
                break;
        }
        camera.position.set(0, 0, 0);
        camera.update();

        fontCamera.setToOrtho(true, width, height);
        fontCamera.update();

    }

    @Override
    public void render(float delta) {

        if (timePassed >= Constants.GAME_DURATION) {
            game.setScreen(new ResultScreen(game, arena.getTeams()));
            this.dispose();
        }

        arena.update(delta);

        //timePassed += delta;

        float gray = .9f;
        Gdx.gl.glClearColor(gray, gray, gray, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render(arena, camera.combined);
        batch.setProjectionMatrix(fontCamera.combined);

        batch.begin();
        font.setColor(Color.BLACK);
        Player player = arena.getPlayers().get(0);
        font.draw(batch, String.valueOf(player.getTeam().getPointTotal()), 15, 15);
        font.draw(batch, String.valueOf(Constants.GAME_DURATION - timePassed), 15, 50);
        batch.end();

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
