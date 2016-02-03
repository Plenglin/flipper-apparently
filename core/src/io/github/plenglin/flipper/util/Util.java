package io.github.plenglin.flipper.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import java.util.Random;

import io.github.plenglin.flipper.game.Constants;

public class Util {

    private static final Random random = new Random();

    /**
     * Create a walled-off area in a world.
     *
     * @param world     the world to operate on
     * @param x         the central x-coord
     * @param y         the central y-coord
     * @param width     the width of the area
     * @param height    the height of the area
     * @param thickness the thickness of the walls
     * @return an array of the walls {north, south, east, west}
     */
    public static void generateRectangularWalls(World world, float x, float y, float width, float height, float thickness) {

        float hwidth = width / 2, hheight = height / 2;
        BodyDef northb, southb, eastb, westb;
        FixtureDef northf, southf, eastf, westf;
        Body north, south, east, west;
        PolygonShape norths, souths, easts, wests;

        norths = new PolygonShape();
        norths.setAsBox(hwidth, thickness / 2);
        souths = new PolygonShape();
        souths.setAsBox(hwidth, thickness / 2);
        easts = new PolygonShape();
        easts.setAsBox(thickness / 2, hheight);
        wests = new PolygonShape();
        wests.setAsBox(thickness / 2, hheight);

        northb = new BodyDef();
        northb.type = BodyDef.BodyType.StaticBody;
        northb.position.set(x, y + hheight);
        southb = new BodyDef();
        southb.type = BodyDef.BodyType.StaticBody;
        southb.position.set(x, y - hheight);
        eastb = new BodyDef();
        eastb.type = BodyDef.BodyType.StaticBody;
        eastb.position.set(x + hwidth, y);
        westb = new BodyDef();
        westb.type = BodyDef.BodyType.StaticBody;
        westb.position.set(x - hwidth, y);

        northf = new FixtureDef();
        northf.shape = norths;
        northf.restitution = Constants.WALL_BOUNCE;
        northf.friction = 0;
        southf = new FixtureDef();
        southf.shape = souths;
        southf.restitution = Constants.WALL_BOUNCE;
        southf.friction = 0;
        eastf = new FixtureDef();
        eastf.shape = easts;
        eastf.restitution = Constants.WALL_BOUNCE;
        eastf.friction = 0;
        westf = new FixtureDef();
        westf.shape = wests;
        westf.restitution = Constants.WALL_BOUNCE;
        westf.friction = 0;

        north = world.createBody(northb);
        south = world.createBody(southb);
        east = world.createBody(eastb);
        west = world.createBody(westb);

        north.createFixture(northf);
        south.createFixture(southf);
        east.createFixture(eastf);
        west.createFixture(westf);

    }

    public static int randint(int min, int max) {
        return random.nextInt(max - min) + min;
    }

    public static float randfloat(float min, float max) {
        return (max - min) * random.nextFloat() + min;
    }

    public static Vector2 getProjectedPos(OrthographicCamera camera, int x, int y) {
        float scale = camera.viewportHeight / Gdx.graphics.getHeight();
        Vector2 screenPos = new Vector2(x, y); // where the click was on screen
        Vector2 camCenter = new Vector2(camera.position.x, camera.position.y);
        Vector2 camDimensions = new Vector2(camera.viewportWidth, camera.viewportHeight);
        Vector2 camCorner = camCenter.cpy().sub(camDimensions.cpy().scl(.5f));
        Vector2 projectedPos = screenPos.cpy().scl(scale).add(camCorner);
        return projectedPos;
    }

    public static Vector2 toUnitVector(Vector2 vec) {
        if (vec.len() == 0) {
            return new Vector2(0, 0);
        }
        return vec.cpy().scl(1 / vec.len());
    }

    public static float getDist(Vector2 a, Vector2 b) {
        return Math.abs(a.sub(b).len());
    }

}
