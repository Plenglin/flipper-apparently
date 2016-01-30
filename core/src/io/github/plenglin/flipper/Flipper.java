package io.github.plenglin.flipper;

import com.badlogic.gdx.Game;

import io.github.plenglin.flipper.game.arena.Arena;
import io.github.plenglin.flipper.screen.GameScreen;

public class Flipper extends Game {

	
	@Override
	public void create () {
        setScreen(new GameScreen(new Arena(16, 9, 8)));
	}

	@Override
	public void render () {
		super.render();
    }
}
