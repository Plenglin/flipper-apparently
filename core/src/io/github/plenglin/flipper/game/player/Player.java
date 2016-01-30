package io.github.plenglin.flipper.game.player;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import java.util.ArrayList;
import java.util.List;

import io.github.plenglin.flipper.Constants;

public class Player {

    private List<io.github.plenglin.flipper.game.point.Point> controlledPoints = new ArrayList<io.github.plenglin.flipper.game.point.Point>();
    private Color color;
    private float speed = Constants.PLAYER_SPEED;
    private float radius = Constants.PLAYER_RADIUS;
    private Body body;
    private io.github.plenglin.flipper.game.player.PlayerController controller;

    public Player(Color color) {
        this.controller = new io.github.plenglin.flipper.game.player.BlankPlayerController();
        this.color = color;
    }

    public void update(float delta) {
        Vector2 mv = controller.getMovement().scl(this.getSpeed()*delta);
        this.body.applyForceToCenter(mv, true);
    }

    public Color getColor() {
        return color;
    }

    public float getSpeed() {
        return speed;
    }

    public float getRadius() {
        return radius;
    }

    public void addPoint(io.github.plenglin.flipper.game.point.Point point) {
        controlledPoints.add(point);
    }

    public void removePoint(io.github.plenglin.flipper.game.point.Point point) {
        controlledPoints.remove(point);
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
        body.setUserData(this);
    }

    public void attachController(io.github.plenglin.flipper.game.player.PlayerController controller) {
        if (this.controller != null) {
            this.controller.detach();
        }
        this.controller = controller;
        controller.attachTo(this);
    }

    public PlayerController getController() {
        return controller;
    }

    public int getPointTotal() {
        int sum = 0;
        for (io.github.plenglin.flipper.game.point.Point p: controlledPoints) {
            sum += p.getValue();
        }
        return sum;
    }


}
