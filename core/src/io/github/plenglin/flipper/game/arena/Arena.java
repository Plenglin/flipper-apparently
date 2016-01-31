package io.github.plenglin.flipper.game.arena;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;
import java.util.List;

import io.github.plenglin.flipper.Util;
import io.github.plenglin.flipper.game.Constants;
import io.github.plenglin.flipper.game.player.Player;
import io.github.plenglin.flipper.game.point.NormalPoint;
import io.github.plenglin.flipper.game.point.Point;

/**
 * The place where the game takes place in
 */
public class Arena implements ContactListener {

    private float width, height, hwidth, hheight;

    private World world;
    private List<Player> players = new ArrayList<Player>();
    private List<Point> points = new ArrayList<Point>();

    /**
     * Create a new arena.
     *
     * @param width      the width of the arena
     * @param height     the height of the arena
     * @param pointCount the number of points on each side of the field
     */
    public Arena(float width, float height, int pointCount) {

        this.width = width;
        this.height = height;
        this.hwidth = width / 2;
        this.hheight = height / 2;
        this.world = new World(new Vector2(0f, 0f), true);

        Util.generateRectangularWalls(world, 0f, 0f, width * 1.1f, height * 1.1f, 0.5f);

        Player p1 = new Player(Color.BLUE);
        Player p2 = new Player(Color.RED);

        players.add(p1);
        players.add(p2);

        for (Player player : players) {

            BodyDef playerBodyDef = new BodyDef();
            playerBodyDef.position.set(Util.randfloat(-hwidth, hwidth), Util.randfloat(-hheight, hheight));
            playerBodyDef.type = BodyDef.BodyType.DynamicBody;
            FixtureDef playerFixtureDef = new FixtureDef();
            CircleShape playerShape = new CircleShape();
            playerShape.setRadius(Constants.PLAYER_RADIUS);
            playerFixtureDef.shape = playerShape;

            Body body = world.createBody(playerBodyDef);
            body.createFixture(playerFixtureDef);
            player.setBody(body);
            System.out.println(player.getBody().getPosition());

            for (int i = 0; i < pointCount; i++) {

                Point point = new NormalPoint(player);

                points.add(point);

                BodyDef bodyDef = new BodyDef();
                bodyDef.position.set(Util.randfloat(-hwidth, hwidth), Util.randfloat(-hheight, hheight));

                FixtureDef fixtureDef = new FixtureDef();
                CircleShape shape = new CircleShape();
                shape.setRadius(point.getRadius());
                fixtureDef.shape = shape;
                fixtureDef.isSensor = true;

                Body pointBody = world.createBody(bodyDef);
                pointBody.createFixture(fixtureDef);
                point.setOwner(player);
                point.setBody(pointBody);

            }

        }

        world.setContactListener(this);

    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public World getWorld() {
        return world;
    }

    public void update(float delta) {
        for (Player player : players) {
            player.update(delta);
        }
        world.step(delta, 6, 2);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<Point> getPoints() {
        return points;
    }

    @Override
    public void beginContact(Contact contact) {

        Object a = contact.getFixtureA().getBody().getUserData();
        Object b = contact.getFixtureB().getBody().getUserData();
        Point point;
        Player player;

        if (a instanceof Point && b instanceof Player) {
            point = (Point) a;
            player = (Player) b;
            point.setOwner(player);

        } else if (a instanceof Player && b instanceof Point) {
            point = (Point) b;
            player = (Player) a;
            point.setOwner(player);
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
