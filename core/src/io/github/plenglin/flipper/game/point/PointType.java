package io.github.plenglin.flipper.game.point;

import io.github.plenglin.flipper.game.player.Player;

/**
 * Created by Maxim on 1/26/2016.
 */
public interface PointType {

    String getName();

    Point create(Player owner, double x, double y, double radius);

}
