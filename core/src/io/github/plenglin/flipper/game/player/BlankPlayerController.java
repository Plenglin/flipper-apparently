package io.github.plenglin.flipper.game.player;

import com.badlogic.gdx.math.Vector2;

public class BlankPlayerController implements PlayerController {

    @Override
    public void attachTo(Player player) {

    }

    @Override
    public void detach() {

    }

    @Override
    public Vector2 getMovement() {
        return new Vector2(0, 0);
    }

}
