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
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.List;

import io.github.plenglin.flipper.game.Constants;
import io.github.plenglin.flipper.game.GameConfiguration;
import io.github.plenglin.flipper.game.Team;
import io.github.plenglin.flipper.game.player.CaptureAIPlayerController;
import io.github.plenglin.flipper.game.player.Player;
import io.github.plenglin.flipper.game.point.NormalPoint;
import io.github.plenglin.flipper.game.point.Point;
import io.github.plenglin.flipper.util.Util;

/**
 * The place where the game takes place in
 */
public class Arena implements ContactListener {

    private float width, height, hwidth, hheight;

    private World world;
    private List<Team> teams;
    private List<Point> points;

    public Arena(GameConfiguration cfg) {
        this.width = cfg.width;
        this.height = cfg.height;
        this.hwidth = width / 2;
        this.hheight = height / 2;

        this.world = new World(new Vector2(0f, 0f), true);
        this.teams = cfg.teamList;
        this.points = new ArrayList<Point>();

        Util.generateRectangularWalls(world, 0f, 0f, width * 1.1f, height * 1.1f, 0.5f);

        for (int i = 0; i < cfg.neutralPoints; i++) { // Add points
            createPoint(new NormalPoint(null), getRandomPosition());
        }

        for (Team team: teams) {
            team.setArena(this);

            for (Player player : team) { // Give the players representations in the physics engine
                BodyDef playerBodyDef = new BodyDef();
                playerBodyDef.position.set(Util.randfloat(-hwidth, hwidth), Util.randfloat(-hheight, hheight));
                playerBodyDef.type = BodyDef.BodyType.DynamicBody;
                FixtureDef playerFixtureDef = new FixtureDef();
                CircleShape playerShape = new CircleShape();
                playerShape.setRadius(Constants.PLAYER_RADIUS);
                playerFixtureDef.shape = playerShape;
                playerFixtureDef.restitution = Constants.PLAYER_BOUNCE;

                Body body = world.createBody(playerBodyDef);
                body.createFixture(playerFixtureDef);
                player.setBody(body);
                System.out.println(player.getBody().getPosition());
            }

            for (int i = 0; i < cfg.controlledPointsPerTeam; i++) { // Add points

                createPoint(new NormalPoint(team), getRandomPosition());

            }

        }

        world.setContactListener(this);

    }

    public Vector2 getRandomPosition() {
        return new Vector2(Util.randfloat(-hwidth, hwidth), Util.randfloat(-hheight, hheight));
    }

    public void createPoint(Point point, Vector2 pos) {
        points.add(point);

        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(pos);

        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(point.getRadius());
        fixtureDef.shape = shape;
        fixtureDef.isSensor = true;

        Body pointBody = world.createBody(bodyDef);
        pointBody.createFixture(fixtureDef);
        point.setBody(pointBody);
    }

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
        this.teams = new ArrayList<Team>();
        this.points = new ArrayList<Point>();

        Util.generateRectangularWalls(world, 0f, 0f, width * 1.1f, height * 1.1f, 0.5f);

        Player p1 = new Player();
        Player p2 = new Player();
        p2.attachController(new CaptureAIPlayerController());

        Team t1 = new Team(Color.BLUE);
        Team t2 = new Team(Color.RED);

        t1.addPlayer(p1);
        t2.addPlayer(p2);

        t1.setArena(this);
        t2.setArena(this);

        teams.add(t1);
        teams.add(t2);

        for (Team team : teams) {

            for (Player player : team) {
                BodyDef playerBodyDef = new BodyDef();
                playerBodyDef.position.set(Util.randfloat(-hwidth, hwidth), Util.randfloat(-hheight, hheight));
                playerBodyDef.type = BodyDef.BodyType.DynamicBody;
                FixtureDef playerFixtureDef = new FixtureDef();
                CircleShape playerShape = new CircleShape();
                playerShape.setRadius(Constants.PLAYER_RADIUS);
                playerFixtureDef.shape = playerShape;
                playerFixtureDef.restitution = Constants.PLAYER_BOUNCE;

                Body body = world.createBody(playerBodyDef);
                body.createFixture(playerFixtureDef);
                player.setBody(body);
                System.out.println(player.getBody().getPosition());
            }

            for (int i = 0; i < pointCount; i++) {

                Point point = new NormalPoint(team);

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
                point.setOwner(team);
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

        for (Player player : getPlayers()) {
            player.update(delta);
        }

        Array<Body> bodies = new Array<Body>();
        world.getBodies(bodies);
        for (Body body: bodies) {
            body.applyForceToCenter(body.getLinearVelocity().scl(-Constants.FRICTION_COEFF), true);
        }
        world.step(delta, 8, 3);

    }

    public List<Player> getPlayers() {
        List<Player> players = new ArrayList<Player>();
        for (Team t: teams) {
            for (Player p: t)
            players.add(p);
        }
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
            point.setOwner(player.getTeam());
            player.getController().onCapture(point);

        } else if (a instanceof Player && b instanceof Point) {
            point = (Point) b;
            player = (Player) a;
            point.setOwner(player.getTeam());
            player.getController().onCapture(point);
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

    public List<Team> getTeams() {
        return teams;
    }
}
