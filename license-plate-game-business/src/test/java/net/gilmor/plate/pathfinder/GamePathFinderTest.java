package net.gilmor.plate.pathfinder;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import net.gilmor.plate.maps.TerritoryMap;
import net.gilmor.plate.scoring.PathScoreCard;
import net.gilmor.plate.scoring.ScoreCard;
import net.gilmor.plate.scoring.ScoreSettings;

public class GamePathFinderTest {

    static TerritoryMap map;
    static ScoreSettings settings;
    static ScoreCard scores;

    @BeforeAll
    static void setup() {
        map = TerritoryMap.buildMap();
        settings = new ScoreSettings();
        settings.setTerritoryBorderScore(1);
        settings.setCountryBorderScore(5);
        settings.setOceanBorderScore(10);
        scores = new PathScoreCard(settings);
    }

    @Test
    void testBFS() {
        GamePathFinder bfs = new GamePathFinder(map, scores);
        assertThat(scores.calculateScore(bfs.findWay("CT", "WA")), equalTo(9));
        assertThat(scores.calculateScore(bfs.findWay("CT", "ME")), equalTo(3));
        assertThat(scores.calculateScore(bfs.findWay("CA", "WA")), equalTo(2));
        assertThat(scores.calculateScore(bfs.findWay("CT", "QC")), equalTo(6));
        assertThat(scores.calculateScore(bfs.findWay("CT", "AK")), equalTo(15));
        assertThat(scores.calculateScore(bfs.findWay("CT", "HI")), equalTo(19));
        assertThat(scores.calculateScore(bfs.findWay("CT", "HI")), equalTo(19));
    }

    @Test
    void testInvalidArguments() {
        GamePathFinder bfs = new GamePathFinder(map, scores);
        assertThrows(IllegalArgumentException.class, () -> bfs.findWay(null, "WA"));
        assertThrows(IllegalArgumentException.class, () -> bfs.findWay("CT", null));
        assertThrows(IllegalArgumentException.class, () -> bfs.findWay("JA", "WA"));
        assertThrows(IllegalArgumentException.class, () -> bfs.findWay("CT", "XE"));
        assertThrows(IllegalArgumentException.class, () -> bfs.findWay("CT", "CT"));
    }
}
