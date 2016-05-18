package io.github.plenglin.flipper.game.point;

import io.github.plenglin.flipper.game.Team;

public class NormalPoint extends Point {

    public NormalPoint(Team owner) {
        super(owner);
    }

    @Override
    protected void onFlipped(Team prevOwner, Team newOwner) {

    }

    @Override
    public int getValue() {
        return 10;
    }

    @Override
    public float getRadius() {
        return 0.1f;
    }

}
