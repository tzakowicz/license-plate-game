package net.gilmor.plate.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.enterprise.context.SessionScoped;
import net.gilmor.plate.maps.TerritoryMap;
import net.gilmor.plate.model.Borders;
import net.gilmor.plate.model.CountrySummary;
import net.gilmor.plate.model.Crossing;
import net.gilmor.plate.model.GameSettings;
import net.gilmor.plate.model.MapSummary;
import net.gilmor.plate.model.Path;
import net.gilmor.plate.model.PlayerScore;
import net.gilmor.plate.model.Scoreboard;
import net.gilmor.plate.model.Sighting;
import net.gilmor.plate.model.SightingClaim;
import net.gilmor.plate.model.TerritorySummary;
import net.gilmor.plate.pathfinder.GamePathFinder;
import net.gilmor.plate.scoring.PathScoreCard;
import net.gilmor.plate.scoring.ScoreCard;

@SessionScoped
public class GameBean implements Serializable {

    private static final long serialVersionUID = -3611197482770626270L;

    private boolean playing = false;

    private String[] players;
    private int[] scores;

    private TerritoryMap map;
    private GamePathFinder pathFinder;
    private ScoreCard scorecard;

    private String currentTerritory;
    private Set<String> foundTerritories;
    private List<CountrySummary> countrySummaries;

    private Sighting lastSighting;

    public GameBean() {
        this.foundTerritories = new HashSet<String>();
        // @TODO Rework this to allow for separate json maps
        this.map = TerritoryMap.buildMap();
        refreshStates();
    }

    public boolean initialize(GameSettings settings) {
        this.players = settings.getPlayers();
        this.currentTerritory = settings.getStartingTerritory();
        this.scorecard = new PathScoreCard(settings.getScoreSettings());

        return ready();
    }

    public boolean ready() {
        return players.length > 0 && !"".equals(currentTerritory) && scorecard != null;
    }

    public boolean playing() {
        return playing;
    }

    public void start() {
        if (playing) {
            throw new IllegalStateException("Game is started");
        }
        scores = new int[players.length];
        this.pathFinder = new GamePathFinder(map, scorecard);
        playing = true;
    }

    public void end() {
        if (!playing) {
            throw new IllegalStateException("Game is not started");
        }
        playing = false;
        this.currentTerritory = "";
        this.foundTerritories = new HashSet<String>();
        refreshStates();
    }

    public String currentTerritory() {
        return currentTerritory;
    }

    public String[] foundTerritories() {
        return foundTerritories.toArray(new String[0]);
    }

    public MapSummary countries() {
        return new MapSummary(countrySummaries.toArray(new CountrySummary[0]));
    }

    private void refreshStates() {
        countrySummaries = new ArrayList<>();
        map.getCountries().stream().forEach((country) -> {
            CountrySummary cs = new CountrySummary(country.getCode());
            country.getTerritories().stream().forEach((t) -> {
                cs.addState(new TerritorySummary(t.getCode()));
            });
            countrySummaries.add(cs);
        });
    }

    public Borders borderTerritories() {
        if (!playing) {
            throw new IllegalStateException("Game is not started");
        }
        return new Borders(map.getTerritory(currentTerritory).get().getBorderCodes().toArray(new String[0]));
    }

    public void crossBorder(String newState) {
        if (!playing) {
            throw new IllegalStateException("Game is not started");
        }
        this.currentTerritory = newState;
        this.foundTerritories = new HashSet<String>();
        refreshStates();
    }

    public Scoreboard scoreboard() {
        PlayerScore[] scoreboard = new PlayerScore[players.length];
        for (int i = 0; i < players.length; i++) {
            scoreboard[i] = new PlayerScore(i, players[i], scores[i]);
        }
        return new Scoreboard(scoreboard);
    }

    public void addSighting(SightingClaim claim) {
        if (!playing) {
            throw new IllegalStateException("Game is not started");
        }
        Path path = pathFinder.findWay(currentTerritory, claim.getCode());
        int points = 0;
        if (currentTerritory.equals(claim.getCode())) {
            points = scorecard.getSettings().getTerritoryBorderScore();
        } else {
            points = scorecard.calculateScore(path);
        }
        if (claim.isPenalty() || foundTerritories.contains(claim.getCode())) {
            points *= -1;
        } else {
            foundTerritories.add(claim.getCode());
            refreshStates();
        }
        scores[claim.getId()] += points;
        lastSighting = new Sighting(players[claim.getId()], points, flattenPath(path));
    }

    private String[] flattenPath(Path path) {
        String[] flat = new String[path.getCrossings().size() + 1];
        int count = 0;
        flat[count++] = path.getStart().getCode();
        for (Crossing c : path.getCrossings()) {
            flat[count++] = c.getEnd().getCode();
        }
        return flat;
    }

    public Sighting lastSighting() {
        if (!playing) {
            throw new IllegalStateException("Game is not started");
        }
        return lastSighting;
    }
}
