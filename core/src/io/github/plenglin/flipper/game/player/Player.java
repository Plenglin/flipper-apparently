package io.github.plenglin.flipper.game.player;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import io.github.plenglin.flipper.Util;
import io.github.plenglin.flipper.game.Constants;
import io.github.plenglin.flipper.game.arena.Arena;
import io.github.plenglin.flipper.game.point.Point;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private List<Point> controlledPoints = new ArrayList<Point>();
    private Color color;
    private float speed = Constants.PLAYER_SPEED;
    private float radius = Constants.PLAYER_RADIUS;
    private Body body;
    private PlayerController controller;
    private Arena arena;

    public Player(Arena arena, Color color) {
        this.controller = new BlankPlayerController();
        this.color = color;
        this.arena = arena;
    }

    public void update(float delta) {
        Vector2 mv = Util.toUnitVector(controller.getMovement()).scl(this.getSpeed() * delta);
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

    public void addPoint(Point point) {
        controlledPoints.add(point);
    }

    public void removePoint(Point point) {
        controlledPoints.remove(point);
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
        body.setUserData(this);
    }

    public void attachController(PlayerController controller) {
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
        for (Point p : controlledPoints) {
            sum += p.getValue();
        }
        return sum;
    }

    public Arena getArena() {
        return arena;
    }

    public Vector2 getPosition() {
        return body.getPosition();
    }

    public Point getClosestUncontrolledPoint() {
        Point closest = null;
        float closestDist = Float.MAX_VALUE;
        for (Point p: arena.getPoints()) {
            if (p.getOwner() == this) {
                continue;
            }
            float dist = Util.getDist(getPosition(), p.getPosition());
            if (dist < closestDist) {
                closest = p;
                closestDist = dist;
            }
        }
        return closest;
    }

}
