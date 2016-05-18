package io.github.plenglin.flipper.game;

import com.badlogic.gdx.graphics.Color;
import io.github.plenglin.flipper.game.arena.Arena;
import io.github.plenglin.flipper.game.player.Player;
import io.github.plenglin.flipper.game.point.Point;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 */
public class Team implements Iterable<Player> {

    private List<Player> players;
    private Color color;
    private List<Point> controlledPoints;
    private Arena arena;

    public Team(Color color, Arena arena) {
        this.color = color;
        this.arena = arena;
        this.players = new ArrayList<Player>();
        this.controlledPoints = new ArrayList<Point>();
    }

    @Override
    public Iterator<Player> iterator() {
        return players.iterator();
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Color getColor() {
        return color;
    }

    public void addPlayer(Player player) {
        players.add(player);
        player.setTeam(this);
    }

    public void addPoint(Point point) {
        controlledPoints.add(point);
    }

    public void removePoint(Point point) {
        controlledPoints.remove(point);
    }

    public int getPointTotal() {
        int sum = 0;
        for (Point p : controlledPoints) {
            sum += p.getValue();
        }
        return sum;
    }

    public Arena getArena() {
        return arena;
    }
}
