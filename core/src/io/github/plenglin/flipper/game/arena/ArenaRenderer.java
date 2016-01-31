package io.github.plenglin.flipper.game.arena;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Disposable;
import io.github.plenglin.flipper.game.player.Player;
import io.github.plenglin.flipper.game.point.Point;

public class ArenaRenderer implements Disposable {

    private ShapeRenderer shape;

    public ArenaRenderer() {
        shape = new ShapeRenderer();
    }

    public void render(Arena arena, Matrix4 matrix) {

        shape.setProjectionMatrix(matrix);
        shape.begin(ShapeRenderer.ShapeType.Filled);

        for (Point point : arena.getPoints()) {
            Body body = point.getBody();
            Player owner = point.getOwner();
            Color color = owner == null ? Color.GRAY : owner.getColor();
            shape.setColor(color);
            shape.circle(body.getPosition().x, body.getPosition().y, point.getRadius(), 8);
        }

        for (Player player : arena.getPlayers()) {
            Body body = player.getBody();
            Color color = player.getColor();
            shape.setColor(color);
            shape.circle(body.getPosition().x, body.getPosition().y, player.getRadius(), 16);
        }

        shape.end();

        shape.begin(ShapeRenderer.ShapeType.Line);

        float width = 1.1f*arena.getWidth(), height = 1.1f*arena.getHeight();
        shape.setColor(Color.BLACK);
        shape.rect(-width/2, -height/2, width, height);

        shape.end();
    }

    @Override
    public void dispose() {
        shape.dispose();
    }
}
