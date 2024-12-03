package net.gilmor.plate.util;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

import org.junit.jupiter.api.Test;

import net.gilmor.plate.model.CountrySummary;
import net.gilmor.plate.model.JsonResponse;
import net.gilmor.plate.model.MapSummary;
import net.gilmor.plate.model.PlayerScore;
import net.gilmor.plate.model.Scoreboard;
import net.gilmor.plate.model.TerritorySummary;
import net.gilmor.plate.scoring.ScoreSettings;

public class JsonUtilTest {

    @Test
    void testJsonScoreSettings() {
        ScoreSettings settings = new ScoreSettings();
        settings.setTerritoryBorderScore(1);
        settings.setCountryBorderScore(5);
        settings.setOceanBorderScore(10);
        String json = JsonUtil.buildJson(new JsonResponse<ScoreSettings>(true, "message", settings));
        assertThat(json, containsString("\"territoryBorderScore\":1"));
        assertThat(json, containsString("\"countryBorderScore\":5"));
        assertThat(json, containsString("\"oceanBorderScore\":10"));
    }

    @Test
    void testJsonCountrySummary() {
        CountrySummary cs = new CountrySummary("FAKE");
        cs.addState(new TerritorySummary("FAKE2"));
        cs.addState(new TerritorySummary("FAKE3"));
        String json = JsonUtil.buildJson(new JsonResponse<CountrySummary>(true, "message", cs));
        assertThat(json, containsString("\"code\":\"FAKE\""));
        assertThat(json, containsString("\"code\":\"FAKE2\""));
        assertThat(json, containsString("\"code\":\"FAKE3\""));
    }

    @Test
    void testJsonCountryResponseSummary() {
        CountrySummary cs = new CountrySummary("FAKE");
        cs.addState(new TerritorySummary("FAKE2"));
        cs.addState(new TerritorySummary("FAKE3"));
        CountrySummary[] countries = new CountrySummary[] { cs };
        String json = JsonUtil.buildJson(new JsonResponse<MapSummary>(true, "message", new MapSummary(countries)));
        assertThat(json, containsString("\"code\":\"FAKE\""));
        assertThat(json, containsString("\"code\":\"FAKE2\""));
        assertThat(json, containsString("\"code\":\"FAKE3\""));
    }

    @Test
    void testJsonScoreboard() {
        PlayerScore[] scoreboard = new PlayerScore[3];
        scoreboard[0] = new PlayerScore(0, "PLAYER1", 1);
        scoreboard[1] = new PlayerScore(1, "PLAYER2", 5);
        scoreboard[2] = new PlayerScore(2, "PLAYER3", 10);
        String json = JsonUtil.buildJson(new JsonResponse<Scoreboard>(true, "message", new Scoreboard(scoreboard)));
        assertThat(json, containsString("\"name\":\"PLAYER1\""));
        assertThat(json, containsString("\"name\":\"PLAYER2\""));
        assertThat(json, containsString("\"name\":\"PLAYER3\""));
        assertThat(json, containsString("\"id\":0"));
        assertThat(json, containsString("\"id\":1"));
        assertThat(json, containsString("\"id\":2"));
        assertThat(json, containsString("\"score\":1"));
        assertThat(json, containsString("\"score\":5"));
        assertThat(json, containsString("\"score\":10"));
    }
}
