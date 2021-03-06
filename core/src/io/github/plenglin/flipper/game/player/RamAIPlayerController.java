package io.github.plenglin.flipper.game.player;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import io.github.plenglin.flipper.game.arena.Arena;
import io.github.plenglin.flipper.game.point.Point;

/**
 * A player controlled by a jerkass AI that rams into the nearest enemy.
 */
public class RamAIPlayerController implements PlayerController {

    Player currentTarget;
    Body body;
    Player player;
    boolean isAttached = false;

    @Override
    public void attachTo(Player player) {
        this.player = player;
        body = player.getBody();
        isAttached = true;
    }

    @Override
    public void detach() {
        isAttached = false;
    }

    @Override
    public void init() {
    }

    @Override
    public Vector2 getMovement() {
        currentTarget = player.getClosestEnemy();
        if (!isAttached || currentTarget == null) {
            return new Vector2(0, 0);
        }
        return currentTarget.getPosition().sub(player.getPosition());
    }

    @Override
    public void onCapture(Point point) {

    }

    public Arena getArena() {
        return player.getArena();
    }

}
