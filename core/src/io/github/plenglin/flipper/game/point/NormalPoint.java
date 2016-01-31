package io.github.plenglin.flipper.game.point;

import io.github.plenglin.flipper.game.player.Player;

public class NormalPoint extends Point {

    public NormalPoint(Player owner) {
        super(owner);
    }

    @Override
    protected void onFlipped(Player prevOwner, Player newOwner) {

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
