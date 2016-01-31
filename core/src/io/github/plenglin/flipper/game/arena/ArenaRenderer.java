package io.github.plenglin.flipper.game.arena;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Disposable;

public class ArenaRenderer implements Disposable {

    private ShapeRenderer shape;

    public ArenaRenderer() {
        shape = new ShapeRenderer();
    }

    public void render(Arena arena, Matrix4 matrix) {

        shape.setProjectionMatrix(matrix);
        shape.begin(ShapeRenderer.ShapeType.Filled);

        for (io.github.plenglin.flipper.game.point.Point point : arena.getPoints()) {
            Body body = point.getBody();
            io.github.plenglin.flipper.game.player.Player owner = point.getOwner();
            Color color = owner == null ? Color.GRAY : owner.getColor();
            shape.setColor(color);
            shape.circle(body.getPosition().x, body.getPosition().y, point.getRadius(), 8);
        }

        for (io.github.plenglin.flipper.game.player.Player player : arena.getPlayers()) {
            Body body = player.getBody();
            Color color = player.getColor();
            shape.setColor(color);
            shape.circle(body.getPosition().x, body.getPosition().y, player.getRadius(), 8);
        }

        shape.end();
    }

    @Override
    public void dispose() {
        shape.dispose();
    }
}
