package io.github.plenglin.flipper.game.player;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import java.util.ArrayList;
import java.util.List;

import io.github.plenglin.flipper.game.Constants;
import io.github.plenglin.flipper.game.Team;
import io.github.plenglin.flipper.game.arena.Arena;
import io.github.plenglin.flipper.game.point.Point;
import io.github.plenglin.flipper.util.Util;

public class Player {

    private float speed = Constants.PLAYER_SPEED;
    private float radius = Constants.PLAYER_RADIUS;
    private Body body;
    private PlayerController controller;
    private Arena arena;
    private Team team;

    public Player() {
        this.controller = new BlankPlayerController();
    }

    public void update(float delta) {
        Vector2 mv = Util.toUnitVector(controller.getMovement()).scl(this.getSpeed() * delta);
        this.body.applyForceToCenter(mv, true);
    }

    public Color getColor() {
        return team.getColor();
    }

    public float getSpeed() {
        return speed;
    }

    public float getRadius() {
        return radius;
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
            if (p.getOwner() == getTeam()) {
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

    public void setTeam(Team team) {
        this.team = team;
        this.arena = team.getArena();
    }

    public Team getTeam() {
        return team;
    }

}
