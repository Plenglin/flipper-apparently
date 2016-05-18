package io.github.plenglin.flipper.game.point;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import io.github.plenglin.flipper.game.Team;
import io.github.plenglin.flipper.game.player.Player;

public abstract class Point {

    private final double radius;
    private Team owner;
    private Body body;

    public Point(Team owner) {
        this.radius = getRadius();
        setOwner(this.owner);
    }

    public static float getDist(Vector2 a, Vector2 b) {
        return Math.abs(a.sub(b).len());
    }

    public final Team getOwner() {
        return owner;
    }

    public final void setOwner(Team owner) {
        if (this.owner != null) {
            this.owner.removePoint(this);
        }
        this.owner = owner;
        if (owner != null) {
            owner.addPoint(this);
        }
    }

    public final void flip(Team newOwner) {
        Team prevOwner = this.owner;
        setOwner(newOwner);
        this.onFlipped(prevOwner, newOwner);
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
        body.setUserData(this);
    }

    public Vector2 getPosition() {
        return body.getPosition();
    }

    protected abstract void onFlipped(Team prevOwner, Team newOwner);

    public abstract int getValue();

    public abstract float getRadius();

}
