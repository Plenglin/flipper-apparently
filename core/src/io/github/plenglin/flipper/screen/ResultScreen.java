package io.github.plenglin.flipper.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.List;

import io.github.plenglin.flipper.Flipper;
import io.github.plenglin.flipper.game.player.Player;

public class ResultScreen implements Screen {

    BitmapFont font;
    SpriteBatch batch;
    private List<Player> players;
    private Game game;
    private boolean won;


    public ResultScreen(Game game, List<Player> players) {
        this.game = game;
        this.players = players;
        font = Flipper.font;
        batch = Flipper.batch;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        if (Gdx.input.isTouched()) {
            game.setScreen(new MainMenu(game));
        }

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        int y = 15;
        for (Player player: players) {
            font.setColor(player.getColor());
            font.draw(batch, String.valueOf(player.getPointTotal()), 10, y);
            y += 30;
        }

        font.draw(batch, "Tap anywhere to continue", 400, 100);

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
