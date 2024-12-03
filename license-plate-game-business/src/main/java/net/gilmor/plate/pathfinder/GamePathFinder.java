package net.gilmor.plate.pathfinder;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import net.gilmor.plate.maps.GameMap;
import net.gilmor.plate.model.Area;
import net.gilmor.plate.model.AreaPair;
import net.gilmor.plate.model.Crossing;
import net.gilmor.plate.model.Path;
import net.gilmor.plate.model.Territory;
import net.gilmor.plate.scoring.ScoreCard;

public class GamePathFinder implements WayFinder {

    private GameMap map;
    private ScoreCard scores;
    private Map<AreaPair, Path> pathCache;
    private Queue<Step> toCheck;
    private Set<Area> checked;

    public GamePathFinder(GameMap map, ScoreCard scores) {
        this.map = map;
        this.scores = scores;
        pathCache = new HashMap<>();
        toCheck = new PriorityQueue<Step>(10, Comparator.comparingInt(Step::score));
        checked = new HashSet<>();
    }

    @Override
    public Path findWay(String fromCode, String toCode) {
        Territory from = map.getTerritory(fromCode).orElse(null);
        Territory to = map.getTerritory(toCode).orElse(null);
        return findPath(from, to);
    }

    private Path findPath(Territory from, Territory to) {
        if (from == null || to == null) {
            throw new IllegalArgumentException("Arguments must not be null");
        }
        if (from.equals(to)) {
            throw new IllegalArgumentException("Arguments cannot be the same");
        }
        AreaPair pair = new AreaPair(to, from);
        if (pathCache.containsKey(pair)) {
            return pathCache.get(pair);
        }
        toCheck.clear();
        toCheck.add(new Step(null, from));
        checked.clear();
        checked.add(from);
        Path path = bfs(0, to);
        pathCache.put(pair, path);
        return path;
    }

    private Path bfs(int previous, Territory to) {
        while (!toCheck.isEmpty()) {
            Step s = toCheck.remove();
            if (s.territory().equals(to)) {
                return constructPath(new Path(), s);
            }
            for (String code : s.territory().getBorderCodes()) {
                map.getTerritory(code).ifPresent((codeTerritory) -> {
                    if (!checked.contains(codeTerritory)) {
                        checked.add(codeTerritory);
                        toCheck.add(new Step(s, codeTerritory));
                    }
                });
            }
        }
        return null;
    }

    private Path constructPath(Path originalPath, Step s) {
        Path p = new Path(originalPath.getCrossings());
        Step t = s;
        while (t.previous() != null) {
            p.addStart(new Crossing(t.previous().territory(), t.territory()));
            t = t.previous();
        }
        return p;
    }

    private class Step {

        private int score;
        private Step previous;
        private Territory territory;

        public Step(Step previous, Territory territory) {
            this.score = 0;
            if (previous != null) {
                this.score = previous.score() + scores.calculateScore(new Crossing(previous.territory(), territory));
            }
            this.previous = previous;
            this.territory = territory;
        }

        public int score() {
            return score;
        }

        public Step previous() {
            return previous;
        }

        public Territory territory() {
            return territory;
        }
    }
}
