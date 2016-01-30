package io.github.plenglin.flipper.game.point;

public class NormalPoint extends Point {

    public NormalPoint(io.github.plenglin.flipper.game.player.Player owner) {
        super(owner);
    }

    @Override
    protected void onFlipped(io.github.plenglin.flipper.game.player.Player prevOwner, io.github.plenglin.flipper.game.player.Player newOwner) {

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
