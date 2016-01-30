package io.github.plenglin.flipper.game.point;

import com.badlogic.gdx.physics.box2d.Body;

import io.github.plenglin.flipper.game.player.Player;

public abstract class Point {

    private final double radius;
    private Player owner;
    private Body body;

    public Point(Player owner) {
        this.radius = getRadius();
        setOwner(this.owner);
    }

    public final Player getOwner() {
        return owner;
    }

    public final void setOwner(Player owner) {
        if (this.owner != null) {
            this.owner.removePoint(this);
        }
        this.owner = owner;
        if (owner != null) {
            owner.addPoint(this);
        }
    }

    public final void flip(Player newOwner) {
        Player prevOwner = this.owner;
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

    protected abstract void onFlipped(Player prevOwner, Player newOwner);

    public abstract int getValue();

    public abstract float getRadius();

}
