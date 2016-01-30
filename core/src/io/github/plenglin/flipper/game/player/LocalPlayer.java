package io.github.plenglin.flipper.game.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import io.github.plenglin.flipper.Util;

public class LocalPlayer implements PlayerController {

    Body body;
    Player player;
    OrthographicCamera camera;
    boolean attached = false;

    public LocalPlayer(OrthographicCamera camera) {
        this.camera = camera;
    }

    @Override
    public void attachTo(Player player) {
        player = player;
        body = player.getBody();
        attached = true;
    }

    @Override
    public void detach() {
        attached = false;
    }

    @Override
    public Vector2 getMovement() {
        if (!attached)  {
            return new Vector2(0, 0);
        }
        if (!(Gdx.input.isTouched() || Gdx.input.isButtonPressed(0))) {
            return new Vector2(0, 0);
        }
        int mx = Gdx.input.getX(), my = Gdx.input.getY();
        Vector2 projectedMouseClick = Util.getProjectedClickPos(camera, mx, my);
        Vector2 playerPos = body.getPosition();
        Vector2 offset = projectedMouseClick.sub(playerPos);
        return Util.toUnitVector(offset);
    }
}
