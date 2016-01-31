package io.github.plenglin.flipper.game.player;


import com.badlogic.gdx.math.Vector2;
import io.github.plenglin.flipper.game.point.Point;

public interface PlayerController {

    void attachTo(Player player);

    void detach();

    void init();

    /**
     * Get the controller's movements.
     *
     * @return a vector indicating how to move the player. It will be converted into a unit vector.
     */
    Vector2 getMovement();

    void onCapture(Point point);

}
