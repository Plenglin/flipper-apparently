package io.github.plenglin.flipper.game.player;


import com.badlogic.gdx.math.Vector2;

public interface PlayerController {

    void attachTo(Player player);

    void detach();

    /**
     * Get the controller's movements.
     * @return a vector indicating how to move the player
     */
    Vector2 getMovement();

}
