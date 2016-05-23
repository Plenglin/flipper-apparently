package io.github.plenglin.flipper;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import io.github.plenglin.flipper.game.GameConfiguration;
import io.github.plenglin.flipper.game.Team;
import io.github.plenglin.flipper.game.arena.Arena;
import io.github.plenglin.flipper.game.player.CaptureAIPlayerController;
import io.github.plenglin.flipper.game.player.Player;
import io.github.plenglin.flipper.game.player.RamAIPlayerController;
import io.github.plenglin.flipper.screen.GameScreen;

import java.util.ArrayList;

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

        GameConfiguration cfg = new GameConfiguration();
        cfg.neutralPoints = 16;
        cfg.controlledPointsPerTeam = 8;
        cfg.width = 16;
        cfg.height = 8;
        cfg.teamList = new ArrayList<Team>() {{

            add(new Team(Color.BLUE) {{
                addPlayer(new Player(new RamAIPlayerController()));
                addPlayer(new Player(new RamAIPlayerController()));
                addPlayer(new Player(new RamAIPlayerController()));
                addPlayer(new Player(new RamAIPlayerController()));
            }});

            add(new Team(Color.RED) {{
                addPlayer(new Player(new RamAIPlayerController()));
                addPlayer(new Player(new RamAIPlayerController()));
                addPlayer(new Player(new RamAIPlayerController()));
                addPlayer(new Player(new RamAIPlayerController()));
            }});

        }};

        setScreen(new GameScreen(this, new Arena(cfg)));
        //setScreen(new MainMenu(this));
    }

    @Override
    public void render() {
        super.render();
    }
}
