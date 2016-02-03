package io.github.plenglin.flipper;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import io.github.plenglin.flipper.screen.MainMenu;

public class Flipper extends Game {

    public static SpriteBatch batch;
    public static BitmapFont font;
    public static ShapeRenderer shape;

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont(true);
        font.scale(2);
        shape = new ShapeRenderer();
        //setScreen(new GameScreen(this, new Arena(16, 9, 8)));
        setScreen(new MainMenu(this));
    }

    @Override
    public void render() {
        super.render();
    }
}
